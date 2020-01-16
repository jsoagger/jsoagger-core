/**
 *
 */
package io.github.jsoagger.jfxcore.api.services;

import io.github.jsoagger.jfxcore.api.IJSoaggerController;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewContextFilterXML;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewFilterXML;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface CommonComponentsServices {

  /**
   * @param controller
   * @param id
   * @return
   */
  VLViewFilterXML getFilter(IJSoaggerController controller, String id);

  /**
   * @param id
   * @return
   */
  VLViewContextFilterXML getFiltersContext(String id);
}
