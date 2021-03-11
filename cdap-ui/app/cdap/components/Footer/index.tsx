/*
 * Copyright © 2016-2018 Cask Data, Inc.
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

import React from 'react';
import { Theme } from 'services/ThemeHelper';
import If from '../If';
import { isNilOrEmptyString, objectQuery } from 'services/helpers';
import { getCurrentNamespace } from 'services/NamespaceStore';
import NamespaceStore from 'services/NamespaceStore';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles({
  root: {
    background: 'white',
    color: '#cccccc',
    fontSize: '11px',
    fontWeight: 600,
    zIndex: 0,
    position: 'absolute',
    width: '100%',
    bottom: '0',
    borderTop: 'solid 1px #cdcdcd',
  },
  footerText: {
    height: '53px',
    lineHeight: '53px',
    margin: '0',
    textAlign: 'center',
  },
  footerUrl: {
    color: 'inherit',
  },
  instanceMetadataId: {
    position: 'absolute',
    top: '0',
    right: '10px',
    color: '#cccccc',
    height: '53px',
    lineHeight: '53px',
    margin: '0',
  },
  selectedNamespace: {
    position: 'absolute',
    top: '0',
    left: '10px',
    color: '#cccccc',
    height: '53px',
    lineHeight: '53px',
    margin: '0',
  },
});

const nonNamespacePages = ['Operations', 'Reports', 'Administration'];
export default function Footer() {
  const footerText = Theme.footerText;
  const footerUrl = Theme.footerLink;
  // 'project-id-30-characters-name1/instance-id-30-characters-name';
  const instanceMetadataId = objectQuery(window, 'CDAP_CONFIG', 'instanceMetadataId');
  const [selectedNamespace, setSelectedNamespace] = React.useState(getCurrentNamespace());
  const classes = useStyles();
  React.useEffect(() => {
    const sub = NamespaceStore.subscribe(() => setSelectedNamespace(getCurrentNamespace()));
    const mutationObserver = new MutationObserver((mutations) => {
      if (!Array.isArray(mutations) || mutations.length === 0) {
        return;
      }
      const title = objectQuery(mutations[0], 'target', 'textContent');
      if (isNilOrEmptyString(title)) {
        return;
      }
      const featureName = (objectQuery(title.split('|'), 1) || '').trim();
      if (nonNamespacePages.indexOf(featureName) !== -1) {
        setSelectedNamespace('--');
      }
    });
    mutationObserver.observe(document.querySelector('title'), { childList: true, subtree: true });
    return () => {
      sub();
      mutationObserver.disconnect();
    };
  }, []);
  return (
    <footer className={classes.root}>
      <p className={classes.selectedNamespace}>Namespace: {selectedNamespace}</p>
      <p className={classes.footerText}>
        <a href={footerUrl} target="_blank" rel="noopener noreferrer" className={classes.footerUrl}>
          {footerText}
        </a>
      </p>
      <If condition={instanceMetadataId}>
        <p className={classes.instanceMetadataId}>Instance Id: {instanceMetadataId}</p>
      </If>
    </footer>
  );
}
