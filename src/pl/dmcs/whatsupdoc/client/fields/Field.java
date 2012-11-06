/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;

/**
 * 05-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public abstract class Field {

	protected FlowPanel mainPanel;
	protected FlowPanel part1, part2, part3;
	
	Field(){
		mainPanel = new FlowPanel();
		mainPanel.setStylePrimaryName("row");
		part1 = new FlowPanel();
		part1.setStyleName("firstColumn");
		
		part2 = new FlowPanel();
		part2.setStyleName("secondColumn");
		
		part3 = new FlowPanel();
		part3.setStyleName("thirdColumn");
		
		mainPanel.add(part1);
		mainPanel.add(part2);
		mainPanel.add(part3);
	}
	
	/**
	 * @return Panel - return entire content of object
	 */
	public final Panel returnContent(){
		return mainPanel;
	}
	
	/**
	 * @return Object - return value, it can be object name or object itself
	 */
	public abstract Object getValue();
	
	/**
	 * Clear some of content
	 */
	public abstract void clear();
	
}
