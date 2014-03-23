package net.michaelfuerst.xjcp.web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This is a static class providing HTTP Methods.
 * 
 * @author TheDwoon
 * @version 1.0
 * @since 23.03.2014
 *
 */
public final class HTTPService {

	private HTTPService() {

	}

	public static String sendPostRequest(String targetUrl,
			HttpParameter... parameters) {
		URL url;
		HttpURLConnection connection = null;

		StringBuilder urlParameters = new StringBuilder();
		for (int i = 0; i < parameters.length; i++) {
			if (i > 0) {
				urlParameters.append('&');
			}

			urlParameters.append(parameters[i]);
		}

		try {
			// Create connection
			url = new URL(targetUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty(
					"Content-Length",
					""
							+ Integer.toString(urlParameters.toString()
									.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters.toString());
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}

}
