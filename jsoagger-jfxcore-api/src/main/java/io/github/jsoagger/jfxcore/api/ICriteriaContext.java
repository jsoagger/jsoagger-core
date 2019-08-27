/**
 *
 */
package io.github.jsoagger.jfxcore.api;

import java.util.HashMap;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface ICriteriaContext {

  HashMap<String, String> getAllFilters();

  boolean containsFilter(String name);

  String filterValue(String name);

  void setFilter(String e, String string);
}
