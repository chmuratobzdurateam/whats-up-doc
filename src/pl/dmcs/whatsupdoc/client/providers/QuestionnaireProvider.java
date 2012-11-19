/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.model.RecognitionDetails;
import pl.dmcs.whatsupdoc.client.model.SymptomTreatmentResult;
import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.client.services.TreatmentService;
import pl.dmcs.whatsupdoc.client.services.TreatmentServiceAsync;


public class QuestionnaireProvider extends BodyProvider {

	SymptomTreatmentResult str;
	ArrayList<SymptomTreatmentResult> resultList = new ArrayList<SymptomTreatmentResult>();
	String recognitionKeyString;
	RecognitionDetails details;
	TreatmentService ts;
	String doctorName;
	String patientName;
	java.util.Date date;
	ArrayList<Treatment> treatmentsList = new ArrayList<Treatment>();
	Label doctor, doctor_name, patient, patient_name, date_label,
			date_recognition, symptoms_label, symptoms_name, swistak;

	
private AsyncCallback<RecognitionDetails> questionnaireCallback = new AsyncCallback<RecognitionDetails>() {
		
		@Override
		public void onSuccess(RecognitionDetails rd) {
			
			doctor = new Label();
			doctor.setText("Lekarz: ");
			doctor.setStyleName("first_label");

			doctor_name = new Label();
			doctor_name.setText(rd.getDoctorName());
			doctor_name.setStyleName("second_label");

			patient = new Label();
			patient.setText("Pacjent: ");
			patient.setStyleName("first_label");

			patient_name = new Label();
			patient_name.setText(rd.getPatientName());
			patient_name.setStyleName("second_label");

			date_label = new Label();
			date_label.setText("Data");
			date_label.setStyleName("first_label");

			date_recognition = new Label();
			date_recognition.setText(rd.getDate().toString());
			date_recognition.setStyleName("second_label");

			symptoms_label = new Label();
			symptoms_label.setText("Objawy: ");
			symptoms_label.setStyleName("symptoms_label");

			FlowPanel details = new FlowPanel();
			details.setStyleName("details_questionnaire");
			FlowPanel doctorRow = new FlowPanel();
			doctorRow.setStyleName("doctorRow");
			FlowPanel patientRow = new FlowPanel();
			patientRow.setStyleName("patientRow");
			FlowPanel dateRow = new FlowPanel();
			dateRow.setStyleName("dateRow");

			doctorRow.add(doctor);
			doctorRow.add(doctor_name);
			patientRow.add(patient);
			patientRow.add(patient_name);
			dateRow.add(date_recognition);

			details.add(doctorRow);
			details.add(patientRow);
			details.add(dateRow);

			mainPanel.add(details);
			mainPanel.add(symptoms_label);

			for (Treatment tmp : treatmentsList) {
				FlowPanel treatment_object = new FlowPanel();
				SymptomTreatmentResult str = new SymptomTreatmentResult(tmp);
				resultList.add(str);
				treatment_object = str.treatment_object;
				mainPanel.add(treatment_object);
			}
			
			
			getCm().drawContent();
			
		}
		
		@Override
		public void onFailure(Throwable caught) {			
			Label err = new Label("err");
			mainPanel.add(err);
		}
	};
	
	
	public QuestionnaireProvider(ContentManager cm, String recognitionKeyString) {
		super(cm);

		
		/*  RecognitionDetails rd = new RecognitionDetails();
		  
		  rd.setDoctorName("Pastor Piotr");
		  rd.setPatientName("Nowak Jan");
		//  treatmentsList = rd.getTreatments(); 
		  Treatment nowe = new Treatment();
		  nowe.setSymptom(Symptom.CATARRH);
		  nowe.setTreatmentStatus(TreatmentStatus.SUCCESSFULL);
		  treatmentsList.add(nowe); */
		 
			final TreatmentServiceAsync treatmentService = GWT.create(TreatmentService.class);
			treatmentService.getRecognitionDetails(recognitionKeyString, questionnaireCallback);
		 

	}
}
