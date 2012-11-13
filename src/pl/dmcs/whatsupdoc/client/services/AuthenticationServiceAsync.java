package pl.dmcs.whatsupdoc.client.services;

import pl.dmcs.whatsupdoc.client.model.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AuthenticationServiceAsync {

	void authenticate(String username, String password,
			AsyncCallback<Boolean> callback);

	void getCurrentLoggedInUser(AsyncCallback<User> callback);

	void isUserLoggedIn(AsyncCallback<Boolean> callback);

}
