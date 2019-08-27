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

package io.github.jsoagger.core.bridge.operation;




import java.io.Serializable;
import java.util.List;
import java.util.Map;

import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.OperationMessage;
import io.github.jsoagger.core.bridge.result.OperationMessage.OperationMessageBuilder;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IOperationResult extends Serializable {

  public final static String	pageElements	= "pageElements";
  public final static String	pageNumber		= "pageNumber";
  public final static String	page			= "page";
  public final static String	hasNextPage		= "hasNextPage";
  public final static String	totalPages		= "totalPages";
  public final static String	pageSize		= "pageSize";
  public final static String	hasPreviousPage	= "hasPreviousPage";
  public final static String	totalElements	= "totalElements";

  /**
   * @return {@link IOperationResult}
   */
  public static IOperationResult emptyPaginatedData() {
    IOperationResult result = new MultipleResult.Builder().addMeta("total-pages", 0).addMeta("total-elements", 2).addMeta("current-page", 0).addMeta("current-elements", 0).build();
    return result;
  }

  /**
   * @return {@link IOperationResult}
   */
  public static IOperationResult emptyData() {
    IOperationResult result = new SingleResult();
    result.setData(new OperationData.Builder().build());
    return result;
  }


  /**
   * @return {@link IOperationResult}
   */
  public static IOperationResult emptyMultipleResult() {
    IOperationResult result = new MultipleResult();
    result.setData(new OperationData.Builder().build());
    return result;
  }


  /**
   * @return
   */
  public static IOperationResult getGeneralSingleResultError() {
    IOperationResult result = new SingleResult();
    OperationMessage message = new OperationMessageBuilder().title("Network error").detail("You request can not be processed due to unknown error.Please contact your administrator.").build();
    result.addMessage(message);
    return result;
  }


  /**
   * @return
   */
  public static IOperationResult generalMultipleResutError() {
    IOperationResult result = new MultipleResult();
    OperationMessage message = new OperationMessageBuilder().title("Network error").detail("You request can not be processed due to unknown error.Please contact your administrator.").build();
    result.addMessage(message);
    return result;
  }


  /**
   * @return
   */
  public static IOperationResult getNetworkError() {
    IOperationResult result = new SingleResult();
    OperationMessage message = new OperationMessageBuilder().title("Network error").detail("You request can not be processed due to network error.").build();
    result.addMessage(message);
    return result;
  }


  /**
   * @param metaData
   */
  public void setMetaData(Map<String, Object> meta);


  /**
   * @param key
   * @param value
   */
  public void addMetaData(String key, Object value);


  /**
   * @return
   */
  public Object rootData();


  /**
   * @return
   */
  public Map<String, Object> getMetaData();


  /**
   * Return true if there is at least one message to display.
   *
   * @return
   */
  public boolean hasMessage();


  /**
   *
   * @return
   */
  public default boolean hasBusinessError() {
    return hasMessage() == Boolean.TRUE;
  }


  /**
   * @return
   */
  public List<OperationMessage> getMessages();


  /**
   * @return
   */
  public void setMessages(List<OperationMessage> messages);


  /**
   * @param data
   */
  public void setData(Object data);


  /**
   * @param message
   */
  public void addMessage(OperationMessage message);
}
