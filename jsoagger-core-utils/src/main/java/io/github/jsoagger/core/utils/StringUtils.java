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




import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

@SuppressWarnings({"unchecked", "rawtypes"})
public class StringUtils {
  private static final String FOLDER_SEPARATOR = "/";

  private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

  private static final String TOP_PATH = "..";

  private static final String CURRENT_PATH = ".";

  private static final char EXTENSION_SEPARATOR = '.';

  // ---------------------------------------------------------------------
  // General convenience methods for working with Strings
  // ---------------------------------------------------------------------

  public static boolean hasLength(String str) {
    return ((str != null) && (str.length() > 0));
  }

  public static boolean hasText(String str) {

    if (!StringUtils.hasLength(str)) {
      return false;
    }
    int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  public static boolean containsWhitespace(String str) {

    if (!StringUtils.hasLength(str)) {
      return false;
    }
    int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  public static String trimWhitespace(String str) {

    if (!StringUtils.hasLength(str)) {
      return str;
    }
    StringBuffer buf = new StringBuffer(str);
    while ((buf.length() > 0) && Character.isWhitespace(buf.charAt(0))) {
      buf.deleteCharAt(0);
    }
    while ((buf.length() > 0) && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
      buf.deleteCharAt(buf.length() - 1);
    }
    return buf.toString();
  }

  public static String trimLeadingWhitespace(String str) {

    if (!StringUtils.hasLength(str)) {
      return str;
    }
    StringBuffer buf = new StringBuffer(str);
    while ((buf.length() > 0) && Character.isWhitespace(buf.charAt(0))) {
      buf.deleteCharAt(0);
    }
    return buf.toString();
  }

  public static String trimTrailingWhitespace(String str) {

    if (!StringUtils.hasLength(str)) {
      return str;
    }
    StringBuffer buf = new StringBuffer(str);
    while ((buf.length() > 0) && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
      buf.deleteCharAt(buf.length() - 1);
    }
    return buf.toString();
  }

  public static String trimAllWhitespace(String str) {

    if (!StringUtils.hasLength(str)) {
      return str;
    }
    StringBuffer buf = new StringBuffer(str);
    int index = 0;
    while (buf.length() > index) {
      if (Character.isWhitespace(buf.charAt(index))) {
        buf.deleteCharAt(index);
      } else {
        index++;
      }
    }
    return buf.toString();
  }

  public static boolean startsWithIgnoreCase(String str, String prefix) {

    if ((str == null) || (prefix == null)) {
      return false;
    }
    if (str.startsWith(prefix)) {
      return true;
    }
    if (str.length() < prefix.length()) {
      return false;
    }
    String lcStr = str.substring(0, prefix.length()).toLowerCase();
    String lcPrefix = prefix.toLowerCase();
    return lcStr.equals(lcPrefix);
  }

  public static boolean endsWithIgnoreCase(String str, String suffix) {

    if ((str == null) || (suffix == null)) {
      return false;
    }
    if (str.endsWith(suffix)) {
      return true;
    }
    if (str.length() < suffix.length()) {
      return false;
    }

    String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
    String lcSuffix = suffix.toLowerCase();
    return lcStr.equals(lcSuffix);
  }
  public static int countOccurrencesOf(String str, String sub) {

    if ((str == null) || (sub == null) || (str.length() == 0) || (sub.length() == 0)) {
      return 0;
    }
    int count = 0, pos = 0, idx = 0;
    while ((idx = str.indexOf(sub, pos)) != -1) {
      ++count;
      pos = idx + sub.length();
    }
    return count;
  }

  public static String replace(String inString, String oldPattern, String newPattern) {

    if (inString == null) {
      return null;
    }
    if ((oldPattern == null) || (newPattern == null)) {
      return inString;
    }

    StringBuffer sbuf = new StringBuffer();
    // output StringBuffer we'll build up
    int pos = 0; // our position in the old string
    int index = inString.indexOf(oldPattern);
    // the index of an occurrence we've found, or -1
    int patLen = oldPattern.length();
    while (index >= 0) {
      sbuf.append(inString.substring(pos, index));
      sbuf.append(newPattern);
      pos = index + patLen;
      index = inString.indexOf(oldPattern, pos);
    }
    sbuf.append(inString.substring(pos));

    // remember to append any characters to the right of a match
    return sbuf.toString();
  }

  public static String delete(String inString, String pattern) {

    return StringUtils.replace(inString, pattern, "");
  }
  public static String deleteAny(String inString, String charsToDelete) {

    if ((inString == null) || (charsToDelete == null)) {
      return inString;
    }
    StringBuffer out = new StringBuffer();
    for (int i = 0; i < inString.length(); i++) {
      char c = inString.charAt(i);
      if (charsToDelete.indexOf(c) == -1) {
        out.append(c);
      }
    }
    return out.toString();
  }

  // ---------------------------------------------------------------------
  // Convenience methods for working with formatted Strings
  // ---------------------------------------------------------------------

  public static String quote(String str) {

    return (str != null ? "'" + str + "'" : null);
  }

  public static Object quoteIfString(Object obj) {

    return (obj instanceof String ? StringUtils.quote((String) obj) : obj);
  }

  public static String unqualify(String qualifiedName) {

    return StringUtils.unqualify(qualifiedName, '.');
  }

  public static String unqualify(String qualifiedName, char separator) {

    return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
  }

  public static String capitalize(String str) {

    return StringUtils.changeFirstCharacterCase(str, true);
  }

  public static String uncapitalize(String str) {

    return StringUtils.changeFirstCharacterCase(str, false);
  }

  private static String changeFirstCharacterCase(String str, boolean capitalize) {

    if ((str == null) || (str.length() == 0)) {
      return str;
    }
    StringBuffer buf = new StringBuffer(str.length());
    if (capitalize) {
      buf.append(Character.toUpperCase(str.charAt(0)));
    } else {
      buf.append(Character.toLowerCase(str.charAt(0)));
    }
    buf.append(str.substring(1));
    return buf.toString();
  }

  public static String getFilename(String path) {

    if (path == null) {
      return null;
    }
    int separatorIndex = path.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
    return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
  }

  public static String getFilenameExtension(String path) {

    if (path == null) {
      return null;
    }
    int sepIndex = path.lastIndexOf(StringUtils.EXTENSION_SEPARATOR);
    return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
  }
  public static String stripFilenameExtension(String path) {

    if (path == null) {
      return null;
    }
    int sepIndex = path.lastIndexOf(StringUtils.EXTENSION_SEPARATOR);
    return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
  }

  public static String applyRelativePath(String path, String relativePath) {

    int separatorIndex = path.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
    if (separatorIndex != -1) {
      String newPath = path.substring(0, separatorIndex);
      if (!relativePath.startsWith(StringUtils.FOLDER_SEPARATOR)) {
        newPath += StringUtils.FOLDER_SEPARATOR;
      }
      return newPath + relativePath;
    } else {
      return relativePath;
    }
  }

  public static String cleanPath(String path) {

    String pathToUse =
        StringUtils.replace(path, StringUtils.WINDOWS_FOLDER_SEPARATOR,
            StringUtils.FOLDER_SEPARATOR);

    // Strip prefix from path to analyze, to not treat it as part of the
    // first path element. This is necessary to correctly parse paths like
    // "file:core/../core/io/Resource.class", where the ".." should just
    // strip the first "core" directory while keeping the "file:" prefix.
    int prefixIndex = pathToUse.indexOf(":");
    String prefix = "";
    if (prefixIndex != -1) {
      prefix = pathToUse.substring(0, prefixIndex + 1);
      pathToUse = pathToUse.substring(prefixIndex + 1);
    }

    String[] pathArray =
        StringUtils.delimitedListToStringArray(pathToUse, StringUtils.FOLDER_SEPARATOR);
    List pathElements = new LinkedList<>();
    int tops = 0;

    for (int i = pathArray.length - 1; i >= 0; i--) {
      if (StringUtils.CURRENT_PATH.equals(pathArray[i])) {
        // Points to current directory - drop it.
      } else if (StringUtils.TOP_PATH.equals(pathArray[i])) {
        // Registering top path found.
        tops++;
      } else {
        if (tops > 0) {
          // Merging path element with corresponding to top path.
          tops--;
        } else {
          // Normal path element found.
          pathElements.add(0, pathArray[i]);
        }
      }
    }

    // Remaining top paths need to be retained.
    for (int i = 0; i < tops; i++) {
      pathElements.add(0, StringUtils.TOP_PATH);
    }

    return prefix
        + StringUtils.collectionToDelimitedString(pathElements, StringUtils.FOLDER_SEPARATOR);
  }

  public static boolean pathEquals(String path1, String path2) {

    return StringUtils.cleanPath(path1).equals(StringUtils.cleanPath(path2));
  }

  public static Locale parseLocaleString(String localeString) {

    String[] parts = StringUtils.tokenizeToStringArray(localeString, "_ ", false, false);
    String language = (parts.length > 0 ? parts[0] : "");
    String country = (parts.length > 1 ? parts[1] : "");
    String variant = (parts.length > 2 ? parts[2] : "");
    return (language.length() > 0 ? new Locale(language, country, variant) : null);
  }

  // ---------------------------------------------------------------------
  // Convenience methods for working with String arrays
  // ---------------------------------------------------------------------

  public static String[] addStringToArray(String[] array, String str) {

    if ((array == null) || (array.length == 0)) {
      return new String[] {str};
    }
    String[] newArr = new String[array.length + 1];
    System.arraycopy(array, 0, newArr, 0, array.length);
    newArr[array.length] = str;
    return newArr;
  }

  public static String[] concatenateStringArrays(String[] array1, String[] array2) {
    if (array1 == null || array1.length == 0) {
      return array2;
    }
    if (array2 == null || array2.length == 0) {
      return array1;
    }
    String[] newArr = new String[array1.length + array2.length];
    System.arraycopy(array1, 0, newArr, 0, array1.length);
    System.arraycopy(array2, 0, newArr, array1.length, array2.length);
    return newArr;
  }

  public static String[] mergeStringArrays(String[] array1, String[] array2) {
    if (array1 == null || array1.length == 0) {
      return array2;
    }
    if (array2 == null || array2.length == 0) {
      return array1;
    }
    List result = new ArrayList();
    result.addAll(Arrays.asList(array1));
    for (int i = 0; i < array2.length; i++) {
      String str = array2[i];
      if (!result.contains(str)) {
        result.add(str);
      }
    }
    return StringUtils.toStringArray(result);
  }

  public static String[] sortStringArray(String[] array) {
    if (array == null || array.length == 0) {
      return new String[0];
    }
    Arrays.sort(array);
    return array;
  }

  public static String[] toStringArray(Collection collection) {

    if (collection == null) {
      return null;
    }
    return (String[]) collection.toArray(new String[collection.size()]);
  }

  public static String[] removeDuplicateStrings(String[] array) {
    if (array == null || array.length == 0) {
      return array;
    }
    Set set = new TreeSet();
    for (int i = 0; i < array.length; i++) {
      set.add(array[i]);
    }
    return StringUtils.toStringArray(set);
  }

  public static String[] split(String toSplit, String delimiter) {

    if (!StringUtils.hasLength(toSplit) || !StringUtils.hasLength(delimiter)) {
      return null;
    }
    int offset = toSplit.indexOf(delimiter);
    if (offset < 0) {
      return null;
    }
    String beforeDelimiter = toSplit.substring(0, offset);
    String afterDelimiter = toSplit.substring(offset + delimiter.length());
    return new String[] {beforeDelimiter, afterDelimiter};
  }

  public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {

    return StringUtils.splitArrayElementsIntoProperties(array, delimiter, null);
  }

  public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter,
      String charsToDelete) {
    if (array == null || array.length == 0) {
      return null;
    }
    Properties result = new Properties();
    for (int i = 0; i < array.length; i++) {
      String element = array[i];
      if (charsToDelete != null) {
        element = StringUtils.deleteAny(array[i], charsToDelete);
      }
      String[] splittedElement = StringUtils.split(element, delimiter);
      if (splittedElement == null) {
        continue;
      }
      result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
    }
    return result;
  }

  public static String[] tokenizeToStringArray(String str, String delimiters) {

    return StringUtils.tokenizeToStringArray(str, delimiters, true, true);
  }

  public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens,
      boolean ignoreEmptyTokens) {

    if (str == null) {
      return null;
    }
    StringTokenizer st = new StringTokenizer(str, delimiters);
    List tokens = new ArrayList();
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (trimTokens) {
        token = token.trim();
      }
      if (!ignoreEmptyTokens || (token.length() > 0)) {
        tokens.add(token);
      }
    }
    return StringUtils.toStringArray(tokens);
  }

  public static String[] delimitedListToStringArray(String str, String delimiter) {

    if (str == null) {
      return new String[0];
    }
    if (delimiter == null) {
      return new String[] {str};
    }
    List result = new ArrayList();
    if ("".equals(delimiter)) {
      for (int i = 0; i < str.length(); i++) {
        result.add(str.substring(i, i + 1));
      }
    } else {
      int pos = 0;
      int delPos = 0;
      while ((delPos = str.indexOf(delimiter, pos)) != -1) {
        result.add(str.substring(pos, delPos));
        pos = delPos + delimiter.length();
      }
      if ((str.length() > 0) && (pos <= str.length())) {
        // Add rest of String, but not in case of empty input.
        result.add(str.substring(pos));
      }
    }
    return StringUtils.toStringArray(result);
  }

  public static String[] commaDelimitedListToStringArray(String str) {

    return StringUtils.delimitedListToStringArray(str, ",");
  }

  public static Set commaDelimitedListToSet(String str) {
    Set set = new TreeSet();
    String[] tokens = StringUtils.commaDelimitedListToStringArray(str);
    for (int i = 0; i < tokens.length; i++) {
      set.add(tokens[i]);
    }
    return set;
  }

  public static String collectionToDelimitedString(Collection coll, String delim, String prefix,
      String suffix) {
    if (coll == null || coll.size() == 0) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    Iterator<?> it = coll.iterator();
    while (it.hasNext()) {
      sb.append(prefix).append(it.next()).append(suffix);
      if (it.hasNext()) {
        sb.append(delim);
      }
    }
    return sb.toString();
  }

  public static String collectionToDelimitedString(Collection coll, String delim) {

    return StringUtils.collectionToDelimitedString(coll, delim, "", "");
  }

  public static String collectionToCommaDelimitedString(Collection coll) {

    return StringUtils.collectionToDelimitedString(coll, ",");
  }

  public static String arrayToDelimitedString(Object[] arr, String delim) {
    if (arr == null || arr.length == 0) {
      return "";
    }
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < arr.length; i++) {
      if (i > 0) {
        sb.append(delim);
      }
      sb.append(arr[i]);
    }
    return sb.toString();
  }

  public static String arrayToCommaDelimitedString(Object[] arr) {

    return StringUtils.arrayToDelimitedString(arr, ",");
  }

  public static String removeEnd(final String str, final String remove) {
    if (isEmpty(str) || isEmpty(remove)) {
      return str;
    }
    if (str.endsWith(remove)) {
      return str.substring(0, str.length() - remove.length());
    }
    return str;
  }

  //Empty checks
  //-----------------------------------------------------------------------
  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  public static boolean isNotEmpty(final CharSequence cs) {
    return !isEmpty(cs);
  }

  public static String substringAfter(final String str, final String separator) {
    if (isEmpty(str)) {
      return str;
    }
    if (separator == null) {
      return "";
    }
    final int pos = str.indexOf(separator);
    if (pos == -1) {
      return "";
    }
    return str.substring(pos + separator.length());
  }

  public static boolean isBlank(String token) {
    return token == null || token.trim().equals("");
  }

  public static boolean isNotBlank(String token) {
    return token != null && !token.trim().equals("");
  }

  public static String substringAfterLast(final String str, final String separator) {
    if (isEmpty(str)) {
      return str;
    }
    if (isEmpty(separator)) {
      return "";
    }
    final int pos = str.lastIndexOf(separator);
    if (pos == -1 || pos == str.length() - separator.length()) {
      return "";
    }
    return str.substring(pos + separator.length());
  }

  public static String substringBeforeLast(final String str, final String separator) {
    if (isEmpty(str) || isEmpty(separator)) {
      return str;
    }
    final int pos = str.lastIndexOf(separator);
    if (pos == -1) {
      return str;
    }
    return str.substring(0, pos);
  }

  public static String substringBefore(final String str, final String separator) {
    if (isEmpty(str) || separator == null) {
      return str;
    }
    if (separator.isEmpty()) {
      return "";
    }
    final int pos = str.indexOf(separator);
    if (pos == -1) {
      return str;
    }
    return str.substring(0, pos);
  }
}
