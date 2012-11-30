package pl.dmcs.whatsupdoc.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.server.datastore.model.PDoctor;
import pl.dmcs.whatsupdoc.server.datastore.model.PPatient;
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
	public User authenticate(String login, String password) {
		PersistenceManager persister = Persister.getPersistenceManager();
		User user = null;
		try {
			Query query = persister.newQuery(PPatient.class);
			query.declareParameters("String aLogin");
			query.setFilter("login == aLogin");
			List<PPatient> pPatients = (List<PPatient>) query.execute(login);

			if (pPatients.size() != 0) {
				System.out.println("Patient founded.");
				PPatient pPatient = pPatients.get(0);
				if (pPatient.getPassword().equals(password)) {
					user = pPatient.asUser();
					this.getThreadLocalRequest().getSession()
							.setAttribute("user", user);
				}
			} else {

				query = persister.newQuery(PDoctor.class);
				query.declareParameters("String aLogin");
				query.setFilter("login == aLogin");
				List<PDoctor> pDoctors = (List<PDoctor>) query.execute(login);

				if (pDoctors.size() != 0) {
					System.out.println("Doctor founded.");
					PDoctor pDoctor = pDoctors.get(0);
					if (pDoctor.getPassword().equals(password)) {
						user = pDoctor.asUser();
						this.getThreadLocalRequest().getSession()
								.setAttribute("user", user);
					}
				} else {
					query = persister.newQuery(PVerifier.class);
					query.declareParameters("String aLogin");
					query.setFilter("login == aLogin");
					List<PVerifier> pVerifiers = (List<PVerifier>) query
							.execute(login);

					if (pVerifiers.size() != 0) {
						System.out.println("Verifier founded.");
						PVerifier pVerifier = pVerifiers.get(0);
						if (pVerifier.getPassword().equals(password)) {
							user = pVerifier.asUser();
							this.getThreadLocalRequest().getSession()
									.setAttribute("user", user);
						}
					}
				}
			}
		} finally {
			persister.close();
		}

		return user;
	}

	@Override
	public Boolean isUserLoggedIn() {
		if (this.getThreadLocalRequest().getSession().getAttribute("user") == null) {
			return false;
		}
		return true;
	}

	@Override
	public User getCurrentLoggedInUser() {
		if (isUserLoggedIn()) {
			User user = (User)this.getThreadLocalRequest().getSession()
					.getAttribute("user");
			return user;
		}
		return null;
	}

	@Override
	public void logOut() {
		this.getThreadLocalRequest().getSession().removeAttribute("user");
	}

}
