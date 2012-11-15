package pl.dmcs.whatsupdoc.server.datastore.model;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.dmcs.whatsupdoc.shared.Medicine;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PMedicineRate {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private Medicine medicine;
	@Persistent
	private Integer totalTreatmentLength;
	@Persistent
	private Integer treatmentsNumber;
	@Persistent
	private Integer successTreatmentsNumber;
	
	public PMedicineRate(){
		totalTreatmentLength = 0;
		treatmentsNumber = 0;
		successTreatmentsNumber = 0;
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
	
	public void onSymptomDisappear(Integer treatmentLength){
		totalTreatmentLength += treatmentLength;
		treatmentsNumber += 1;
		successTreatmentsNumber += 1;
	}
	
	public void onSymptomNotDisappear(){
		treatmentsNumber += 1;
	}
	
	public Float getSuccessTreatmentsRate(){
		Float successTreatmentsRate = (float)successTreatmentsNumber/treatmentsNumber;
		return successTreatmentsRate;
	}
	
	public Float getAverageTreatmentLength(){
		Float averageTreatmentLength = (float)totalTreatmentLength/successTreatmentsNumber;
		return averageTreatmentLength;
	}
	
	public Float getMedicineRate(){
		return (getSuccessTreatmentsRate()/getAverageTreatmentLength());
	}

	public void onFailedStatusCancel() {
		treatmentsNumber--;
	}

	public void onSuccesfullStatusCancel(Integer oldTreatmentLength) {
		treatmentsNumber--;
		totalTreatmentLength -= oldTreatmentLength;
		successTreatmentsNumber--;
	}

	public void onTreatmentLengthChange(Integer treatmentLengthDiff) {
		totalTreatmentLength += treatmentLengthDiff;
	}
}
