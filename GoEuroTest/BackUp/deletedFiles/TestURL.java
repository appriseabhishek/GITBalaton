package com.goeuro.deletedFiles;

import java.net.HttpURLConnection;
import java.util.List;
import org.apache.log4j.Logger;

public class TestURL {

	static Logger log = Logger.getLogger(TestURL.class.getName());
	public static void main(String[] args) {
		HTTPParser parser = new HTTPParser();

		try{

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

	public static String validateCommandLineParameters(String args[]) {

		String commandLineArg = "India";
		//		http://www.karlin.mff.cuni.cz/network/prirucky/javatut/java/cmdLineArgs/parsing.html
		//			System.out.println("Parameter from command Line"+ args[0]);
		/*if (args.length == 0) { 
			System.out.println("There is no command line arguments passed!");
		}else{
			commandLineArg = args[0];
		}*/
		return commandLineArg;
	}
}
