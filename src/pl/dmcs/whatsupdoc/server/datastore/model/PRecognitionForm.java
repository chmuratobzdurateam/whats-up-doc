package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.dmcs.whatsupdoc.client.model.Recognition;
import pl.dmcs.whatsupdoc.shared.Disease;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Owned;
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PRecognitionForm {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private Disease disease;
	@Persistent
	@Unowned
	private PPatient patient;
	@Persistent
	@Unowned
	private PDoctor doctor;
	@Persistent
	@Owned
	private ArrayList<PTreatment> treatments;
	@Persistent
	private Date date;
	
	public PRecognitionForm(){
		treatments = new ArrayList<PTreatment>();
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
	 * @return the disease
	 */
	public Disease getDisease() {
		return disease;
	}

	/**
	 * @param disease the disease to set
	 */
	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	/**
	 * @return the patient
	 */
	public PPatient getPatient() {
		return patient;
	}

	/**
	 * @param patient the patient to set
	 */
	public void setPatient(PPatient patient) {
		this.patient = patient;
	}

	/**
	 * @return the doctor
	 */
	public PDoctor getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(PDoctor doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return the treatments
	 */
	public ArrayList<PTreatment> getTreatments() {
		return treatments;
	}

	/**
	 * @param treatments the treatments to set
	 */
	public void setTreatments(ArrayList<PTreatment> treatments) {
		this.treatments = treatments;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	public Recognition asRecognition() {
		Recognition recognition = new Recognition();
		recognition.setDate(date);
		recognition.setDisease(disease);
		recognition.setDoctorName(doctor.getName()+" "+doctor.getSurname());
		return recognition;
	}
}
