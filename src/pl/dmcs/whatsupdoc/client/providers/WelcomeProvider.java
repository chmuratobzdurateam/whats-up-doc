/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.model.User;

/**
 * 30-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class WelcomeProvider extends BodyProvider {

	/**
	 * @param cm
	 */
	public WelcomeProvider(ContentManager cm, User user) {
		super(cm);
		this.drawWaitContent();
		
		FlowPanel fP1 = new FlowPanel();
		fP1.setStyleName("welcomePageImg");
		FlowPanel fP2 = new FlowPanel();
		fP2.setStyleName("welcomePageName");
		FlowPanel fP3 = new FlowPanel();
		fP3.setStyleName("welcomePageText");
		
		Image img = new Image("images/logo_35x35.png");
		img.setStyleName("welcomeImage");
		fP1.add(img);
		
		Label welcomeName = new Label("Witaj "+user.getName()+" "+user.getSurname()+"!");
		welcomeName.setStyleName("welcomeName");
		fP2.add(welcomeName);
		
		Label welcomeText = new Label("Rozpocznij korzystanie z serwisu poprzez wybranie przycisku z górnego menu.");
		welcomeText.setStyleName("welcomeText");
		fP3.add(welcomeText);
		
		mainPanel.add(fP1);
		mainPanel.add(fP2);
		mainPanel.add(fP3);
 	}

}
