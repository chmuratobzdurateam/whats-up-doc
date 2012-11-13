package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.dmcs.whatsupdoc.shared.Symptom;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Owned;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PSymptomTreatmentRates {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private Symptom symptom;
	@Persistent
	@Owned
	private ArrayList<PMedicineRate> medicineRates;
	
	public PSymptomTreatmentRates(){
		medicineRates = new ArrayList<PMedicineRate>();
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the symptom
	 */
	public Symptom getSymptom() {
		return symptom;
	}

	/**
	 * @param symptom the symptom to set
	 */
	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	/**
	 * @return the medicineRates
	 */
	public ArrayList<PMedicineRate> getMedicineRates() {
		return medicineRates;
	}

	/**
	 * @param medicineRates the medicineRates to set
	 */
	public void setMedicineRates(ArrayList<PMedicineRate> medicineRates) {
		this.medicineRates = medicineRates;
	}

}
