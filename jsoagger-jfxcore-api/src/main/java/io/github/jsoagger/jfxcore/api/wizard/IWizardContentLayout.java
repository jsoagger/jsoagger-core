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



import java.util.List;

import io.github.jsoagger.jfxcore.api.IActionRequest;
import io.github.jsoagger.jfxcore.api.IActionResult;
import io.github.jsoagger.jfxcore.api.IBuildable;
import io.github.jsoagger.jfxcore.api.IUIDataValidationResult;

import javafx.application.Platform;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IWizardContentLayout extends IBuildable {

  /**
   * Mark the indexed step as valid step.
   *
   * @param index
   */
  public default void setValid(int index) {
    final IWizardStepper stepper = getStepper(index);
    Platform.runLater(() -> stepper.setValid());
  }


  /**
   * Mark the indexed step as not valid step.
   *
   * @param index
   */
  public default void setError(int index) {
    final IWizardStepper stepper = getStepper(index);
    stepper.setError();
  }


  /**
   * @param index
   */
  public default IWizardStepper getStepper(int index) {
    return getSteppers().get(index);
  }


  /**
   * Get all {@link IWizardStepper} of this layout
   *
   * @return
   */
  public List<IWizardStepper> getSteppers();


  /**
   * @param index
   */
  public void select(int index);


  /**
   * @param actionRequest
   * @param result
   */
  public void handleValidationResult(IActionRequest actionRequest, IUIDataValidationResult result);


  /**
   * @param actionRequest
   * @param actionResult
   */
  public void handleValidationResult(IActionRequest actionRequest, IActionResult actionResult);


  /**
   * @param step
   */
  public void addStep(IWizardStep step);


  /**
   * @param i
   */
  public void navTo(int i);


  /**
   * @return
   */
  public int stepSize();


  /**
   * @param nextStepIdx
   * @return
   */
  public IWizardStep getStep(int index);
}
