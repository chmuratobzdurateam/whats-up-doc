/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import pl.dmcs.whatsupdoc.client.ContentManager;
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
	
	private Label nameLabel, surnameLabel, passwordLabel, mailLabel, phoneLabel, PESEL_Label, userTypeLabel, errorLabel;
	private TextBox nameBox, surnameBox, mailBox, phoneBox, PESEL_Box;
	private PasswordTextBox passwordBox;
	private RadioButton patient, doctor, verifier;
	private Button addUser, cancel;
	private UserType defaultType = UserType.PATIENT;
	private AsyncCallback<Boolean> loginCallback = new AsyncCallback<Boolean>() {
		
		@Override
		public void onSuccess(Boolean result) {
			clearWidgets();
			errorLabel.setText("Problem z dodaniem użytkownika do bazy danych.");
			getCm().drawContent();
		}
		
		@Override
		public void onFailure(Throwable caught) {
			clearWidgets();
			getCm().drawContent();
		}
	};

	/**
	 * @param cm - ContentManager for BodyProvider
	 */
	public AddUserProvider(ContentManager cm) {
		super(cm);
		
		final UserServiceAsync userService = GWT.create(UserService.class);
		
		nameLabel = new Label("Imię:");
		nameLabel.setStyleName("label");
		
		surnameLabel = new Label("Nazwisko:");
		surnameLabel.setStyleName("label");
		
		passwordLabel = new Label("Hasło:");
		passwordLabel.setStyleName("label");
		
		mailLabel = new Label("E-mail:");
		mailLabel.setStyleName("label");
		
		phoneLabel = new Label("Tel.:");
		phoneLabel.setStyleName("label");
		
		PESEL_Label = new Label("PESEL:");
		PESEL_Label.setStyleName("label");
		
		userTypeLabel = new Label("Uprawnienia:");
		userTypeLabel.setStyleName("label");
		
		errorLabel = new Label();
		errorLabel.setStyleName("error");
		
		nameBox = new TextBox();
		nameBox.setStyleName("textBox");
		
		surnameBox = new TextBox();
		surnameBox.setStyleName("textBox");
		
		mailBox = new TextBox();
		mailBox.setStyleName("textBox");
		
		phoneBox = new TextBox();
		phoneBox.setStyleName("textBox");
		
		PESEL_Box = new TextBox();
		PESEL_Box.setStyleName("textBox");
		
		passwordBox = new PasswordTextBox();
		passwordBox.setStyleName("textBox");
		
		
		patient = new RadioButton("userType", UserType.PATIENT.toString());
		patient.setValue(true);
		patient.setStyleName("radioButton");
		
		doctor = new RadioButton("userType", UserType.DOCTOR.toString());
		doctor.setStyleName("radioButton");
		
		verifier = new RadioButton("userType", UserType.VERIFIER.toString());
		verifier.setStyleName("radioButton");
		
		
		addUser = new Button("Dodaj");
		addUser.setStyleName("confirmButton");
		addUser.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(widgetsAreNull()){
					logger.log(Level.SEVERE, "Somehow some field is eq null");
					return;
				}
				if(!FieldVerifier.isValidName(nameBox.getText())){
					errorLabel.setText("Imie musi być dłuższe niż 3 znaki.");
					getCm().drawContent();
					return;
				}
				if(!FieldVerifier.isValidName(surnameBox.getText())){
					errorLabel.setText("Nazwisko musi być dłuższe niż 3 znaki.");
					getCm().drawContent();
					return;
				}
				if(!FieldVerifier.isValidPassword(passwordBox.getText())){
					errorLabel.setText("Hasło nie może zawierać spacji i musi być dłuższe niż 4 znaki.");
					getCm().drawContent();
					return;
				}
				if(!FieldVerifier.isValidEmail(mailBox.getText())){
					errorLabel.setText("To nie jest poprawny składniowo Email.");
					getCm().drawContent();
					return;
				}
				if(!FieldVerifier.isValidPESEL(PESEL_Box.getText())){
					errorLabel.setText("PESEL musi być dlugości 11 znaków i składać się z samych cyfr.");
					getCm().drawContent();
					return;
				}
				if(!FieldVerifier.isValidPhone(phoneBox.getText())){
					errorLabel.setText("Numer telefonu musi być długości 9 znaków i składać się z samych cyfr.");
					getCm().drawContent();
					return;
				}
				
				if(doctor.getValue().booleanValue()){
					defaultType = UserType.DOCTOR;
					userService.addDoctor(nameBox.getText(), nameBox.getText(), surnameBox.getText(), passwordBox.getText(), mailBox.getText(), 
						phoneBox.getText(), PESEL_Box.getText(), defaultType, Speciality.GINEKOLOG, loginCallback);
				}else {
					if(verifier.getValue().booleanValue()){
						defaultType = UserType.VERIFIER;
						userService.addVerifier(nameBox.getText(), nameBox.getText(), surnameBox.getText(), passwordBox.getText(), mailBox.getText(), 
								phoneBox.getText(), PESEL_Box.getText(), defaultType, loginCallback);
					}else{
						userService.addPatient(nameBox.getText(), nameBox.getText(), surnameBox.getText(), passwordBox.getText(), mailBox.getText(), 
								phoneBox.getText(), PESEL_Box.getText(), defaultType, new Address(), loginCallback);
						defaultType = UserType.PATIENT;
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
				//reCreatePanel();
				getCm().drawContent();
			}
		});
		
		reCreatePanel();
	}
	
	/**
	 * Set all boxes and error to empty value.
	 */
	private void clearWidgets(){
		if(widgetsAreNull()){
			logger.log(Level.SEVERE, "Somehow some field is eq null");
			return;
		}
		errorLabel.setText("");
		nameBox.setText("");
		surnameBox.setText("");
		passwordBox.setText("");
		mailBox.setText("");
		phoneBox.setText("");
		PESEL_Box.setText("");
		patient.setValue(true);
		doctor.setValue(false);
		verifier.setValue(false);
	}
	
	/**
	 * Check if there is any null widget.
	 * 
	 * @return boolean true if there is at least one null object, false if all of them are initialized
	 */
	private boolean widgetsAreNull(){
		if(nameBox == null || nameLabel == null || surnameBox == null || surnameLabel == null || passwordBox == null || passwordLabel == null
				|| mailBox == null || mailLabel == null || phoneBox == null || phoneLabel == null || PESEL_Box == null || PESEL_Label == null
				|| userTypeLabel == null || patient == null || doctor == null || verifier == null || errorLabel == null || mainPanel == null){
			return true;
		}
		return false;
	}
	
	/**
	 * Method add widget's to panel.
	 */
	private void reCreatePanel(){
		if(widgetsAreNull()){
			logger.log(Level.SEVERE, "Somehow some field is eq null");
			return;
		}
		mainPanel.clear();
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setStyleName("horizontalPanel");
		hPanel.add(nameLabel);
		hPanel.add(nameBox);
		mainPanel.add(hPanel);
		
		hPanel = new HorizontalPanel();
		hPanel.setStyleName("horizontalPanel");
		hPanel.add(surnameLabel);
		hPanel.add(surnameBox);
		mainPanel.add(hPanel);
		
		hPanel = new HorizontalPanel();
		hPanel.setStyleName("horizontalPanel");
		hPanel.add(passwordLabel);
		hPanel.add(passwordBox);
		mainPanel.add(hPanel);
		
		hPanel = new HorizontalPanel();
		hPanel.setStyleName("horizontalPanel");
		hPanel.add(PESEL_Label);
		hPanel.add(PESEL_Box);
		mainPanel.add(hPanel);
		
		hPanel = new HorizontalPanel();
		hPanel.setStyleName("horizontalPanel");
		hPanel.add(mailLabel);
		hPanel.add(mailBox);
		mainPanel.add(hPanel);
		
		hPanel = new HorizontalPanel();
		hPanel.setStyleName("horizontalPanel");
		hPanel.add(phoneLabel);
		hPanel.add(phoneBox);
		mainPanel.add(hPanel);
		
		hPanel = new HorizontalPanel();
		hPanel.setStyleName("horizontalPanel");
		hPanel.add(userTypeLabel);
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setStyleName("verticalPanel");
		vPanel.add(patient);
		vPanel.add(doctor);
		vPanel.add(verifier);
		hPanel.add(vPanel);
		mainPanel.add(hPanel);
		
		mainPanel.add(errorLabel);
		
		hPanel = new HorizontalPanel();
		hPanel.setStyleName("horizontalPanel");
		hPanel.add(addUser);
		hPanel.add(cancel);
		
		mainPanel.add(hPanel);

	}
	

}
