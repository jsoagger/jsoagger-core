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


import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.jfxcore.viewdefinition.json.xml.model.VLViewComponentXML;

import javafx.scene.Node;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IFormRowEditor {

  public void build(IJSoaggerController controller, VLViewComponentXML inlineActionconfiguration, IFormFieldsetRow row, int callerIndex);


  public void setInlineEditionHandler(IAttributeEditionHandler inlineEditionHandler);

  public IAttributeEditionHandler getInlineEditionHandler();

  public void onCommitSuccess(IOperationResult result);


  public void onCommitError(IOperationResult result);


  public Node getDisplay();


  public IFormFieldsetRow getRow();


  public VLViewComponentXML getInlineActionConfiguration();


  public default void closeEditor() {}

  public default void onOk() {}

  public default void onCancel() {}

  /**
   * Commit the modification without doing nothing more
   */
  public void commitModification();
}
