package pl.dmcs.whatsupdoc.client.services;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.client.model.Recognition;
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
	void addRecognition(Disease disease, String patientKeyString, String doctorKeyString, ArrayList<Treatment> treatments);
}
