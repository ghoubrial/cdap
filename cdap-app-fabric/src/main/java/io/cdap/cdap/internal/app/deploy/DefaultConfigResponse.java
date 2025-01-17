/*
 * Copyright © 2014-2018 Cask Data, Inc.
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

package io.cdap.cdap.internal.app.deploy;

import io.cdap.cdap.app.deploy.ConfigResponse;
import io.cdap.cdap.internal.app.deploy.pipeline.AppSpecInfo;

import javax.annotation.Nullable;

/**
 * This is implementation of {@link ConfigResponse}.
 * <p>
 * Immutable class that hold exit code and stream of input.
 * </p>
 */
public final class DefaultConfigResponse implements ConfigResponse {

  private final int exit;
  private final AppSpecInfo appSpecInfo;

  /**
   * Constructor.
   *
   * @param exit code returned from processing a command.
   * @param appSpecInfo the response generated by the configuration process
   */
  public DefaultConfigResponse(int exit, @Nullable AppSpecInfo appSpecInfo) {
    this.exit = exit;
    this.appSpecInfo = exit == 0 ? appSpecInfo : null;
  }

  @Nullable
  @Override
  public AppSpecInfo getAppSpecInfo() {
    return appSpecInfo;
  }

  /**
   * @return Exit code of command that was executed.
   */
  @Override
  public int getExitCode() {
    return exit;
  }
}
