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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class JsonUtils {

  private static final String	MESSAGES	= "messages";
  private static final String	META		= "metaData";
  private static final String	ERROR		= "error";
  public static String		EMPTY_JSON	= "{}";


  public  static <T>  T toJsonObject(String jsonString, Class<T> clazz) {
    if(jsonString == null || "".equals(jsonString.trim())) {
      try {
        return clazz.newInstance();
      } catch (InstantiationException | IllegalAccessException e) {
        e.printStackTrace();
      }
    }

    Gson gson = new Gson();
    T o = gson.fromJson(jsonString, clazz);
    if(isASingleResult(o)) {
      flattenAttributes(((SingleResult) o).getData());
    }
    else if(isAMultipleResult(o)) {
      MultipleResult r = (MultipleResult) o;
      if(r.getData().size() > 0) {
        for(OperationData d : r.getData()) {
          flattenAttributes(d);
        }
      }
    }
    else {

    }

    return o;
  }


  /**
   * Convert a formatted json string to {@link JsonObject}.
   * <p>
   * Remark: jsonObject implements {@link Map}
   *
   * @param jsonString
   * @return JsonObject
   */
  public static JsonObject toJsonObject(String jsonString) {
    if (jsonString == null || jsonString.trim().length() == 0 || "null".equalsIgnoreCase(jsonString)) {
      return new JsonObject();
    }

    if(jsonString.contains("Internal Server Error")) {
      JsonObject o = new JsonObject();
      o.addProperty("Error", "Internal Server Error");
      o.addProperty("error", true);
      return o;
    }

    Gson gson = new Gson();

    final Map classMap = new HashMap<>();
    classMap.put("workInfo", JsonElement.class);
    classMap.put("iterationInfo", JsonElement.class);
    classMap.put("versionInfo", JsonElement.class);
    classMap.put("owner", JsonElement.class);
    classMap.put("links", JsonElement.class);
    classMap.put("lifecyleInfo", JsonElement.class);
    classMap.put("softAttributeColumns", JsonElement.class);
    classMap.put("attributes", JsonElement.class);
    classMap.put("container", JsonObject.class);
    classMap.put("user", JsonObject.class);
    classMap.put("account", JsonObject.class);
    return gson.fromJson(jsonString, JsonObject.class);
  }


  /**
   * Convert an {@link HashMap} to {@link JsonObject}.
   * <p>
   * Remark: jsonObject implements {@link Map}
   *
   * @param jsonString
   * @return JsonObject
   */
  public static JsonObject toJsonObject(Map map) {
    if (map == null || map.isEmpty()) {
      return new JsonObject();
    }

    Gson gson = new Gson();
    JsonElement elem = gson.toJsonTree(map);
    return elem.getAsJsonObject();
  }

  /**
   * JsonObject To map
   *
   * @param result
   * @return
   */
  public static Map toMap(JsonObject result) {
    Gson gson = new Gson();
    Type type = new TypeToken<Map<String, Object>>(){}.getType();
    return gson.fromJson(result, type);
  }



  /**
   * @param jsonString
   * @return JsonObject
   */
  public static IOperationResult toOperationResult(JsonObject result, Class clazz) {
    final IOperationResult operationResult = getResultInstance(clazz);
    Gson gson = new Gson();

    if (!result.get("data").isJsonNull()) {
      final String dataString = toString(result.get("data"));
      final JsonObject dataObject = toJsonObject(dataString);

      if (dataObject instanceof JsonObject) {
        //final JsonConfig config = new JsonConfig();
        final Map classMap = new HashMap<>();
        classMap.put("persistenceInfo", JsonObject.class);
        classMap.put("workInfo", JsonObject.class);
        classMap.put("iterationInfo", JsonObject.class);
        classMap.put("versionInfo", JsonObject.class);
        classMap.put("owner", JsonObject.class);
        classMap.put("lifecyleInfo", JsonObject.class);
        classMap.put("softAttributeColumns", JsonObject.class);
        classMap.put("attributes", JsonObject.class);
        //config.setClassMap(classMap);
        //operationResult.setData(JsonObject.toBean((JsonObject) dataObject, OperationData.class, classMap));
        operationResult.setData(gson.fromJson(dataString, OperationData.class));
        //JsonObject.toBean((JsonObject) dataObject, OperationData.class, classMap));
      }
      else {
        /*if (dataObject.isArray()) {
            final JSONArray array = (JSONArray) dataObject;
            final List<OperationData> datas = new ArrayList<>();

            final JsonConfig config = new JsonConfig();
            final Map classMap = new HashMap<>();
            classMap.put("workInfo", JsonElement.class);
            classMap.put("iterationInfo", JsonElement.class);
            classMap.put("versionInfo", JsonElement.class);
            classMap.put("owner", JsonElement.class);
            classMap.put("lifecyleInfo", JsonElement.class);
            classMap.put("softAttributeColumns", JsonElement.class);
            classMap.put("attributes", JsonElement.class);
            config.setClassMap(classMap);

            for (int i = 0; i < array.size(); i++) {
              final String json = array.getString(i);
              final JsonElement o = JsonUtils.toJsonObject(json);
              final OperationData opData = (OperationData) JsonObject.toBean(o, OperationData.class, classMap);
              datas.add(opData);
            }

            operationResult.setData(datas);
        }*/
      }
    }

    // metaData is present woth both error or data
    final Map meta = getMeta(result);
    if (meta != null) {
      operationResult.setMetaData(meta);
    }

    return operationResult;
  }


  /**
   * @param clazz
   * @return
   */
  private static IOperationResult getResultInstance(Class clazz) {
    try {
      final Constructor<?> entryLinkClazzConstructor = clazz.getConstructors()[0];
      final IOperationResult operationResult = (IOperationResult) entryLinkClazzConstructor.newInstance();
      return operationResult;
    }
    catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return null;
  }


  /**
   * A {@link JsonObject} result is an error result if the error section is
   * not null.
   *
   * @param result
   * @return
   */
  private static boolean isErrorResult(JsonObject result) {
    return result.get("messages") != null
        && !result.get("messages").isJsonNull()
        && result.get("messages").getAsJsonArray().size() > 0;
  }


  private static Map getMeta(JsonObject result) {
    try {
      if (result.get(META) != null && !result.get(META).isJsonNull()) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        final JsonElement meta = result.get(META);
        return gson.fromJson(meta, type);
      }
    }
    catch (final IllegalArgumentException e) {
      e.printStackTrace();
    }

    return null;
  }


  /**
   * Convert the given object into a json string.
   *
   * @param object
   * @return
   */
  public static String toString(Object object) {
    Gson gson = new Gson();
    return gson.toJson(object);
  }


  public static int getJsonInt(JsonObject jo, String name) {
    return getJsonInt(jo, name, -1);
  }



  public static int getJsonInt(JsonObject jo, String name, int defVal) {
    if(jo.get(name) != null && !jo.get(name).isJsonNull()) {
      return jo.get(name).getAsInt();
    }

    return defVal;
  }

  public static String getJsonString(JsonObject jo, String name) {
    return getJsonString(jo, name, null);
  }

  public static String getJsonString(JsonObject jo, String name, String defVal) {
    if(jo.get(name) != null && !jo.get(name).isJsonNull()) {
      return jo.get(name).getAsString();
    }

    return defVal;
  }

  private static boolean isASingleResult(Object o) {
    if(o instanceof SingleResult) {
      return true;
    }
    return false;
  }

  private static boolean isAMultipleResult(Object o) {
    if(o instanceof MultipleResult) {
      return true;
    }
    return false;
  }

  private static void flattenAttributes(OperationData data) {
    // flatten core attributes
    if(data != null) {
      Map<String, Object> attr = data.getAttributes();
      Map<String, Object> initial = new HashMap<>();

      for(String s: attr.keySet()) {
        Object current = attr.get(s);
        if(current instanceof AbstractMap) {
          flattenTreeMap(s, (AbstractMap) current, initial);
        }
      }
      attr.putAll(initial);

      // fatten master attributes
      Map<String, Object> mastattr = data.getMasterAttributes();
      initial = new HashMap<>();

      for(String s: mastattr.keySet()) {
        Object current = mastattr.get(s);
        String mstkey = "masterAttributes.".concat(s);
        if(current instanceof AbstractMap) {
          flattenTreeMap(mstkey, (AbstractMap) current, initial);
        }
        else {
          initial.put(mstkey, current);
        }
      }
      // flatten it into attributes
      attr.putAll(initial);
    }
  }

  private static void flattenTreeMap(String key, AbstractMap map, Map initial) {
    // single value
    if(map.size() > 0) {
      for(Object s: map.keySet()) {
        String kk = (String) s;
        Object val = map.get(s);
        String nkey = key.concat(".").concat(kk);

        if(val instanceof AbstractMap) {
          flattenTreeMap(nkey, (AbstractMap) val, initial);
        }

        initial.put(nkey, val);
        //System.out.println("--> " + nkey + " : " + val);
      }
    }
  }
}


