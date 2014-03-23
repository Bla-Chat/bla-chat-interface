package net.michaelfuerst.xjcp.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * This class warps the HTTPParameter and encodes them for us.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public final class HttpParameter {
	private static final String ENCODING = "UTF-8";
	
	private String key;
	private String value;

	/**
	 * Creates a new pair of parameters.
	 * 
	 * @param key The key.
	 * @param value Its value.
	 */
	public HttpParameter(String key, String value) {
		setKey(key);
		setValue(value);
	}
	
	/**
	 * Takes an amount of strings and groups them together to pairs of key value.
	 * 
	 * @param strings The string you want to encode.
	 * @return An array of HTTPParameters.
	 */
	public static HttpParameter[] fromStrings(String... strings) {
		HttpParameter[] result = new HttpParameter[(strings.length / 2) + (strings.length & 1)];
		
		for (int i = 0; i < strings.length; ++i) {
			if (i + 1 < strings.length) {
				result[i / 2] = new HttpParameter(strings[i], strings[i + 1]);
				++i;
			} else {
				result[(i / 2) + (i & 1)] = new HttpParameter(strings[i], null);
			}
		}
		
		return result;
	}
	
	/**
	 * @return The key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Changes the key.
	 * 
	 * @param key The key.
	 */
	public void setKey(String key) {
		if (!key.matches("^[a-zA-Z]+")) {
			throw new IllegalArgumentException("key must not contain characters besides [a-zA-Z]");
		}
		
		this.key = key;
	}

	/**
	 * @return The url-encoded value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Changes the value. It will be urlencoded.
	 * 
	 * @param value The value.
	 */
	public void setValue(String value) {
		if (value == null) {
			this.value = null;
		} else {
			try {
				this.value = URLEncoder.encode(value, ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * @return True if the key is set.
	 */
	public boolean hasKey() {
		return key != null;
	}
	
	/**
	 * @return True if the value is set.
	 */
	public boolean hasValue() {
		return value != null;
	}
	
	/**
	 * @return True if value and key are set.
	 */
	public boolean isValid() {
		return hasKey() && hasValue();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(key);
		
		if (hasValue()) {
			builder.append('=').append(value);
		}

		return builder.toString();
	}
}
