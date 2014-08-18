package com.goeuro.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.goeuro.processor.CSVWriter;

public class CSVWriterTests {
	static Logger log = Logger.getLogger(CSVWriterTests.class.getName());
	private CSVWriter writer = new CSVWriter();
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testZeroRecordInResultedList() {
		// Initialization
		List records = new ArrayList();
		// Method execution
		log.info("No of records writing to the file " + records);
		int numberOfRecords = writer.writingObjectToCSVFile(records);
		System.out.println("numberOfRecords"+ numberOfRecords);
		assertEquals(true, numberOfRecords == 0 );
	}
}
