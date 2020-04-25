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

import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;

/**
 * Handle layout of list fo {@link IFieldset}
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IFieldsetGroupLayout {

  public void addFieldset(IFieldset fieldset);

  public void displayAll();

  public javafx.scene.Node getDisplay();

  public javafx.collections.ObservableList<IFieldset> getFieldsets();

  public void setDisplaySelectors(Boolean displayGroupSelector);

  public void setRootConfig(VLViewComponentXML fieldsetListConfig);

  public void addComponents(List<IBuildable> buildables);

  public void addComponent(IDisplayable displayable);
}
