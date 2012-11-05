/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.InputField;
import pl.dmcs.whatsupdoc.client.fields.InputFieldType;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.client.services.AuthenticationServiceAsync;
import pl.dmcs.whatsupdoc.shared.FieldVerifier;

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
	
	private Label errorLabel;
	private InputField loginField, passwordField;
	
	/**
	 * @param cm CoontentManager of this class
	 */
	public LoginProvider(ContentManager contentManager){
		super(contentManager);
		
		final AuthenticationServiceAsync auth = GWT.create(AuthenticationService.class);
		
		
		loginField = new InputField("Login:", InputFieldType.TEXT_BOX);
		passwordField = new InputField("Hasło:", InputFieldType.PASSWORD_BOX);
		
		errorLabel = new Label();
		errorLabel.setStyleName("error");
		
		Button login = new Button("Zaloguj");
		login.setStyleName("confirmButton");
		login.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				errorLabel.setText("");
				
				if(!loginField.checkConstraint() || !passwordField.checkConstraint()){
					getCm().drawContent();
					return;
				}
				
				
				auth.authenticate(loginField.getValue(), passwordField.getValue(), new AsyncCallback<Boolean>() {
						
					@Override
					public void onSuccess(Boolean result) {
						BodyProvider b = new BodyProvider(getCm());
						getCm().setBody(b);
						MenuProvider menu = new VerifierMenuProvider(getCm());
						getCm().setMenu(menu);
							getCm().drawContent();
					}
						
					@Override
					public void onFailure(Throwable caught) {
						// Right now it's dummy method. Later it'll clear login and password box and render it again.
						BodyProvider b = new BodyProvider(getCm());
						getCm().setBody(b);
						MenuProvider menu = new VerifierMenuProvider(getCm());
						getCm().setMenu(menu);
						getCm().drawContent();
					}
				});
				
			}
		});
		
		
		mainPanel.add(loginField.returnContent());
		mainPanel.add(passwordField.returnContent());
		mainPanel.add(errorLabel);
		mainPanel.add(login);
	}

}
