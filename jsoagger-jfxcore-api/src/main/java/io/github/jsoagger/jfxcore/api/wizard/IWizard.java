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

package io.github.jsoagger.jfxcore.api.wizard;


import io.github.jsoagger.jfxcore.api.IActionRequest;
import io.github.jsoagger.jfxcore.api.IActionResult;
import io.github.jsoagger.jfxcore.api.IBuildable;
import io.github.jsoagger.jfxcore.api.IUIDataValidationResult;

import javafx.scene.Node;

/**
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IWizard extends IBuildable {

  /**
   * @{inheritedDoc}
   */
  @Override
  Node getDisplay();


  /**
   * Getter of listViewPaneHeader
   *
   * @return the listViewPaneHeader
   */
  IWizardHeader getHeader();


  /**
   * Setter of listViewPaneHeader
   *
   * @param listViewPaneHeader the listViewPaneHeader to set
   */
  void setHeader(IWizardHeader header);


  /**
   * Getter of content
   *
   * @return the content
   */
  IWizardContent getContent();


  /**
   * Setter of content
   *
   * @param content the content to set
   */
  void setContent(IWizardContent content);


  /**
   * Getter of footer
   *
   * @return the footer
   */
  IWizardFooter getFooter();


  /**
   * Setter of footer
   *
   * @param footer the footer to set
   */
  void setFooter(IWizardFooter footer);


  /**
   * @return
   */
  int getStepsSize();


  /**
   * @param nextStepIdx
   */
  void select(int nextStepIdx);


  /**
   * @param curStepIdx
   * @return
   */
  IWizardStep getStep(int curStepIdx);


  /**
   * @param curStepIdx
   */
  void setValid(int curStepIdx);


  /**
   * @param curStepIdx
   */
  void setError(int curStepIdx);


  /**
   * @param actionRequest
   * @param result
   */
  void handleValidationResult(IActionRequest actionRequest, IUIDataValidationResult result);


  /**
   * @param actionRequest
   * @param actionResult
   */
  void handleValidationResult(IActionRequest actionRequest, IActionResult actionResult);
}
