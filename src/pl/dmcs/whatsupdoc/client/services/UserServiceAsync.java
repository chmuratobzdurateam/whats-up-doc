package pl.dmcs.whatsupdoc.client.services;

import java.util.List;

import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

	void addUser(String username, String userFullName, String password,
			String mail, String phone, Integer PESEL, UserType userType,
			AsyncCallback<Boolean> callback);

	void getAllUsers(AsyncCallback<List<User>> callback);

	void getUserByName(String username, String password,
			AsyncCallback<User> callback);

}
