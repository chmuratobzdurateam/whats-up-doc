/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.client.ContentManager;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;


/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class PatientMenuProvider extends MenuProvider {
	
	/**
	 * @param manager - ContentManager type
	 */
	public PatientMenuProvider(ContentManager manager){
		super(manager);
		ArrayList<Button> button1LvlList = new ArrayList<Button>();
		Button bb = new Button("AHA");
		bb.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		button1LvlList.add(bb);
		button1LvlList.add(new Button("PatientFirstLevel"));
		button1LvlList.add(new Button("PatientFirstLevel"));
		ArrayList<Button> button2LvlList = new ArrayList<Button>();
		button2LvlList.add(new Button("PatientSecondLevel"));
		
		for(Button b : button1LvlList){
			hFirstPanel.add(b);
		}
		
		for(Button b : button2LvlList){
			hSecondPanel.add(b);
		}
	}
	
}
