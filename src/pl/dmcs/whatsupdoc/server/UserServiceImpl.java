package pl.dmcs.whatsupdoc.server;

import java.util.List;

import javax.jdo.PersistenceManager;

import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.services.UserService;
import pl.dmcs.whatsupdoc.server.datastore.model.PUser;
import pl.dmcs.whatsupdoc.shared.Persister;
import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6858534888724724619L;

	@Override
	public Boolean addUser(String username, String userFullName,
			String password, String mail, String phone, Integer PESEL, UserType userType) {
		
		PersistenceManager persister = Persister.getPersistenceManager();
		try{
			PUser newUser = new PUser(username, userFullName, password, mail, phone, PESEL, userType);
			persister.makePersistent(newUser);
		} catch (Exception e){
			return false;
		} finally{
			persister.close();
		}
		return true;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByName(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
