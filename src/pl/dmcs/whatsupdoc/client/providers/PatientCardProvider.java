/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.ButtonStatusField;
import pl.dmcs.whatsupdoc.client.model.PatientCard;
import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.client.services.AuthenticationServiceAsync;
import pl.dmcs.whatsupdoc.client.services.UserService;
import pl.dmcs.whatsupdoc.client.services.UserServiceAsync;
import pl.dmcs.whatsupdoc.shared.Alergy;
import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;


public class PatientCardProvider extends BodyProvider {

	private String key, PESEL, fullPatientName;
	private LinkedHashMap<String, String> infos = new LinkedHashMap<String,String>();
	private FlowPanel row, column;
	private Label information, text;
	private ButtonStatusField buttonA, buttonB, buttonC;
	private ArrayList<String> CSS;
	private StringBuilder stringAlergy;
	private UserType user;
	private AsyncCallback<PatientCard> patientCardCallback = new AsyncCallback<PatientCard>() {
		
		@Override
		public void onSuccess(PatientCard patientCard) {
			infos.put("Imię", patientCard.getName());
			infos.put("Nazwisko", patientCard.getSurname());
			infos.put("Data Urodzin", "25-08-1978");
			infos.put("Pesel", patientCard.getPESEL());
			infos.put("Numer ubezpieczenia", "333-23-54355");
			infos.put("Płeć", "Płeć");
			infos.put("Miasto", patientCard.getAddress().getCity());
			infos.put("Kod Pocztowy", patientCard.getAddress().getPostalCode());
			infos.put("Ulica", patientCard.getAddress().getStreet());
			infos.put("Telefon", patientCard.getPhone());
			infos.put("Email", patientCard.getMail());
			infos.put("Numer domu/mieszkania", patientCard.getAddress().getHouseNumber());
			key = patientCard.getPatientKey();
			fullPatientName=patientCard.getName()+" "+patientCard.getSurname();
			if(patientCard.getAlergies().size()!=0)
				for(Alergy alergy: patientCard.getAlergies()){
					stringAlergy.append(alergy.toString());
				}
			else
				stringAlergy.append("Brak alergii");
			
			infos.put("Alergie", stringAlergy.toString());
			addPatientCardWidgets();
		}
		
		@Override
		public void onFailure(Throwable caught) {			
			Label err = new Label("errPatientCard");
			mainPanel.add(err);
		}
	};
	
	
	/**
	 * @param cm CoontentManager of this class
	 * @param Pesel Patient PESEL number.
	 */
	
	public PatientCardProvider(ContentManager cm, String Pesel) {
		super(cm);
		
		BreadcrumbProvider bF = new BreadcrumbProvider(cm);
		bF.addField(false, "Strona logowania", cm.getBody());
		cm.setBreadcrumb(bF);
		cm.setBody(cm.getBody());
		cm.drawContent();
		
		final AuthenticationServiceAsync auth = GWT.create(AuthenticationService.class);
		
		auth.getCurrentLoggedInUser(new AsyncCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				if(result!=null){
					user = result.getUserType();
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				Label err = new Label("errUserType");
				mainPanel.add(err);
				
			}
		});
		
		this.PESEL=Pesel;
		stringAlergy = new StringBuilder();
		CSS = new ArrayList<String>();
		CSS.add("patientCardRow");
		CSS.add("patientCardColumn");
		CSS.add("name");
		CSS.add("confirmButton");
		CSS.add("value");
		CSS.add("PatientCardRowAlergie");
		final UserServiceAsync userService = GWT.create(UserService.class);
		userService.getPatientCard(PESEL, patientCardCallback);
		
	}
	
	/**
	 * Method add widget's to panel.
	 */
	
	private void addPatientCardWidgets(){
		int a=3;
		row = new FlowPanel();
		row.setStyleName(CSS.get(0));
		
		for (String key : infos.keySet()) {
			if(a==0){
				a=3;
				mainPanel.add(row);
				row = new FlowPanel();
				if(key.equalsIgnoreCase("Alergie"))
					row.setStyleName(CSS.get(5));
				else
					row.setStyleName(CSS.get(0));
			}
			column = new FlowPanel();
			column.setStyleName(CSS.get(1));
				text = new Label();
				text.setStyleName(CSS.get(2));
				text.setText(key);
				information = new Label();
				information.setStyleName(CSS.get(4));
				information.setText(infos.get(key));
			column.add(text);
			column.add(information);
			row.add(column);
			a--;
		}
		
		if(UserType.DOCTOR.equals(user))
		{
			column = new  FlowPanel();
			column.setStyleName(CSS.get(1));
				buttonA = new ButtonStatusField("Dodaj Alergie");
				buttonA.setMainCSS(CSS.get(3));
				buttonA.setErrorMessage("errAlergie");
				buttonA.setProgressMessage("Szukam Alergii");
				//button.setCorrectMessage("I O");
				
				buttonA.getButton().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						
						buttonA.showProgressMessage();
						
						
					}
				});
				
			column.add(buttonA.returnContent());
		row.add(column);
		}
		mainPanel.add(row);
		
		row = new FlowPanel();
		row.setStyleName(CSS.get(0));
			column = new  FlowPanel();
			column.setStyleName(CSS.get(1));
				buttonB = new ButtonStatusField("Historia Rozpoznań");
				buttonB.setMainCSS(CSS.get(3));
				buttonB.setErrorMessage("errHistoriaR");
				buttonB.setProgressMessage("Szukam Historii");
				
				buttonB.getButton().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						buttonB.showProgressMessage();
						
						BodyProvider b = new PatientRecognitionsProvider(getCm(), key);
						getCm().setBody(b);
						getCm().drawContent();
					}
				});
				
			column.add(buttonB.returnContent());
		row.add(column);
			
		row.add(column);
		if(UserType.DOCTOR.equals(user))
		{
			column = new  FlowPanel();
			column.setStyleName(CSS.get(1));
				buttonC = new ButtonStatusField("Dodaj Rozpoznanie");
				buttonC.setMainCSS(CSS.get(3));
				buttonC.setErrorMessage("errDodaj");
				buttonC.setProgressMessage("Dodajam ;D");
				
				buttonC.getButton().addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						buttonC.showProgressMessage();
						
						AddRecognitionProvider login = new AddRecognitionProvider(getCm(),key,fullPatientName);
						getCm().setBody(login);
						getCm().drawContent();
						
					}
				});
				
			column.add(buttonC.returnContent());
		row.add(column);
		}
		
		mainPanel.add(row);
	}
}