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


package io.github.jsoagger.core.utils;


import io.github.jsoagger.core.utils.exception.RestException;

import java.util.Base64;

public class ObjectIdentifierUtils {

    private ObjectIdentifierUtils() {
        // utility not public constructor
    }

    public static Long getBase64Id(String base64Id) {
        String id = decode(base64Id);
        return getId(id);
    }

    public static String encode(String fullIdentifier) {
        String fullId = Base64.getEncoder().encodeToString(fullIdentifier.getBytes());
        return fullId;
    }

    public static String decode(String fullIdentifier) {
        String fullId = new String(Base64.getDecoder().decode(fullIdentifier.getBytes()));
        return fullId;
    }


    public static Long getId(String identifier) {
        try {
            return Long.valueOf(identifier.split(":")[0]);
        } catch (Exception e) {
        }

        return -1L;
    }


    public static Class getDomainClassOf(String identifier) {
        try {
            String className = identifier.split(":")[1];
            return Class.forName(className);
        } catch (Exception e) {
            throw new RestException("R001");
        }
    }


    public static boolean isValid(String fullIdentifier) {
        if (fullIdentifier == null) {
            return false;
        }

        if (fullIdentifier.split(":").length != 2) {
            return false;
        }

        try {
            String className = fullIdentifier.split(":")[1];
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try {
            Long.valueOf(fullIdentifier.split(":")[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static Long getVersion(String fullIdentifier) {
        if (fullIdentifier == null) {
            return 0L;
        }

        if (fullIdentifier.split(":").length != 3) {
            return 0L;
        }

        return Long.valueOf(fullIdentifier.split(":")[2]);
    }


    public static String partialId(Class clazz) {
        return ":" + clazz.getCanonicalName();
    }
}
