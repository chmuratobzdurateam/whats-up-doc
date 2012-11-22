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
	private RootPanel content;
	private RootPanel breadcrumb;
	
	/**
	 * Default constructor
	 */
	public PanelsManager() {
		firstMenu = RootPanel.get("menu_level_1");
		if(firstMenu==null){
			logger.log(Level.SEVERE, "Wrong ID for first level menu");
		}
		secondMenu = RootPanel.get("menu_level_2");
		if(secondMenu==null){
			logger.log(Level.SEVERE, "Wrong ID for second level menu");
		}
		content = RootPanel.get("contents");
		if(content==null){
			logger.log(Level.SEVERE, "Wrong ID for page content");
		}
		breadcrumb = RootPanel.get("belt");
		if(breadcrumb==null){
			logger.log(Level.SEVERE, "Wrong ID for page content");
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
			firstMenu.clear();
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
			secondMenu.clear();
			secondMenu.add(menu);
		}
	}
	
	/**
	 * @param body - Panel to draw into html
	 */
	public void drawBody(Panel body){
		if(body==null){
			logger.log(Level.WARNING, "drawBody Method get null parameter");
			return;
		}
		if(content!=null){
			content.clear();
			content.add(body);
		}
	}
	
	public void drawBreadcrumb(Panel breadcrumb){
		if(breadcrumb==null){
			logger.log(Level.WARNING, "drawBreadcrumb Method get null parameter");
			return;
		}
		if(this.breadcrumb!=null){
			this.breadcrumb.clear();
			this.breadcrumb.add(breadcrumb);
		}
	}
}
