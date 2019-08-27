/**
 *
 */
package io.github.jsoagger.jfxcore.api.services;

import io.github.jsoagger.jfxcore.api.css.IStylesheetManager;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface ApplicationContextService {

  /**
   * Returns the {@link IStylesheetManager} of the application.
   *
   * @return {@link IStylesheetManager}
   */
  public  IStylesheetManager getStylesheetManager();

  /**
   * Dispatch the given event
   */
  public  void dispatchEvent(Object event) ;

  /**
   * Search for bean with the given identifier from {@link ApplicationContext}
   *
   * @param identifier The identifier of the bean
   * @return null if no bean with that name
   */
  public  Object getBean(String identifier) ;

  public void publishEvent(Object event);
}
