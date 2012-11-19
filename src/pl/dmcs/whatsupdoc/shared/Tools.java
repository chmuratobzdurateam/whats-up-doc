package pl.dmcs.whatsupdoc.shared;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {
	public static String getCurrentDate(){
		final String DATE_PATTERN = "dd-MM-yyyy";
		final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
		
		Date currentDate = new Date();
		String currentFullDateString = dateFormat.format(currentDate);
		
		return currentFullDateString;
	}
}
