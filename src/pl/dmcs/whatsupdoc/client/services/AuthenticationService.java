package pl.dmcs.whatsupdoc.client.services;

import pl.dmcs.whatsupdoc.client.model.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("authService")
public interface AuthenticationService extends RemoteService {
	Boolean authenticate(String login, String password);
	Boolean isUserLoggedIn();
	User getCurrentLoggedInUser();
}
