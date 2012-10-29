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
public class DoctorMenuProvider extends MenuProvider {

	
	/**
	 * @param manager - ContentManager type
	 */
	public DoctorMenuProvider(ContentManager manager) {
		super(manager);
		
		ArrayList<Button> button1LvlList = new ArrayList<Button>();
		Button bb = new Button("AHA");
		bb.addClickHandler(new PatientSearchClickHandler(manager));
		button1LvlList.add(bb);
		button1LvlList.add(new Button("DoctorFirstLevel"));
		button1LvlList.add(new Button("DoctorFirstLevel"));
		ArrayList<Button> button2LvlList = new ArrayList<Button>();
		button2LvlList.add(new Button("DoctorSecondLevel"));
		
		for(Button b : button1LvlList){
			hFirstPanel.add(b);
		}
		
		for(Button b : button2LvlList){
			hSecondPanel.add(b);
		}
	}
}
