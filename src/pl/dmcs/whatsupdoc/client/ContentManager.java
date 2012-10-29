/**
 * 
 */
package pl.dmcs.whatsupdoc.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.collections.map.HashedMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import pl.dmcs.whatsupdoc.client.providers.ContentProviderInt;
import pl.dmcs.whatsupdoc.client.providers.MenuProvider;
import pl.dmcs.whatsupdoc.shared.ContentType;



/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class ContentManager {
	private Logger logger = Logger.getLogger("ContentManager");
	
	private MenuProvider menu;
	private ContentType currentType;
	private PanelsManager panels;

	public ContentManager(ContentType type, PanelsManager panels) {
		currentType = type;
		this.panels = panels;
	}

	/**
	 * @return the panels of PanelsManager type
	 */
	public PanelsManager getPanels() {
		return panels;
	}

	/**
	 * @param panels of PanelsManager type
	 */
	public void setPanels(PanelsManager panels) {
		this.panels = panels;
	}



	/**
	 * @return the menu of MenuProvider
	 */
	public MenuProvider getMenu() {
		return menu;
	}


	/**
	 * @param menu of MenuProvider
	 */
	public void setMenu(MenuProvider menu) {
		this.menu = menu;
	}


	/**
	 * @return the currentType
	 */
	public ContentType getCurrentType() {
		return currentType;
	}

	/**
	 * @param currentType the currentType to set
	 */
	public void setCurrentType(ContentType currentType) {
		this.currentType = currentType;
	}

	/**
	 * Draw content into html.
	 */
	public void drawContent() {
		if(panels!=null){
			if(menu!=null){
				panels.drawFirstMenu(menu.getFirstLevel());
				panels.drawSecondMenu(menu.getSecondLevel());
			}
			else{
				logger.log(Level.WARNING, "No menu in Content manager");
			}
		}
		else{
			logger.log(Level.WARNING, "No PanelsManager in ContentManager");
		}

	}
}
