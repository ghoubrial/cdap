/*
 * Copyright © 2020 Cask Data, Inc.
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
import withStyles, { WithStyles, StyleRules } from '@material-ui/core/styles/withStyles';
import TablesList from 'components/Replicator/Detail/Overview/TablesList';
import Heading, { HeadingTypes } from 'components/Heading';

const styles = (): StyleRules => {
  return {
    root: {
      marginTop: '25px',
    },
  };
};

const OverviewView: React.FC<WithStyles<typeof styles>> = ({ classes }) => {
  return (
    <div className={classes.root}>
      <Heading type={HeadingTypes.h3} label="All tables replicated" />
      <TablesList />
    </div>
  );
};

const Overview = withStyles(styles)(OverviewView);
export default Overview;