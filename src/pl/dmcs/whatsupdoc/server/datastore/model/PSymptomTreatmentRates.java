package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.shared.Medicine;
import pl.dmcs.whatsupdoc.shared.Symptom;
import pl.dmcs.whatsupdoc.shared.TreatmentStatus;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Owned;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PSymptomTreatmentRates {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private Symptom symptom;
	@Persistent
	@Owned
	private ArrayList<PMedicineRate> medicineRates;

	public PSymptomTreatmentRates() {
		medicineRates = new ArrayList<PMedicineRate>();
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
	 * @return the medicineRates
	 */
	public ArrayList<PMedicineRate> getMedicineRates() {
		return medicineRates;
	}

	/**
	 * @param medicineRates
	 *            the medicineRates to set
	 */
	public void setMedicineRates(ArrayList<PMedicineRate> medicineRates) {
		this.medicineRates = medicineRates;
	}

	/*
	public void updateMedicineRates(Treatment treatment,
			Integer oldTreatmentLength) {
		for (Medicine medicine : treatment.getMedicines()) {
			boolean foundedMedicineRate = false;
			for (PMedicineRate pMedicineRate : medicineRates) {
				if (pMedicineRate.getMedicine().equals(medicine)) {
					if(oldTreatmentLength.equals(-1)){ // FAILED status set earlier
						switch(treatment.getTreatmentStatus()){
						case UNKNOWN:
							pMedicineRate.onFailedStatusCancel();
							break;
						case SUCCESSFULL:
							pMedicineRate.onFailedStatusCancel();
							pMedicineRate.onSymptomDisappear(treatment.getThreatmentLength());
							break;
						}
					} else if(oldTreatmentLength.equals(0)){ // UNKNOWN status set earlier
						switch(treatment.getTreatmentStatus()){
						case FAILED:
							pMedicineRate.onSymptomNotDisappear();
							break;
						case SUCCESSFULL:
							pMedicineRate.onSymptomDisappear(treatment.getThreatmentLength());
							break;
						}
					} else{ //
						switch(treatment.getTreatmentStatus()){
						case FAILED:
							pMedicineRate.onSuccesfullStatusCancel(oldTreatmentLength);
							pMedicineRate.onSymptomDisappear(treatment.getThreatmentLength());
							break;
						case UNKNOWN:
							pMedicineRate.onSuccesfullStatusCancel(oldTreatmentLength);
							break;
						case SUCCESSFULL:
							if(!oldTreatmentLength.equals(treatment.getThreatmentLength())){
								Integer treatmentLengthDiff = treatment.getThreatmentLength() - oldTreatmentLength;
								pMedicineRate.onTreatmentLengthChange(treatmentLengthDiff);
							}
							break;
						}
					}
					
					foundedMedicineRate = true;
					break;
				}
			}

			if (!foundedMedicineRate) {
				if (!treatment.getTreatmentStatus().equals(
						TreatmentStatus.UNKNOWN)) {
					PMedicineRate newMedicineRate = new PMedicineRate();
					newMedicineRate.setMedicine(medicine);
					if (treatment.getTreatmentStatus().equals(
							TreatmentStatus.SUCCESSFULL)) {
						newMedicineRate.onSymptomDisappear(treatment
								.getThreatmentLength());
					} else if (treatment.getTreatmentStatus().equals(
							TreatmentStatus.FAILED)) {
						newMedicineRate.onSymptomNotDisappear();
					}
					medicineRates.add(newMedicineRate);
				}
			}
		}
	}*/

}
