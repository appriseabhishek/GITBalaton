package com.goeuro.processor;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;

public class HTTPClient {
	static Logger log = Logger.getLogger(HTTPClient.class.getName());
	private final String URL_GO_EURO = "https://api.goeuro.com/api/v2/position/suggest/en/";
	private final String USER_AGENT = "Mozilla/5.0";
	private final int HTTP_RESPONSE_CODE_CLIENT_ERROR = 400;
	private final String HTTP_METHOD_GET = "GET";
	private final String HTTP_USER_AGENT = "User-Agent";
	private final String INVALID_INPUT_STRING = "Invalid input string";

	public HttpURLConnection sendRequest(String searchString){
		log.info("Sending a HTTP request:- " + URL_GO_EURO + searchString);
		URL url;
		HttpURLConnection connection = null;
		try {
			url = new URL( URL_GO_EURO + searchString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(HTTP_METHOD_GET);
			connection.setRequestProperty(HTTP_USER_AGENT, USER_AGENT);

			int responseCode = connection.getResponseCode();
			log.info("HTTP Response Code : " + responseCode);
			if(responseCode == HTTP_RESPONSE_CODE_CLIENT_ERROR){
				throw new Exception(INVALID_INPUT_STRING);
			}
			return connection;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}