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

package io.github.jsoagger.jfxcore.api.wizard;



import io.github.jsoagger.jfxcore.api.IBuildable;
import io.github.jsoagger.jfxcore.api.IJSoaggerController;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IWizardStep extends IBuildable {

  @Override
  public void buildFrom(IJSoaggerController controller, VLViewComponentXML configuration);


  public VLViewComponentXML getConfiguration();


  public void setConfiguration(VLViewComponentXML configuration);


  public IJSoaggerController getController();


  public void setController(IJSoaggerController controller);


  public IWizardStepContent getStepContent();


  public IWizardStepHeader getStepHeader();


  public IWizardStepFooter getStepFooter();


  public void setIndex(int pageIndex);


  public int getIndex();


  public String getTitle();


  public void validate();


  public boolean isValid();


  public void displayErrors();

  public SimpleBooleanProperty validProperty();

  public void select();
}
