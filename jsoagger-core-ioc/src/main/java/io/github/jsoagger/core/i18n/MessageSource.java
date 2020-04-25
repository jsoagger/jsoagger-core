/**
 *
 */
package io.github.jsoagger.core.i18n;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class MessageSource {

  private List<String> basenames = new ArrayList<>();
  private List<ResourceBundle> resourcesBundles = new ArrayList<>();
  private Locale defaultOne = Locale.getDefault();

  private boolean initialized = false;
  private boolean useCodeAsDefaultMessage;
  private String defaultEncoding = "UTF-8";

  private MessageSource parentMessageSource;

  /**
   * Constructor.
   */
  public MessageSource() {}


  /**
   * Load bundles
   */
  private void initialise() {
    for (String location : basenames) {
      try {
        ResourceBundle bundle = ResourceBundle.getBundle(location, defaultOne, new UTF8Control());
        resourcesBundles.add(bundle);
      } catch (MissingResourceException e) {
        // System.out.println("ERROR - ERROR Missing resource bundle " + location);
      }
    }

    initialized = true;
  }

  /**
   * @param key
   * @return
   */
  public String getWithKeys(String key, Object... args) {
    if (!initialized) {
      initialise();
    }

    String message = key;
    for (ResourceBundle resourcesBundle : resourcesBundles) {
      try {
        message = resourcesBundle.getString(key);
        if (args.length > 0) {
          message = MessageFormat.format(message, args);
        }
        return message;
      } catch (MissingResourceException e) {
      }
    }

    if (message.equals(key) && parentMessageSource != null) {
      message = parentMessageSource.get(key);
    }

    return message.trim();
  }

  /**
   * @param key
   * @return
   */
  public String get(String key) {
    return getWithKeys(key);
  }

  /**
   * @param key
   * @param defaultMessage
   * @param locale
   * @return
   */
  public String getOrDefault(String key, String defaultMessage, Locale locale, Object... keys) {
    String message = getWithKeys(key, keys);
    return message == null ? defaultMessage : message;
  }

  /**
   * @param key
   * @param defaultMessage
   * @return
   */
  public String getOrDefault(String key, String defaultMessage, Object... keys) {
    String message = getWithKeys(key, keys);
    return message == null ? defaultMessage : message;
  }


  /**
   * @return the basenames
   */
  public List<String> getBasenames() {
    return basenames;
  }

  /**
   * @param basenames the basenames to set
   */
  public void setBasenames(List<String> basenames) {
    this.basenames = basenames;
  }

  /**
   * @return the useCodeAsDefaultMessage
   */
  public boolean isUseCodeAsDefaultMessage() {
    return useCodeAsDefaultMessage;
  }

  /**
   * @param useCodeAsDefaultMessage the useCodeAsDefaultMessage to set
   */
  public void setUseCodeAsDefaultMessage(boolean useCodeAsDefaultMessage) {
    this.useCodeAsDefaultMessage = useCodeAsDefaultMessage;
  }

  /**
   * @return the defaultEncoding
   */
  public String getDefaultEncoding() {
    return defaultEncoding;
  }

  /**
   * @param defaultEncoding the defaultEncoding to set
   */
  public void setDefaultEncoding(String defaultEncoding) {
    this.defaultEncoding = defaultEncoding;
  }

  /**
   * @return the parentMessageSource
   */
  public MessageSource getParentMessageSource() {
    return parentMessageSource;
  }

  /**
   * @param parentMessageSource the parentMessageSource to set
   */
  public void setParentMessageSource(MessageSource parentMessageSource) {
    this.parentMessageSource = parentMessageSource;
  }
}

