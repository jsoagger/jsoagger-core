/**
 *
 */
package io.github.jsoagger.jfxcore.api;

import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface ITableStructure {

  VLViewComponentXML getContentConfig();

  MultipleResult initialQuery();
}
