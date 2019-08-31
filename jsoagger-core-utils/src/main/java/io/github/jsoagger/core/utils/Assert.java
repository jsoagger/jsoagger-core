/*-
 * ========================LICENSE_START=================================
 * JSoagger 
 * %%
 * Copyright (C) 2019 JSOAGGER
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

package io.github.jsoagger.core.utils;

import java.util.Collection;
import java.util.Map;

import io.github.jsoagger.core.utils.exception.VLValidationException;

/**
 * @author Ramilafananana  VONJISOA
 */
public class Assert {

  public static void isTrue(boolean expression, String message) {
    if (!expression) {
      throw new VLValidationException(message);
    }
  }

  public static void isTrue(boolean expression) {
    Assert.isTrue(expression, "Expected value is true");
  }

  public static void isNull(Object object, String message) {
    if (object != null) {
      throw new VLValidationException(message);
    }
  }


  public static void isNull(Object object) {
    Assert.isNull(object, "[Assertion failed] - the object argument must be null");
  }

  public static void notNull(Object object, String message) {
    if (object == null) {
      throw new VLValidationException(message);
    }
  }


  public static void notNull(Object object) {
    Assert.notNull(object, "[Assertion failed] - this argument is required; it must not be null");
  }

  public static void hasLength(String text, String message) {

    if (!io.github.jsoagger.core.utils.StringUtils.hasLength(text)) {
      throw new VLValidationException(message);
    }
  }

  public static void hasLength(String text) {
    Assert.hasLength(text,
        "[Assertion failed] - this String argument must have length; it must not be null or empty");
  }

  public static void same(String text, String compareTo) {
    Assert.hasLength(text,
        "[Assertion failed] - this String argument must have value : " + compareTo);
  }

  public static boolean hasMaxLength(String text, int size) {
    if (text != null) {
      if (text.length() > size) {
        throw new VLValidationException("Size of attribute is bigger than authorized : " + size);
      }
    }

    return true;
  }


  public static void hasText(String text, String message) {

    if (!StringUtils.hasText(text)) {
      throw new VLValidationException(message);
    }
  }

  public static void hasText(String text) {
    Assert
    .hasText(text,
        "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
  }

  public static void doesNotContain(String textToSearch, String substring, String message) {
    if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
        && textToSearch.contains(substring)) {
      throw new VLValidationException(message);
    }
  }

  public static void doesNotContain(String textToSearch, String substring) {

    Assert.doesNotContain(textToSearch, substring,
        "[Assertion failed] - this String argument must not contain the substring [" + substring
        + "]");
  }

  public static void notEmpty(Object[] array, String message) {
    if (array == null || array.length == 0) {
      throw new VLValidationException(message);
    }
  }

  public static void notEmpty(Object[] array) {

    Assert.notEmpty(array,
        "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
  }

  public static void noNullElements(Object[] array, String message) {

    if (array != null) {
      for (Object element : array) {
        if (element == null) {
          throw new VLValidationException(message);
        }
      }
    }
  }

  public static void noNullElements(Object[] array) {

    Assert.noNullElements(array,
        "[Assertion failed] - this array must not contain any null elements");
  }


  /**
   * Assert that a collection has elements; that is, it must not be
   * {@code null} and must have at
   * least one element.
   *
   * 
   * Assert.notEmpty(collection, &quot;Collection must have elements&quot;);
   * 
   *
   * @param collection
   *            the collection to check
   * @param message
   *            the exception message to use if the assertion fails
   * @throws VLValidationException
   *             if the collection is {@code null} or has no elements
   */
  public static void notEmpty(Collection<?> collection, String message) {
    if (collection == null || collection.size() == 0) {
      throw new VLValidationException(message);
    }
  }


  public static void notEmpty(Collection<?> collection) {

    Assert
    .notEmpty(collection,
        "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
  }

  public static void notEmpty(Map<?, ?> map, String message) {
    if (map == null || map.isEmpty()) {
      throw new VLValidationException(message);
    }
  }

  public static void notEmpty(Map<?, ?> map) {

    Assert.notEmpty(map,
        "[Assertion failed] - this map must not be empty; it must contain at least one entry");
  }

  public static void isInstanceOf(Class<?> clazz, Object obj) {

    Assert.isInstanceOf(clazz, obj, "Argument is not instance of expected class");
  }


  public static void isInstanceOf(Class<?> type, Object obj, String message) {
    Assert.notNull(type, "Type to check against must not be null");
    if (!type.isInstance(obj)) {
      throw new VLValidationException((StringUtils.hasLength(message) ? message + " " : "")
          + "Object of class [" + (obj != null ? obj.getClass().getName() : "null")
          + "] must be an instance of " + type);
    }
  }

  public static void isAssignable(Class<?> superType, Class<?> subType) {

    Assert.isAssignable(superType, subType, "");
  }

  public static void isAssignable(Class<?> superType, Class<?> subType, String message) {

    Assert.notNull(superType, "Type to check against must not be null");
    if ((subType == null) || !superType.isAssignableFrom(subType)) {
      throw new VLValidationException(message + subType + " is not assignable to " + superType);
    }
  }

  public static void state(boolean expression, String message) {

    if (!expression) {
      throw new IllegalStateException(message);
    }
  }

  public static void state(boolean expression) {

    Assert.state(expression, "[Assertion failed] - this state invariant must be true");
  }
}
