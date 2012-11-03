/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import pl.dmcs.whatsupdoc.client.ContentManager;
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
	
	private TextBox loginBox;
	private PasswordTextBox passBox;
	private Label errorPasswordLabel, errorLoginLabel, errorLabel;
	
	/**
	 * @param cm CoontentManager of this class
	 */
	public LoginProvider(ContentManager contentManager){
		super(contentManager);
		
		final AuthenticationServiceAsync auth = GWT.create(AuthenticationService.class);
		
		HorizontalPanel hPanel1 = new HorizontalPanel();
		hPanel1.setStyleName("horizontalPanel");
		
		HorizontalPanel hPanel2 = new HorizontalPanel();
		hPanel2.setStyleName("horizontalPanel");
		
		Label loginLabel = new Label("Login:");
		loginLabel.setStyleName("label");
		Label passwordLabel = new Label("Hasło:");
		passwordLabel.setStyleName("label");
		errorPasswordLabel = new Label();
		errorPasswordLabel.setStyleName("error");
		
		errorLoginLabel = new Label();
		errorLoginLabel.setStyleName("error");
		
		errorLabel = new Label();
		errorLabel.setStyleName("error");
		
		loginBox = new TextBox();
		loginBox.setStyleName("textBox");
		
		passBox = new PasswordTextBox();
		passBox.setStyleName("textBox");
		
		Button login = new Button("Zaloguj");
		login.setStyleName("confirmButton");
		login.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String login = loginBox.getText();
				String password = passBox.getText();
				errorLabel.setText("");
				errorPasswordLabel.setText("");
				errorLoginLabel.setText("");
				if(!FieldVerifier.isValidName(login)){
					errorLoginLabel.setText("Zły format loginu!");
					getCm().drawContent();
					return;
				}
				if(!FieldVerifier.isValidPassword(password)){
					errorPasswordLabel.setText("Zły format hasła!");
					getCm().drawContent();
					return;
				}
				
				auth.authenticate(login, password, new AsyncCallback<Boolean>() {
						
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
		
		hPanel1.add(loginLabel);
		hPanel1.add(loginBox);
		hPanel1.add(errorLoginLabel);
		
		hPanel2.add(passwordLabel);
		hPanel2.add(passBox);
		hPanel2.add(errorPasswordLabel);
		
		mainPanel.add(hPanel1);
		mainPanel.add(hPanel2);
		mainPanel.add(errorLabel);
		mainPanel.add(login);
	}

}
