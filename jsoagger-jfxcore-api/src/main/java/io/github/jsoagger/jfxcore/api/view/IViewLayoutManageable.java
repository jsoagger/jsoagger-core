/*-
 * ========================LICENSE_START=================================
 * JSoagger 
 * %%
 * Copyright (C) 2019 JSOAGGER
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

package io.github.jsoagger.jfxcore.api.view;


import io.github.jsoagger.jfxcore.api.IJSoaggerController;
import io.github.jsoagger.jfxcore.api.IParentResponsiveMatrix;
import io.github.jsoagger.jfxcore.api.ViewLayoutPosition;
import io.github.jsoagger.jfxcore.viewdefinition.json.xml.model.VLViewComponentXML;

import javafx.scene.Node;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IViewLayoutManageable {

  public IJSoaggerController getController();


  public VLViewComponentXML getConfiguration();


  /**
   * Get the node at this position.
   */
  public default Node getNodeOnPosition(ViewLayoutPosition position) {
    return null;
  }


  /**
   * With this method, child layout can override layout defined from {@link RootStructureController}.
   * If this method return not null value the content will layout according to this
   * {@link ParentResponsiveMatrix}.
   * <p>
   * {@link #getChildRootStructureContentLayout(ParentResponsiveMatrix)} should be overrided too.
   *
   * @return
   */
  public default IParentResponsiveMatrix getResponsiveMatrix() {
    return null;
  }


  public default void setResponsiveMatrix(IParentResponsiveMatrix responsiveMatrix) {
  }
}
