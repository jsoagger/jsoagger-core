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



import java.util.List;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class VLViewTableSettingXML {

  private String id;
  private boolean isDefault;
  private List<VLViewTableSettingColumnXML> columns;

  /**
   * @return the columns
   */
  public List<VLViewTableSettingColumnXML> getColumns() {
    return columns;
  }

  /**
   * @param columns the columns to set
   */
  public void setColumns(List<VLViewTableSettingColumnXML> columns) {
    this.columns = columns;
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

  /**
   * @return the isDefault
   */
  public boolean isDefault() {
    return isDefault;
  }

  /**
   * @param isDefault the isDefault to set
   */
  public void setDefault(boolean isDefault) {
    this.isDefault = isDefault;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "VLViewTableSettingXML [id=" + id + ", isDefault=" + isDefault + ", columns=" + columns + "]";
  }
}
