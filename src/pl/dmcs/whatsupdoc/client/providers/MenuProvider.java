/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;

/**
 * @author Jakub Jeleński
 * @email jjelenski90@gmail.com
 *
 */
public class MenuProvider implements ContentProviderInt {
private HorizontalPanel hPanel;
	
	public MenuProvider(){
		hPanel = new HorizontalPanel();
	}
	
	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.providers.ContentProviderInt#getContent()
	 */
	@Override
	public Panel getContent() {
		return hPanel;
	}

	/**
	 * @param button
	 * 
	 * Add gwt Button to vertical panel
	 */
	public void addMenuBar(Button button){
		hPanel.add(button);
	}
}
