/**
 *
 */
package io.github.jsoagger.core.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

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
	public MessageSource() {
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
				if(args.length > 0) {
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
	 * Load bundles
	 */
	private void initialise() {
		for (String location : basenames) {
			try {
				ResourceBundle bundle = ResourceBundle.getBundle(location, defaultOne, new UTF8Control());
				resourcesBundles.add(bundle);
			} catch (MissingResourceException e) {
				System.out.println("ERROR - ERROR Missing resource bundle " + location);
			}
		}

		initialized = true;
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

class UTF8Control extends Control {

	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {
		// The below is a copy of the default implementation.
		String bundleName = toBundleName(baseName, locale);
		String resourceName = toResourceName(bundleName, "properties");
		ResourceBundle bundle = null;
		InputStream stream = null;
		if (reload) {
			URL url = loader.getResource(resourceName);
			if (url != null) {
				URLConnection connection = url.openConnection();
				if (connection != null) {
					connection.setUseCaches(false);
					stream = connection.getInputStream();
				}
			}
		} else {
			stream = loader.getResourceAsStream(resourceName);
		}
		if (stream != null) {
			try {
				// Only this line is changed to make it to read properties files as UTF-8.
				bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
			} finally {
				stream.close();
			}
		}
		return bundle;
	}
}