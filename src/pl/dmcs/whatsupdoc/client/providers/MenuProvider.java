/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;

import pl.dmcs.whatsupdoc.client.ContentManager;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;


/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class MenuProvider implements ContentProviderInt {
	protected FlowPanel hFirstPanel;
	protected FlowPanel hSecondPanel;
	protected ArrayList<String> CSS;
	protected Button firstLevelButton; // Current button from first level
	protected Button secondLevelButton; // Current button from second level
	private ContentManager manager;
	
	/**
	 * @param manager - ContentManager type
	 */
	public MenuProvider(ContentManager manager){
		this.manager = manager;
		CSS = new ArrayList<String>();
		CSS.add("currentMenuButton");
		CSS.add("menuButton");
		hFirstPanel = new FlowPanel();
		
		hSecondPanel = new FlowPanel();
		
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

	/**
	 * @return the manager
	 */
	public ContentManager getManager() {
		return manager;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(ContentManager manager) {
		this.manager = manager;
	}
	
	/**
	 * Method to change currentMenuButton class. It should be call on button
	 * from first level menu, because it reset current button of second level.
	 * 
	 * @param b - button which will get currentMenuButton CSS class
	 */
	protected void setFirstLvlCSS(Button b){
		firstLevelButton.setStyleName(CSS.get(1));
		
		b.setStyleName(CSS.get(0));
		//b.addStyleName(CSS.get(1));
		
		firstLevelButton = b;
		
		if(secondLevelButton!=null){
			secondLevelButton.setStyleName(CSS.get(3));
			secondLevelButton = null;
		}
	}
	
	/**
	 * Method to change currentMenuButton class. It should be call on button
	 * from second level menu, because it doesn't change anything in first level.
	 * 
	 * @param b
	 */
	protected void setSecondLvlCSS(Button b) {
		b.setStyleName(CSS.get(0));
		//b.addStyleName(CSS.get(2));
		if(secondLevelButton==null){
			secondLevelButton = b;
		}else{
			secondLevelButton.setStyleName(CSS.get(1));
			secondLevelButton = b;
		}
		
	}
	
}
