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



import java.util.function.Consumer;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;

/**
 * Load datas in context of pagination of a component or a view.
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IPaginatedDataProvider {

  /**
   * @author Ramilafananana VONJISOA
   * @mailto yvonjisoa@nexitia.com
   * @date 2019
   */
  public enum Direction {
    NEXT, PREVIOUS, FIRST, LAST, CURRENT;
  }


  /**
   * Get dynamical attributes associated to loaded datas.
   *
   * @return
   */
  public default IOperationResult getTypeDynamicalAttributes() {
    return null;
  }


  /**
   * @param direction
   * @param currentPage
   * @return
   */
  public default int getNexPageIndex(Direction direction, MultipleResult currentPage) {
    int nextPageIndex = -1;

    switch (direction) {
      case FIRST:
        nextPageIndex = currentPage.firstPage();
        break;

      case LAST:
        nextPageIndex = currentPage.lastPage();
        break;

      case NEXT:
        nextPageIndex = currentPage.getNextPageIndex();
        break;

      case PREVIOUS:
        nextPageIndex = currentPage.getPreviousPageIndex();
        break;

      case CURRENT:
        nextPageIndex = currentPage.getCurrentPageIndex();
        break;

      default:
        break;
    }

    return nextPageIndex;
  }


  /**
   * @param controller
   * @param componentConfiguration
   */
  public void initFromConfiguration(IJSoaggerController controller, VLViewComponentXML componentConfiguration);


  /**
   * @param controller
   * @param currentPage
   * @param consumer
   */
  void count(IJSoaggerController controller, IOperationResult currentPage, Consumer<IOperationResult> consumer);


  /**
   * @param controller
   * @param currentResult
   * @param direction
   */
  void navigate(IJSoaggerController controller, IOperationResult currentResult, Direction direction, Consumer<IOperationResult> consumer);
}
