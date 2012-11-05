/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.InputField;
import pl.dmcs.whatsupdoc.client.fields.InputFieldType;
import pl.dmcs.whatsupdoc.client.fields.RadioInput;
import pl.dmcs.whatsupdoc.client.services.UserService;
import pl.dmcs.whatsupdoc.client.services.UserServiceAsync;
import pl.dmcs.whatsupdoc.shared.Address;
import pl.dmcs.whatsupdoc.shared.FieldVerifier;
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
	
	private InputField name, surname, password, mail, phone, PESEL, city, street, houseNr;
	private RadioInput userType;
	private Label errorLabel, specialityLabel;
	/*private Label nameLabel, surnameLabel, passwordLabel, mailLabel, phoneLabel, PESEL_Label, userTypeLabel, specialityLabel, errorLabel;
	private Label cityLabel, streetLabel, houseNrLabel;
	private TextBox nameBox, surnameBox, mailBox, phoneBox, PESEL_Box, cityBox, streetBox, houseNrBox;
	private PasswordTextBox passwordBox;
	private RadioButton patient, doctor, verifier;*/
	private ListBox doctorSpeciality;
	private Map<String, Speciality> specialityMap;
	private Button addUser, cancel;
	private ClickHandler userTypeChange = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			reCreatePanel();
		}
	};
	private AsyncCallback<Boolean> loginCallback = new AsyncCallback<Boolean>() {
		
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
		
		specialityMap = new HashMap<String, Speciality>();
		specialityMap.put(Speciality.GINEKOLOG.toString(), Speciality.GINEKOLOG);
		specialityMap.put(Speciality.KARDIOLOG.toString(), Speciality.KARDIOLOG);
		specialityMap.put(Speciality.NEUROLOG.toString(), Speciality.NEUROLOG);
		specialityMap.put(Speciality.UROLOG.toString(), Speciality.UROLOG);
		
		doctorSpeciality = new ListBox();
		doctorSpeciality.setVisibleItemCount(1);
		doctorSpeciality.addItem(Speciality.GINEKOLOG.toString());
		doctorSpeciality.addItem(Speciality.KARDIOLOG.toString());
		doctorSpeciality.addItem(Speciality.NEUROLOG.toString());
		doctorSpeciality.addItem(Speciality.UROLOG.toString());
		doctorSpeciality.setSelectedIndex(0);
		doctorSpeciality.setStyleName("selectBox");
		
		name = new InputField("Imię:", InputFieldType.TEXT_BOX);
		surname = new InputField("Nazwisko:", InputFieldType.TEXT_BOX);
		password = new InputField("Hasło:", InputFieldType.PASSWORD_BOX);
		phone = new InputField("Tel.:", InputFieldType.PHONE_BOX);
		mail = new InputField("Email:", InputFieldType.EMAIL);
		PESEL = new InputField("PESEL:", InputFieldType.PESEL_BOX);
		city = new InputField("Miasto:", InputFieldType.CITY);
		street = new InputField("Ulica:", InputFieldType.STREET);
		houseNr = new InputField("Mieszkanie:", InputFieldType.HOUSE_NR);
		
		userType = new RadioInput("Uprawnienia:", Arrays.asList(new String[] {"Pacjent","Doktor","Weryfikator"}), "userType", 
				Arrays.asList(new Object[] {UserType.PATIENT,UserType.DOCTOR, UserType.VERIFIER}), 
				Arrays.asList(new ClickHandler[]{userTypeChange}));
		
		errorLabel = new Label();
		specialityLabel = new Label("Specjalnosc");
		
		addUser = new Button("Dodaj");
		addUser.setStyleName("confirmButton");
		addUser.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				/*if(widgetsAreNull()){
					logger.log(Level.SEVERE, "Somehow some field is eq null");
					return;
				}*/
				if(!name.checkConstraint() || !surname.checkConstraint() || !password.checkConstraint() || !mail.checkConstraint() || PESEL.checkConstraint()
					|| !phone.checkConstraint()){
					getCm().drawContent();
					return;
				}
				
				
				if(userType.getValue() == UserType.DOCTOR){
					int index = doctorSpeciality.getSelectedIndex();
					if(index==-1){
						errorLabel.setText("Proszę wybrać specjalizację.");
						getCm().drawContent();
						return;
					}
					String sp = doctorSpeciality.getItemText(doctorSpeciality.getSelectedIndex());
					Speciality speciality = specialityMap.get(sp);
					if(speciality==null){
						errorLabel.setText("Proszę wybrać specjalizację.");
						getCm().drawContent();
						return;
					}
					
					userService.addDoctor(name.getValue(), name.getValue(), surname.getValue(), password.getValue(), mail.getValue(), 
						phone.getValue(), PESEL.getValue(), UserType.DOCTOR, speciality, loginCallback);
				}else {
					if(userType.getValue() == UserType.VERIFIER){
						
						userService.addVerifier(name.getValue(), name.getValue(), surname.getValue(), password.getValue(), mail.getValue(), 
								phone.getValue(), PESEL.getValue(), UserType.VERIFIER, loginCallback);
					}else{
						if(!city.checkConstraint() || !street.checkConstraint() || !houseNr.checkConstraint()){
							getCm().drawContent();
							return;
						}
						
						Address addr = new Address();
						addr.setCity(city.getValue());
						addr.setStreet(street.getValue());
						addr.setHouseNumber(houseNr.getValue());
						userService.addPatient(name.getValue(), name.getValue(), surname.getValue(), password.getValue(), mail.getValue(), 
								phone.getValue(), PESEL.getValue(), UserType.PATIENT, addr, loginCallback);

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
		/*if(widgetsAreNull()){
			logger.log(Level.SEVERE, "Somehow some field is eq null");
			return;
		}*/
		errorLabel.setText("");
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
		
	}
	
	/**
	 * Check if there is any null widget.
	 * 
	 * @return boolean true if there is at least one null object, false if all of them are initialized
	 */
	/*private boolean widgetsAreNull(){
		if(nameBox == null || nameLabel == null || surnameBox == null || surnameLabel == null || passwordBox == null || passwordLabel == null
				|| mailBox == null || mailLabel == null || phoneBox == null || phoneLabel == null || PESEL_Box == null || PESEL_Label == null
				|| userTypeLabel == null || patient == null || doctor == null || verifier == null || errorLabel == null || mainPanel == null){
			return true;
		}
		return false;
	}*/
	
	private void addPatientWidgets(){
		mainPanel.add(city.returnContent());
		mainPanel.add(street.returnContent());
		mainPanel.add(houseNr.returnContent());
		
	}
	
	private void addDoctorWidgets(){
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.add(specialityLabel);
		hPanel.add(doctorSpeciality);
		hPanel.setStyleName("horizontalPanel");
		mainPanel.add(hPanel);
	}
	
	/**
	 * Method add widget's to panel.
	 */
	private void reCreatePanel(){
		/*if(widgetsAreNull()){
			logger.log(Level.SEVERE, "Somehow some field is eq null");
			return;
		}*/
		mainPanel.clear();
		
		mainPanel.add(userType.returnContent());
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
