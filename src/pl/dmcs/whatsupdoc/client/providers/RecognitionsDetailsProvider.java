/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.model.RecognitionDetails;
import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.client.services.TreatmentService;
import pl.dmcs.whatsupdoc.client.services.TreatmentServiceAsync;
import pl.dmcs.whatsupdoc.shared.Alergy;
import pl.dmcs.whatsupdoc.shared.Medicine;
import pl.dmcs.whatsupdoc.shared.TreatmentStatus;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;


public class RecognitionsDetailsProvider extends BodyProvider {
	
	private LinkedHashMap<String, String> details = new LinkedHashMap<String,String>();
	private ArrayList<String> symptomDetails = new ArrayList<String>();
	private FlowPanel row;
	private Label information, text;
	private ArrayList<String> CSS, CSS2;
	private StringBuilder stringMedicines;

	private AsyncCallback<RecognitionDetails> recognitionsDetailsCallback = new AsyncCallback<RecognitionDetails>() {
		
		@Override
		public void onSuccess(RecognitionDetails recognitionDetails) {
			details.put("Doktor:", recognitionDetails.getDoctorName());
			details.put("Pacjent:", recognitionDetails.getPatientName());
			details.put("Data:", recognitionDetails.getDate().toString());
			details.put("Choroba", recognitionDetails.getDisease().toString());
			
			if(recognitionDetails.getTreatments()!=null)
				for(Treatment treatment: recognitionDetails.getTreatments()){
					stringMedicines = new StringBuilder();
					symptomDetails.add(treatment.getSymptom().toString());
					symptomDetails.add("Przypisane leki:");
					if(treatment.getMedicines().size()!=0)
						for(Medicine medicines: treatment.getMedicines()){
							stringMedicines.append(medicines.toString());
						}
					else
						stringMedicines.append("Brak przypisanych leków");
					symptomDetails.add(stringMedicines.toString());
					symptomDetails.add("Etap leczenia:");
					if(treatment.getTreatmentStatus().equals(TreatmentStatus.SUCCESSFULL))
						symptomDetails.add("Ustąpił po: "+treatment.getThreatmentLength().toString()+" dniach.");
					else if (treatment.getTreatmentStatus().equals(TreatmentStatus.FAILED))
						symptomDetails.add("Nie ustąpił");
					else
						symptomDetails.add("Nieokreślone");
				}
			else
				symptomDetails.add("Pogubiłem objawy");
			
			addRecognitionsDetailsdWidgets();
		}
		
		@Override
		public void onFailure(Throwable caught) {
			Label err = new Label("errRecognitionDetails");
			mainPanel.add(err);
		}
	};
	
	
	/**
	 * @param cm CoontentManager of this class
	 * @param recognitionKey ????
	 */
	
	public RecognitionsDetailsProvider(ContentManager cm, String recognitionKey) {
		super(cm);
		CSS = new ArrayList<String>();
		CSS.add("recognitionsDetailsRow");
		CSS.add("name");
		CSS.add("value");
		CSS2 = new ArrayList<String>();
		CSS2.add("objaw");
		CSS2.add("przypisane");
		CSS2.add("leki");
		CSS2.add("etap");
		CSS2.add("etapLeczenia");
		//recognitionKey = "aglub19hcHBfaWRyFwsSEFBSZWNvZ25pdGlvbkZvcm0YsAEM";
		final TreatmentServiceAsync userService = GWT.create(TreatmentService.class);
		userService.getRecognitionDetails(recognitionKey, recognitionsDetailsCallback);
		
	}
	
	/**
	 * Method add widget's to panel.
	 */
	
	private void addRecognitionsDetailsdWidgets(){
		
		row = new FlowPanel();
		row.setStyleName(CSS.get(0));
		for (Entry<String, String> item : details.entrySet()) {
			text = new Label();
			text.setStyleName(CSS.get(1));
			text.setText(item.getKey());
			information = new Label();
			information.setStyleName(CSS.get(2));
			information.setText(item.getValue());
			row.add(text);
			row.add(information);
		}
		text = new Label();
		text.setStyleName(CSS.get(1));
		text.setText("Objawy:");
		row.add(text);
		mainPanel.add(row);
		
		int a = 0, parity = 0;
			for (String item : symptomDetails) {
				if(a%5==0){
					a=0;
					mainPanel.add(row);
					row = new FlowPanel();
					switch(parity){
					case 0:
						row.setStyleName(CSS.get(0)+"P");
						parity = 1;
						break;
					case 1:
						row.setStyleName(CSS.get(0)+"N");
						parity = 0;
						break;
					}
				}
				information = new Label();
				information.setStyleName(CSS2.get(a));
				information.setText(item);
				row.add(information);
				a++;
			}
		mainPanel.add(row);
	}
}