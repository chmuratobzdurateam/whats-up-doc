package pl.dmcs.whatsupdoc.client.services;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.client.model.Recognition;
import pl.dmcs.whatsupdoc.client.model.RecognitionDetails;
import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.shared.Disease;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("treatService")
public interface TreatmentService extends RemoteService {
	ArrayList<Recognition> getRecognitions(String patientKeyString);
	Boolean addRecognition(String patientKeyString, String doctorKeyString, Disease disease, ArrayList<Treatment> treatments);
	Boolean updateRecognition(String recognitionKeyString, ArrayList<Treatment> treatments);
	RecognitionDetails getRecognitionDetails(String recognitionKeyString);
}
