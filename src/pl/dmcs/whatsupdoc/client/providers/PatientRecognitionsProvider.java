/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.ListItemField;
import pl.dmcs.whatsupdoc.client.model.Recognition;
import pl.dmcs.whatsupdoc.client.services.TreatmentService;
import pl.dmcs.whatsupdoc.client.services.TreatmentServiceAsync;

/**
 * 17-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class PatientRecognitionsProvider extends BodyProvider {
	
	private String patientKey;
	private List<ListItemField> items;
	private Label timeLabel, doctorLabel, sicknessLabel;
	private Button detailsButton;
	
	/**
	 * 
	 */
	public PatientRecognitionsProvider(ContentManager cm, String key) {
		super(cm);
		final TreatmentServiceAsync userService = GWT.create(TreatmentService.class);
		
		items = new ArrayList<ListItemField>();
		patientKey = key;
		if(key!=null){
			userService.getRecognitions(patientKey, new AsyncCallback<ArrayList<Recognition>>() {
				
				@Override
				public void onSuccess(ArrayList<Recognition> result) {
					if(result!=null){
						List<String> css = Arrays.asList(new String[] {"timeDiv", "personDiv", "diseaseDiv", "buttonDiv"});
						for(Recognition recognition : result){
							ArrayList<Widget> widgets = new ArrayList<Widget>();
							
							timeLabel = new Label(recognition.getDate().toString()); // In future we should use some date format with user local time
							timeLabel.setStyleName("date");
							doctorLabel = new Label(recognition.getDoctorName());
							doctorLabel.setStyleName("personName");
							sicknessLabel = new Label(recognition.getDisease().name());
							sicknessLabel.setStyleName("diseaseName");
							detailsButton = new Button("Szczegóły");
							detailsButton.setStyleName("button");
							detailsButton.addClickHandler(new RecognitionDetailHandler(""));
							
							widgets.add(timeLabel);
							widgets.add(doctorLabel);
							widgets.add(sicknessLabel);
							widgets.add(detailsButton);
							
							items.add(new ListItemField(widgets, css));
						}
					}
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}
			});
		}
		Label timeColumn = new Label("Data rozpoznania");
		timeColumn.setStyleName("time");
		Label doctorColumn = new Label("Imię i nazwisko prowadzącego");
		doctorColumn.setStyleName("doctor");
		Label diseaseColumn = new Label("Choroba");
		diseaseColumn.setStyleName("disease");
		
		ListItemField item = new ListItemField(Arrays.asList(new Widget[]{timeColumn, doctorColumn, diseaseColumn}), 
				Arrays.asList(new String[] {"timeColumn", "doctorColumn", "diseaseColumn"}));
		mainPanel.add(item.returnContent());
		
		for(ListItemField i : items){
			mainPanel.add(i.returnContent());
		}
	}
	
	
	class RecognitionDetailHandler implements ClickHandler{
		
		private String recognitionKey;
		
		/**
		 * @param recognitionKey - string unique to given recognition
		 */
		public RecognitionDetailHandler(String recognitionKey) {
			this.recognitionKey = recognitionKey;
		}

		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		@Override
		public void onClick(ClickEvent event) {
			// TODO when RecognitionDetail implemented create it and draw it
			
		}
		
	}

}
