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
 *
 */
public class Assert {

  /**
   * Assert a boolean expression, throwing {@code VLValidationException} if
   * the test result is
   * {@code false}.
   *
   * 
   * Assert.isTrue(i &gt; 0, &quot;The value must be greater than zero&quot;);
   * 
   *
   * @param expression
   *            a boolean expression
   * @param message
   *            the exception message to use if the assertion fails
   * @throws VLValidationException
   *             if expression is {@code false}
   */
  public static void isTrue(boolean expression, String message) {
    if (!expression) {
      throw new VLValidationException(message);
    }
  }


  /**
   * Assert a boolean expression, throwing {@code VLValidationException} if
   * the test result is
   * {@code false}.
   *
   * 
   * Assert.isTrue(i &gt; 0);
   * 
   *
   * @param expression
   *            a boolean expression
   * @throws VLValidationException
   *             if expression is {@code false}
   */
  public static void isTrue(boolean expression) {
    Assert.isTrue(expression, "Expected value is true");
  }


  /**
   * Assert that an object is {@code null} .
   *
   * 
   * Assert.isNull(value, &quot;The value must be null&quot;);
   * 
   *
   * @param object
   *            the object to check
   * @param message
   *            the exception message to use if the assertion fails
   * @throws VLValidationException
   *             if the object is not {@code null}
   */
  public static void isNull(Object object, String message) {
    if (object != null) {
      throw new VLValidationException(message);
    }
  }


  /**
   * Assert that an object is {@code null} .
   *
   * 
   * Assert.isNull(value);
   * 
   *
   * @param object
   *            the object to check
   * @throws VLValidationException
   *             if the object is not {@code null}
   */
  public static void isNull(Object object) {
    Assert.isNull(object, "[Assertion failed] - the object argument must be null");
  }


  /**
   * Assert that an object is not {@code null} .
   *
   * 
   * Assert.notNull(clazz, &quot;The class must not be null&quot;);
   * 
   *
   * @param object
   *            the object to check
   * @param message
   *            the exception message to use if the assertion fails
   * @throws VLValidationException
   *             if the object is {@code null}
   */
  public static void notNull(Object object, String message) {
    if (object == null) {
      throw new VLValidationException(message);
    }
  }


  /**
   * Assert that an object is not {@code null} .
   *
   * 
   * Assert.notNull(clazz);
   * 
   *
   * @param object
   *            the object to check
   * @throws VLValidationException
   *             if the object is {@code null}
   */
  public static void notNull(Object object) {
    Assert.notNull(object, "[Assertion failed] - this argument is required; it must not be null");
  }


  /**
   * Assert that the given String is not empty; that is, it must not be
   * {@code null} and not the
   * empty String.
   *
   * 
   * Assert.hasLength(name, &quot;Name must not be empty&quot;);
   * 
   *
   * @param text
   *            the String to check
   * @param message
   *            the exception message to use if the assertion fails
   * @see StringUtils#hasLength
   */
  public static void hasLength(String text, String message) {

    if (!io.github.jsoagger.core.utils.StringUtils.hasLength(text)) {
      throw new VLValidationException(message);
    }
  }


  /**
   * Assert that the given String is not empty; that is, it must not be
   * {@code null} and not the
   * empty String.
   *
   * 
   * Assert.hasLength(name);
   * 
   *
   * @param text
   *            the String to check
   * @see StringUtils#hasLength
   */
  public static void hasLength(String text) {
    Assert.hasLength(text,
        "[Assertion failed] - this String argument must have length; it must not be null or empty");
  }


  /**
   * Assert that the given the given string is same as the first string
   *
   */
  public static void same(String text, String compareTo) {
    Assert.hasLength(text,
        "[Assertion failed] - this String argument must have value : " + compareTo);
  }


  /**
   * Assert that given String has "size" maximum length
   *
   * @param text
   * @param size
   * @return
   */
  public static boolean hasMaxLength(String text, int size) {
    if (text != null) {
      if (text.length() > size) {
        throw new VLValidationException("Size of attribute is bigger than authorized : " + size);
      }
    }

    return true;
  }


  /**
   * Assert that the given String has valid text content; that is, it must not
   * be {@code null} and
   * must contain at least one non-whitespace character.
   *
   * 
   * Assert.hasText(name, &quot;'name' must not be empty&quot;);
   * 
   *
   * @param text
   *            the String to check
   * @param message
   *            the exception message to use if the assertion fails
   * @see StringUtils#hasText
   */
  public static void hasText(String text, String message) {

    if (!StringUtils.hasText(text)) {
      throw new VLValidationException(message);
    }
  }


  /**
   * Assert that the given String has valid text content; that is, it must not
   * be {@code null} and
   * must contain at least one non-whitespace character.
   *
   * 
   * Assert.hasText(name, &quot;'name' must not be empty&quot;);
   * 
   *
   * @param text
   *            the String to check
   * @see StringUtils#hasText
   */
  public static void hasText(String text) {

    Assert
    .hasText(text,
        "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
  }


  /**
   * Assert that the given text does not contain the given substring.
   *
   * 
   * Assert.doesNotContain(name, &quot;rod&quot;, &quot;Name must not contain 'rod'&quot;);
   * 
   *
   * @param textToSearch
   *            the text to search
   * @param substring
   *            the substring to find within the text
   * @param message
   *            the exception message to use if the assertion fails
   */
  public static void doesNotContain(String textToSearch, String substring, String message) {

    if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
        && textToSearch.contains(substring)) {
      throw new VLValidationException(message);
    }
  }


  /**
   * Assert that the given text does not contain the given substring.
   *
   * 
   * Assert.doesNotContain(name, &quot;rod&quot;);
   * 
   *
   * @param textToSearch
   *            the text to search
   * @param substring
   *            the substring to find within the text
   */
  public static void doesNotContain(String textToSearch, String substring) {

    Assert.doesNotContain(textToSearch, substring,
        "[Assertion failed] - this String argument must not contain the substring [" + substring
        + "]");
  }


  /**
   * Assert that an array has elements; that is, it must not be {@code null}
   * and must have at least
   * one element.
   *
   * 
   * Assert.notEmpty(array, &quot;The array must have elements&quot;);
   * 
   *
   * @param array
   *            the array to check
   * @param message
   *            the exception message to use if the assertion fails
   * @throws VLValidationException
   *             if the object array is {@code null} or has no elements
   */
  public static void notEmpty(Object[] array, String message) {
    if (array == null || array.length == 0) {
      throw new VLValidationException(message);
    }
  }


  /**
   * Assert that an array has elements; that is, it must not be {@code null}
   * and must have at least
   * one element.
   *
   * 
   * Assert.notEmpty(array);
   * 
   *
   * @param array
   *            the array to check
   * @throws VLValidationException
   *             if the object array is {@code null} or has no elements
   */
  public static void notEmpty(Object[] array) {

    Assert.notEmpty(array,
        "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
  }


  /**
   * Assert that an array has no null elements. Note: Does not complain if the
   * array is empty!
   *
   * 
   * Assert.noNullElements(array, &quot;The array must have non-null elements&quot;);
   * 
   *
   * @param array
   *            the array to check
   * @param message
   *            the exception message to use if the assertion fails
   * @throws VLValidationException
   *             if the object array contains a {@code null} element
   */
  public static void noNullElements(Object[] array, String message) {

    if (array != null) {
      for (Object element : array) {
        if (element == null) {
          throw new VLValidationException(message);
        }
      }
    }
  }


  /**
   * Assert that an array has no null elements. Note: Does not complain if the
   * array is empty!
   *
   * 
   * Assert.noNullElements(array);
   * 
   *
   * @param array
   *            the array to check
   * @throws VLValidationException
   *             if the object array contains a {@code null} element
   */
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
   * @throws VLValidationException
   *             if the collection is {@code null} or has no elements
   */
  public static void notEmpty(Collection<?> collection) {

    Assert
    .notEmpty(collection,
        "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
  }


  /**
   * Assert that a Map has entries; that is, it must not be {@code null} and
   * must have at least one
   * entry.
   *
   * 
   * Assert.notEmpty(map, &quot;Map must have entries&quot;);
   * 
   *
   * @param map
   *            the map to check
   * @param message
   *            the exception message to use if the assertion fails
   * @throws VLValidationException
   *             if the map is {@code null} or has no entries
   */
  public static void notEmpty(Map<?, ?> map, String message) {
    if (map == null || map.isEmpty()) {
      throw new VLValidationException(message);
    }
  }


  /**
   * Assert that a Map has entries; that is, it must not be {@code null} and
   * must have at least one
   * entry.
   *
   * 
   * Assert.notEmpty(map);
   * 
   *
   * @param map
   *            the map to check
   * @throws VLValidationException
   *             if the map is {@code null} or has no entries
   */
  public static void notEmpty(Map<?, ?> map) {

    Assert.notEmpty(map,
        "[Assertion failed] - this map must not be empty; it must contain at least one entry");
  }


  /**
   * Assert that the provided object is an instance of the provided class.
   *
   * 
   * Assert.instanceOf(Foo.class, foo);
   * 
   *
   * @param clazz
   *            the required class
   * @param obj
   *            the object to check
   * @throws VLValidationException
   *             if the object is not an instance of clazz
   * @see Class#isInstance
   */
  public static void isInstanceOf(Class<?> clazz, Object obj) {

    Assert.isInstanceOf(clazz, obj, "Argument is not instance of expected class");
  }


  /**
   * Assert that the provided object is an instance of the provided class.
   *
   * 
   * Assert.instanceOf(Foo.class, foo);
   * 
   *
   * @param type
   *            the type to check against
   * @param obj
   *            the object to check
   * @param message
   *            a message which will be prepended to the message produced by
   *            the function
   *            itself, and which may be used to provide context. It should
   *            normally end in a ": " or ".
   *            " so that the function generate message looks ok when
   *            prepended to it.
   * @throws VLValidationException
   *             if the object is not an instance of clazz
   * @see Class#isInstance
   */
  public static void isInstanceOf(Class<?> type, Object obj, String message) {

    Assert.notNull(type, "Type to check against must not be null");
    if (!type.isInstance(obj)) {
      throw new VLValidationException((StringUtils.hasLength(message) ? message + " " : "")
          + "Object of class [" + (obj != null ? obj.getClass().getName() : "null")
          + "] must be an instance of " + type);
    }
  }


  /**
   * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
   *
   * 
   * Assert.isAssignable(Number.class, myClass);
   * 
   *
   * @param superType
   *            the super type to check
   * @param subType
   *            the sub type to check
   * @throws VLValidationException
   *             if the classes are not assignable
   */
  public static void isAssignable(Class<?> superType, Class<?> subType) {

    Assert.isAssignable(superType, subType, "");
  }


  /**
   * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
   *
   * 
   * Assert.isAssignable(Number.class, myClass);
   * 
   *
   * @param superType
   *            the super type to check against
   * @param subType
   *            the sub type to check
   * @param message
   *            a message which will be prepended to the message produced by
   *            the function
   *            itself, and which may be used to provide context. It should
   *            normally end in a ": " or ".
   *            " so that the function generate message looks ok when
   *            prepended to it.
   * @throws VLValidationException
   *             if the classes are not assignable
   */
  public static void isAssignable(Class<?> superType, Class<?> subType, String message) {

    Assert.notNull(superType, "Type to check against must not be null");
    if ((subType == null) || !superType.isAssignableFrom(subType)) {
      throw new VLValidationException(message + subType + " is not assignable to " + superType);
    }
  }


  /**
   * Assert a boolean expression, throwing {@code IllegalStateException} if
   * the test result is
   * {@code false}. Call isTrue if you wish to throw VLValidationException on
   * an assertion failure.
   *
   * 
   * Assert.state(id == null, &quot;The id property must not already be initialized&quot;);
   * 
   *
   * @param expression
   *            a boolean expression
   * @param message
   *            the exception message to use if the assertion fails
   * @throws IllegalStateException
   *             if expression is {@code false}
   */
  public static void state(boolean expression, String message) {

    if (!expression) {
      throw new IllegalStateException(message);
    }
  }


  /**
   * Assert a boolean expression, throwing {IllegalStateException} if
   * the test result is
   * {@code false}.
   * <p>
   * Call {#isTrue(boolean)} if you wish to throw
   * {VLValidationException} on an
   * assertion failure.
   *
   * 
   * Assert.state(id == null);
   * 
   *
   * @param expression
   *            a boolean expression
   * @throws IllegalStateException
   *             if the supplied expression is {@code false}
   */
  public static void state(boolean expression) {

    Assert.state(expression, "[Assertion failed] - this state invariant must be true");
  }

}
