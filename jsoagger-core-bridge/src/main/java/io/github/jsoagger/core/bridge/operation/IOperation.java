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


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import com.google.gson.JsonObject;


/**
 * Used to make data fetching from local or remote server. Parameters are
 * transmitted in json format.
 *
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IOperation {

  public static ExecutorService service = Executors
      .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  /**
   * @return
   */
  public default IOperationResult getResult() {
    return null;
  }

  /**
   * Do the operation from json string a paramters.
   *
   * @param params
   * @param resultHandler
   */
  public default void doOperation(String jsonString,
      Consumer<IOperationResult> resultHandler) {
    doOperation(JsonUtils.toJsonObject(jsonString), resultHandler, null);
  }


  /**
   * Do the operation from json string a paramters.
   *
   * @param params
   * @param resultHandler
   */
  public default void doOperation(String jsonString,
      Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {
    doOperation(JsonUtils.toJsonObject(jsonString), resultHandler, exHandler);
  }


  /**
   * Do the operation without exceptionhandler.
   *
   * @param params
   * @param
   */
  public default void doOperation(JsonObject params,
      Consumer<IOperationResult> resultHandler) {
    doOperation(params, resultHandler, null);
  }


  /**
   * @param controller
   * @param params
   * @param resultHandler
   * @param exHandler
   * @return
   */
  public void doOperation(JsonObject params,
      Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler);
}
