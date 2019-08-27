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


import java.net.URL;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface MenuConfigurationProvider {

  /**
   * @return
   */
  String getMenuConfigLoction();

  /**
   * Get the menu XML file definition from which the normal a menu of application
   */
  URL getMenuConfiguration(IJSoaggerController context);


  /**
   * When root context of application is updated, primary menu may be changed or not!
   *
   * @param context
   * @return
   */
  URL rootContextUpdate(IJSoaggerController context);
}
