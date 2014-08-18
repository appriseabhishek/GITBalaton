package com.goeuro.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import com.goeuro.processor.CSVWriter;
import com.goeuro.processor.HTTPClient;
import com.goeuro.processor.JSONProcessor;

public class MainStub {
	/**
	 * @param args[0] as the string make HTTPS request
	 */
	static Logger log = Logger.getLogger(MainStub.class.getName());
	public static void main(String[] args) {
		String searchString = validate(args);
		try {
			log.info("Making a HTTP Request for the String:- " + searchString);
			// Sending the HTTP request and getting the response
			HTTPClient client = new HTTPClient();
			HttpURLConnection connection = client.sendRequest(searchString);

			log.info("Parsing the HTTP Response");
			// Parsing the HTTP Response to a list
			JSONProcessor processor = new JSONProcessor();
			List records = processor.parsingJson(connection);

			// Writing the records to the CSV file
			log.info("Writing the Response to the CSV file");
			CSVWriter writer = new CSVWriter();
			writer.writingObjectToCSVFile(records);
			
			log.info("Execution Successful ");
		} catch (IOException | ParseException e ) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String validate(String [] args){
		String searchStringTemp = "IN";
		/*String searchString;
		if(args.length == 0){
			try {
				throw new Exception("No command line argument passed");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(args.length > 0){
			searchString = args[0];
		}
		*/
		// If extra command line parameters are being passed it will take only the first one.
		return searchStringTemp;
	}
}
