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

import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IWizardContent extends IBuildable {

  /**
   * Go to next step if there is one
   */
  void next();


  /**
   * Go to previous step if there is one
   */
  void back();


  /**
   * @return
   */
  SimpleBooleanProperty hasNextProperty();


  /**
   * @return
   */
  SimpleBooleanProperty hasPreviousProperty();


  /**
   * @return
   */
  int getStepsSize();


  /**
   * Select the given step
   *
   * @param step
   */
  void select(int index);


  /**
   * @param nextPageIdx
   */
  void navTo(int index);


  /**
   * @param pageTile
   */
  void setValid(int index);


  /**
   * @param pageTile
   */
  void setError(int index);


  /**
   * @param nextStepIdx
   * @return
   */
  IWizardStep getStep(int nextStepIdx);


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
