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


import io.github.jsoagger.core.bridge.operation.IOperationResult;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class OperationResult implements Serializable, IOperationResult {

    private static final long serialVersionUID = -2152659463428448410L;

    protected Map<String, Object> links = new HashMap<>();
    protected Map<String, Object> metaData = new HashMap<>();

    // error messages only
    protected List<OperationMessage> messages = new ArrayList<>();
    private String status;
    private String timestamp;
    private String errorFamily;
    private String errorCode;
    private String message;

    public OperationResult() {
        super();
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(final String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(final String status) {
        this.status = status;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public Map<String, Object> getMetaData() {
        return metaData;
    }


    @Override
    public void addMetaData(String key, Object value) {
        if (metaData == null) {
            metaData = new HashMap<>();
        }
        metaData.put(key, value);
    }


    @Override
    public void addMessage(OperationMessage message) {
        if (messages == null) messages = new ArrayList<>();
        messages.add(message);
    }


    public Map<String, Object> getLinks() {
        return links;
    }


    public void setLinks(Map<String, Object> links) {
        this.links = links;
    }


    @Override
    public void setMetaData(Map<String, Object> meta) {
        this.metaData = meta;
    }


    @Override
    public List<OperationMessage> getMessages() {
        if (!hasMessage()) {
            return new ArrayList<>();
        }
        return messages;
    }


    @Override
    public void setMessages(List<OperationMessage> messages) {
        if (messages != null) {
            this.messages.clear();
            this.messages.addAll(messages);
        }
    }


    @Override
    public boolean hasMessage() {
        return (messages != null) && (messages.size() > 0);
    }

    public String _getFirstErrorMessage() {
        if (getMessages().size() > 0) {
            OperationMessage msg = getMessages().get(0);
            return msg.getDetail();
        }
        return null;
    }


    @Override
    public boolean hasBusinessError() {
        return IOperationResult.super.hasBusinessError();
    }

    public int getMetaDataIntValue(String key) {
        try {
            int t = Double.valueOf(String.valueOf(getMetaData().get(key))).intValue();
            return t;
        } catch (Exception e) {
            return -1;
        }
    }

    public String getErrorFamily() {
        return errorFamily;
    }

    public void setErrorFamily(final String errorFamily) {
        this.errorFamily = errorFamily;
    }
}
