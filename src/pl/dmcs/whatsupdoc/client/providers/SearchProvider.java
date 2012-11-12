/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.InputField;
import pl.dmcs.whatsupdoc.client.fields.InputFieldType;
import pl.dmcs.whatsupdoc.client.fields.PatientField;
import pl.dmcs.whatsupdoc.client.fields.SearchFieldType;
import pl.dmcs.whatsupdoc.client.fields.SearchInputField;
import pl.dmcs.whatsupdoc.client.model.Patient;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.client.services.AuthenticationServiceAsync;
import pl.dmcs.whatsupdoc.client.services.UserService;
import pl.dmcs.whatsupdoc.client.services.UserServiceAsync;
import pl.dmcs.whatsupdoc.shared.FieldVerifier;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * 29-10-2012
 * @author Adrian Œpionek, adrian.spionek@gmail.com
 * 
 * 
 */
public class SearchProvider extends BodyProvider{
	
	private Label errorLabel;
	private SearchInputField searchField;
	private Button searchButton;
	private FlowPanel searchResult;
	
	/**
	 * @param cm CoontentManager of this class
	 */
	public SearchProvider(ContentManager contentManager){
		super(contentManager);
		final UserServiceAsync userService = GWT.create(UserService.class);
		searchResult = new FlowPanel();
		searchResult.setStyleName("searchResult");
		
		searchButton = new Button("Znajdz");
		errorLabel = new Label();
		errorLabel.setStyleName("error");
		
		searchButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				errorLabel.setText("");
				
				if(!searchField.checkConstraint() ){
					getCm().drawContent();
					return;
				}
				
				searchResult.clear(); // clearing previously found patient
				
				
				userService.getPatientByPESEL(searchField.getValue(), new AsyncCallback<Patient>() {

					@Override
					public void onFailure(Throwable caught) {
						
						errorLabel.setText("Pacjent umarl.. Wystapil blad");
					}

					@Override
					public void onSuccess(Patient result) {
						if(result==null){
							errorLabel.setText("Pacjent umarl.. nic nie da sie zrobic.. a i przy okazji PESEL nie znaleziono");
						}else{
							String patient = result.getName()+" "+result.getSurname()+" "+result.getPESEL();
							PatientField patientField = new PatientField(patient, new ArrayList<Button>());
							searchResult.add(patientField.returnContent());
							
						}
					}
				});
				
			}
		});
		
		searchButton.setStyleName("confirmButton");
		
		searchField = new SearchInputField("Wyszukaj:", SearchFieldType.PESEL_SEARCH, searchButton);
		

		mainPanel.add(searchField.returnContent());
		mainPanel.add(searchResult);
		mainPanel.add(errorLabel);
		getCm().drawContent();
	}

}
