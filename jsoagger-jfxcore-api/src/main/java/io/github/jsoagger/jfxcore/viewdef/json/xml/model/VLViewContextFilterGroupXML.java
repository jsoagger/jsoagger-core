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



import java.io.Serializable;
import java.util.List;

import io.github.jsoagger.jfxcore.api.components.annotation.GraalComponent;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@GraalComponent
public class VLViewContextFilterGroupXML implements Serializable {

  private static final long serialVersionUID = -4986049280105503691L;

  private String id;
  private String ref;
  private List<VLViewContextFilterXML> filters;


  public VLViewContextFilterGroupXML() {}

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
   * @return the ref
   */
  public String getRef() {
    return ref;
  }


  /**
   * @param ref the ref to set
   */
  public void setRef(String ref) {
    this.ref = ref;
  }


  /**
   * @return the filters
   */
  public List<VLViewContextFilterXML> getFilters() {
    return filters;
  }


  /**
   * @param filters the filters to set
   */
  public void setFilters(List<VLViewContextFilterXML> filters) {
    this.filters = filters;
  }
}
