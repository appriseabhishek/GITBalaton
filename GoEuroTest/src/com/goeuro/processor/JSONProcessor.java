package com.goeuro.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.goeuro.model.Record;

public class JSONProcessor {

	static Logger log = Logger.getLogger(JSONProcessor.class.getName());
	private final String CONNECTION_IS_NULL = "Connection is null";
	private final String LOG_NO_RECORDS_FETCHED = "No records fetched for the specified search string";
	private final String ID = "_id";
	private final String NAME = "name";
	private final String TYPE = "type";
	private final String GEO_POSITION = "geo_position";
	private final String LATITUDE = "latitude";
	private final String LONGITUDE = "longitude";

	public List<Record> parsingJson(HttpURLConnection connection) throws Exception{

		if(connection == null){
			throw new Exception(CONNECTION_IS_NULL);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		List<Record> records = new ArrayList<Record>();
		StringBuffer response = new StringBuffer();
		JSONParser parser = new JSONParser();
		Record record;
		JSONArray jsonArray;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
			log.info("HTTP Response:- " + inputLine);
			jsonArray = (JSONArray)parser.parse(inputLine);
			if(jsonArray.size()==0){
				log.info(LOG_NO_RECORDS_FETCHED);
				throw new Exception(LOG_NO_RECORDS_FETCHED);
			}else{
				for(int i =0; i < jsonArray.size();i++){
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					// Fetching required attributes from the response
					int id = Integer.parseInt(jsonObject.get(ID).toString());
					String name = (String) jsonObject.get(NAME);
					String type = (String) jsonObject.get(TYPE);
					JSONObject geo_position = (JSONObject) jsonObject.get(GEO_POSITION);
					Double latitude =  (Double) geo_position.get(LATITUDE);
					Double longitude =  (Double) geo_position.get(LONGITUDE);
					// Instantiating Record Object and adding it to the resulted List
					record = new Record( id, name, type, latitude, longitude);
					log.info(i + " Record :- "+ record);
					records.add(record);
				}
			}
		}
		in.close();
		return records;
	}
}
