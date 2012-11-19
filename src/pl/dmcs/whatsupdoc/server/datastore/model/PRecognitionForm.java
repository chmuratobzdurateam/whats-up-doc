package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.dmcs.whatsupdoc.client.model.Recognition;
import pl.dmcs.whatsupdoc.client.model.RecognitionDetails;
import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.shared.Disease;
import pl.dmcs.whatsupdoc.shared.FormStatus;
import pl.dmcs.whatsupdoc.shared.TreatmentStatus;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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
	private String date;
	@Persistent
	private FormStatus formStatus = FormStatus.NOT_APPROVED;
	
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

	public Recognition asRecognition() {
		Recognition recognition = new Recognition();
		recognition.setRecognitionKeyString(KeyFactory.keyToString(getKey()));
		recognition.setDate(getDate());
		recognition.setDisease(getDisease());
		recognition.setDoctorName(getDoctor().getName()+" "+getDoctor().getSurname());
		recognition.setStatusForm(getFormStatus());
		return recognition;
	}

	public Integer updateTreatment(Treatment treatment) {
		boolean isFormApproved = true;
		Integer oldTreatmentLength = 0;
		for(PTreatment pTreatment: treatments){
			if(pTreatment.getSymptom().equals(treatment.getSymptom())){
				oldTreatmentLength = pTreatment.getThreatmentLength();
				pTreatment.setThreatmentLength(treatment.getThreatmentLength());
				pTreatment.setTreatmentStatus(treatment.getTreatmentStatus());
			}
			
			if(pTreatment.getTreatmentStatus().equals(TreatmentStatus.UNKNOWN)){
				isFormApproved = false;
			}
		}
		
		if(isFormApproved){
			setFormStatus(FormStatus.APPROVED);
		}
		
		return oldTreatmentLength;
	}

	/**
	 * @return the formStatus
	 */
	public FormStatus getFormStatus() {
		return formStatus;
	}

	/**
	 * @param formStatus the formStatus to set
	 */
	public void setFormStatus(FormStatus formStatus) {
		this.formStatus = formStatus;
	}

	public RecognitionDetails asRecognitionDetails() {
		RecognitionDetails recognitionDetails = new RecognitionDetails();
		recognitionDetails.setDate(getDate());
		recognitionDetails.setDisease(getDisease());
		recognitionDetails.setDoctorName(getDoctor().getSurname()+" "+getDoctor().getName());
		recognitionDetails.setPatientName(getPatient().getSurname()+" "+getPatient().getName());
		ArrayList<Treatment> treatments = new ArrayList<Treatment>();
		for(PTreatment pTreatment: this.treatments){
			treatments.add(pTreatment.asTreatment());
		}
		recognitionDetails.setTreatments(treatments);
		return recognitionDetails;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
