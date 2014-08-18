package com.goeruro.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.goeuro.processor.HTTPClient;

public class HTTPClientTest {
	// Init
	HTTPClient client = new HTTPClient();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testSuccessfulHTTPResponse() { 
		String searchString = "Sun";
		HttpURLConnection connection = client.sendRequest(searchString);
		try {
			boolean responseCode = (connection.getResponseCode() == 200);
			assertEquals(true, responseCode );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEmpltyHTTPResponse() {
		String searchString = "";

		try {
			HttpURLConnection connection = client.sendRequest(searchString);

			// Invalid request made to the server
			boolean responseCode = (connection.getResponseCode() == 400);
			assertEquals(true, responseCode );

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*	@Test
	public void testInvalidHTTPResponse() {
		String searchString = "123";
		HttpURLConnection connection = client.sendRequest(searchString);

		try {
			boolean responseCode = (connection.getResponseCode() == 200);
			assertEquals(true, responseCode );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 */

}
