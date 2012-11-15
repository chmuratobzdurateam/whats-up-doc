package pl.dmcs.whatsupdoc.client.services;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.client.model.Recognition;
import pl.dmcs.whatsupdoc.client.model.RecognitionDetails;
import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.shared.Disease;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TreatmentServiceAsync {

	void getRecognitions(String patientKeyString,
			AsyncCallback<ArrayList<Recognition>> callback);

	void addRecognition(String patientKeyString, String doctorKeyString,
			Disease disease, ArrayList<Treatment> treatments,
			AsyncCallback<Boolean> callback);

	void updateRecognition(String recognitionKey,
			ArrayList<Treatment> treatments, AsyncCallback<Boolean> callback);

	void getRecognitionDetails(String recognitionKey,
			AsyncCallback<RecognitionDetails> callback);

}