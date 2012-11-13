package pl.dmcs.whatsupdoc.server.datastore.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.shared.Gender;
import pl.dmcs.whatsupdoc.shared.UserType;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class PUser {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String login;
	@Persistent
	private String name;
	@Persistent
	private String surname;
	@Persistent
	private String password;
	@Persistent
	private String mail;
	@Persistent
	private String phone;
	@Persistent
	private String PESEL;
	@Persistent
	private UserType userType;
	@Persistent
	private Gender gender;
	
	public PUser(){
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
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


	public User asUser() {
		User user = new User();
		user.setLogin(login);
		user.setName(name);
		user.setSurname(surname);
		user.setUserType(userType);
		return user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
	public String getPESEL() {
		return PESEL;
	}

	/**
	 * @param pESEL the pESEL to set
	 */
	public void setPESEL(String PESEL) {
		this.PESEL = PESEL;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
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
}
