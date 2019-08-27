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

package io.github.jsoagger.jfxcore.api;




import javafx.scene.Node;

/**
 * Is a component that is selectable can accessed via the {@link FieldsetSelectorMenu} and its
 * content displayed in center area of the view.
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface ISelectableComponent {

  /**
   * Get the label to be displayed in the menu
   *
   * @return {@link String}
   */
  public String getSelectionLabel();


  /**
   * Get the associated content.
   *
   * @return {@link Node}
   */
  public Node content();


  /**
   * For selecting this component from the parent
   *
   * @return
   */
  public String getComponentId();
}
