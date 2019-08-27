/**
 *
 */
package io.github.jsoagger.core.ioc.api.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
/**
 * @author Ramilafananana  VONJISOA
 *
 */
public @interface I18n {

  String dest();
  String[] locations();
}
