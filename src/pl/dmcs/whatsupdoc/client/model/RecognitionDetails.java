package pl.dmcs.whatsupdoc.client.model;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

import pl.dmcs.whatsupdoc.shared.Disease;

public class RecognitionDetails implements IsSerializable{
	private Disease disease;
	private String patientName;
	private String doctorName;
	private ArrayList<Treatment> treatments;
	private Date date;
	
	public RecognitionDetails(){
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
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the treatments
	 */
	public ArrayList<Treatment> getTreatments() {
		return treatments;
	}

	/**
	 * @param treatments the treatments to set
	 */
	public void setTreatments(ArrayList<Treatment> treatments) {
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
	
	
}
