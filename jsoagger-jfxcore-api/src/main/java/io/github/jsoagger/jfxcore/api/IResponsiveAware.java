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

import java.lang.reflect.Method;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * Can handle {@link ResponsiveSizing} event.
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IResponsiveAware {

  /**
   * If node is {@link IResponsiveAware}, call custom resize algorithm on it, or else do standard one.
   *
   * @param node
   * @param sizing
   */
  public static void resize(Object node, IResponsiveSizing sizing) {
    if (node instanceof IResponsiveAware) {
      // the component the only one who knows how it want to rezised
      ((IResponsiveAware) node).doResize(sizing);
    } else if (node instanceof Node) {
      doResize((Node) node, sizing);
    }
  }


  /**
   * Do standard resizing algo.
   *
   * @param node
   * @param sizing
   */
  public static void doResize(Node node, IResponsiveSizing sizing) {
    // sizing behaviour
    // as not every node has pref width property, call it by
    // introspection and let it through exception ...
    setPrefWidth(node, sizing.getWidth());

    // visibility behaviour
    node.setVisible(sizing.isToMinimize() || !sizing.isToHide());

    // minimizable behaviour
    if (node instanceof IMinimizable) {
      if (sizing.isToMinimize())
        ((IMinimizable) node).minimize();
      else
        ((IMinimizable) node).maximize();
    } else if (node instanceof Pane) {
      if (((Pane) node).getChildren().size() == 1) {
        final Node singleChild = ((Pane) node).getChildren().get(0);

        if (singleChild instanceof IMinimizable) {
          if (sizing.isToMinimize())
            ((IMinimizable) singleChild).minimize();
          else
            ((IMinimizable) singleChild).maximize();
        }
      }
    }
  }


  public static void setPrefWidth(Node node, double value) {
    Method method = null;

    try {
      method = node.getClass().getMethod("setPrefWidth", Double.TYPE);
      if (method != null) {
        method.invoke(node, value);
      }
    } catch (final Exception e1) {
    }
  }


  /**
   * Do custom resizing
   *
   * @param responsiveSizing
   */
  public void doResize(IResponsiveSizing responsiveSizing);
}
