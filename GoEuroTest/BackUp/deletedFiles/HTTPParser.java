package com.goeuro.deletedFiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.sojo.interchange.Serializer;
import net.sf.sojo.interchange.csv.CsvSerializer;
import net.sf.sojo.interchange.object.ObjectSerializer;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.goeuro.model.Record;

public class HTTPParser {

	static Logger log = Logger.getLogger(HTTPParser.class.getName());
	private final String USER_AGENT = "Mozilla/5.0";
	private String url = "http://api.goeuro.com/api/v2/position/suggest/en/";

	public HttpURLConnection sendGetRequest(String searchString) throws Exception {

		log.info(" Making a HTTP request " + url + searchString);
		URL obj = new URL( url + searchString);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		connection.setRequestMethod("GET");

		//add request header
		connection.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = connection.getResponseCode();

		//		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		log.info("\nSending 'GET' request to URL : " + url);
		log.info("Response Code : " + responseCode);
		return connection;

	}

	List parsingJson(HttpURLConnection con) throws IOException, ParseException{

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		List<Record> records = new ArrayList<Record>();
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			log.info("HTTP Response :-  " + inputLine);
			System.out.println("Input Message:- "+ inputLine);
			JSONParser parser = new JSONParser();
			Object obj2 = parser.parse(inputLine);
			JSONArray jsonArray = (JSONArray) obj2;

			for(int i =0; i < jsonArray.size();i++){
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);

				// _type key is not coming in the request or is hidden..???????
				String _type = (String) jsonObject.get("_type");
				int id = Integer.parseInt(jsonObject.get("_id").toString());
				String name = (String) jsonObject.get("name");
				String type = (String) jsonObject.get("type");
				JSONObject geo_position = (JSONObject) jsonObject.get("geo_position");
				Double latitude =  (Double) geo_position.get("latitude");
				Double longitude =  (Double) geo_position.get("longitude");

				log.debug(_type +" ---  "+ name + type + " ---> "+ id  + longitude);
				//				System.out.println(_type +" ---  "+ name + type + " ---> "+ id  + longitude);
				Record record = new Record(_type, id, name, type, latitude, longitude);
				records.add(record);
			}
		}
		in.close();
		return records;
	}

	void writingObjectToCSVFile(List records) throws IOException{

		// Create a CSV file and write all the content to the file once.
		Serializer serializer = new CsvSerializer();
		String recordsInCsvFormat = (String) serializer.serialize(records);
		System.out.println("Generated CSV : \n"+ recordsInCsvFormat);
		log.info("Generated CSV : \n"+ recordsInCsvFormat);

		// Serialize the object to a file
		String filePath = "D:\\Abhishek\\WorkSpaces\\WorkSpace1\\GoEuroTest\\records.csv";
		ObjectSerializer fileSerializer = new ObjectSerializer();
		fileSerializer.serializeToFile(recordsInCsvFormat, filePath);
	}

	/*	public static void main(String[] args) {
	try{
		HTTPParser parser = new HTTPParser();

		// Validating input from the command line
		String commandLineArg = validateCommandLineParameters(args);

		// Making a HTTP request to fetch the data from the server
		HttpURLConnection connection = parser.sendGetRequest(commandLineArg);

		// parsing the Http response to List
		List records = parser.parsingJson(connection);

		// Writing it to the CSV file
		parser.writingObjectToCSVFile(records);

	}catch(Exception exception){
		System.out.println("Exception" + exception.getMessage());
	}
}
	 */
}
