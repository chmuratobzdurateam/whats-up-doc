/**
 * 
 */
package pl.dmcs.whatsupdoc.client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.HashedMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

import pl.dmcs.whatsupdoc.client.providers.ContentProviderInt;
import pl.dmcs.whatsupdoc.client.providers.MenuProvider;

/**
 * @author Jakub Jeleński
 * @email jjelenski90@gmail.com
 * 
 */
public class ContentManager {
	private Map<MenuProvider, String> menus;

	public ContentManager() {
		menus = new HashMap<MenuProvider, String>();
	}

	/**
	 * @param menu
	 * 
	 *            Add unique menu to collection
	 */
	public void addMenu(MenuProvider menu, String where) {
		if (menu == null || where == null) {
			GWT.log("addMenu recived null value");
		} else {
			if (menus.containsKey(menu)) {
				GWT.log("Given menu already exist.");
			} else {
				menus.put(menu, where);
			}
		}
	}

	/**
	 * Draw content into html.
	 */
	public void drawContent() {
		RootPanel panel;
		for (ContentProviderInt p : menus.keySet()) {
			panel = RootPanel.get(menus.get(p));
			if (panel == null) {
				GWT.log("Wrong element id: " + menus.get(p));
			} else {
				panel.add(p.getContent());
			}
		}

	}
}
