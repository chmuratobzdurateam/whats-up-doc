/**
 * 
 */
package pl.dmcs.whatsupdoc.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;



/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class PanelsManager {
	Logger logger = Logger.getLogger("PanelsManager");
	
	private RootPanel firstMenu;
	private RootPanel secondMenu;
	
	/**
	 * 
	 */
	public PanelsManager() {
		firstMenu = RootPanel.get("nawigacja");
		if(firstMenu==null){
			logger.log(Level.WARNING, "Wrong ID for first level menu");
		}
		secondMenu = RootPanel.get("wstawTekst");
		if(secondMenu==null){
			logger.log(Level.WARNING, "Wrong ID for second level menu");
		}
	}
	
	/**
	 * @param menu - Panel type to draw into html
	 */
	public void drawFirstMenu(Panel menu){
		if(menu==null){
			logger.log(Level.WARNING, "drawFirstMenu Method get null parameter");
			return;
		}
		if(firstMenu != null){
			firstMenu.add(menu);
		}
	}
	
	/**
	 * @param menu - Panel type to draw into html
	 */
	public void drawSecondMenu(Panel menu){
		if(menu==null){
			logger.log(Level.WARNING, "drawSecondMenu Method get null parameter");
			return;
		}
		if(secondMenu != null){
			secondMenu.add(menu);
		}
	}
}
