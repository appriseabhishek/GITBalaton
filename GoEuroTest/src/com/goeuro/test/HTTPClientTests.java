package com.goeuro.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.Test;

import com.goeuro.processor.HTTPClient;

public class HTTPClientTests {
	private final int HTTP_RESPONSE_CODE_CLIENT_ERROR = 400;
	private final int HTTP_RESPONSE_CODE_SUCCESS = 200;
	
	// Initialization 
	HTTPClient client = new HTTPClient();

	@Test
	public void testSuccessfulHTTPResponse() { 
		String searchString = "Sun";
		HttpURLConnection connection = client.sendRequest(searchString);
		try {
			boolean responseCode = (connection.getResponseCode() == HTTP_RESPONSE_CODE_SUCCESS);
			assertEquals(true, responseCode );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmpltySearchString() { 
		String searchString = "";
		HttpURLConnection connection = client.sendRequest(searchString);
		try {
			boolean responseCode = (connection.getResponseCode() == HTTP_RESPONSE_CODE_CLIENT_ERROR);
			assertEquals(true, responseCode );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
