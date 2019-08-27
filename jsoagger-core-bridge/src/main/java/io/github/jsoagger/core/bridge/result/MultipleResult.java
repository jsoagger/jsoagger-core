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

package io.github.jsoagger.core.bridge.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.jsoagger.core.bridge.operation.IOperationResult;


/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class MultipleResult extends OperationResult {

  private static final long	serialVersionUID = -2152659463428448410L;
  private List<OperationData>	data			 = new ArrayList<>();


  /**
   * Constructor
   */
  public MultipleResult() {
    super();
  }


  public List<String> collectIdentifiers() {
    List<String> ids = new ArrayList<>();
    if (data != null) {
      for (OperationData opd : data) {
        String id = (String) opd.getAttributes().get("fullId");
        ids.add(id);
      }
    }

    return ids;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public Object rootData() {
    return data;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void setData(Object data) {
    if (data != null) {
      try {
        List dataList = (List) data;
        setData(dataList);
      }
      catch (ClassCastException e) {
      }
    }
    else {
      setData(new ArrayList<>());
    }
  }


  /**
   * Setter of data
   *
   * @param data
   *            the data to set
   */
  public void setData(List<OperationData> data) {
    this.data = data;
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("MultipleResult [");
    if (data != null) {
      builder.append("data=");
      builder.append(data);
    }
    builder.append("]");
    return builder.toString();
  }

  /**
   * @author Ramilafananana VONJISOA
   * @mailto yvonjisoa@nexitia.com
   * @date 2019
   */
  public static class Builder {

    private List<OperationData>	data;
    public Map<String, Object>	meta;


    public Builder data(List<OperationData> data) {
      this.data = data;
      return this;
    }


    public Builder addMeta(String key, Object value) {
      if (this.meta == null) {
        this.meta = new HashMap<>();
      }

      this.meta.put(key, value);
      return this;
    }


    public Builder addData(OperationData data) {
      if (this.data == null) {
        this.data = new ArrayList<>();
      }

      this.data.add(data);
      return this;
    }


    public MultipleResult build() {
      return new MultipleResult(this);
    }
  }


  /**
   * Constructor
   *
   * @param builder
   */
  private MultipleResult(Builder builder) {
    this.data = builder.data;
    this.metaData = builder.meta;
  }


  /**
   * Getter of data
   *
   * @return the data
   */
  public List<OperationData> getData() {
    if(data == null) {
      data = new ArrayList<>();
    }
    return data;
  }


  /**
   * Return true if has elements in data.
   *
   * @return
   */
  public boolean hasElements() {
    return (getMetaData() != null)
        && (getMetaData().get(IOperationResult.totalElements) != null)
        && (totaElements() > 0);
  }


  public boolean hasNext() {
    return (getMetaData() != null)
        && Boolean.valueOf(String.valueOf(getMetaData().get(IOperationResult.hasNextPage)));
  }


  public boolean hasPrevious() {
    return (getMetaData() != null)
        && (boolean) getMetaData().get(IOperationResult.hasPreviousPage);
  }


  /**
   * Get the current page elements.
   *
   * @return
   */
  public int pageElements() {
    if ((getMetaData() != null)
        && (getMetaData().get(IOperationResult.pageElements) != null)) {
      return integerValueOf(String.valueOf(getMetaData().get(IOperationResult.pageElements)));
    }
    return -1;
  }


  /**
   * Get the total elements of query result, not page result.
   *
   * @return
   */
  public int totaElements() {
    if ((getMetaData() != null)
        && (getMetaData().get(IOperationResult.totalElements) != null)) {
      return integerValueOf(String.valueOf(getMetaData().get(IOperationResult.totalElements)));
    }
    return -1;
  }


  /**
   * @param val
   * @return
   */
  private Integer integerValueOf(String val) {
    try {
      return Double.valueOf(val).intValue();
    }
    catch (Exception e) {
      return -1;
    }
  }


  /**
   * @return
   */
  public int getPreviousPageIndex() {
    if ((getMetaData() != null) && (getMetaData().get(IOperationResult.pageNumber) != null)) {
      int curPage = integerValueOf(String.valueOf(getMetaData().get(IOperationResult.pageNumber)));
      return curPage - 1;
    }

    return -1;
  }

  /**
   * Get the index of current page
   *
   * @return
   */
  public int getCurrentPageIndex() {
    return  integerValueOf(String.valueOf(getMetaData().get(IOperationResult.pageNumber)));
  }


  /**
   * @return
   */
  public int getNextPageIndex() {
    if ((getMetaData() != null) && (getMetaData().get(IOperationResult.pageNumber) != null)) {
      int curPage = integerValueOf(String.valueOf(getMetaData().get(IOperationResult.pageNumber)));
      return curPage + 1;
    }
    return -1;
  }


  /**
   * @return
   */
  public int lastPage() {
    if ((getMetaData() != null)
        && (getMetaData().get(IOperationResult.pageNumber) != null)) {
      return integerValueOf(String.valueOf(getMetaData().get(IOperationResult.pageNumber)));
    }
    return -1;
  }


  /**
   * @return
   */
  public int firstPage() {
    return 0;
  }


  /**
   * @return
   */
  public Object getPageSize() {
    if ((getMetaData() != null)
        && (getMetaData().get(IOperationResult.pageSize) != null)) {
      return integerValueOf(String.valueOf(getMetaData().get(IOperationResult.pageSize)));
    }
    return -1;
  }
}
