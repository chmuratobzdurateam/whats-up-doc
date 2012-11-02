package pl.dmcs.whatsupdoc.client.services;

import java.util.List;

import pl.dmcs.whatsupdoc.client.model.Patient;
import pl.dmcs.whatsupdoc.client.model.PatientCard;
import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.shared.Address;
import pl.dmcs.whatsupdoc.shared.Speciality;
import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	void getAllUsers(AsyncCallback<List<User>> callback);

	void addVerifier(String login, String name, String surname,
			String password, String mail, String phone, String PESEL,
			UserType userType, AsyncCallback<Boolean> callback);

	void addPatient(String login, String name, String surname, String password,
			String mail, String phone, String PESEL, UserType userType,
			Address address, AsyncCallback<Boolean> callback);

	void addDoctor(String login, String name, String surname, String password,
			String mail, String phone, String PESEL, UserType userType,
			Speciality speciality, AsyncCallback<Boolean> callback);

	void getPatientByPESEL(String PESEL, AsyncCallback<Patient> callback);

	void getPatientCard(String PESEL, AsyncCallback<PatientCard> callback);

}
