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


import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.OperationMessage;
import io.github.jsoagger.core.bridge.result.SingleResult;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public interface IOperationResult extends Serializable {

    public final static String pageElements = "pageElements";
    public final static String pageNumber = "pageNumber";
    public final static String page = "page";
    public final static String hasNextPage = "hasNextPage";
    public final static String totalPages = "totalPages";
    public final static String pageSize = "pageSize";
    public final static String hasPreviousPage = "hasPreviousPage";
    public final static String totalElements = "totalElements";

    public final static String ERROR = "error";
    public final static String SUCCESS = "success";
    public final static String ERREUR_INTERNE = "Erreur interne";
    public final static String RUNTIME = "RUNTIME";
    public final static String VOTRE_DEMANDE_N_A_PAS_PAS_PU_ÊTRE_TRAITÉE = "Votre demande n'a pas pas pu être traitée";

    public static IOperationResult emptyPaginatedData() {
        IOperationResult result = new MultipleResult.Builder()
                .addMeta("total-pages", 0)
                .addMeta("total-elements", 0)
                .addMeta("current-page", 0)
                .addMeta("current-elements", 0).build();
        return result;
    }

    public static IOperationResult emptyBasicResult() {
        IOperationResult result = new SingleResult();
        result.setData(new OperationData.Builder().build());
        result.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return result;
    }


    public static IOperationResult basicResultSuccess() {
        IOperationResult result = new SingleResult();
        result.setData(new OperationData.Builder().build());
        result.setStatus(SUCCESS);
        result.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return result;
    }

    public static IOperationResult basicResultError(String errorCode, String mainMessage, String messageDescription) {
        IOperationResult result = new SingleResult();
        result.setData(new OperationData.Builder().build());

        result.setStatus(ERROR);
        result.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        result.setMessage(mainMessage);
        result.setDescription(messageDescription);
        result.setErrorCode(errorCode);
        return result;
    }

    public static IOperationResult basicResultRuntimeError() {
        IOperationResult result = new SingleResult();
        result.setStatus(ERROR);
        result.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        result.setMessage(ERREUR_INTERNE);
        result.setDescription(VOTRE_DEMANDE_N_A_PAS_PAS_PU_ÊTRE_TRAITÉE);
        result.setErrorCode(RUNTIME);
        return result;
    }


    public static IOperationResult listResultSuccess() {
        IOperationResult result = new MultipleResult();
        result.setData(new OperationData.Builder().build());
        result.setStatus(SUCCESS);
        result.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        return result;
    }

    public static IOperationResult listResultError(String errorCode, String mainMessage, String messageDescription) {
        IOperationResult result = new MultipleResult();
        result.setData(new OperationData.Builder().build());

        result.setStatus(ERROR);
        result.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        result.setMessage(mainMessage);
        result.setDescription(messageDescription);
        result.setErrorCode(errorCode);
        return result;
    }

    public static IOperationResult listResultRuntimeError() {
        IOperationResult result = new MultipleResult();
        result.setStatus(ERROR);
        result.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        result.setMessage("Erreur interne");
        result.setDescription(VOTRE_DEMANDE_N_A_PAS_PAS_PU_ÊTRE_TRAITÉE);
        result.setErrorCode("RUNTIME");
        return result;
    }


    String getTimestamp() ;

    void setTimestamp(final String timestamp) ;

    void setMetaData(Map<String, Object> meta);

    void addMetaData(String key, Object value);

    Object rootData();

    Map<String, Object> getMetaData();

    boolean hasMessage();

    default boolean hasBusinessError() {
        return hasMessage() == Boolean.TRUE;
    }

    List<OperationMessage> getMessages();

    void setMessages(List<OperationMessage> messages);

    void setData(Object data);

    void addMessage(OperationMessage message);


    String getStatus();

    void setStatus(final String status) ;

    String getErrorCode() ;

    void setErrorCode(final String errorCode) ;

    String getMessage() ;

    void setMessage(final String message) ;

    String getDescription() ;

    void setDescription(final String description) ;
}
