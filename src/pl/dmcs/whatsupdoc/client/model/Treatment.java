package pl.dmcs.whatsupdoc.client.model;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.shared.DoseType;
import pl.dmcs.whatsupdoc.shared.Medicine;
import pl.dmcs.whatsupdoc.shared.Symptom;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Treatment implements IsSerializable{
	private Symptom symptom;
	private ArrayList<Medicine> medicine;
	private Float dose;
	private DoseType doseType;
	private Boolean symptomDisappear;
	private Integer threatmentLength;
	
	public Treatment(){
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
