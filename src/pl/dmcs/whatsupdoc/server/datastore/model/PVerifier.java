package pl.dmcs.whatsupdoc.server.datastore.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PVerifier extends PUser{
	public PVerifier(){
	}
}
