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
import java.util.Collections;
import java.util.List;

public class PageResult implements Serializable {

  private static final long serialVersionUID = 697918630960569774L;
  public static PageResult DEFAULT;

  static {
    List defaultresult = new ArrayList<>();
    DEFAULT = new PageResult(defaultresult);
  }

  private List content = new ArrayList();
  private long total;
  private int totalPages;
  private boolean hasPreviousPage;
  private boolean isFirstPage;
  private boolean hasNextPage;
  private boolean isLastPage;
  private int pageNumber;
  private PageRequest sourceRequest;


  /**
   * Constructor
   *
   * @param content
   * @param pageRequest
   * @param total
   */
  public PageResult(List content, PageRequest pageRequest, long total) {
    if (null == content) {
      throw new IllegalArgumentException("Content must not be null!");
    }

    this.content = content;
    this.total = total;
    this.sourceRequest = pageRequest;
    if (pageRequest != null) {
      pageNumber = pageRequest.getPage();
      totalPages = pageRequest.getSize() == 0 ? 1 : (int) Math.ceil(total / (double) pageRequest.getSize());
    } else {
      totalPages = 0;
      pageNumber = 0;
    }

    hasPreviousPage = (pageRequest == null ? 0 : pageRequest.getPage()) > 0;
    isFirstPage = !hasPreviousPage;
    hasNextPage = (pageRequest == null ? 0 : pageRequest.getPage()) + 1 < totalPages;
    isLastPage = !hasNextPage;

  }


  /**
   *
   * Constructor
   *
   * @param content
   */
  public PageResult(List content) {
    this(content, null, null == content ? 0 : content.size());
  }


  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Page#getNumberOfElements()
   */
  public int getNumberOfElements() {
    return content.size();
  }


  /*
   * (non-Javadoc)
   *
   * @see org.springframework.data.domain.Page#getTotalElements()
   */
  public long getTotalElements() {
    return total;
  }


  /**
   *
   * @return
   */
  public PageRequest sourceRequest() {
    return sourceRequest;
  }


  /**
   * @return
   */
  @SuppressWarnings("unchecked")
  public List getContent() {
    if (content == null) {
      return new ArrayList<>();
    }

    return Collections.unmodifiableList(content);
  }


  /**
   * @return the total
   */
  public long getTotal() {
    return total;
  }


  /**
   * @param total the total to set
   */
  public void setTotal(long total) {
    this.total = total;
  }


  /**
   * @return the totalPages
   */
  public int getTotalPages() {
    return totalPages;
  }


  /**
   * @param totalPages the totalPages to set
   */
  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }


  /**
   * @return the hasPreviousPage
   */
  public boolean getHasPreviousPage() {
    return hasPreviousPage;
  }


  /**
   * @param hasPreviousPage the hasPreviousPage to set
   */
  public void setHasPreviousPage(boolean hasPreviousPage) {
    this.hasPreviousPage = hasPreviousPage;
  }


  /**
   * @return the isFirstPage
   */
  public boolean getIsFirstPage() {
    return isFirstPage;
  }


  /**
   * @param isFirstPage the isFirstPage to set
   */
  public void setIsFirstPage(boolean isFirstPage) {
    this.isFirstPage = isFirstPage;
  }


  /**
   * @return the hasNextPage
   */
  public boolean getHasNextPage() {
    return hasNextPage;
  }


  /**
   * @param hasNextPage the hasNextPage to set
   */
  public void setHasNextPage(boolean hasNextPage) {
    this.hasNextPage = hasNextPage;
  }


  /**
   * @return the isLastPage
   */
  public boolean getIsLastPage() {
    return isLastPage;
  }


  /**
   * @param isLastPage the isLastPage to set
   */
  public void setIsLastPage(boolean isLastPage) {
    this.isLastPage = isLastPage;
  }


  /**
   * @param content the content to set
   */
  public void setContent(List content) {
    this.content = content;
  }


  /**
   * @return the pageNumber
   */
  public int getPageNumber() {
    return pageNumber;
  }


  /**
   * @param pageNumber the pageNumber to set
   */
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }


  public boolean isNotEmpty() {
    return content != null && !content.isEmpty();
  }


  public boolean isEmpty() {
    return content == null || content.isEmpty();
  }


  public PageRequest getNextPageRequest() {

    if (hasNextPage) {
      PageRequest pageRequest = new PageRequest();
      pageRequest.setPage(sourceRequest.getPage() + 1);
      pageRequest.setSize(sourceRequest.getSize());
      return pageRequest;
    }

    return null;
  }


  public PageRequest getPreviousPageRequest() {
    if (hasPreviousPage) {
      PageRequest pageRequest = new PageRequest();
      pageRequest.setPage(sourceRequest.getPage() - 1);
      pageRequest.setSize(sourceRequest.getSize());
      return pageRequest;
    }

    return null;
  }


  /**
   *
   * @param pageRequest
   */
  public void setPageRequest(PageRequest pageRequest) {
    this.sourceRequest = pageRequest;
  }


  @Override
  public String toString() {
    return "PageResult [total=" + total + ", totalPages=" + totalPages + ", hasPreviousPage="
        + hasPreviousPage + ", isFirstPage=" + isFirstPage + ", hasNextPage=" + hasNextPage
        + ", isLastPage=" + isLastPage + ", pageNumber=" + pageNumber + ", sourceRequest="
        + sourceRequest + "]";
  }
}
