package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;
import java.util.Iterator;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PUser {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String username;
	@Persistent
	private String userFullName;
	@Persistent
	private String password;
	@Persistent
	private String mail;
	@Persistent
	private String phone;
	@Persistent
	private Integer PESEL;
	@Persistent
	private UserType userType;
	
	public PUser(){
	}
	
	public PUser(String username, String userFullName, String password, String mail, String phone, Integer PESEL, UserType userType){
		setUsername(username);
		setUserFullName(userFullName);
		setPassword(password);
		setMail(mail);
		setPhone(phone);
		setUserType(userType);
		setPESEL(PESEL);		
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	public User asUser() {
		return new User(userFullName, userType);
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the pESEL
	 */
	public Integer getPESEL() {
		return PESEL;
	}

	/**
	 * @param pESEL the pESEL to set
	 */
	public void setPESEL(Integer pESEL) {
		PESEL = pESEL;
	}
}
