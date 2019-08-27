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



import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.jsoagger.core.utils.exception.VLException;
import io.github.jsoagger.core.utils.exception.VLKnoerDoerInfoInvocationException;

public class ReflectionUtils {

  /**
   * Create a new instance of a class. Call default constructor.
   */
  public static Object newInstance(String className) {

    Class<?> entryLinkClazz;
    try {
      entryLinkClazz = Class.forName(className);
      final Constructor<?> entryLinkClazzConstructor = entryLinkClazz.getConstructors()[0];
      return entryLinkClazzConstructor.newInstance();
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return null;
  }


  /**
   * Create a new instance of a class.
   *
   * @param className the class name
   * @param var table of parameters value
   * @param args args type
   */
  public static Object newInstance(String className, Object var, Class<?>... args) {

    Class<?> entryLinkClazz;
    try {
      entryLinkClazz = Class.forName(className);
      final Constructor<?> entryLinkClazzConstructor = entryLinkClazz.getConstructor(args);
      return entryLinkClazzConstructor.newInstance(var);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
    }

    return null;
  }


  /**
   * Copy all attributes from left object to right object based on the entities attributes name. All
   * properties with the same name are copied.
   *
   * @param from The object from which copy value
   * @param to The object to which set values
   * @throws VLException
   */
  public static void copyIdenticalAttributesFrom(Object from, Object to) throws VLException {
    try {
      //BeanUtils.copyProperties(to, from);
    } catch (Exception e) {
      throw new VLException(e, "Error when copying attributes");
    }
  }


  /**
   *
   * @param from
   * @param toClass
   * @param withAnnotation
   * @return
   * @throws VLException
   */
  @SuppressWarnings("rawtypes")
  public static <T> T copyAnnotatedPropertiesFrom(Object from, Class<T> toClass, Class withAnnotation) throws VLException {
    List<Field> fields = ReflectionUtils.getFieldAnnotedWith(toClass, withAnnotation);
    try {
      T resultingObject = ReflectionUtils.invokeDefaultConstructorOn(toClass);

      /*for (Field field : fields) {
        Object value = BeanUtils.getProperty(from, field.getName());
        BeanUtils.setProperty(resultingObject, field.getName(), value);
      }*/

      return resultingObject;
    } catch (Exception e) {
      throw new VLException(e, "Error when copying attributes");
    }
  }


  /**
   * Creates a new instance of the given class by invoking its default constructor.
   *
   * @param clazz The class to instanciate
   * @return class
   * @throws VLException
   */
  @SuppressWarnings("unchecked")
  private static <T> T invokeDefaultConstructorOn(Class<T> clazz) throws VLException {

    try {
      final Constructor<T> constr = (Constructor<T>) clazz.getConstructors()[0];
      final T object = constr.newInstance();

      return object;
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      throw new VLException(e, "Error when creating new instance of entity. Make sure that default constructor exists on class : " + clazz);
    }
  }


  /**
   * Get all field annotated with the given annotation for the given class.
   *
   * @param clazz the class
   * @param annotationClass The annotation
   * @return List
   */
  @SuppressWarnings("rawtypes")
  private static List<Field> getFieldAnnotedWith(Class clazz, Class annotationClass) {
    final List<Field> annotedFields = new ArrayList<>();
    for (Field field : clazz.getDeclaredFields()) {
      Annotation[] annotations = field.getDeclaredAnnotations();
      for (Annotation annotation : annotations) {
        if (annotation.getClass().equals(annotationClass)) {
          annotedFields.add(field);
        }
      }
    }

    return annotedFields;
  }


  /**
   * Invoke setter on an objet
   *
   * @param entity The entity on which invoke the method
   * @param name The info attribute name
   * @param value The entity to info to attach
   * @throws VLKnoerDoerInfoInvocationException When invoking the method
   */
  public static void invokeSetterOn(Object entity, String name, Object value) {
    try {
      //BeanUtils.setProperty(entity, name, value);
    } catch (Exception e) {
      throw new VLKnoerDoerInfoInvocationException(e);
    }
  }


  /**
   * This method is used to invoke getter on an object
   *
   * @param entity The entity
   * @param name The name
   * @throws VLKnoerDoerInfoInvocationException When invoking the method
   */
  public static Object invokeGetterOn(Object entity, String name) {
    try {
      //return PropertyUtils.getProperty(entity, name);
      return null;
    } catch (Exception e) {
      System.out.println("Property : " + name + ", not found on entity " + entity.getClass().getName());
      throw new VLKnoerDoerInfoInvocationException(e);
    }
  }


  /**
   * Get the value of a dynamic attribute on a type managed
   *
   * @param entity The type managed
   * @param index The index
   * @return String
   */
  public static String invokeGetDynamicAttributeValueOn(Object entity, int index) {
    try {
      //return BeanUtils.getProperty(entity, "softAttributeColumns.dyn_att_" + index);
      return null;
    } catch (Exception e) {
      throw new VLKnoerDoerInfoInvocationException(e);
    }
  }


  /**
   * Set the value of a dynamic attribute on a type managed
   *
   * @param entity The type managed
   * @param index The index
   * @param value The value
   */
  public static void invokeSetDynamicAttributeValueOn(Object entity, int index, String value) {
    try {
      //BeanUtils.setProperty(entity, "softAttributeColumns.dyn_att_" + index, value);
    } catch (Exception e) {
      throw new VLKnoerDoerInfoInvocationException(e);
    }
  }


  private static VLBeanUtilsBean beanUtilsBeanInstance(List<String> exclusionList) {
    if (exclusionList != null) {
      beanUtilsBean.setExclusionsList(exclusionList);
    }
    return beanUtilsBean;
  }


  public static void initialize(Object instance) {
    Class<? extends Object> clazz = instance.getClass();
    // invokeMethodWithAnnotation(clazz, instance, PostConstruct.class);
  }


  public static void destroy(Object instance) {
    Class<? extends Object> clazz = instance.getClass();
    // invokeMethodWithAnnotation(clazz, instance, PreDestroy.class);
  }


  @SuppressWarnings("unchecked")
  public static void invokeMethodWithAnnotation(Class clazz, final Object instance, final Class<? extends Annotation> annotationClass) throws IllegalStateException, SecurityException {

    Method[] declaredMethods = clazz.getDeclaredMethods();
    for (final Method method : declaredMethods) {
      if (method.isAnnotationPresent(annotationClass)) {
        AccessController.doPrivileged((PrivilegedAction) () -> {
          boolean wasAccessible = method.isAccessible();
          try {
            method.setAccessible(true);
            return method.invoke(instance, new Object[] {});
          } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new IllegalStateException("Problem invoking " + annotationClass + " : " + method, ex);
          } finally {
            method.setAccessible(wasAccessible);
          }
        });
      }
    }
    Class superclass = clazz.getSuperclass();
    if (superclass != null) {
      invokeMethodWithAnnotation(superclass, instance, annotationClass);
    }
  }


  public static List<Object> toArgs(Object... vars) {
    return Arrays.asList(vars);
  }

  private static VLBeanUtilsBean beanUtilsBean;
  static {
    beanUtilsBean = new VLBeanUtilsBean();
  }
}
