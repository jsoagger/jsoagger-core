/**
 *
 */
package io.github.jsoagger.jfxcore.api.services;

import io.github.jsoagger.jfxcore.viewdefinition.json.xml.model.VLViewComponentXML;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface GlobalComponentsService {

  /**
   * Get component with given identifier form global configuration.
   *
   * @param id
   * @return
   */
  public VLViewComponentXML getCompFromGlobalConfig(String id) ;

}
