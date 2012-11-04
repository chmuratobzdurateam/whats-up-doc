package pl.dmcs.whatsupdoc.server;

import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.server.datastore.model.PDoctor;
import pl.dmcs.whatsupdoc.server.datastore.model.PPatient;
import pl.dmcs.whatsupdoc.server.datastore.model.PUser;
import pl.dmcs.whatsupdoc.server.datastore.model.PVerifier;
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
	public Boolean authenticate(String login, String password) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try{
			Query query = persister.newQuery(PPatient.class);
			query.declareParameters("String aLogin");
			query.setFilter("login == aLogin");
			List<PPatient> pPatients = (List<PPatient>)query.execute(login);
			
			if(pPatients.size() != 0){
				System.out.println("Patient founded.");
				PPatient pPatient = pPatients.get(0);
				if(pPatient.getPassword().equals(password)){
					this.getThreadLocalRequest().getSession().setAttribute("user", pPatient.asUser());
					return true;
				}
			}
			
			query = persister.newQuery(PDoctor.class);
			query.declareParameters("String aLogin");
			query.setFilter("login == aLogin");
			List<PDoctor> pDoctors = (List<PDoctor>)query.execute(login);
			
			if(pDoctors.size() != 0){
				System.out.println("Doctor founded.");
				PDoctor pDoctor = pDoctors.get(0);
				if(pDoctor.getPassword().equals(password)){
					this.getThreadLocalRequest().getSession().setAttribute("user", pDoctor.asUser());
					return true;
				}
			}
			
			query = persister.newQuery(PVerifier.class);
			query.declareParameters("String aLogin");
			query.setFilter("login == aLogin");
			List<PVerifier> pVerifiers = (List<PVerifier>)query.execute(login);
			
			if(pVerifiers.size() != 0){
				System.out.println("Doctor founded.");
				PVerifier pVerifier = pVerifiers.get(0);
				if(pVerifier.getPassword().equals(password)){
					this.getThreadLocalRequest().getSession().setAttribute("user", pVerifier.asUser());
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
