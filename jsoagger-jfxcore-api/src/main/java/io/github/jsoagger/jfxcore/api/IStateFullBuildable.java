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




import javafx.beans.property.SimpleObjectProperty;

/**
 * Is {@link IBuildable} that can publish its build status on each step.
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IStateFullBuildable extends IBuildable {

  SimpleObjectProperty<BuildStatus> buildStatusProperty();


  /**
   * Get the build status of current node
   *
   * @return BuildStatus
   */
  default BuildStatus buildStatus() {
    if (buildStatusProperty() != null) {
      return buildStatusProperty().get();
    }

    return BuildStatus.UNKOWN;
  }


  /**
   * Set the build status of the node
   *
   * @param status
   */
  default void buildStatus(BuildStatus status) {
    if (buildStatusProperty() != null) {
      buildStatusProperty().set(status);
    }
  }

  /**
   * Status of no building may be helfull for displaying some icon when processing.
   *
   * @author Ramilafananana VONJISOA
   * @mailto yvonjisoa@nexitia.com
   * @date 2019
   */
  public enum BuildStatus {
    INITIALIZED, LOADING_DATA, PROCESSING, UNKOWN, DISPLAYABE, BUILDED, ERROR;
  }
}
