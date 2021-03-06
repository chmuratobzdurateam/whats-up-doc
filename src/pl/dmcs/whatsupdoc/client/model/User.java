package pl.dmcs.whatsupdoc.client.model;

import java.io.Serializable;

import pl.dmcs.whatsupdoc.shared.UserType;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2792102342567875699L;
	private String login;
	private String name;
	private String surname;
	private String PESEL;
	private String phone;
	private String mail;
	private UserType userType;
	private String keyString;
	
	public User(){
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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the pESEL
	 */
	public String getPESEL() {
		return PESEL;
	}

	/**
	 * @param pESEL the pESEL to set
	 */
	public void setPESEL(String pESEL) {
		PESEL = pESEL;
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
	 * @return the keyString
	 */
	public String getKeyString() {
		return keyString;
	}

	/**
	 * @param keyString the keyString to set
	 */
	public void setKeyString(String keyString) {
		this.keyString = keyString;
	}
}
