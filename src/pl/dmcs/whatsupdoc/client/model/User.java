package pl.dmcs.whatsupdoc.client.model;

import java.util.ArrayList;
import java.util.Iterator;

import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable{
	private String userFullName;
	private UserType userType;
	
	public User(){
	}
	
	public User(String username, UserType userType){
		setUserFullName(userFullName);
		setUserType(userType);
	}

	/**
	 * @return the type
	 */
	public UserType getUserType() {
		return userType;
	}

	/**
	 * @param type the type to set
	 */
	public void setUserType(UserType userType) {
		this.userType = userType;
	}


	/**
	 * @return the userFullName
	 */
	public String getUserFullName() {
		return userFullName;
	}

	/**
	 * @param userFullName the userFullName to set
	 */
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
}
