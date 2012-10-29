package pl.dmcs.whatsupdoc.shared;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.google.gwt.regexp.shared.RegExp;

/**
 * <p>
 * FieldVerifier validates that the name the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> package because we use it in both
 * the client code and on the server. On the client, we verify that the name is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the name is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is not translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
 */
public class FieldVerifier {
	
	//public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	//public static final Pattern VALID_PESEL_REGEX = Pattern.compile("^[0-9]{11}");
	//public static final Pattern VALID_PHONE_REGEX = Pattern.compile("^[0-9]{9}");
	
	/**
	 * Verifies that the specified name is valid for our service.
	 * 
	 * In this example, we only require that the name is at least four
	 * characters. In your application, you can use more complex checks to ensure
	 * that usernames, passwords, email addresses, URLs, and other fields have the
	 * proper syntax.
	 * 
	 * @param name the name to validate
	 * @return true if valid, false if invalid
	 */
	public static boolean isValidName(String name) {
		if (name == null) {
			return false;
		}
		return name.length() > 3;
	}
	
	/**
	 * Password is valid iff is != null and doesn't contains ' ' and is longer than 4 characters
	 * 
	 * @param password the password to validate
	 * @return true if valid, false if invalid
	 */
	public static boolean isValidPassword(String password){
		if(password == null){
			return false;
		}
		if(password.contains(" ")){
			return false;
		}
		return password.length() > 4;
	}
	
	/**
	 * Email is valid iff it validate against "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
	 * 
	 * @param email the email to validate
	 * @return true if valid, false if invalid
	 */
	public static boolean isValidEmail(String email){
		if(email == null){
			return false;
		}
		return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");	
	}
	
	/**
	 * PESEL is valid iff it is 11 digit number
	 * 
	 * @param PESEL the PESEL to validate
	 * @return true if valid, false, if invalid
	 */
	public static boolean isValidPESEL(String PESEL){
		if(PESEL==null){
			return false;
		}
		//Matcher matcher = VALID_PESEL_REGEX.matcher(PESEL);
        //return matcher.find();
		return PESEL.matches("^[0-9]{3}$");
	}
	
	/**
	 * Phone number is valid iff it is 9 digit number
	 * 
	 * @param phone
	 * @return true if valid, false, if invalid
	 */
	public static boolean isValidPhone(String phone){
		if(phone==null){
			return false;
		}
		//Matcher matcher = VALID_PHONE_REGEX.matcher(phone);
        //return matcher.find();
		return phone.matches("^[0-9]{9}$");
	}
}
