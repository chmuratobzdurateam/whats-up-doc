package pl.dmcs.whatsupdoc.client.model;

import pl.dmcs.whatsupdoc.shared.Disease;
import pl.dmcs.whatsupdoc.shared.FormStatus;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Recognition implements IsSerializable{
	private String recognitionKeyString;
	private Disease disease;
	private String doctorName;
	private String date;
	private FormStatus statusForm;
	
	
	public Recognition(){
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
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * @return the recognitionKeyString
	 */
	public String getRecognitionKeyString() {
		return recognitionKeyString;
	}

	/**
	 * @param recognitionKeyString the recognitionKeyString to set
	 */
	public void setRecognitionKeyString(String recognitionKeyString) {
		this.recognitionKeyString = recognitionKeyString;
	}



	/**
	 * @return the statusForm
	 */
	public FormStatus getStatusForm() {
		return statusForm;
	}



	/**
	 * @param statusForm the statusForm to set
	 */
	public void setStatusForm(FormStatus statusForm) {
		this.statusForm = statusForm;
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
