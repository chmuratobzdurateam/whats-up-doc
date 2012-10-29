/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.client.services.AuthenticationServiceAsync;

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
		
		TextBox loginBox = new TextBox();
		PasswordTextBox passBox = new PasswordTextBox();
		
		Button login = new Button("Zaloguj");
		login.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				auth.isUserLoggedIn(new AsyncCallback<Boolean>() {
					
					@Override
					public void onSuccess(Boolean result) {
						BodyProvider b = new BodyProvider(cm);
						cm.setBody(b);
						cm.drawContent();
					}
					
					@Override
					public void onFailure(Throwable caught) {
						BodyProvider b = new BodyProvider(cm);
						cm.setBody(b);
						cm.drawContent();
					}
				});
				
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
