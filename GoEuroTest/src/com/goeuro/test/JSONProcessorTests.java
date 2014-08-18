package com.goeuro.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.goeuro.model.Record;
import com.goeuro.processor.JSONProcessor;

public class JSONProcessorTests {
	static Logger log = Logger.getLogger(JSONProcessorTests.class.getName());
	private JSONProcessor processor = new JSONProcessor();
	private HttpURLConnection connection = null;
	private final String URL_GO_EURO = "https://api.goeuro.com/api/v2/position/suggest/en/";
	private final String CONNECTION_IS_NULL = "Connection is null";

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void validateZeroRecords() {
		try {
			processor.parsingJson(connection);
			// Validation
			thrown.expect(Exception.class);
			thrown.expectMessage(CONNECTION_IS_NULL);
		} catch (IOException e) {
			log.info("Exception "+ e.getMessage());
		} catch (ParseException e) {
			log.info("Exception "+ e.getMessage());
		} catch (Exception e) {
			log.info("Exception "+ e.getMessage());
		}
	}
	@Test
	public void testRecordsWrittenTotheCSVFile() {
		List<Record> records ;
		try {
			URL url = new URL(URL_GO_EURO + "ABC");
			connection = (HttpURLConnection) url.openConnection();
			// Method execution
			records = processor.parsingJson(connection);
			assertEquals(true, records.size()== 1);
			log.info("Records fetched from the Server:-  " + records);

		} catch (Exception e) {
			System.out.println("Exception Message:- "+ e.getMessage());
		}
	}

}
