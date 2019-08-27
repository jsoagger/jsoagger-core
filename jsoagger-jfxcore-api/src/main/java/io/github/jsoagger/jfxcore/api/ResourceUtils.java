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


package io.github.jsoagger.jfxcore.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


@SuppressWarnings("rawtypes")
public class ResourceUtils  {

  /**
   * Get classpath url associated to resource with given path.
   *
   * @param clazz
   * @param path
   * @return
   */
  public static URL getURL(String path) {
    URL rul = ResourceUtils.class.getResource(path);
    if (rul == null) {
      rul = ResourceUtils.class.getClassLoader().getResource(path);
    }

    if (rul == null) {
      rul = Thread.currentThread().getContextClassLoader().getResource(path);
    }

    if (rul == null) {
      try {
        // ? another method ???
        throw new IOException("ERROR : File with name " + path + " not found!");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (rul == null) {
      System.out.println("Definitly Resource not found : " + path);
    }

    return rul;
  }

  /**
   * Get classpath url associated to resource with given path.
   *
   * @param clazz
   * @param path
   * @return
   */
  public static URL getURL(Class clazz, String path) {
    URL rul = getURL(path);
    if(rul == null) {
      rul = clazz.getResource(path);
    }

    if (rul == null) {
      rul = clazz.getClassLoader().getResource(path);
    }

    if (rul == null) {
      rul = Thread.currentThread().getContextClassLoader().getResource(path);
    }

    if (rul == null) {
      try {
        // ? another method ???
        throw new IOException("ERROR : File with name " + path + " not found!");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (rul == null) {
      System.out.println("Definitly Resource not found : " + path);
    }

    return rul;
  }

  /**
   * Uses all possible methods to find out this resource.
   *
   * @param clazz
   * @param path
   * @return
   */
  public static InputStream getStream(Class clazz, String path) {
    InputStream inputStream = clazz.getResourceAsStream(path);
    if (inputStream == null) {
      inputStream = clazz.getClassLoader().getResourceAsStream(path);
    }

    if (inputStream == null) {
      inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    if (inputStream == null) {
      inputStream = getFromStringContext(path);
    }

    if (inputStream == null) {
      System.out.println("Definitly Resource not found : " + path);
    }

    return inputStream;
  }


  /**
   * Uses all possible methods to find out this resource.
   *
   * @param clazz
   * @param path
   * @return
   */
  public static InputStream getStream(String path) {
    InputStream inputStream = ResourceUtils.class.getResourceAsStream(path);
    if (inputStream == null) {
      inputStream = ResourceUtils.class.getClassLoader().getResourceAsStream(path);
    }

    if (inputStream == null) {
      inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }

    if (inputStream == null) {
      inputStream = getFromStringContext(path);
    }

    if (inputStream == null) {
      System.out.println("Definitly Resource not found : " + path);
    }

    return inputStream;
  }

  private static InputStream getFromStringContext(String path) {
    if(path.startsWith("classpath:")) {
      return getStream(path.split("classpath:")[1]);
    }
    return null;
  }
}
