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


import java.util.Map;

import javafx.event.ActionEvent;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IActionRequest {

  /**
   * Get value of given key
   */
  public Object getProperty(String key);


  public Map<String, Object> properties();


  public void setProperty(String key, Object value);


  /**
   * Getter of source
   *
   * @return the source
   */
  IBuildable getSource();


  /**
   * Setter of source
   *
   * @param source the source to set
   */
  void setSource(IBuildable source);


  /**
   * Getter of controller
   *
   * @return the controller
   */
  IJSoaggerController getController();


  default IJSoaggerController getTargetController() {
    return null;
  }


  default void setTargetController(IJSoaggerController controller) {}


  /**
   * Setter of controller
   *
   * @param controller the controller to set
   */
  void setController(IJSoaggerController controller);


  /**
   * Getter of event
   *
   * @return the event
   */
  ActionEvent getEvent();


  /**
   * Setter of event
   *
   * @param event the event to set
   */
  void setEvent(ActionEvent event);


  /**
   * @{inheritedDoc}
   */
  @Override
  String toString();
}
