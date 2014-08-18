package com.goeuro.processor;

import java.io.IOException;
import java.util.List;

import net.sf.sojo.interchange.Serializer;
import net.sf.sojo.interchange.csv.CsvSerializer;
import net.sf.sojo.interchange.object.ObjectSerializer;

import org.apache.log4j.Logger;

import com.goeuro.model.Record;

public class CSVWriter {
	private static Logger log = Logger.getLogger(CSVWriter.class.getName());
	private final String LOG_NO_RECORDS_FETCHED = "No records fetched for the specified search string";
	private final String OUTPUT_CSV_FILE_PATH = ".\\resources\\records.csv";
	
	public int writingObjectToCSVFile(List<Record> records) {
		try {
			if(records.size() == 0){
				throw new Exception(LOG_NO_RECORDS_FETCHED);
			}
			// Serialize the object to a file
			log.info("CSV File Path Location:- "+ OUTPUT_CSV_FILE_PATH);
			Serializer serializer = new CsvSerializer();
			String recordsInCsvFormat = (String) serializer.serialize(records);
			log.info("No Of records written to CSVFile:- "+ recordsInCsvFormat.length());
			ObjectSerializer fileSerializer = new ObjectSerializer();
			fileSerializer.serializeToFile(recordsInCsvFormat, OUTPUT_CSV_FILE_PATH);
			return recordsInCsvFormat.length();
		} catch (IOException e ) {
			e.printStackTrace();
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return 0;
	}
}
