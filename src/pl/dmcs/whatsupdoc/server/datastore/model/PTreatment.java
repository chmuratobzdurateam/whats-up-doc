package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.dmcs.whatsupdoc.shared.DoseType;
import pl.dmcs.whatsupdoc.shared.Medicine;
import pl.dmcs.whatsupdoc.shared.Symptom;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PTreatment {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private Symptom symptom;
	@Persistent
	private ArrayList<Medicine> medicine;
	@Persistent
	private Float dose;
	@Persistent
	private DoseType doseType;
	@Persistent
	private Boolean symptomDisappear;
	@Persistent
	private Integer threatmentLength;
	
	public PTreatment(){
		medicine = new ArrayList<Medicine>();
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
	 * @return the medicine
	 */
	public ArrayList<Medicine> getMedicine() {
		return medicine;
	}

	/**
	 * @param medicine the medicine to set
	 */
	public void setMedicine(ArrayList<Medicine> medicine) {
		this.medicine = medicine;
	}

	/**
	 * @return the dose
	 */
	public Float getDose() {
		return dose;
	}

	/**
	 * @param dose the dose to set
	 */
	public void setDose(Float dose) {
		this.dose = dose;
	}

	/**
	 * @return the doseType
	 */
	public DoseType getDoseType() {
		return doseType;
	}

	/**
	 * @param doseType the doseType to set
	 */
	public void setDoseType(DoseType doseType) {
		this.doseType = doseType;
	}

	/**
	 * @return the symptomDisappear
	 */
	public Boolean getSymptomDisappear() {
		return symptomDisappear;
	}

	/**
	 * @param symptomDisappear the symptomDisappear to set
	 */
	public void setSymptomDisappear(Boolean symptomDisappear) {
		this.symptomDisappear = symptomDisappear;
	}

	/**
	 * @return the threatmentLength
	 */
	public Integer getThreatmentLength() {
		return threatmentLength;
	}

	/**
	 * @param threatmentLength the threatmentLength to set
	 */
	public void setThreatmentLength(Integer threatmentLength) {
		this.threatmentLength = threatmentLength;
	}
}
