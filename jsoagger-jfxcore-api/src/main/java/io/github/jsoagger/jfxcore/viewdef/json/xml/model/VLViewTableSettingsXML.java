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

package io.github.jsoagger.jfxcore.viewdef.json.xml.model;



import java.util.ArrayList;
import java.util.List;

import io.github.jsoagger.jfxcore.api.components.annotation.GraalComponent;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@GraalComponent
public class VLViewTableSettingsXML {

  private String id;
  private List<VLViewTableSettingXML> tableSettings = new ArrayList<>();

  public VLViewTableSettingsXML() {}

  /**
   * @return the tableSettings
   */
  public List<VLViewTableSettingXML> getTableSettings() {
    return tableSettings;
  }

  /**
   * @param tableSettings the tableSettings to set
   */
  public void setTableSettings(List<VLViewTableSettingXML> tableSettings) {
    this.tableSettings = tableSettings;
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }
}
