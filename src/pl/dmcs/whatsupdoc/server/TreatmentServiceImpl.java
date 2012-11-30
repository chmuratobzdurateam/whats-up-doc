package pl.dmcs.whatsupdoc.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import pl.dmcs.whatsupdoc.client.model.MedicineRate;
import pl.dmcs.whatsupdoc.client.model.Recognition;
import pl.dmcs.whatsupdoc.client.model.RecognitionDetails;
import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.client.services.TreatmentService;
import pl.dmcs.whatsupdoc.server.datastore.model.PDoctor;
import pl.dmcs.whatsupdoc.server.datastore.model.PMedicineRate;
import pl.dmcs.whatsupdoc.server.datastore.model.PPatient;
import pl.dmcs.whatsupdoc.server.datastore.model.PRecognitionForm;
import pl.dmcs.whatsupdoc.server.datastore.model.PSymptomTreatmentRates;
import pl.dmcs.whatsupdoc.server.datastore.model.PTreatment;
import pl.dmcs.whatsupdoc.shared.Disease;
import pl.dmcs.whatsupdoc.shared.Medicine;
import pl.dmcs.whatsupdoc.shared.Persister;
import pl.dmcs.whatsupdoc.shared.Symptom;
import pl.dmcs.whatsupdoc.shared.Tools;
import pl.dmcs.whatsupdoc.shared.TreatmentStatus;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TreatmentServiceImpl extends RemoteServiceServlet implements
		TreatmentService {

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Recognition> getRecognitions(String patientKeyString) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try {
			Key key = KeyFactory.stringToKey(patientKeyString);
			Query queryPatient = persister.newQuery(PPatient.class);
			queryPatient.declareParameters(Key.class.getName() + " aKey");
			queryPatient.setFilter("key == aKey");
			List<PPatient> pPatients = (List<PPatient>) queryPatient
					.execute(key);
			if (pPatients != null) {
				PPatient patient = pPatients.get(0);

				Query queryRecognition = persister
						.newQuery(PRecognitionForm.class);
				queryRecognition.declareParameters("PPatient aPatient");
				queryRecognition.setFilter("patient == aPatient");
				List<PRecognitionForm> pForms = (List<PRecognitionForm>) queryRecognition
						.execute(patient);

				if (pForms.size() != 0) {
					ArrayList<Recognition> recognitions = new ArrayList<Recognition>();
					for (PRecognitionForm pForm : pForms) {
						recognitions.add(pForm.asRecognition());
					}
					return recognitions;
				}
			}

		} finally {
			persister.close();
		}

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Boolean addRecognition(String patientKeyString,
			String doctorKeyString, Disease disease,
			ArrayList<Treatment> treatments) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try {
			Key patientKey = KeyFactory.stringToKey(patientKeyString);
			Query queryPatient = persister.newQuery(PPatient.class);
			queryPatient.declareParameters(Key.class.getName() + " aKey");
			queryPatient.setFilter("key == aKey");
			List<PPatient> pPatients = (List<PPatient>) queryPatient
					.execute(patientKey);

			Key doctorKey = KeyFactory.stringToKey(doctorKeyString);
			Query querydoctor = persister.newQuery(PDoctor.class);
			querydoctor.declareParameters(Key.class.getName() + " aKey");
			querydoctor.setFilter("key == aKey");
			List<PDoctor> pDoctors = (List<PDoctor>) querydoctor
					.execute(doctorKey);

			if ((pPatients != null) && (pDoctors != null)) {
				PPatient patient = pPatients.get(0);
				PDoctor doctor = pDoctors.get(0);

				PRecognitionForm pForm = new PRecognitionForm();
				pForm.setPatient(patient);
				pForm.setDoctor(doctor);
				pForm.setDisease(disease);
				pForm.setDate(Tools.getCurrentDate());

				ArrayList<PTreatment> pTreatments = new ArrayList<PTreatment>();
				for (Treatment treatment : treatments) {
					PTreatment newPTreatment = new PTreatment();
					newPTreatment.setDose(treatment.getDose());
					newPTreatment.setDoseType(treatment.getDoseType());
					newPTreatment.setMedicine(treatment.getMedicines());
					newPTreatment.setSymptom(treatment.getSymptom());
					pTreatments.add(newPTreatment);
				}
				pForm.setTreatments(pTreatments);

				persister.makePersistent(pForm);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			persister.close();
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Boolean updateRecognition(String recognitionKeyString,
			ArrayList<Treatment> treatments) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try {
			Key key = KeyFactory.stringToKey(recognitionKeyString);
			Query queryForm = persister.newQuery(PRecognitionForm.class);
			queryForm.declareParameters(Key.class.getName() + " aKey");
			queryForm.setFilter("key == aKey");
			List<PRecognitionForm> pForms = (List<PRecognitionForm>) queryForm
					.execute(key);
			if (pForms != null) {
				PRecognitionForm pForm = pForms.get(0);

				for (Treatment treatment : treatments) {
					Integer oldTreatmentLength = pForm
							.updateTreatment(treatment);
					updateSymptomTreatmentRates(persister, treatment,
							oldTreatmentLength);
				}
				persister.close();
				return true;
			}
		} finally {
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	private void updateSymptomTreatmentRates(PersistenceManager persister,
			Treatment treatment, Integer oldTreatmentLength) {
		try {
			Query querySymptomTreatmentRates = persister
					.newQuery(PSymptomTreatmentRates.class);
			querySymptomTreatmentRates.declareParameters(Symptom.class
					.getName() + " aSymptom");
			querySymptomTreatmentRates.setFilter("symptom == aSymptom");
			List<PSymptomTreatmentRates> pSTRates = (List<PSymptomTreatmentRates>) querySymptomTreatmentRates
					.execute(treatment.getSymptom());

			if ((pSTRates != null) && (pSTRates.size() > 0)) {
				PSymptomTreatmentRates pSTRate = pSTRates.get(0);
				for (Medicine medicine : treatment.getMedicines()) {
					boolean foundedMedicineRate = false;
					for (PMedicineRate pMedicineRate : pSTRate
							.getMedicineRates()) {
						if (pMedicineRate.getMedicine().equals(medicine)) {
							if (oldTreatmentLength.equals(-1)) { /*
																 * FAILED status
																 * set earlier
																 */
								switch (treatment.getTreatmentStatus()) {
								case UNKNOWN:
									pMedicineRate.onFailedStatusCancel();
									break;
								case SUCCESSFULL:
									pMedicineRate.onFailedStatusCancel();
									pMedicineRate.onSymptomDisappear(treatment
											.getThreatmentLength());
									break;
								}
							} else if (oldTreatmentLength.equals(0)) { /*
																		 * UNKNOWN
																		 * status
																		 * set
																		 * earlier
																		 */
								switch (treatment.getTreatmentStatus()) {
								case FAILED:
									pMedicineRate.onSymptomNotDisappear();
									break;
								case SUCCESSFULL:
									pMedicineRate.onSymptomDisappear(treatment
											.getThreatmentLength());
									break;
								}
							} else { /* SUCCESFULL status set earlier */
								switch (treatment.getTreatmentStatus()) {
								case FAILED:
									pMedicineRate
											.onSuccesfullStatusCancel(oldTreatmentLength);
									pMedicineRate.onSymptomDisappear(treatment
											.getThreatmentLength());
									break;
								case UNKNOWN:
									pMedicineRate
											.onSuccesfullStatusCancel(oldTreatmentLength);
									break;
								case SUCCESSFULL:
									if (!oldTreatmentLength.equals(treatment
											.getThreatmentLength())) {
										Integer treatmentLengthDiff = treatment
												.getThreatmentLength()
												- oldTreatmentLength;
										pMedicineRate
												.onTreatmentLengthChange(treatmentLengthDiff);
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
							persister.makePersistent(newMedicineRate);
							pSTRate.getMedicineRates().add(newMedicineRate);
						}
					}
				}
			} else { /* Doesn't exist PSymptomTreatmentRates for this symptom */
				if (!treatment.getTreatmentStatus().equals(
						TreatmentStatus.UNKNOWN)) {
					PSymptomTreatmentRates newPSTRates = new PSymptomTreatmentRates();
					newPSTRates.setSymptom(treatment.getSymptom());

					ArrayList<PMedicineRate> pMedicineRates = new ArrayList<PMedicineRate>();
					for (Medicine medicine : treatment.getMedicines()) {
						PMedicineRate pMedicineRate = new PMedicineRate();
						pMedicineRate.setMedicine(medicine);
						if (treatment.getTreatmentStatus().equals(
								TreatmentStatus.SUCCESSFULL)) {
							pMedicineRate.onSymptomDisappear(treatment
									.getThreatmentLength());
						} else if (treatment.getTreatmentStatus().equals(
								TreatmentStatus.FAILED)) {
							pMedicineRate.onSymptomNotDisappear();
						}
						pMedicineRates.add(pMedicineRate);
					}
					newPSTRates.setMedicineRates(pMedicineRates);
					persister.makePersistent(newPSTRates);
				}
			}
		} finally {
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public RecognitionDetails getRecognitionDetails(String recognitionKey) {
		PersistenceManager persister = Persister.getPersistenceManager();
		try {
			Key key = KeyFactory.stringToKey(recognitionKey);
			Query queryForm = persister.newQuery(PRecognitionForm.class);
			queryForm.declareParameters(Key.class.getName() + " aKey");
			queryForm.setFilter("key == aKey");
			List<PRecognitionForm> pForms = (List<PRecognitionForm>) queryForm
					.execute(key);
			if (pForms != null) {
				PRecognitionForm pForm = pForms.get(0);
				return pForm.asRecognitionDetails();
			}
		} finally {
			persister.close();
		}

		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MedicineRate> getTopMedicineRates(Symptom symptom,
			Integer topRatesNumber) {
		ArrayList<MedicineRate> topMedicineRates = new ArrayList<MedicineRate>();
		PersistenceManager persister = Persister.getPersistenceManager();
		try {
			Query querySymptomTreatmentRates = persister
					.newQuery(PSymptomTreatmentRates.class);
			querySymptomTreatmentRates.declareParameters(Symptom.class
					.getName() + " aSymptom");
			querySymptomTreatmentRates.setFilter("symptom == aSymptom");
			List<PSymptomTreatmentRates> pSTRates = (List<PSymptomTreatmentRates>) querySymptomTreatmentRates
					.execute(symptom);
			if ((pSTRates != null) && (pSTRates.size() > 0)) {
				PSymptomTreatmentRates pSTR = pSTRates.get(0);
				ArrayList<PMedicineRate> pMedicineRates = pSTR.getMedicineRates();
				float[] tmp2 = new float[pMedicineRates.size()];
                int z=0;
                for(PMedicineRate pMedicineRate: pMedicineRates){
                    tmp2[z]=pMedicineRate.getMedicineRate();
                    z++;
                }
                Arrays.sort(tmp2);
                for(int i=0;i<topRatesNumber;i++){
                    for(PMedicineRate pMedicineRate: pMedicineRates){
                        if(pMedicineRate.getMedicineRate()==tmp2[0])
                        {
                            topMedicineRates.add(pMedicineRate.asMedicineRate());
                            break;
                        }
                    }
                }
                

			}
		} finally {
			persister.close();
		}
		return topMedicineRates;
	}

}
