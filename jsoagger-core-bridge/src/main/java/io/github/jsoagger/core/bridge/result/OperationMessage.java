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

import java.util.Date;

import com.google.gson.Gson;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class OperationMessage {

  private String title;
  private String detail;
  protected Date timestamp = new Date();
  private String code = OperationErrorStatus.TECHNICAL_ERROR.toString();


  /**
   * Constructor
   */
  public OperationMessage() {
  }


  /**
   * @{inheritedDoc}
   */

  public String getCode() {
    return code;
  }


  /**
   * @{inheritedDoc}
   */

  public String getTitle() {
    return title;
  }


  /**
   * Setter of title
   *
   * @param title
   *            the title to set
   */

  public void setTitle(String title) {
    this.title = title;
  }


  /**
   * Getter of detail
   *
   * @return the detail
   */

  public String getDetail() {
    return detail;
  }


  /**
   * Setter of detail
   *
   * @param detail
   *            the detail to set
   */

  public void setDetail(String detail) {
    this.detail = detail;
  }


  /**
   * Setter of code
   *
   * @param code
   *            the code to set
   */

  public void setCode(String code) {
    this.code = code;
  }



  /**
   * @{inheritedDoc}
   */

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + ((detail == null) ? 0 : detail.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    return result;
  }


  /**
   * @{inheritedDoc}
   */

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    OperationMessageBuilder other = (OperationMessageBuilder) obj;
    if (code == null) {
      if (other.code != null) return false;
    }
    else if (!code.equals(other.code)) return false;
    if (detail == null) {
      if (other.detail != null) return false;
    }
    else if (!detail.equals(other.detail)) return false;
    if (title == null) {
      if (other.title != null) return false;
    }
    else if (!title.equals(other.title)) return false;
    return true;
  }

  /**
   * @author Ramilafananana VONJISOA
   * @mailto yvonjisoa@nexitia.com
   * @date 2019
   */
  public static class OperationMessageBuilder {

    private String	title;
    private String	detail;
    private String	code;


    public OperationMessageBuilder title(String title) {
      this.title = title;
      return this;
    }


    public OperationMessageBuilder detail(String detail) {
      this.detail = detail;
      return this;
    }


    public OperationMessageBuilder code(String code) {
      this.code = code;
      return this;
    }


    public OperationMessage build() {
      return new OperationMessage(this);
    }
  }


  /**
   * Constructor
   *
   * @param builder
   */
  private OperationMessage(OperationMessageBuilder builder) {
    this.title = builder.title;
    this.detail = builder.detail;
    this.code = builder.code;
  }


  public Date getTimestamp() {
    return timestamp;
  }


  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public String toString() {
    Gson gson = new Gson();
    return gson.toJson(this);
  }
}
