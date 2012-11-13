package pl.dmcs.whatsupdoc.shared;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;

public class Tools {
	public static Date getCurrentDate(){
		final String DATE_PATTERN = "dd-MM-yyyy";
		DateTimeFormat dateFormat = DateTimeFormat.getFormat(DATE_PATTERN);
		
		Date currentDate = new Date();
		String currentFullDateString = dateFormat.format(currentDate);
		
		return dateFormat.parse(currentFullDateString);
	}
}
