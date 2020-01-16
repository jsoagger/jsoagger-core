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


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IPersistenceServiceDelegate {

  public default Map<String, Object> extracValue(List<IInputComponentWrapper> rows) {
    Map<String, Object> result = new HashMap<>();
    for(IInputComponentWrapper row: rows) {
      IEditInputComponent edit = row.getEditInputComponent();
      String value = edit.getInputComponentWrapper().getCurrentInternalValue();
      String name = row.getAttributeName();
      result.put(name, value);
    }
    return result;
  }


  public default void successHandler(IOperationResult result) {
    if (getSuccessHandler() != null) {
      getSuccessHandler().accept(result);
    }
  }


  /**
   * Commit the given form in context of the controller
   *
   * @param sourceController
   * @param inlineActionconfiguration
   * @param form
   * @throws Exception
   */
  public default void editCommit(IJSoaggerController sourceController, VLViewComponentXML inlineActionconfiguration, IFieldsetContent form) {
    System.out.println("######## IPersistenceServiceDelegate editCommit");
  }


  public default void editCommit(IJSoaggerController sourceController, VLViewComponentXML inlineActionConfiguration, List<IInputComponentWrapper> rows) {
    System.out.println("######## IPersistenceServiceDelegate editCommit");
  }


  public default Consumer<Throwable> getErrorHandler() {
    return null;
  }


  public default void setErrorHandler(Consumer<Throwable> errorHandler) {

  }


  public Consumer<IOperationResult> getSuccessHandler();


  public void setSuccessHandler(Consumer<IOperationResult> successHandler);
}
