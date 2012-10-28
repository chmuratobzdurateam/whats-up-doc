package pl.dmcs.whatsupdoc.client.services;

import java.util.List;

import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService{
	Boolean addUser(String username, String userFullName, String password, String mail, String phone, Integer PESEL, UserType userType);
	List<User> getAllUsers();
	User getUserByName(String username, String password);
}
