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


package io.github.jsoagger.core.utils;


import java.util.ArrayList;
import java.util.List;

/*import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;*/

public class VLBeanUtilsBean { //extends //BeanUtilsBean {

  private List<String> exclusionsList = new ArrayList<>();

  /*
   * (non-Javadoc)
   *
   * @see org.apache.commons.beanutils.BeanUtilsBean#copyProperties(java.lang.Object,
   * java.lang.Object)
   */
  public void copyProperties(Object dest, Object orig) {
    // Copy the properties, converting as necessary
    /*if (orig instanceof DynaBean) {
      DynaProperty origDescriptors[] =
          ((DynaBean) orig).getDynaClass().getDynaProperties();
      for (int i = 0; i < origDescriptors.length; i++) {
        String name = origDescriptors[i].getName();
        if (getPropertyUtils().isWriteable(dest, name)) {
          Object value = ((DynaBean) orig).get(name);
          copyProperty(dest, name, value);
        }
      }
    } else if (orig instanceof Map) {
      Iterator names = ((Map) orig).keySet().iterator();
      while (names.hasNext()) {
        String name = (String) names.next();
        if (getPropertyUtils().isWriteable(dest, name)) {
          Object value = ((Map) orig).get(name);
          copyProperty(dest, name, value);
        }
      }
    } else*/ /* if (orig is a standard JavaBean) */{
      /*PropertyDescriptor origDescriptors[] =
          getPropertyUtils().getPropertyDescriptors(orig);
      for (int i = 0; i < origDescriptors.length; i++) {
        String name = origDescriptors[i].getName();

        if (exclusionsList.contains(name) || "class".equals(name)) {
          continue; // No point in trying to set an object's class
        }

        if (getPropertyUtils().isReadable(orig, name) &&
            getPropertyUtils().isWriteable(dest, name)) {
          try {
            Object value =
                getPropertyUtils().getSimpleProperty(orig, name);
            copyProperty(dest, name, value);
          } catch (NoSuchMethodException e) {
            ; // Should not happen
          }
        }
      }*/
    }
  }

  /**
   * @param exclusionsList the exclusionsList to set
   */
  public void setExclusionsList(List<String> exclusionsList) {
    this.exclusionsList = exclusionsList;
  }
}
