package pl.dmcs.whatsupdoc.client.services;

import java.util.List;

import pl.dmcs.whatsupdoc.client.model.Patient;
import pl.dmcs.whatsupdoc.client.model.PatientCard;
import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.shared.Address;
import pl.dmcs.whatsupdoc.shared.Speciality;
import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService{
	/**
	 * Adds new Verifier.
	 * @param login User login.
	 * @param name User name.
	 * @param surname User surname.
	 * @param password User password.
	 * @param mail User e-mail address.
	 * @param phone User phone number.
	 * @param PESEL User PESEL number.
	 * @param userType UserType of User.
	 * @return true when user can be add or false if not.
	 */
	Boolean addVerifier(String login, String name, String surname, String password, String mail, String phone, String PESEL, UserType userType);
	
	/**
	 * Adds new Patient.
	 * @param login User login.
	 * @param name User name.
	 * @param surname User surname.
	 * @param password User password.
	 * @param mail User e-mail address.
	 * @param phone User phone number.
	 * @param PESEL User PESEL number.
	 * @param userType UserType of User.
	 * @param address User address.
	 * @return true when user can be add or false if not.
	 */
	Boolean addPatient(String login, String name, String surname, String password, String mail, String phone, String PESEL, UserType userType, Address address);
	
	/**
	 * 
	 * Adds new Doctor.
	 * @param login User login.
	 * @param name User name.
	 * @param surname User surname.
	 * @param password User password.
	 * @param mail User e-mail address.
	 * @param phone User phone number.
	 * @param PESEL User PESEL number.
	 * @param userType UserType of User.
	 * @param speciality Doctor speciality.
	 * @return true when user can be add or false if not.
	 */
	Boolean addDoctor(String login, String name, String surname, String password, String mail, String phone, String PESEL, UserType userType, Speciality speciality);
	
	/**
	 * Returns Patient with entered PESEL number.
	 * @param PESEL User PESEL number.
	 * @return Patient with entered PESEL number.
	 */
	Patient getPatientByPESEL(String PESEL);
	
	/**
	 * Returns Patient Card of Patient with entered PESEL number.
	 * @param PESEL User PESEL number.
	 * @return Patient Card of Patient with entered PESEL number.
	 */
	PatientCard getPatientCard(String PESEL);
	
	/**
	 * Returns all users.
	 * @return All users.
	 */
	List<User> getAllUsers();
}
