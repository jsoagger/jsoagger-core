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



import java.util.List;

import io.github.jsoagger.jfxcore.api.IBuildable;
import io.github.jsoagger.jfxcore.api.IParentResponsiveMatrix;
import io.github.jsoagger.jfxcore.api.ViewLayoutPosition;

import javafx.scene.Node;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 8 févr. 2018
 */
public interface IViewLayoutManager {

  /**
   * Getter of responsiveMatrix
   *
   * @return the responsiveMatrix
   */
  public default IParentResponsiveMatrix getResponsiveMatrix() {
    return null;
  }


  /**
   * Setter of responsiveMatrix
   *
   * @param responsiveMatrix the responsiveMatrix to set
   */
  public default void setResponsiveMatrix(IParentResponsiveMatrix responsiveMatrix) {}


  /**
   * Process layout of the {@link IViewLayoutManageable}
   */
  public void layout(IViewLayoutManageable layoutManageable);


  /**
   * Return the displayable content of the layout.
   */
  public Node getDisplay();


  /**
   * Replace current displayed content by this node. Previous node can be redisplayed by pop.
   */
  public default void pushContent(Node node) {

  }


  /**
   * Redisplay the first content
   */
  public default void popContent() {

  }


  public default void minimizePushedContent() {

  }


  public default void restorePushedContent() {

  }


  public default void setDefaultActions(List<IBuildable> comps) {

  }


  /**
   * Use in search only
   *
   * @param position
   */
  public default void display(ViewLayoutPosition position) {

  }


  /**
   * Return true is the layout manager has a switchable pane on its left or right. Some controllers
   * like search need it after doing some actions inside the in order to close it.
   *
   * @return
   */
  public default boolean isSwitchable() {
    return false;
  }


  /**
   * Close left or right switchable pane.
   *
   * @param position
   */
  public default void closeSwitchable(ViewLayoutPosition position) {

  }


  /**
   * Display left or right switchable pane.
   *
   * @param position
   */
  public default void displaySwitchable(ViewLayoutPosition position) {

  }
}
