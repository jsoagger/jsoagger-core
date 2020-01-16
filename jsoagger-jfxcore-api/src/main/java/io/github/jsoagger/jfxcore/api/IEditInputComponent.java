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


import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IEditInputComponent {

  public IInputComponentWrapper getInputComponentWrapper();

  public void build(IInputComponentWrapper inputComponentWrapper);

  public default VLViewComponentXML getConfiguration() {
    return getInputComponentWrapper().getConfiguration();
  }

  public IComponent getComponent();

  public Node getDisplay();

  public Node getContent();

  public void displayError();

  public SimpleBooleanProperty visibleProperty();

  public void validate();

  public boolean isNotValid();

  public void addConstraint(IVLConstraint constraint);

  /**
   * Bind this property to the internal value
   */
  public default void addInternalValueBinding(StringProperty property) {
    if (getInputComponentWrapper() != null) {
      property.bind(getInputComponentWrapper().currentInternalValueProperty());
    }
  }


  /**
   * Commit/cancel modifications. Commit: Set initial value to current value Cancel: Set current value
   * to initial value
   */
  public default void cancelModification() {
    if (getInputComponentWrapper() != null) {
      getInputComponentWrapper().cancelModification();
    }
  }

  public default void commitModification() {
    if (getInputComponentWrapper() != null) {
      getInputComponentWrapper().commitModification();
    }
  }


  public default String getFirstErrorMessage() {
    return null;
  }

  // @formatter:on
}
