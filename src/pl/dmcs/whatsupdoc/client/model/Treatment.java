package pl.dmcs.whatsupdoc.client.model;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.shared.DoseType;
import pl.dmcs.whatsupdoc.shared.Medicine;
import pl.dmcs.whatsupdoc.shared.Symptom;
import pl.dmcs.whatsupdoc.shared.TreatmentStatus;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Treatment implements IsSerializable{
	private Symptom symptom;
	private ArrayList<Medicine> medicine = new ArrayList<Medicine>();
	private Float dose;
	private DoseType doseType;
	private Integer threatmentLength;
	private TreatmentStatus treatmentStatus;
	
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
	public ArrayList<Medicine> getMedicines() {
		return medicine;
	}
	/**
	 * @param medicine the medicine to set
	 */
	public void setMedicine(ArrayList<Medicine> medicine) {
		this.medicine = new ArrayList<Medicine>(medicine);
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

	/**
	 * @return the treatmentStatus
	 */
	public TreatmentStatus getTreatmentStatus() {
		return treatmentStatus;
	}

	/**
	 * @param treatmentStatus the treatmentStatus to set
	 */
	public void setTreatmentStatus(TreatmentStatus treatmentStatus) {
		this.treatmentStatus = treatmentStatus;
	}
}
