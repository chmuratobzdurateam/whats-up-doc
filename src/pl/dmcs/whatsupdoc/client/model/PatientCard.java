package pl.dmcs.whatsupdoc.client.model;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.shared.Alergy;

public class PatientCard extends User{
	private ArrayList<Alergy> alergies;
	private Address address;
	private String patientKey;
	
	public PatientCard(){
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
		this.alergies = new ArrayList<Alergy>();
		for(Alergy alergy: alergies){
			this.alergies.add(alergy);
		}
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

	/**
	 * @return the patientKey
	 */
	public String getPatientKey() {
		return patientKey;
	}

	/**
	 * @param patientKey the patientKey to set
	 */
	public void setPatientKey(String patientKey) {
		this.patientKey = patientKey;
	}
}
