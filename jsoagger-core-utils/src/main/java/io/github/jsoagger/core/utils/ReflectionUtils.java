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

public class ReflectionUtils {

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


    public static void copyIdenticalAttributesFrom(Object from, Object to) {

    }


    @SuppressWarnings("rawtypes")
    public static <T> T copyAnnotatedPropertiesFrom(Object from, Class<T> toClass, Class withAnnotation) {
        List<Field> fields = ReflectionUtils.getFieldAnnotedWith(toClass, withAnnotation);
        try {
            T resultingObject = ReflectionUtils.invokeDefaultConstructorOn(toClass);
            return resultingObject;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @SuppressWarnings("unchecked")
    private static <T> T invokeDefaultConstructorOn(Class<T> clazz) {

        try {
            final Constructor<T> constr = (Constructor<T>) clazz.getConstructors()[0];
            final T object = constr.newInstance();
            return object;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }


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


    public static void invokeSetterOn(Object entity, String name, Object value) {
        try {
            //BeanUtils.setProperty(entity, name, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Object invokeGetterOn(Object entity, String name) {
        try {
            //return PropertyUtils.getProperty(entity, name);
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static String invokeGetDynamicAttributeValueOn(Object entity, int index) {
        try {
            //return BeanUtils.getProperty(entity, "softAttributeColumns.dyn_att_" + index);
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void invokeSetDynamicAttributeValueOn(Object entity, int index, String value) {
        try {
            //BeanUtils.setProperty(entity, "softAttributeColumns.dyn_att_" + index, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
                        return method.invoke(instance, new Object[]{});
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
