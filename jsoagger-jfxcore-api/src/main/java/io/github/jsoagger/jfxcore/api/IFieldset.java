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
import javafx.scene.Node;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IFieldset {

  /**
   *
   * @param wizardConfiguration
   * @param controller
   */
  void build(VLViewComponentXML configuration, IJSoaggerController controller);


  /**
   *
   * @return
   */
  String getTitle();


  /**
   * validate entries
   *
   * @return
   */
  void validate();


  /**
   *
   * @return
   */
  boolean isValid();


  /**
   *
   * @return
   */
  boolean isNotValid();

  void displayErrors();


  /**
   * Get the wizardConfiguration
   *
   * @return the wizardConfiguration
   */
  VLViewComponentXML getConfiguration();


  /**
   * Get the controller
   *
   * @return the controller
   */
  IJSoaggerController getController();


  /**
   * @return
   */
  Node getDisplay();


  /**
   * Getter of fieldsetHeader
   *
   * @return the fieldsetHeader
   */
  IFieldsetHeader getFieldsetHeader();


  /**
   * Getter of fieldsetContent
   *
   * @return the fieldsetContent
   */
  IFieldsetContent getFieldsetContent();


  /**
   * Setter of isValid
   *
   * @param isValid the isValid to set
   */
  void setValid(boolean isValid);


  /**
   * @param configuration
   */
  void setConfiguration(VLViewComponentXML configuration);


  /**
   * @param controller
   */
  void setController(IJSoaggerController controller);


  /**
   * @param fieldsetContent
   */
  void setFieldsetContent(IFieldsetContent fieldsetContent);


  /**
   * @param fieldsetHeader
   */
  void setFieldsetHeader(IFieldsetHeader fieldsetHeader);


  public void beginForwardEdition(Node editor);


  public void endForwardEdition();


  void setForwardEditor(IForwardEditor editor);

}
