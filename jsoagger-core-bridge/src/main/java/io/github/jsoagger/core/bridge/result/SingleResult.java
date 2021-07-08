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

import java.util.HashMap;
import java.util.Map;

public class SingleResult extends OperationResult {

    private static final long serialVersionUID = -2152659463428448410L;

    protected OperationData data = new OperationData();

    public SingleResult() {
    }

    @Override
    public void setData(Object data) {
        this.data = (OperationData) data;
    }


    @Override
    public Object rootData() {
        return data;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SingleResult [");
        if (data != null) {
            builder.append("data=");
            builder.append(data);
        }
        builder.append("]");
        return builder.toString();
    }

    public static class Builder {

        private OperationData data;
        private Map<String, Object> meta = null;


        public Builder data(OperationData data) {
            this.data = data;
            return this;
        }


        public Builder addMessage(OperationMessage message) {
            addMessage(message);
            return this;
        }


        public SingleResult build() {
            return new SingleResult(this);
        }


        public Builder addMeta(String key, Object value) {
            if (this.meta == null) {
                this.meta = new HashMap<>();
            }

            this.meta.put(key, value);
            return this;
        }
    }


    private SingleResult(Builder builder) {
        this.data = builder.data;
        this.metaData = builder.meta;
    }


    public OperationData getData() {
        return data;
    }
}
