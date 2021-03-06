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


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import com.mysema.query.types.OrderSpecifier;
//import com.mysema.query.types.Predicate;
//import com.mysema.query.types.path.EntityPathBase;
/**
 * @author vonji
 *
 */
public class PageRequest implements Serializable {

  private static final long		serialVersionUID = 8071485258870796112L;
  public static final int			QUERY_LIMIT		 = 20;
  public static PageRequest		DEFAULT;
  /**
   * Field default sort
   * (+persistenceInfo.createDate,+persistenceInfo.lastModifiedDate)
   */
  public static final String		DEFAULT_SORT_1	 = "+persistenceInfo.lastModifiedDate";
  public static final String		DEFAULT_SORT_2	 = "+persistenceInfo.createDate";

  private int						page;
  private int						size;
  private Map<String, String>		params			 = new HashMap<>();
  private List<Sort>				sort;
  private Object predicate;

  static {
    List<Sort> sorts = new ArrayList<>();
    sorts.add(Sort.fromString(DEFAULT_SORT_1));
    sorts.add(Sort.fromString(DEFAULT_SORT_2));
    DEFAULT = new PageRequest(0, QUERY_LIMIT, sorts);
  }


  /**
   * Default one, page is 0 and page size is 200.
   */
  public static PageRequest pageRequest() {
    return new PageRequest(0, QUERY_LIMIT);
  }


  /**
   * Constructor
   */
  public PageRequest(int page, int pageSize, String sortString) {
    this(page, pageSize);
    this.sort = Sort.fromListString(sortString);
  }


  /**
   * Constructor
   */
  public PageRequest() {
    page = 0;
    size = QUERY_LIMIT;
  }


  /**
   * Constructor
   */
  public PageRequest(int page, int pageSize) {
    if (page < 0) {
      throw new IllegalArgumentException("Page number must be positif.");
    }

    this.page = page;
    this.size = pageSize;
    this.sort = new ArrayList<>();
  }

/**
 * 
 * @return
 */
  public boolean hasExternalSelect() {
    //return select != null && !select.isEmpty();
    return false;
  }


  /**
   * Constructor
   */
  public PageRequest(int page, int size, List<Sort> sort) {
    this(page, size);
    if (sort != null && sort.size() > 0) {
      this.sort = sort;
    }
  }


  /**
   * Constructor
   */
  public PageRequest(int page, int size, Sort sort) {
    this(page, size);
    if (sort != null) {
      this.sort.add(sort);
    }
  }


  /**
   * Constructor
   */
  public PageRequest(int page, int pageSize, Direction direction, String... properties) {
    this(page, pageSize, Arrays.asList(new Sort(direction, properties)));
  }


  /**
   * @return
   */
  public int getOffset() {
    return page * size;
  }


  /**
   * Getter of the field size
   *
   * @return the size
   */
  public int getCurrentpageSizeToLoad() {
    return size;
  }


  /**
   * Setter of the size
   */
  public void setCurrentpageSizeToLoad(int currentpageSizeToLoad) {
    size = currentpageSizeToLoad;
  }


  /**
   * Getter of the field page
   *
   * @return the page
   */
  public int getCurrentPageNumberToLoad() {
    return page;
  }


  /**
   * Getter of the field page
   *
   * @return the page
   */
  public int getPage() {

    return page;
  }


  /**
   * Setter of the page
   */
  public void setPage(int page) {
    this.page = page;
  }


  /**
   * Getter of the field size
   */
  public int getSize() {
    return size;
  }


  /**
   * Setter of the size
   *
   */
  public void setSize(int size) {
    this.size = size;
  }


  /**
   * Getter of the field sort
   */
  public List<Sort> getSort() {
    return sort;
  }


  /**
   * Setter of the sort
   */
  public void setSort(List<Sort> sort) {
    this.sort = sort;
  }


  /**
   * @return
   */
  public static PageRequest buildFrom(int page, int pagesize, List<Sort> sorts, String defaultSort) {
    PageRequest pageRequest = null;
    if (sorts == null || sorts.size() == 0) {
      pageRequest = new PageRequest(page, pagesize, Arrays.asList(new Sort(Direction.ASC, defaultSort)));
    }
    else {
      pageRequest = new PageRequest(page, pagesize, sorts);
    }

    return pageRequest;
  }


  /**
   * 
   * @author vonji
   *
   */
  public static class Builder {

    private int						page;
    private int						size;
    private List<Sort>				sort;
    private Map<String, String>		params = new HashMap<>();


    public Builder param(String key, String value) {
      params.put(key, value);
      return this;
    }


    public Builder page(int page) {
      if (page == -1) {
        this.page = 0;
      }
      else {
        this.page = page;
      }
      return this;
    }


    public Builder size(int size) {
      if (size < 1) {
        this.size = Integer.MAX_VALUE;
      }
      else {
        this.size = size;
      }
      return this;
    }


    public Builder sort(List<Sort> sort) {
      this.sort = sort;
      return this;
    }


    public PageRequest build() {
      return new PageRequest(this);
    }
  }


  private PageRequest(Builder builder) {
    this.page = builder.page;
    this.size = builder.size;
    //    this.orders = builder.orders;
    //    this.predicate = builder.predicate;
    //    this.select = builder.select;
    this.sort = builder.sort;
    this.params = builder.params;
  }


  /**
   * Get the predicate string value
   */
  public Object getPredicate() {
    return predicate;
  }


  public void setPredicate(Object predicate) {
    this.predicate = predicate;
  }


  @Override
  public String toString() {
    return "PageRequest [page=" + page + ", size=" + size + ", params=" + params + ", sort=" + sort
        + ", predicate=" + predicate + "]";
  }
}
