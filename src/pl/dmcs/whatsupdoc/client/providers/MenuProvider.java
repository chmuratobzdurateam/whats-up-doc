/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import pl.dmcs.whatsupdoc.client.ContentManager;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;


/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class MenuProvider implements ContentProviderInt {
	protected HorizontalPanel hFirstPanel;
	protected HorizontalPanel hSecondPanel;
	protected ContentManager manager;
	
	/**
	 * @param manager - ContentManager type
	 */
	public MenuProvider(ContentManager manager){
		this.manager = manager;
		hFirstPanel = new HorizontalPanel();
		hSecondPanel = new HorizontalPanel();
	}
	
	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.providers.ContentProviderInt#getContent()
	 */
	@Override
	public Panel getContent() {
		return hFirstPanel;
	}
	
	/**
	 * @return first level menu of HorizontalPanel type
	 */
	public Panel getFirstLevel() {
		return hFirstPanel;
	}
	
	/**
	 * @return second level menu of HorizontalPanel type
	 */
	public Panel getSecondLevel() {
		return hSecondPanel;
	}
}
