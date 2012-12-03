/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.ButtonStatusField;
import pl.dmcs.whatsupdoc.client.fields.PatientField;
import pl.dmcs.whatsupdoc.client.fields.SearchFieldType;
import pl.dmcs.whatsupdoc.client.fields.SearchInputField;
import pl.dmcs.whatsupdoc.client.model.Patient;
import pl.dmcs.whatsupdoc.client.services.UserService;
import pl.dmcs.whatsupdoc.client.services.UserServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * 29-10-2012
 * @author Adrian �pionek, adrian.spionek@gmail.com
 * 
 * 
 */
public class SearchProvider extends BodyProvider{
	
	private Label errorLabel;
	private SearchInputField searchField;
	private ButtonStatusField searchButton;
	private FlowPanel searchResult;
	
	/**
	 * @param cm CoontentManager of this class
	 */
	public SearchProvider(ContentManager contentManager){
		super(contentManager);
		final UserServiceAsync userService = GWT.create(UserService.class);
		searchResult = new FlowPanel();
		searchResult.setStyleName("searchResult");
		
		searchButton = new ButtonStatusField("szukaj");
		searchButton.setErrorMessage("Nie znaleziono pacjenta o podanym numerze PESEL.");
		searchButton.setProgressMessage("Trwa wyszukiwanie pacjenta...");
		
		errorLabel = new Label();
		errorLabel.setStyleName("error");
		
		searchButton.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				errorLabel.setText("");
				
				if(!searchField.checkConstraint() ){
					getCm().drawContent();
					return;
				}
				
				searchResult.clear(); // clearing previously found patient
				searchButton.showProgressMessage();
				
				userService.getPatientByPESEL(searchField.getValue(), new AsyncCallback<Patient>() {

					@Override
					public void onFailure(Throwable caught) {
						
						errorLabel.setText("Wystąpił błąd. Spróbuj ponownie.");
					}

					@Override
					public void onSuccess(Patient result) {
						if(result==null){
							searchButton.showErrorMessage();
						}else{
							searchButton.hideMessage();
							String patient = result.getName()+" "+result.getSurname()+" "+result.getPESEL();
							final String PESEL = result.getPESEL();
							/* new button list for Patient Card*/
							ArrayList buttonList = new ArrayList<Button>();
							Button patientCardButton = new Button("Karta Pacjenta");
							patientCardButton.setStyleName("patientCard");
							buttonList.add(patientCardButton);
							
							/* onClick event to change view from patients found by PESEL list to detailed PatientCard of found Patient*/
							patientCardButton.addClickHandler( new ClickHandler() {
								
								@Override
								public void onClick(ClickEvent event) {
									/* Creating new body provider for selected Patient*/
									PatientCardProvider patientCard = new PatientCardProvider(getCm(), PESEL);
									
									/* sending new body for Content Manager and Drawing New View of PatientCard*/
									getCm().setBody(patientCard);
									getCm().drawContent();
									
								}
								
							});
						
							
							PatientField patientField = new PatientField(patient, buttonList);
							searchResult.add(patientField.returnContent());
							
						}
					}
				});
				
			}
		});
		
		searchField = new SearchInputField("Wyszukaj:", SearchFieldType.PESEL_SEARCH);
		

		mainPanel.add(searchField.returnContent());
		mainPanel.add(searchButton.returnContent());
		mainPanel.add(searchResult);
		mainPanel.add(errorLabel);
		getCm().drawContent();
	}

}
