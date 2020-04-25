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

import io.github.jsoagger.jfxcore.api.components.annotation.GraalComponent;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@GraalComponent
public class VLViewFilterXML implements Serializable {

  private static final long serialVersionUID = -6364354880489942068L;

  private String name;
  private VLViewFilterParamXML param;
  private VLViewFilterAndOperatorXML and;
  private VLViewFilterOrOperatorXML or;
  private VLViewFilterNotOperatorXML not;

  public VLViewFilterXML() {}


  public boolean andEmpty() {
    return and == null;
  }


  public boolean notEmpty() {
    return not == null;
  }


  public boolean orEmpty() {
    return or == null;
  }


  public VLViewFilterAndOperatorXML and() {
    if (and == null) {
      return new VLViewFilterAndOperatorXML();
    }

    return and;
  }


  public VLViewFilterOrOperatorXML or() {
    if (or == null) {
      return new VLViewFilterOrOperatorXML();
    }

    return or;
  }


  /**
   * @return the name
   */
  public String getName() {
    return name;
  }


  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * @return the param
   */
  public VLViewFilterParamXML getParam() {
    return param;
  }


  /**
   * @param param the param to set
   */
  public void setParam(VLViewFilterParamXML param) {
    this.param = param;
  }


  /**
   * @return the and
   */
  public VLViewFilterAndOperatorXML getAnd() {
    return and;
  }


  /**
   * @param and the and to set
   */
  public void setAnd(VLViewFilterAndOperatorXML and) {
    this.and = and;
  }


  /**
   * @return the or
   */
  public VLViewFilterOrOperatorXML getOr() {
    return or;
  }


  /**
   * @param or the or to set
   */
  public void setOr(VLViewFilterOrOperatorXML or) {
    this.or = or;
  }


  /**
   * @return the not
   */
  public VLViewFilterNotOperatorXML getNot() {
    return not;
  }


  /**
   * @param not the not to set
   */
  public void setNot(VLViewFilterNotOperatorXML not) {
    this.not = not;
  }
}
