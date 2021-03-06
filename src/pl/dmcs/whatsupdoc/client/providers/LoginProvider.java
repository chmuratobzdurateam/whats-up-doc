/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.logging.Level;
import java.util.logging.Logger;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.ButtonStatusField;
import pl.dmcs.whatsupdoc.client.fields.InputField;
import pl.dmcs.whatsupdoc.client.fields.InputFieldType;
import pl.dmcs.whatsupdoc.client.fields.StatusFieldType;
import pl.dmcs.whatsupdoc.client.model.Patient;
import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.client.services.AuthenticationServiceAsync;
import pl.dmcs.whatsupdoc.shared.FieldVerifier;
import pl.dmcs.whatsupdoc.shared.UserType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class LoginProvider extends BodyProvider{
	private Logger logger = Logger.getLogger("LoginProvider");
	
	private Label errorLabel;
	private InputField loginField, passwordField;
	private ButtonStatusField loginButton;
	
	/**
	 * @param cm CoontentManager of this class
	 */
	public LoginProvider(ContentManager contentManager){
		super(contentManager);
		this.drawWaitContent();
		final AuthenticationServiceAsync auth = GWT.create(AuthenticationService.class);
		
		
		loginField = new InputField("Login:", InputFieldType.TEXT_BOX);
		loginField.setMyError("Login powinien być dłuższy niż 3 znaki.");
		passwordField = new InputField("Hasło:", InputFieldType.PASSWORD_BOX);
		
		errorLabel = new Label();
		errorLabel.setStyleName("error");
		
		loginButton = new ButtonStatusField("Zaloguj");
		loginButton.setErrorMessage("Podano niepoprawne dane.");
		loginButton.setProgressMessage("Trwa sprawdzanie danych...");
		
		loginButton.getButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				//login.setText("Czekaj.");
				loginButton.showProgressMessage();
				errorLabel.setText("");
				
				if(!loginField.checkConstraint() || !passwordField.checkConstraint()){
					loginButton.hideMessage();
					getCm().drawContent();
					return;
				}
				
				
				auth.authenticate(loginField.getValue(), passwordField.getValue(), new AsyncCallback<User>() {
						
					@Override
					public void onSuccess(User result) {
						if(result==null){ // only for now
							//login.setText("Zły login lub hasło.");
							loginButton.showErrorMessage();
							setUpBodyAndMenu(new BodyProvider(getCm()), new VerifierMenuProvider(getCm()));
						}else{
							getCm().getBreadcrumb().clearAll();
							BodyProvider b = new WelcomeProvider(getCm(), result);
							getCm().getBreadcrumb().addField(false, "Powitanie", b);
							switch(result.getUserType()){
							case VERIFIER:
								setUpBodyAndMenu(b, new VerifierMenuProvider(getCm()));
								break;
								
							case DOCTOR:
								setUpBodyAndMenu(b, new DoctorMenuProvider(getCm()));
								break;
							
							case PATIENT:
								String p = result.getPESEL();
								setUpBodyAndMenu(b, new PatientMenuProvider(getCm(), p));
								break;
								
							default:
							}
							/*auth.getCurrentLoggedInUser(new AsyncCallback<User>() {
								
								@Override
								public void onSuccess(User result) {
									
									
								}
								
								@Override
								public void onFailure(Throwable caught) {
									logger.log(Level.WARNING, "Exception while get current user.");
								}
							});*/
						}
						
						getCm().drawContent();
					}
						
					@Override
					public void onFailure(Throwable caught) {
						// Right now it's dummy method. Later it'll clear login and password box and render it again.
						/*BodyProvider b = new BodyProvider(getCm());
						getCm().setBody(b);
						MenuProvider menu = new DoctorMenuProvider(getCm());
						getCm().setMenu(menu);
						getCm().drawContent();*/
						//login.setText("Problem z bazą danych.");
						loginButton.showErrorMessage();
						logger.log(Level.WARNING, "Exception while user authentication.");
					}
				});
				
			}
		});
		
		mainPanel.add(loginField.returnContent());
		mainPanel.add(passwordField.returnContent());
		mainPanel.add(errorLabel);
		mainPanel.add(loginButton.returnContent());
	}
	
	private void setUpBodyAndMenu(BodyProvider body, MenuProvider menu){
		getCm().setBody(body);
		getCm().setMenu(menu);
	}

}
