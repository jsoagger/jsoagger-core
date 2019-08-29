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


package io.github.jsoagger.core.utils.exception;



public class VLLifecyclePreviousStateNotFoundException extends Exception {

   /**
    *
    */
   private static final long serialVersionUID = 7318422669507787704L;

   /**
    * Constructor of VLLifecyclePreviousStateNotFoundException.java
    */
   public VLLifecyclePreviousStateNotFoundException() {
   }

   /**
    * Constructor of VLLifecyclePreviousStateNotFoundException.java
    */
   public VLLifecyclePreviousStateNotFoundException(String message) {
      super(message);
   }

   /**
    * Constructor of VLLifecyclePreviousStateNotFoundException.java
    */
   public VLLifecyclePreviousStateNotFoundException(Throwable cause) {
      super(cause);
   }

   /**
    * Constructor of VLLifecyclePreviousStateNotFoundException.java
    */
   public VLLifecyclePreviousStateNotFoundException(String message, Throwable cause) {
      super(message, cause);
   }

   /**
    * Constructor of VLLifecyclePreviousStateNotFoundException.java
    */
   public VLLifecyclePreviousStateNotFoundException(String message, Throwable cause,
         boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }
}
