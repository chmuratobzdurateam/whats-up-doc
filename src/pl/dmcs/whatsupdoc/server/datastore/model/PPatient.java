package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.smartcardio.CardPermission;

import pl.dmcs.whatsupdoc.client.model.Patient;
import pl.dmcs.whatsupdoc.client.model.PatientCard;
import pl.dmcs.whatsupdoc.shared.Address;
import pl.dmcs.whatsupdoc.shared.Alergy;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class PPatient extends PUser{
	private ArrayList<Alergy> alergies;
	private Address address;
	
	public PPatient(){
	}
	
	/**
	 * @return the alergies
	 */
	public ArrayList<Alergy> getAlergies() {
		return alergies;
	}
	/**
	 * @param alergies the alergies to set
	 */
	public void setAlergies(ArrayList<Alergy> alergies) {
		this.alergies = alergies;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	public Patient asPatient() {
		Patient patient = new Patient();
		patient.setLogin(getLogin());
		patient.setName(getName());
		patient.setSurname(getSurname());
		patient.setPESEL(getPESEL());
		return patient;
	}

	public PatientCard asCardPatient() {
		PatientCard patientCard = new PatientCard();
		patientCard.setName(getName());
		patientCard.setSurname(getSurname());
		patientCard.setAddress(getAddress());
		patientCard.setAlergies(getAlergies());
		patientCard.setPESEL(getPESEL());
		return patientCard;
	}
}
