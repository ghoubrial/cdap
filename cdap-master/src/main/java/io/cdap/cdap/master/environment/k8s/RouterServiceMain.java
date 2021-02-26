/*
 * Copyright © 2019 Cask Data, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.cdap.cdap.master.environment.k8s;

import com.google.common.base.Throwables;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.Service;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.cdap.cdap.common.ServiceBindException;
import io.cdap.cdap.common.conf.CConfiguration;
import io.cdap.cdap.common.conf.Constants;
import io.cdap.cdap.common.guice.DFSLocationModule;
import io.cdap.cdap.common.guice.ZKClientModule;
import io.cdap.cdap.common.logging.LoggingContext;
import io.cdap.cdap.common.logging.ServiceLoggingContext;
import io.cdap.cdap.gateway.router.NettyRouter;
import io.cdap.cdap.gateway.router.RouterModules;
import io.cdap.cdap.master.spi.environment.MasterEnvironment;
import io.cdap.cdap.master.spi.environment.MasterEnvironmentContext;
import io.cdap.cdap.messaging.guice.MessagingClientModule;
import io.cdap.cdap.proto.id.NamespaceId;
import io.cdap.cdap.security.guice.SecurityModules;
import org.apache.twill.internal.Services;
import org.apache.twill.zookeeper.ZKClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/**
 * The main class to run router service.
 */
public class RouterServiceMain extends AbstractServiceMain<EnvironmentOptions> {

  private static final Logger LOG = LoggerFactory.getLogger(RouterServiceMain.class);

  /**
   * Main entry point
   */
  public static void main(String[] args) throws Exception {
    main(RouterServiceMain.class, args);
  }

  @Override
  protected List<Module> getServiceModules(
      MasterEnvironment masterEnv,
      EnvironmentOptions options, CConfiguration cConf) {
    return Arrays.asList(
        new MessagingClientModule(),
        new RouterModules().getDistributedModules(),
        new DFSLocationModule(),
        new ZKClientModule(),
        securityModuleBasedOn(cConf)
    );
  }

  private Module securityModuleBasedOn(CConfiguration cConf) {
    if (cConf.getBoolean(Constants.Security.ENABLED)) {
      return new SecurityModules().getDistributedModules();
    } else {
      return new SecurityModules().getStandaloneModules();
    }
  }

  @Override
  protected void addServices(
      Injector injector, List<? super Service> services,
      List<? super AutoCloseable> closeableResources,
      MasterEnvironment masterEnv, MasterEnvironmentContext masterEnvContext,
      EnvironmentOptions options) {
    CConfiguration configuration = injector.getInstance(CConfiguration.class);

    if (configuration.getBoolean(Constants.Security.ENABLED)) {
      services.add(new AbstractService() {

        @Override
        protected void doStart() {

          ZKClientService zkClientService = injector.getInstance(ZKClientService.class);

          try {
            LOG.info("Connecting to Zookeeper.");

            io.cdap.cdap.common.service.Services.startAndWait(
                zkClientService,
                configuration.getLong(
                    Constants.Zookeeper.CLIENT_STARTUP_TIMEOUT_MILLIS),
                TimeUnit.MILLISECONDS,
                String.format(
                    "Connection timed out while trying to start " +
                        "ZooKeeper client. Please verify that the " +
                        "ZooKeeper quorum settings are correct in " +
                        "cdap-site.xml. Currently configured as: %s",
                    configuration.get(Constants.Zookeeper.QUORUM)));

            notifyStarted();
          } catch (Exception e) {
            Throwable rootCause = Throwables.getRootCause(e);
            if (rootCause instanceof ServiceBindException) {
              LOG.error("Failed to connect to Zookeeper: {}", rootCause.getMessage());
            } else {
              LOG.error("Failed to connect to Zookeeper", e);
            }
          }
        }

        @Override
        protected void doStop() {

          ZKClientService zkClientService = injector.getInstance(ZKClientService.class);

          LOG.info("Stopping Zookeeper client service.");
          Futures.getUnchecked(Services.chainStop(zkClientService));

          notifyStopped();
        }
      });
    }

    services.add(injector.getInstance(NettyRouter.class));
  }

  @Nullable
  @Override
  protected LoggingContext getLoggingContext(EnvironmentOptions options) {
    return new ServiceLoggingContext(
        NamespaceId.SYSTEM.getNamespace(),
        Constants.Logging.COMPONENT_NAME,
        Constants.Service.GATEWAY);
  }

  @Override
  protected void initializeDataSourceConnection(CConfiguration cConf) {
    // no-op since we don't connect to dataset in router service
  }
}