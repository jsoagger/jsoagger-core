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

package io.github.jsoagger.jfxcore.api.security;

import io.github.jsoagger.jfxcore.api.ICriteriaContext;
import io.github.jsoagger.jfxcore.api.IJSoaggerController;

/**
 * Set a parameter value filter in the context
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IContextParamSetter {

  /**
   * Process the parameter and put it into the view context
   *
   * @param model The model
   * @param CriteriaContext The context instance
   */
  public void process(IJSoaggerController controller, final Object model, final ICriteriaContext criteriaContext);
}
