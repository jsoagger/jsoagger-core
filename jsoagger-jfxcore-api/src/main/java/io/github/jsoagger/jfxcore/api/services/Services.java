/**
 *
 */
package io.github.jsoagger.jfxcore.api.services;

import io.github.jsoagger.jfxcore.api.IJSoaggerController;
import io.github.jsoagger.jfxcore.viewdef.json.xml.StringUtils;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewComponentXML;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewConfigXML;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewContextFilterXML;
import io.github.jsoagger.jfxcore.viewdef.json.xml.model.VLViewFilterXML;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class Services {

  private static Services services;

  private ApplicationContextService appContextService;
  private ViewConfigurationService viewConfigurationService;
  private GlobalComponentsService globalConfigService;
  private CommonComponentsServices commonComponentsServices;
  private DialogConfigServices dialogConfigServices;


  /**
   * Constructor
   */
  private Services() {
    if (services != null) {
      throw new IllegalArgumentException("Only one instance allowed");
    }
    services = this;
  }


  /**
   * Get the unique instance ot that service
   *
   * @return
   */
  public static Services instance() {
    if (services == null) {
      services = new Services();
    }

    return services;
  }

  /**
   * @return
   */
  public static ApplicationContextService appCtx() {
    return instance().getAppContextService();
  }


  /**
   * @param location
   * @return
   */
  public static VLViewComponentXML getDialogConfig(String location) {
    return instance().getDialogConfigServices().getDialogConfig(location);
  }

  /**
   * Resolve the given filter.
   *
   * @param abstractViewController
   * @param criteria
   * @return
   */
  public static VLViewFilterXML resolveFilter(IJSoaggerController controller, String criteria) {
    return instance().getCommonComponentsServices().getFilter(controller, criteria);
  }


  /**
   * Get filter context with given id.
   *
   * @param id
   * @return
   */
  public static VLViewContextFilterXML getFiltersContext(String id) {
    return instance().getCommonComponentsServices().getFiltersContext(id);
  }

  /**
   * Get bean with given context
   *
   * @param identifier
   * @return
   */
  public static Object getBean(String identifier) {
    if (StringUtils.isEmpty(identifier)) {
      return null;
    }

    if (instance().getAppContextService() == null) {
      // System.out.println("[ERROR] Application context service is null!!");
    }

    Object bean = instance().getAppContextService().getBean(identifier);
    if (bean != null) {
      return bean;
    }

    throw new RuntimeException("Bean with name [" + identifier + "] not found.");
  }

  /**
   * @param event
   * @return
   */
  public static void dispatchEvent(Object event) {
    instance().getAppContextService().dispatchEvent(event);
  }

  /**
   * Get configuration file associated to this controlles.
   *
   * @param controller
   * @return
   */
  public static VLViewConfigXML getConfigurationFile(final IJSoaggerController controller) {
    return instance().getViewConfigurationService().getConfigurationFile(controller);
  }

  /**
   * @return
   */
  public ApplicationContextService getAppContextService() {
    return appContextService;
  }

  /**
   * @param appContextService
   */
  public void setAppContextService(ApplicationContextService appContextService) {
    this.appContextService = appContextService;
  }

  /**
   *
   * @return
   */
  public ViewConfigurationService getViewConfigurationService() {
    return viewConfigurationService;
  }

  /**
   *
   * @param viewConfigurationService
   */
  public void setViewConfigurationService(ViewConfigurationService viewConfigurationService) {
    this.viewConfigurationService = viewConfigurationService;
  }

  /**
   * Get a component form platform specific global congiguration.
   *
   * @param id
   * @return
   */
  public static VLViewComponentXML getCompFromGlobalConfig(String id) {
    return instance().getGlobalConfigService().getCompFromGlobalConfig(id);
  }


  public GlobalComponentsService getGlobalConfigService() {
    return globalConfigService;
  }


  public void setGlobalConfigService(GlobalComponentsService globalConfigService) {
    this.globalConfigService = globalConfigService;
  }


  public CommonComponentsServices getCommonComponentsServices() {
    return commonComponentsServices;
  }


  public void setCommonComponentsServices(CommonComponentsServices commonComponentsServices) {
    this.commonComponentsServices = commonComponentsServices;
  }

  public DialogConfigServices getDialogConfigServices() {
    return dialogConfigServices;
  }


  public void setDialogConfigServices(DialogConfigServices dialogConfigServices) {
    this.dialogConfigServices = dialogConfigServices;
  }
}
