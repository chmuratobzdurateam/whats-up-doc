/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.handlers.PatientSearchClickHandler;


/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class VerifierMenuProvider extends MenuProvider {

	/**
	 * @param manager - ContentManager type
	 */
	public VerifierMenuProvider(ContentManager manager) {
		super(manager);
		ArrayList<Button> button1LvlList = new ArrayList<Button>();
		Button b = new Button("I've no idea what am I doing here.");
		b.addClickHandler(new PatientSearchClickHandler(manager));
		button1LvlList.add(b);
	}

	
}
