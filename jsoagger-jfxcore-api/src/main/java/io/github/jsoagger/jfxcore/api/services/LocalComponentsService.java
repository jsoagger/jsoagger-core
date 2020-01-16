/**
 *
 */
package io.github.jsoagger.jfxcore.api.services;

import java.util.List;

import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface LocalComponentsService {


  /**
   * @param identifier
   * @return
   */
  public VLViewComponentXML getComponent(String identifier) ;


  /**
   * Getter of configurationFiles
   *
   * @return the configurationFiles
   */
  public List<String> getConfigurationFiles();


  /**
   * Setter of configurationFiles
   *
   * @param configurationFiles the configurationFiles to set
   */
  public void setConfigurationFiles(List<String> configurationFiles);
}
