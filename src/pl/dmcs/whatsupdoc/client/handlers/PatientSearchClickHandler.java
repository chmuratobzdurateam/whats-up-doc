/**
 * 
 */
package pl.dmcs.whatsupdoc.client.handlers;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.shared.ContentType;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;


/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class PatientSearchClickHandler implements ClickHandler {

	private ContentManager manager;
	
	/**
	 * @param manager - ContentManager to control ContentType and refresh
	 */
	public PatientSearchClickHandler(ContentManager manager) {
		this.manager = manager;
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
	 */
	@Override
	public void onClick(ClickEvent event) {
		this.manager.setCurrentType(ContentType.PATIENT_SEARCH);
		// Here generate and set to manager PatientSearchProvider
		this.manager.drawContent();
	}

}
