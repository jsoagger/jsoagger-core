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
import java.util.List;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class OperationError {

  protected String httpStatus;
  protected String code;
  protected List<OperationMessage> messages = new ArrayList<>();


  /**
   */
  public OperationError() {
  }


  public String getHttpStatus() {
    return httpStatus;
  }


  public void setHttpStatus(String httpStatus) {
    this.httpStatus = httpStatus;
  }


  public String getCode() {
    return code;
  }


  public void setCode(String code) {
    this.code = code;
  }


  public List<OperationMessage> getMessages() {
    return messages;
  }


  public void setMessages(List<OperationMessage> messages) {
    this.messages = messages;
  }

  /**
   * @author Ramilafananana VONJISOA
   * @mailto yvonjisoa@nexitia.com
   * @date 2019
   */
  public static class Builder {

    private String					httpStatus;
    private String					code;
    private List<OperationMessage>	messages;


    public Builder httpStatus(String httpStatus) {
      this.httpStatus = httpStatus;
      return this;
    }


    public Builder code(String code) {
      this.code = code;
      return this;
    }


    public Builder messages(List<OperationMessage> messages) {
      this.messages = messages;
      return this;
    }


    /**
     * @param message
     */
    public Builder addMessage(OperationMessage message) {
      if (this.messages == null) {
        this.messages = new ArrayList<>();
      }

      this.messages.add(message);
      return this;
    }


    public OperationError build() {
      return new OperationError(this);
    }
  }


  /**
   * Constructor
   *
   * @param builder
   */
  private OperationError(Builder builder) {
    this.httpStatus = builder.httpStatus;
    this.code = builder.code;
    this.messages = builder.messages;
  }
}
