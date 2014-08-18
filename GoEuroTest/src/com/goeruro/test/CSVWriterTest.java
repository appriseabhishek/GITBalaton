package com.goeruro.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.goeuro.processor.CSVWriter;

public class CSVWriterTest {
	static Logger log = Logger.getLogger(CSVWriterTest.class.getName());
	CSVWriter writer = new CSVWriter();

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testZeroRecordInResultedList() {
		
		// Init resource
		List records = new ArrayList();
		try {
			// Method execution
			log.info("No of records writing to the file " + records);
			writer.writingObjectToCSVFile(records);

			// Validation
			thrown.expect(Exception.class);
			thrown.expectMessage("No records fetched for the specified search string");
		} catch (Exception e) {
			System.out.println("Exception Message:- "+ e.getMessage());
		}
	}
	
	@Test
	public void testRecordsWrittenTotheCSVFile() {
		List records = new ArrayList();
		
		
		/*try {
			// Method execution
			log.info("No of records writing to the file " + records);
			writer.writingObjectToCSVFile(records);
		} catch (Exception e) {
			System.out.println("Exception Message:- "+ e.getMessage());
		}*/
		
	}

}
