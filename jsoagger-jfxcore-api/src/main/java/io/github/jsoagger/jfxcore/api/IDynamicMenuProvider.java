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


import java.util.List;
import java.util.function.Consumer;

import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.jfxcore.viewdefinition.json.xml.model.VLViewPropertiesXML;
import io.github.jsoagger.jfxcore.viewdefinition.json.xml.model.VLViewPropertyXML;
import io.github.jsoagger.jfxcore.viewdefinition.json.xml.model.VLViewRootMenuRowXML;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IDynamicMenuProvider {

  /**
   * @param controller
   * @param callback
   * @return
   */
  public void getRows(IJSoaggerController controller, Consumer<List<VLViewRootMenuRowXML>> callback);


  /**
   * @param data
   * @return
   */
  public static VLViewRootMenuRowXML buildRow(OperationData data) {
    VLViewRootMenuRowXML row = new VLViewRootMenuRowXML();

    VLViewPropertiesXML propertiesXML = new VLViewPropertiesXML();
    row.setProperties(propertiesXML);

    VLViewPropertyXML translatedLabel = new VLViewPropertyXML();
    translatedLabel.setName("translatedLabel");
    translatedLabel.setValue((String) data.getAttributes().get("translatedLabel"));
    propertiesXML.getProperties().add(translatedLabel);

    VLViewPropertyXML icon = new VLViewPropertyXML();
    icon.setName("icon");
    icon.setValue((String) data.getAttributes().get("icon"));
    propertiesXML.getProperties().add(icon);

    return row;
  }
}
