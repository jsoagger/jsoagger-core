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

package io.github.jsoagger.jfxcore.viewdef.xml.model;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "lazyDataLoaderUtility")
@XmlJavaTypeAdapter(value = NormalizedStringAdapter.class)
public class VLLazyDataLoaderUtilityXML {

  @XmlAttribute(name = "id")
  private String id;

  @XmlAttribute(name = "utilityClass")
  private Class<?> utilityClass;


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
   * Get the utilityClass
   * 
   * @return the utilityClass
   */
  public Class<?> getUtilityClass() {
    return utilityClass;
  }


  /**
   * Set the utilityClass
   * 
   * @param utilityClass the utilityClass to set
   */
  public void setUtilityClass(Class<?> utilityClass) {
    this.utilityClass = utilityClass;
  }

}
