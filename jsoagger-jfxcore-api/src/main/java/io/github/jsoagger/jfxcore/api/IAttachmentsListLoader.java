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
import java.util.List;

/**
 * @author Administrator
 *
 */
public interface IAttachmentsListLoader {

  /**
   * Download content asssocited to source object. If object has multiple source, a zip file must be
   * returned.
   * 
   * @param source
   * @return
   * @throws IOException
   */
  String getAttachments(Object source) throws IOException;


  /**
   * @param rootForm
   * @return
   */
  List<String> getAttachmentsName(Object rootForm);


  /**
   * Get the content clob of the attachment with given name. If name is null, loads the unique
   * attachment of the given object or the primary.
   * 
   * @param rootForm
   * @param attachment
   * @throws IOException
   */
  String getAttachment(Object source, String attachment) throws IOException;


  /**
   * Get the primary attachment if the object has multiple attachments.
   * <p>
   * The unique attachment of the object has only one.
   * 
   * @param source
   * @return
   * @throws IOException
   */
  default String getPrimaryAttachment(Object source) throws IOException {
    return getAttachment(source, null);
  }
}
