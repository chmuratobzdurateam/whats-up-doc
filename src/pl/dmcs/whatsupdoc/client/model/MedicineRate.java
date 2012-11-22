package pl.dmcs.whatsupdoc.client.model;

import pl.dmcs.whatsupdoc.shared.Medicine;

public class MedicineRate {
	private Medicine medicine;
	private Integer treatmentsNumber;
	private Float averageTreatmentLength;
	private Float successTreatmentRate;
	
	public MedicineRate(){
	}

	/**
	 * @return the medicine
	 */
	public Medicine getMedicine() {
		return medicine;
	}

	/**
	 * @param medicine the medicine to set
	 */
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	/**
	 * @return the treatmentsNumber
	 */
	public Integer getTreatmentsNumber() {
		return treatmentsNumber;
	}

	/**
	 * @param treatmentsNumber the treatmentsNumber to set
	 */
	public void setTreatmentsNumber(Integer treatmentsNumber) {
		this.treatmentsNumber = treatmentsNumber;
	}

	/**
	 * @return the averageTreatmentLength
	 */
	public Float getAverageTreatmentLength() {
		return averageTreatmentLength;
	}

	/**
	 * @param averageTreatmentLength the averageTreatmentLength to set
	 */
	public void setAverageTreatmentLength(Float averageTreatmentLength) {
		this.averageTreatmentLength = averageTreatmentLength;
	}

	/**
	 * @return the successTreatmentRate
	 */
	public Float getSuccessTreatmentRate() {
		return successTreatmentRate;
	}

	/**
	 * @param successTreatmentRate the successTreatmentRate to set
	 */
	public void setSuccessTreatmentRate(Float successTreatmentRate) {
		this.successTreatmentRate = successTreatmentRate;
	}
	
	
}
