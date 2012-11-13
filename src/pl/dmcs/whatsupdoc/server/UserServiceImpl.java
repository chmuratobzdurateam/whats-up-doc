package pl.dmcs.whatsupdoc.server;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import pl.dmcs.whatsupdoc.client.model.Patient;
import pl.dmcs.whatsupdoc.client.model.PatientCard;
import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.services.UserService;
import pl.dmcs.whatsupdoc.server.datastore.model.PAddress;
import pl.dmcs.whatsupdoc.server.datastore.model.PDoctor;
import pl.dmcs.whatsupdoc.server.datastore.model.PPatient;
import pl.dmcs.whatsupdoc.server.datastore.model.PVerifier;
import pl.dmcs.whatsupdoc.shared.Gender;
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
			String PESEL, UserType userType, Gender gender) {
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
			newVerificator.setGender(gender);
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
			String PESEL, UserType userType, String city, String street, String houseNumber, String postalCode, Gender gender) {
		PersistenceManager persister = Persister.getPersistenceManager();
		Transaction tx = persister.currentTransaction();
		try{
			Query query = persister.newQuery(PPatient.class);
			query.declareParameters("String aPESEL");
			query.setFilter("PESEL == aPESEL");
			List<PPatient> pPatients = (List<PPatient>)query.execute(PESEL);
			
			if(pPatients != null){
				if(pPatients.size() != 0){
					return false;
				}
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
			newPatient.setGender(gender);
			
			PAddress newAddress = new PAddress();
			newAddress.setCity(city);
			newAddress.setStreet(street);
			newAddress.setHouseNumber(houseNumber);
			newAddress.setPostalCode(postalCode);
			newPatient.setAddress(newAddress);
			
	        tx.begin();
	        persister.makePersistent(newPatient);
	        tx.commit();
			
		} catch (Exception e){
			e.printStackTrace();
			return false;
		} finally{
			if(tx.isActive()){
				tx.rollback();
			}
			persister.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean addDoctor(String login, String name,
			String surname, String password, String mail, String phone,
			String PESEL, UserType userType, Speciality speciality, Gender gender) {
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
			newDoctor.setGender(gender);
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
