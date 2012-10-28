package pl.dmcs.whatsupdoc.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.server.datastore.model.PUser;
import pl.dmcs.whatsupdoc.shared.Persister;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AuthenticationServiceImpl extends RemoteServiceServlet implements
		AuthenticationService {

	@Override
	@SuppressWarnings("unchecked")
	public Boolean authenticate(String username, String password) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try{
			Query query = persister.newQuery(PUser.class);
			query.declareParameters("String aUsername");
			query.setFilter("username == aUsername");
			List<PUser> pUsers = (List<PUser>)query.execute(username);
			
			if(pUsers.size() != 0){
				System.out.println("User founded.");
				PUser pUser = pUsers.get(0);
				if(pUser.getPassword().equals(password)){
					this.getThreadLocalRequest().getSession().setAttribute("user", pUser.asUser());
					return true;
				}
			}
		} finally{
			persister.close();
		}
	
		return false;
	}

	@Override
	public Boolean isUserLoggedIn() {
		if(this.getThreadLocalRequest().getSession().getAttribute("user") == null){
			return false;
		}
		return true;
	}

	@Override
	public User getCurrentUser() {
		if(isUserLoggedIn()){
			return (User) this.getThreadLocalRequest().getSession().getAttribute("user");
		}
		return null;
	}

}
