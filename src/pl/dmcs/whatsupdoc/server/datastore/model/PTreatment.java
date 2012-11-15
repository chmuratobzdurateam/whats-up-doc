package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.shared.DoseType;
import pl.dmcs.whatsupdoc.shared.Medicine;
import pl.dmcs.whatsupdoc.shared.Symptom;
import pl.dmcs.whatsupdoc.shared.TreatmentStatus;

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
	private Integer threatmentLength = 0;
	@Persistent
	private TreatmentStatus treatmentStatus = TreatmentStatus.UNKNOWN;

	public PTreatment() {
		medicine = new ArrayList<Medicine>();
	}

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
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
	 * @param symptom
	 *            the symptom to set
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
	 * @param medicine
	 *            the medicine to set
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
	 * @param dose
	 *            the dose to set
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
	 * @param doseType
	 *            the doseType to set
	 */
	public void setDoseType(DoseType doseType) {
		this.doseType = doseType;
	}

	/**
	 * @return the threatmentLength
	 */
	public Integer getThreatmentLength() {
		return threatmentLength;
	}

	/**
	 * @param threatmentLength
	 *            the threatmentLength to set
	 */
	public void setThreatmentLength(Integer threatmentLength) {
		this.threatmentLength = threatmentLength;
	}

	/**
	 * @return the treatmentStatus
	 */
	public TreatmentStatus getTreatmentStatus() {
		return treatmentStatus;
	}

	/**
	 * @param treatmentStatus
	 *            the treatmentStatus to set
	 */
	public void setTreatmentStatus(TreatmentStatus treatmentStatus) {
		this.treatmentStatus = treatmentStatus;
		if (treatmentStatus.equals(TreatmentStatus.FAILED)) {
			setThreatmentLength(-1);
		} else if(treatmentStatus.equals(TreatmentStatus.UNKNOWN)){
			setThreatmentLength(0);
		}
	}

	public Treatment asTreatment() {
		Treatment treatment = new Treatment();
		treatment.setDose(getDose());
		treatment.setDoseType(getDoseType());
		treatment.setSymptom(getSymptom());
		treatment.setTreatmentStatus(getTreatmentStatus());
		treatment.setThreatmentLength(getThreatmentLength());
		return treatment;
	}
}
