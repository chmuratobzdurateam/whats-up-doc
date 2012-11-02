package pl.dmcs.whatsupdoc.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import pl.dmcs.whatsupdoc.client.model.Patient;
import pl.dmcs.whatsupdoc.client.model.PatientCard;
import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.services.UserService;
import pl.dmcs.whatsupdoc.server.datastore.model.PDoctor;
import pl.dmcs.whatsupdoc.server.datastore.model.PPatient;
import pl.dmcs.whatsupdoc.server.datastore.model.PVerifier;
import pl.dmcs.whatsupdoc.shared.Address;
import pl.dmcs.whatsupdoc.shared.Persister;
import pl.dmcs.whatsupdoc.shared.Speciality;
import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6858534888724724619L;



	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean addVerifier(String login, String name,
			String surname, String password, String mail, String phone,
			String PESEL, UserType userType) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try{
			Query query = persister.newQuery(PVerifier.class);
			query.declareParameters("String aPESEL");
			query.setFilter("PESEL == aPESEL");
			List<PVerifier> pVerifiers = (List<PVerifier>)query.execute(PESEL);
			
			if(pVerifiers.size() != 0){
				return false;
			}
			
			PVerifier newVerificator = new PVerifier();
			newVerificator.setLogin(login);
			newVerificator.setName(name);
			newVerificator.setSurname(surname);
			newVerificator.setMail(mail);
			newVerificator.setPassword(password);
			newVerificator.setPESEL(PESEL);
			newVerificator.setPhone(phone);
			newVerificator.setUserType(userType);
			persister.makePersistent(newVerificator);
		} catch (Exception e){
			return false;
		} finally{
			persister.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean addPatient(String login, String name,
			String surname, String password, String mail, String phone,
			String PESEL, UserType userType, Address address) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try{
			Query query = persister.newQuery(PPatient.class);
			query.declareParameters("String aPESEL");
			query.setFilter("PESEL == aPESEL");
			List<PPatient> pPatients = (List<PPatient>)query.execute(PESEL);
			
			if(pPatients.size() != 0){
				return false;
			}
			
			PPatient newPatient = new PPatient();
			newPatient.setLogin(login);
			newPatient.setName(name);
			newPatient.setSurname(surname);
			newPatient.setMail(mail);
			newPatient.setPassword(password);
			newPatient.setPESEL(PESEL);
			newPatient.setPhone(phone);
			newPatient.setUserType(userType);
			newPatient.setAddress(address);
			persister.makePersistent(newPatient);
		} catch (Exception e){
			return false;
		} finally{
			persister.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean addDoctor(String login, String name,
			String surname, String password, String mail, String phone,
			String PESEL, UserType userType, Speciality speciality) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try{
			Query query = persister.newQuery(PDoctor.class);
			query.declareParameters("String aPESEL");
			query.setFilter("PESEL == aPESEL");
			List<PDoctor> pDoctors = (List<PDoctor>)query.execute(PESEL);
			
			if(pDoctors.size() != 0){
				return false;
			}
			
			PDoctor newDoctor = new PDoctor();
			newDoctor.setLogin(login);
			newDoctor.setName(name);
			newDoctor.setSurname(surname);
			newDoctor.setMail(mail);
			newDoctor.setPassword(password);
			newDoctor.setPESEL(PESEL);
			newDoctor.setPhone(phone);
			newDoctor.setUserType(userType);
			newDoctor.setSpeciality(speciality);
			persister.makePersistent(newDoctor);
		} catch (Exception e){
			return false;
		} finally{
			persister.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Patient getPatientByPESEL(String PESEL) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try{
			Query query = persister.newQuery(PPatient.class);
			query.declareParameters("String aPESEL");
			query.setFilter("PESEL == aPESEL");
			List<PPatient> pPatients = (List<PPatient>)query.execute(PESEL);
			
			if(pPatients.size() != 0){
				PPatient pPatient = pPatients.get(0);
				if(pPatient.getPESEL().equals(PESEL)){
					return pPatient.asPatient();
				}
			}
		} finally{
			persister.close();
		}
	
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PatientCard getPatientCard(String PESEL) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try{
			Query query = persister.newQuery(PPatient.class);
			query.declareParameters("String aPESEL");
			query.setFilter("PESEL == aPESEL");
			List<PPatient> pPatients = (List<PPatient>)query.execute(PESEL);
			
			if(pPatients.size() != 0){
				PPatient pPatient = pPatients.get(0);
				if(pPatient.getPESEL().equals(PESEL)){
					return pPatient.asCardPatient();
				}
			}
		} finally{
			persister.close();
		}
	
		return null;
	}

}
