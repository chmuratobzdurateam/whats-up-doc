/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import java.util.ArrayList;
import java.util.List;

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
	protected List<FlowPanel> subDiv;
	
	Field(int number, List<String> cssList){
		mainPanel = new FlowPanel();
		mainPanel.setStylePrimaryName("row");
		
		subDiv = new ArrayList<FlowPanel>(number);
		
		for(int i=0;i<number;i++){
			FlowPanel fP = new FlowPanel();
			if(cssList.size()==number){
				fP.setStyleName(cssList.get(i));
			}else{
				fP.setStyleName("");
			}
			mainPanel.add(fP);
		}
		
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
