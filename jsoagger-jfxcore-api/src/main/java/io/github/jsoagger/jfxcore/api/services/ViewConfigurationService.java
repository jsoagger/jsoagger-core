/**
 *
 */
package io.github.jsoagger.jfxcore.api.services;

import io.github.jsoagger.jfxcore.api.IJSoaggerController;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewConfigXML;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface ViewConfigurationService {

  /**
   * Get the configuration file transformed into {@link VLViewConfigXML} for a given view.
   *
   * @param controller
   * @return
   */
  VLViewConfigXML getConfigurationFile(final IJSoaggerController controller);
}
