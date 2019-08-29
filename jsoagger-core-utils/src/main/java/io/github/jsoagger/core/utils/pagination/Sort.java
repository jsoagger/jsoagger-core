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


package io.github.jsoagger.core.utils.pagination;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.jsoagger.core.utils.StringUtils;

/**
 * 
 * @author vonji
 *
 */
public class Sort {

  private final static String ASC_STRING = "+";

  /**
   * Field DESC_STRING (value is -)
   */
  private final static String DESC_STRING = "-";
  private Direction direction;
  private List<String> properties;


  /**
   * Constructor
   *
   * @param direction
   * @param properties
   */
  public Sort(Direction direction, String... properties) {
    this.direction = direction;
    this.properties = Arrays.asList(properties);
  }


  /**
   * Generates a {@link List} of {@link Sort} from a string. The input must be separated by coma.
   * <p>
   * Examples of valid sort query param:
   * <li><b>+</b>versionInfo.versionNumber: sorting ASC</li>
   * <li><b>-</b>versionInfo.versionNumber: sorting DESC</li>
   * <li><b>+</b>versionInfo.versionNumber, <b>-</b>persistenceInfo.lastModificationDate: sorting
   * multiple
   *
   * @param sortString
   * @return Null safe List
   */
  public static List<Sort> fromListString(String sortString) {
    List<Sort> sorts = new ArrayList<>();

    if (StringUtils.hasText(sortString)) {

      final List<String> split = Arrays.asList(sortString.split(","));

      if (!split.isEmpty()) {
        for (String ss : split) {
          sorts.add(fromString(ss));
        }
      }
    }

    return sorts;
  }


  /**
   * Called by REST API to convert sort string from {@link QueryParam} to {@link Sort} object injected
   * in the a method parameter. If the sort string from {@link QueryParam} is <code>null</code> or has
   * text but is not valid, underlying API will be called with default sort (modification date DESC).
   *
   * <p>
   * Examples of valid sort query param:
   * <li>sort=<b>+</b>versionInfo.versionNumber: sorting ASC</li>
   * <li>sort=<b>+</b>versionInfo.versionNumber, <b>-</b>persistenceInfo.lastModificationDate: Sorting
   * ASC and DESC</li>
   * <li>sort=versionInfo.versionNumber: not valid</li>
   * <li>sort=: evaluates to null</li>
   *
   * @param sortString Sort string
   * @return Sort[]
   */
  public static Sort fromString(String sortString) {

    if (!sortString.startsWith(ASC_STRING) && !sortString.startsWith(DESC_STRING)) {

      Direction direction = Direction.ASC;
      String property = sortString.substring(1);

      if (StringUtils.hasText(property)) {
        return new Sort(direction, property);
      }

    } else {
      if (sortString.startsWith(ASC_STRING) || sortString.startsWith(DESC_STRING)) {

        Direction direction = sortString.startsWith(ASC_STRING) ? Direction.ASC : Direction.DESC;
        String property = sortString.substring(1);

        if (StringUtils.hasText(property)) {
          return new Sort(direction, property);
        }
      }
    }

    return null;
  }


  public static Sort valueOf(String sortString) {
    return fromString(sortString);
  }


  public Direction getDirection() {
    return direction;
  }


  public void setDirection(Direction direction) {
    this.direction = direction;
  }


  public List<String> getProperties() {
    return properties;
  }


  public void setProperties(List<String> properties) {
    this.properties = properties;
  }


  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Sort [direction=" + direction + ", properties=" + properties + "]";
  }
}
