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

  /**
   * Check that the given String is neither <code>null</code> nor of length 0. Note: Will return
   * <code>true</code> for a String that purely consists of whitespace.
   * <p>
   *
   * <pre>
   * StringUtils.hasLength(null) = false
   * StringUtils.hasLength("") = false
   * StringUtils.hasLength(" ") = true
   * StringUtils.hasLength("Hello") = true
   * </pre>
   *
   * @param str the String to check (may be <code>null</code>)
   * @return <code>true</code> if the String is not null and has length
   * @see #hasText(String)
   */
  public static boolean hasLength(String str) {
    return ((str != null) && (str.length() > 0));
  }

  /**
   * Check whether the given String has actual text. More specifically, returns <code>true</code> if
   * the string not <code>null<code>,
   * its length is greater than 0, and it contains at least one non-whitespace character.
   * <p><pre>
   * StringUtils.hasText(null) = false
   * StringUtils.hasText("") = false
   * StringUtils.hasText(" ") = false
   * StringUtils.hasText("12345") = true
   * StringUtils.hasText(" 12345 ") = true
   * </pre>
   *
   * @param str the String to check (may be <code>null</code>)
   * @return <code>true</code> if the String is not <code>null</code>, its length is greater than 0,
   *         and is does not contain whitespace only
   * @see java.lang.Character#isWhitespace
   */
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

  /**
   * Check whether the given String contains any whitespace characters.
   *
   * @param str the String to check (may be <code>null</code>)
   * @return <code>true</code> if the String is not empty and contains at least 1 whitespace
   *         character
   * @see java.lang.Character#isWhitespace
   */
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

  /**
   * Trim leading and trailing whitespace from the given String.
   *
   * @param str the String to check
   * @return the trimmed String
   * @see java.lang.Character#isWhitespace
   */
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

  /**
   * Trim leading whitespace from the given String.
   *
   * @param str the String to check
   * @return the trimmed String
   * @see java.lang.Character#isWhitespace
   */
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

  /**
   * Trim trailing whitespace from the given String.
   *
   * @param str the String to check
   * @return the trimmed String
   * @see java.lang.Character#isWhitespace
   */
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

  /**
   * Trim <i>all</i> whitespace from the given String: leading, trailing, and inbetween characters.
   *
   * @param str the String to check
   * @return the trimmed String
   * @see java.lang.Character#isWhitespace
   */
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

  /**
   * Test if the given String starts with the specified prefix, ignoring upper/lower case.
   *
   * @param str the String to check
   * @param prefix the prefix to look for
   * @see java.lang.String#startsWith
   */
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

  /**
   * Test if the given String ends with the specified suffix, ignoring upper/lower case.
   *
   * @param str the String to check
   * @param suffix the suffix to look for
   * @see java.lang.String#endsWith
   */
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

  /**
   * Count the occurrences of the substring in string s.
   *
   * @param str string to search in. Return 0 if this is null.
   * @param sub string to search for. Return 0 if this is null.
   */
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

  /**
   * Replace all occurences of a substring within a string with another string.
   *
   * @param inString String to examine
   * @param oldPattern String to replace
   * @param newPattern String to insert
   * @return a String with the replacements
   */
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

  /**
   * Delete all occurrences of the given substring.
   *
   * @param pattern the pattern to delete all occurrences of
   */
  public static String delete(String inString, String pattern) {

    return StringUtils.replace(inString, pattern, "");
  }

  /**
   * Delete any character in a given string.
   *
   * @param charsToDelete a set of characters to delete. E.g. "az\n" will delete 'a's, 'z's and new
   *        lines.
   */
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

  /**
   * Quote the given String with single quotes.
   *
   * @param str the input String (e.g. "myString")
   * @return the quoted String (e.g. "'myString'"), or
   *         <code>null<code> if the input was <code>null</code>
   */
  public static String quote(String str) {

    return (str != null ? "'" + str + "'" : null);
  }

  /**
   * Turn the given Object into a String with single quotes if it is a String; keeping the Object
   * as-is else.
   *
   * @param obj the input Object (e.g. "myString")
   * @return the quoted String (e.g. "'myString'"), or the input object as-is if not a String
   */
  public static Object quoteIfString(Object obj) {

    return (obj instanceof String ? StringUtils.quote((String) obj) : obj);
  }

  /**
   * Unqualify a string qualified by a '.' dot character. For example, "this.name.is.qualified",
   * returns "qualified".
   *
   * @param qualifiedName the qualified name
   */
  public static String unqualify(String qualifiedName) {

    return StringUtils.unqualify(qualifiedName, '.');
  }

  /**
   * Unqualify a string qualified by a separator character. For example, "this:name:is:qualified"
   * returns "qualified" if using a ':' separator.
   *
   * @param qualifiedName the qualified name
   * @param separator the separator
   */
  public static String unqualify(String qualifiedName, char separator) {

    return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
  }

  /**
   * Capitalize a <code>String</code>, changing the first letter to upper case as per
   * {Character#toUpperCase(char)}. No other letters are changed.
   *
   * @param str the String to capitalize, may be <code>null</code>
   * @return the capitalized String, <code>null</code> if null
   */
  public static String capitalize(String str) {

    return StringUtils.changeFirstCharacterCase(str, true);
  }

  /**
   * Uncapitalize a <code>String</code>, changing the first letter to lower case as per
   * {Character#toLowerCase(char)}. No other letters are changed.
   *
   * @param str the String to uncapitalize, may be <code>null</code>
   * @return the uncapitalized String, <code>null</code> if null
   */
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

  /**
   * Extract the filename from the given path, e.g. "mypath/myfile.txt" to "myfile.txt".
   *
   * @param path the file path (may be <code>null</code>)
   * @return the extracted filename, or <code>null</code> if none
   */
  public static String getFilename(String path) {

    if (path == null) {
      return null;
    }
    int separatorIndex = path.lastIndexOf(StringUtils.FOLDER_SEPARATOR);
    return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
  }

  /**
   * Extract the filename extension from the given path, e.g. "mypath/myfile.txt" to "txt".
   *
   * @param path the file path (may be <code>null</code>)
   * @return the extracted filename extension, or <code>null</code> if none
   */
  public static String getFilenameExtension(String path) {

    if (path == null) {
      return null;
    }
    int sepIndex = path.lastIndexOf(StringUtils.EXTENSION_SEPARATOR);
    return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
  }

  /**
   * Strip the filename extension from the given path, e.g. "mypath/myfile.txt" to "mypath/myfile".
   *
   * @param path the file path (may be <code>null</code>)
   * @return the path with stripped filename extension, or <code>null</code> if none
   */
  public static String stripFilenameExtension(String path) {

    if (path == null) {
      return null;
    }
    int sepIndex = path.lastIndexOf(StringUtils.EXTENSION_SEPARATOR);
    return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
  }

  /**
   * Apply the given relative path to the given path, assuming standard Java folder separation (i.e.
   * "/" separators);
   *
   * @param path the path to start from (usually a full file path)
   * @param relativePath the relative path to apply (relative to the full file path above)
   * @return the full file path that results from applying the relative path
   */
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

  /**
   * Normalize the path by suppressing sequences like "path/.." and inner simple dots.
   * <p>
   * The result is convenient for path comparison. For other uses, notice that Windows separators
   * ("\") are replaced by simple slashes.
   *
   * @param path the original path
   * @return the normalized path
   */
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

  /**
   * Compare two paths after normalization of them.
   *
   * @param path1 First path for comparizon
   * @param path2 Second path for comparizon
   * @return whether the two paths are equivalent after normalization
   */
  public static boolean pathEquals(String path1, String path2) {

    return StringUtils.cleanPath(path1).equals(StringUtils.cleanPath(path2));
  }

  /**
   * Parse the given locale string into a <code>java.util.Locale</code>. This is the inverse
   * operation of Locale's <code>toString</code>.
   *
   * @param localeString the locale string, following <code>java.util.Locale</code>'s toString
   *        format ("en", "en_UK", etc). Also accepts spaces as separators, as alternative to
   *        underscores.
   * @return a corresponding Locale instance
   */
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

  /**
   * Append the given String to the given String array, returning a new array consisting of the
   * input array contents plus the given String.
   *
   * @param array the array to append to (can be <code>null</code>)
   * @param str the String to append
   * @return the new array (never <code>null</code>)
   */
  public static String[] addStringToArray(String[] array, String str) {

    if ((array == null) || (array.length == 0)) {
      return new String[] {str};
    }
    String[] newArr = new String[array.length + 1];
    System.arraycopy(array, 0, newArr, 0, array.length);
    newArr[array.length] = str;
    return newArr;
  }

  /**
   * Concatenate the given String arrays into one, with overlapping array elements included twice.
   * <p>
   * The order of elements in the original arrays is preserved.
   *
   * @param array1 the first array (can be <code>null</code>)
   * @param array2 the second array (can be <code>null</code>)
   * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
   */
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

  /**
   * Merge the given String arrays into one, with overlapping array elements only included once.
   * <p>
   * The order of elements in the original arrays is preserved (with the exception of overlapping
   * elements, which are only included on their first occurence).
   *
   * @param array1 the first array (can be <code>null</code>)
   * @param array2 the second array (can be <code>null</code>)
   * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
   */
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

  /**
   * Turn given source String array into sorted array.
   *
   * @param array the source array
   * @return the sorted array (never <code>null</code>)
   */
  public static String[] sortStringArray(String[] array) {
    if (array == null || array.length == 0) {
      return new String[0];
    }
    Arrays.sort(array);
    return array;
  }

  /**
   * Copy the given Collection into a String array. The Collection must contain String elements
   * only.
   *
   * @param collection the Collection to copy
   * @return the String array (<code>null</code> if the passed-in Collection was <code>null</code>)
   */
  public static String[] toStringArray(Collection collection) {

    if (collection == null) {
      return null;
    }
    return (String[]) collection.toArray(new String[collection.size()]);
  }

  /**
   * Remove duplicate Strings from the given array. Also sorts the array, as it uses a TreeSet.
   *
   * @param array the String array
   * @return an array without duplicates, in natural sort order
   */
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

  /**
   * Split a String at the first occurrence of the delimiter. Does not include the delimiter in the
   * result.
   *
   * @param toSplit the string to split
   * @param delimiter to split the string up with
   * @return a two element array with index 0 being before the delimiter, and index 1 being after
   *         the delimiter (neither element includes the delimiter); or <code>null</code> if the
   *         delimiter wasn't found in the given input String
   */
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

  /**
   * Take an array Strings and split each element based on the given delimiter. A
   * <code>Properties</code> instance is then generated, with the left of the delimiter providing
   * the key, and the right of the delimiter providing the value.
   * <p>
   * Will trim both the key and value before adding them to the <code>Properties</code> instance.
   *
   * @param array the array to process
   * @param delimiter to split each element using (typically the equals symbol)
   * @return a <code>Properties</code> instance representing the array contents, or
   *         <code>null</code> if the array to process was null or empty
   */
  public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {

    return StringUtils.splitArrayElementsIntoProperties(array, delimiter, null);
  }

  /**
   * Take an array Strings and split each element based on the given delimiter. A
   * <code>Properties</code> instance is then generated, with the left of the delimiter providing
   * the key, and the right of the delimiter providing the value.
   * <p>
   * Will trim both the key and value before adding them to the <code>Properties</code> instance.
   *
   * @param array the array to process
   * @param delimiter to split each element using (typically the equals symbol)
   * @param charsToDelete one or more characters to remove from each element prior to attempting the
   *        split operation (typically the quotation mark symbol), or <code>null</code> if no
   *        removal should occur
   * @return a <code>Properties</code> instance representing the array contents, or
   *         <code>null</code> if the array to process was <code>null</code> or empty
   */
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

  /**
   * Tokenize the given String into a String array via a StringTokenizer. Trims tokens and omits
   * empty tokens.
   * <p>
   * The given delimiters string is supposed to consist of any number of delimiter characters. Each
   * of those characters can be used to separate tokens. A delimiter is always a single character;
   * for multi-character delimiters, consider using <code>delimitedListToStringArray</code>
   *
   * @param str the String to tokenize
   * @param delimiters the delimiter characters, assembled as String (each of those characters is
   *        individually considered as delimiter).
   * @return an array of the tokens
   * @see java.util.StringTokenizer
   * @see java.lang.String#trim()
   * @see #delimitedListToStringArray
   */
  public static String[] tokenizeToStringArray(String str, String delimiters) {

    return StringUtils.tokenizeToStringArray(str, delimiters, true, true);
  }

  /**
   * Tokenize the given String into a String array via a StringTokenizer.
   * <p>
   * The given delimiters string is supposed to consist of any number of delimiter characters. Each
   * of those characters can be used to separate tokens. A delimiter is always a single character;
   * for multi-character delimiters, consider using <code>delimitedListToStringArray</code>
   *
   * @param str the String to tokenize
   * @param delimiters the delimiter characters, assembled as String (each of those characters is
   *        individually considered as delimiter)
   * @param trimTokens trim the tokens via String's <code>trim</code>
   * @param ignoreEmptyTokens omit empty tokens from the result array (only applies to tokens that
   *        are empty after trimming; StringTokenizer will not consider subsequent delimiters as
   *        token in the first place).
   * @return an array of the tokens (<code>null</code> if the input String was <code>null</code>)
   * @see java.util.StringTokenizer
   * @see java.lang.String#trim()
   * @see #delimitedListToStringArray
   */
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

  /**
   * Take a String which is a delimited list and convert it to a String array.
   * <p>
   * A single delimiter can consists of more than one character: It will still be considered as
   * single delimiter string, rather than as bunch of potential delimiter characters - in contrast
   * to <code>tokenizeToStringArray</code>.
   *
   * @param str the input String
   * @param delimiter the delimiter between elements (this is a single delimiter, rather than a
   *        bunch individual delimiter characters)
   * @return an array of the tokens in the list
   * @see #tokenizeToStringArray
   */
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

  /**
   * Convert a CSV list into an array of Strings.
   *
   * @param str the input String
   * @return an array of Strings, or the empty array in case of empty input
   */
  public static String[] commaDelimitedListToStringArray(String str) {

    return StringUtils.delimitedListToStringArray(str, ",");
  }

  /**
   * Convenience method to convert a CSV string list to a set. Note that this will suppress
   * duplicates.
   *
   * @param str the input String
   * @return a Set of String entries in the list
   */
  public static Set commaDelimitedListToSet(String str) {
    Set set = new TreeSet();
    String[] tokens = StringUtils.commaDelimitedListToStringArray(str);
    for (int i = 0; i < tokens.length; i++) {
      set.add(tokens[i]);
    }
    return set;
  }

  /**
   * Convenience method to return a Collection as a delimited (e.g. CSV) String. E.g. useful for
   * <code>toString()</code> implementations.
   *
   * @param coll the Collection to display
   * @param delim the delimiter to use (probably a ",")
   * @param prefix the String to start each element with
   * @param suffix the String to end each element with
   */
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

  /**
   * Convenience method to return a Collection as a delimited (e.g. CSV) String. E.g. useful for
   * <code>toString()</code> implementations.
   *
   * @param coll the Collection to display
   * @param delim the delimiter to use (probably a ",")
   */
  public static String collectionToDelimitedString(Collection coll, String delim) {

    return StringUtils.collectionToDelimitedString(coll, delim, "", "");
  }

  /**
   * Convenience method to return a Collection as a CSV String. E.g. useful for
   * <code>toString()</code> implementations.
   *
   * @param coll the Collection to display
   */
  public static String collectionToCommaDelimitedString(Collection coll) {

    return StringUtils.collectionToDelimitedString(coll, ",");
  }

  /**
   * Convenience method to return a String array as a delimited (e.g. CSV) String. E.g. useful for
   * <code>toString()</code> implementations.
   *
   * @param arr the array to display
   * @param delim the delimiter to use (probably a ",")
   */
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

  /**
   * Convenience method to return a String array as a CSV String. E.g. useful for
   * <code>toString()</code> implementations.
   *
   * @param arr the array to display
   */
  public static String arrayToCommaDelimitedString(Object[] arr) {

    return StringUtils.arrayToDelimitedString(arr, ",");
  }

  /**
   * <p>Removes a substring only if it is at the end of a source string,
   * otherwise returns the source string.</p>
   *
   * <p>A {@code null} source string will return {@code null}.
   * An empty ("") source string will return the empty string.
   * A {@code null} search string will return the source string.</p>
   *
   * <pre>
   * StringUtils.removeEnd(null, *)      = null
   * StringUtils.removeEnd("", *)        = ""
   * StringUtils.removeEnd(*, null)      = *
   * StringUtils.removeEnd("www.domain.com", ".com.")  = "www.domain.com"
   * StringUtils.removeEnd("www.domain.com", ".com")   = "www.domain"
   * StringUtils.removeEnd("www.domain.com", "domain") = "www.domain.com"
   * StringUtils.removeEnd("abc", "")    = "abc"
   * </pre>
   *
   * @param str  the source String to search, may be null
   * @param remove  the String to search for and remove, may be null
   * @return the substring with the string removed if found,
   *  {@code null} if null String input
   * @since 2.1
   */
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
  /**
   * <p>Checks if a CharSequence is empty ("") or null.</p>
   *
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("bob")     = false
   * StringUtils.isEmpty("  bob  ") = false
   * </pre>
   *
   * <p>NOTE: This method changed in Lang version 2.0.
   * It no longer trims the CharSequence.
   * That functionality is available in isBlank().</p>
   *
   * @param cs  the CharSequence to check, may be null
   * @return {@code true} if the CharSequence is empty or null
   * @since 3.0 Changed signature from isEmpty(String) to isEmpty(CharSequence)
   */
  public static boolean isEmpty(final CharSequence cs) {
    return cs == null || cs.length() == 0;
  }

  public static boolean isNotEmpty(final CharSequence cs) {
    return !isEmpty(cs);
  }

  /**
   *
   * @param str
   * @param separator
   * @return
   */
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

  /**
   * @param token
   * @return
   */
  public static boolean isBlank(String token) {
    return token == null || token.trim().equals("");
  }

  /**
   * @param token
   * @return
   */
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
