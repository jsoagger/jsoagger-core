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

package io.github.jsoagger.jfxcore.api.detailsview;



import io.github.jsoagger.jfxcore.api.IBuildable;
import io.github.jsoagger.jfxcore.api.IJSoaggerController;
import io.github.jsoagger.jfxcore.api.ViewLayoutPosition;

import javafx.scene.Node;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 16 févr. 2018
 */
public interface IMinimizedDetailsView extends IBuildable {

  Node getNodeOnPosition(ViewLayoutPosition position);


  void setHeader(IDetailsViewHeader header);


  IDetailsViewHeader getHeader();


  void pushContent(IJSoaggerController controller, Node node);


  void popContent();

}
