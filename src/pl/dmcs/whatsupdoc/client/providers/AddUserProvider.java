/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.Arrays;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.InputField;
import pl.dmcs.whatsupdoc.client.fields.InputFieldType;
import pl.dmcs.whatsupdoc.client.fields.RadioField;
import pl.dmcs.whatsupdoc.client.fields.SelectField;
import pl.dmcs.whatsupdoc.client.fields.SelectFieldType;
import pl.dmcs.whatsupdoc.client.services.UserService;
import pl.dmcs.whatsupdoc.client.services.UserServiceAsync;
import pl.dmcs.whatsupdoc.server.datastore.model.PAddress;
import pl.dmcs.whatsupdoc.shared.Speciality;
import pl.dmcs.whatsupdoc.shared.UserType;

/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class AddUserProvider extends BodyProvider {
	private Logger logger = Logger.getLogger("AddUserProvider");
	
	private InputField name, surname, password, mail, phone, PESEL, city, street, houseNr, userLogin;
	private SelectField select;
	private RadioField userType;
	private Label errorLabel;
	private Button addUser, cancel;
	private ClickHandler userTypeChange = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			reCreatePanel();
		}
	};
	private AsyncCallback<Boolean> registerCallback = new AsyncCallback<Boolean>() {
		
		@Override
		public void onSuccess(Boolean result) {
			clearWidgets();
			getCm().drawContent();
		}
		
		@Override
		public void onFailure(Throwable caught) {
			clearWidgets();
			errorLabel.setText("Problem z dodaniem użytkownika do bazy danych.");
			getCm().drawContent();
		}
	};

	/**
	 * @param cm - ContentManager for BodyProvider
	 */
	public AddUserProvider(ContentManager cm) {
		super(cm);
		
		final UserServiceAsync userService = GWT.create(UserService.class);
		
		select = new SelectField("Specjalność:", 1, SelectFieldType.SINGLE_SELECT, Arrays.asList(new String[] {Speciality.GINEKOLOG.toString(),
				Speciality.KARDIOLOG.toString(), Speciality.NEUROLOG.toString(), Speciality.UROLOG.toString()}), 
				Arrays.asList(new Object[] {Speciality.GINEKOLOG, Speciality.KARDIOLOG, Speciality.NEUROLOG, Speciality.UROLOG}));
		
		userLogin = new InputField("Login:", InputFieldType.TEXT_BOX);
		name = new InputField("Imię:", InputFieldType.TEXT_BOX);
		surname = new InputField("Nazwisko:", InputFieldType.TEXT_BOX);
		password = new InputField("Hasło:", InputFieldType.PASSWORD_BOX);
		phone = new InputField("Tel.:", InputFieldType.PHONE_BOX);
		mail = new InputField("Email:", InputFieldType.EMAIL);
		PESEL = new InputField("PESEL:", InputFieldType.PESEL_BOX);
		city = new InputField("Miasto:", InputFieldType.CITY);
		street = new InputField("Ulica:", InputFieldType.STREET);
		houseNr = new InputField("Mieszkanie:", InputFieldType.HOUSE_NR);
		
		userType = new RadioField("Uprawnienia:", Arrays.asList(new String[] {"Pacjent","Doktor","Weryfikator"}), "userType", 
				Arrays.asList(new Object[] {UserType.PATIENT,UserType.DOCTOR, UserType.VERIFIER}), 
				Arrays.asList(new ClickHandler[]{userTypeChange}));
		
		errorLabel = new Label();
		errorLabel.setStyleName("error");
		
		
		addUser = new Button("Dodaj");
		addUser.setStyleName("confirmButton");
		addUser.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				if(!name.checkConstraint() || !surname.checkConstraint() || !password.checkConstraint() || !mail.checkConstraint() 
						|| !PESEL.checkConstraint() || !phone.checkConstraint() || !userLogin.checkConstraint()){
					getCm().drawContent();
					return;
				}
				
				
				if(userType.getValue() == UserType.DOCTOR){
					
					Speciality speciality = (Speciality) select.getValue();
					if(speciality!=null){
						/*userService.addDoctor(name.getValue(), name.getValue(), surname.getValue(), password.getValue(), mail.getValue(), 
								phone.getValue(), PESEL.getValue(), UserType.DOCTOR, speciality, registerCallback);*/
					}
					
				}else {
					if(userType.getValue() == UserType.VERIFIER){
						
						/*userService.addVerifier(name.getValue(), name.getValue(), surname.getValue(), password.getValue(), mail.getValue(), 
								phone.getValue(), PESEL.getValue(), UserType.VERIFIER, registerCallback);*/
					}else{
						/*if(!city.checkConstraint() || !street.checkConstraint() || !houseNr.checkConstraint()){
							getCm().drawContent();
							return;
						}
						
						PAddress addr = new PAddress();
						addr.setCity(city.getValue());
						addr.setStreet(street.getValue());
						addr.setHouseNumber(houseNr.getValue());
						userService.addPatient(name.getValue(), name.getValue(), surname.getValue(), password.getValue(), mail.getValue(), 
								phone.getValue(), PESEL.getValue(), UserType.PATIENT, addr, registerCallback);*/

					}
				}
				
			}
		});
		
		cancel = new Button("Wyczyść");
		cancel.setStyleName("cancelButton");
		cancel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				clearWidgets();
				reCreatePanel();
				getCm().drawContent();
			}
		});
		
		reCreatePanel();
	}
	
	/**
	 * Set all boxes and error to empty value.
	 */
	private void clearWidgets(){

		errorLabel.setText("");
		userLogin.clear();
		name.clear();
		surname.clear();
		city.clear();
		houseNr.clear();
		mail.clear();
		phone.clear();
		PESEL.clear();
		password.clear();
		street.clear();
		userType.clear();
		select.clear();
	}
	
	
	private void addPatientWidgets(){
		mainPanel.add(city.returnContent());
		mainPanel.add(street.returnContent());
		mainPanel.add(houseNr.returnContent());
		
	}
	
	private void addDoctorWidgets(){
		mainPanel.add(select.returnContent());
	}
	
	/**
	 * Method add widget's to panel.
	 */
	private void reCreatePanel(){

		mainPanel.clear();
		
		mainPanel.add(userType.returnContent());
		mainPanel.add(userLogin.returnContent());
		mainPanel.add(name.returnContent());
		mainPanel.add(surname.returnContent());
		mainPanel.add(password.returnContent());
		mainPanel.add(PESEL.returnContent());
		mainPanel.add(mail.returnContent());
		mainPanel.add(phone.returnContent());
		
		
		UserType current = (UserType) userType.getValue();
		if(current == UserType.PATIENT){
			addPatientWidgets();
		}else{
			if(current == UserType.DOCTOR){
				addDoctorWidgets();
			}else{
				
			}
		}
		
		
		mainPanel.add(errorLabel);
		mainPanel.add(addUser);
		mainPanel.add(cancel);
		

	}
	

}
