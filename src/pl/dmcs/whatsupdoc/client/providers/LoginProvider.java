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
	private Label errorLabel;
	
	/**
	 * @param cm CoontentManager of this class
	 */
	public LoginProvider(ContentManager contentManager){
		super(contentManager);
		
		final AuthenticationServiceAsync auth = GWT.create(AuthenticationService.class);
		
		HorizontalPanel hPanel1 = new HorizontalPanel();
		HorizontalPanel hPanel2 = new HorizontalPanel();
		
		Label loginLabel = new Label("Login:");
		Label passwordLabel = new Label("Hasło:");
		errorLabel = new Label();
		errorLabel.setStyleName("error");
		
		this.loginBox = new TextBox();
		this.passBox = new PasswordTextBox();
		
		Button login = new Button("Zaloguj");
		login.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String login = loginBox.getText();
				String password = passBox.getText();
				if(!FieldVerifier.isValidName(login) || !FieldVerifier.isValidPassword(password)){
					errorLabel.setText("Musisz podać login i hasło");
					mainPanel.add(errorLabel);
					getCm().drawContent();
				}else{
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
							BodyProvider b = new BodyProvider(getCm());
							getCm().setBody(b);
							MenuProvider menu = new VerifierMenuProvider(getCm());
							getCm().setMenu(menu);
							getCm().drawContent();
						}
					});
				}
				
				
			}
		});
		
		hPanel1.add(loginLabel);
		hPanel1.add(loginBox);
		
		hPanel2.add(passwordLabel);
		hPanel2.add(passBox);
		
		mainPanel.add(hPanel1);
		mainPanel.add(hPanel2);
		mainPanel.add(login);
	}

}
