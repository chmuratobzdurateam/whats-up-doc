package pl.dmcs.whatsupdoc.client.services;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.client.model.Recognition;
import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.shared.Disease;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TreatmentServiceAsync {

	void getRecognitions(String patientKeyString,
			AsyncCallback<ArrayList<Recognition>> callback);

	void addRecognition(Disease disease, String patientKeyString, String doctorKeyString,
			ArrayList<Treatment> treatments, AsyncCallback<Void> callback);

}
