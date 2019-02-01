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

class DAGMinimapUtilities {
  constructor() {
    this.SIZE = {
      height: 150,
      width: 250,
      nodeWidth: 200,
      nodeHeight: 100,
      conditionNode: 105,
      padding: 5
    };
    this.MIN_SCALE = 0.5;
  }

  getGraphMetadata(nodes) {
    // Get min/max of nodes
    let minX = null,
      maxX = null,
      minY = null,
      maxY = null;

    nodes.forEach((node) => {
      if (!node._uiPosition) { return; }

      const position = node._uiPosition;

      const x = parseInt(position.left, 10);
      const y = parseInt(position.top, 10);

      minX = minX === null ? x : Math.min(minX, x);
      maxX = maxX === null ? x : Math.max(maxX, x);

      minY = minY === null ? y : Math.min(minY, y);
      maxY = maxY === null ? y : Math.max(maxY, y);
    });

    let width = 0;
    let height = 0;

    if (
      minX !== null &&
      maxX !== null &&
      minY !== null &&
      maxY !== null
    ) {
      width = Math.abs(maxX + this.SIZE.nodeWidth - minX);
      height = Math.abs(maxY + this.SIZE.nodeHeight - minY);
    }

    const widthScale = this.SIZE.width / width;
    const heightScale = this.SIZE.height / height;

    const scale = Math.min(widthScale, heightScale, this.MIN_SCALE) || this.MIN_SCALE;

    // If width and height is less than the minimap size, center the graph.
    let xOffset = 0;
    let yOffset = 0;

    if (width * scale < this.SIZE.width) {
      xOffset = (this.SIZE.width - (width * scale)) / 2;
    }

    if (height * scale < this.SIZE.height) {
      yOffset = (this.SIZE.height - (height * scale)) / 2;
    }

    return {
      scale,
      minX,
      minY,
      width,
      height,
      xOffset,
      yOffset,
    };
  }

  getNodeMetadata(node, graphMetadata) {
    const {
      scale,
      minX,
      minY,
      xOffset,
      yOffset,
    } = graphMetadata;

    const position = node._uiPosition;
    const x = parseInt(position.left, 10) - minX;
    const y = parseInt(position.top, 10) - minY;

    let height = this.SIZE.nodeHeight;
    let width = this.SIZE.nodeWidth;

    if (node.type === 'condition') {
      height = this.SIZE.conditionNode;
      width = this.SIZE.conditionNode;
    }

    height *= scale;
    width *= scale;

    const left = (x * scale) + xOffset;
    const top = (y * scale) + yOffset;

    return {
      height,
      width,
      left,
      top,
    };
  }

  /**
   * Function that will give information about the viewport indicator for minimap
   *
   * @param graphMetadata result from getGraphMetadata function
   * @param dagContainerSize diagram-container getBoundClientRect result (viewport size)
   * @param canvasScale the scale of the actual graph canvas (dag-container)
   * @param canvasPanning the top and left of dag-container
   *
   * Returns the height, width, top, and left property of the viewport for the minimap. The top and left will
   * correlate to the top left point of the viewport.
   */
  getViewportBox(graphMetadata, dagContainerSize, canvasScale, canvasPanning) {
    if (!graphMetadata || !dagContainerSize) { return; }

    const {
      scale,
      minX,
      minY,
      xOffset,
      yOffset,
    } = graphMetadata;

    // on empty dag
    if (minX === null || minY === null) {
      const padding = 2 * this.SIZE.padding;

      return {
        height: this.SIZE.height + padding,
        width: this.SIZE.width + padding,
        top: -this.SIZE.padding,
        left: -this.SIZE.padding,
      };
    }

    const scaleRatio = scale / canvasScale;

    let height = dagContainerSize.height * scaleRatio;
    let width = dagContainerSize.width * scaleRatio;

    const canvasPanningY = canvasPanning.top;
    const canvasPanningX = canvasPanning.left;

    const nodeOffsetY = (canvasScale * minY) + ((1 - canvasScale) / 2 * dagContainerSize.height);
    const nodeOffsetX = (canvasScale * minX) + ((1 - canvasScale) / 2 * dagContainerSize.width);

    let top = (canvasPanningY + nodeOffsetY) * scaleRatio;
    top = -top + yOffset;

    let left = (canvasPanningX + nodeOffsetX) * scaleRatio;
    left = -left + xOffset;

    return {
      height,
      width,
      top,
      left,
    };
  }
}

angular.module(PKG.name + '.commons')
  .service('DAGMinimapUtilities', DAGMinimapUtilities);