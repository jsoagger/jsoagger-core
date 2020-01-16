/**
 *
 */
package io.github.jsoagger.jfxcore.api;

import java.util.List;

import io.github.jsoagger.jfxcore.api.security.IViewContext;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewConfigXML;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IJSoaggerController {

  void handleActionResult(IActionRequest actionRequest, IActionResult result);

  String getLocalised(String msgKey);

  void addIComponent(IComponent abstractComponent);

  Object getModel();

  VLViewConfigXML config();

  String getModelContainerFullId();

  String getModelFullId();

  IJSoaggerController getRootStructure();

  String getGLocalised(String string);

  String getLocalised(String ttl, VLViewComponentXML inlineActionconfiguration);

  List<String> getViewDefinitions();

  IViewContext viewContext();

  void setModel(Object operationResult);

  String getModelContainerPath();

}
