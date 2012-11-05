/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;

/**
 * 05-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class RadioInput extends Field{
	
	private Label title;
	private List<RadioButton> rButtons;
	private Map<String, Object> associatedObjects;
	
	/**
	 * @param title - title of this input
	 * @param titles - list of names for each radio buttons
	 * @param group - name of group
	 * @see RadioInput(String title, List<String> titles, String group, List<Object> obj, List<ClickHandler> clicks)   with clicks and obj = null
	 */
	public RadioInput(String title, List<String> titles, String group){
		this(title, titles, group, null, null);
	}
	
	/**
	 * @param title - title of this input
	 * @param titles - list of names for each radio buttons
	 * @param group - name of group
	 * @param obj - list of object associated with given button
	 * @see RadioInput(String title, List<String> titles, String group, List<Object> obj, List<ClickHandler> clicks) with clicks = null
	 */
	public RadioInput(String title, List<String> titles, String group, List<Object> obj){
		this(title, titles, group, obj, null);
	}
	
	/**
	 * Create RadioInput with as many buttons as titles. Each button can has object associated with it and ClickHandler.
	 * If there is no Handlers then none button will get any handler, if there is less handlers than buttons then 
	 * each will get the same. Buttons can has associated objects iff there is at least one object and obj.size() == titles.size().
	 * Button are mapped 1-to-1 so first titles is mapped to first object, second to second, etc.
	 * 
	 * @param title - title of this input
	 * @param titles - list of names for each radio buttons
	 * @param group - name of group
	 * @param obj - list of object associated with given button
	 * @param clicks - list of ClickHandlers
	 */
	public RadioInput(String title, List<String> titles, String group, List<Object> obj, List<ClickHandler> clicks){
		this.title = new Label(title);
		this.title.setStyleName("l");
		
		this.rButtons = new ArrayList<RadioButton>(titles.size());
		if(obj!=null && obj.size() == titles.size()){
			associatedObjects = new HashMap<String, Object>(obj.size());
			int i = 0;
			for(;i<obj.size();i++){
				associatedObjects.put(titles.get(i), obj.get(i));
			}
		}
		
		int index = 0;
		for(String name : titles){
			
			RadioButton b = new RadioButton(group, name);
			if(rButtons.size()==0){
				b.setValue(true);
				
			}
			b.setStyleName("rb");
			if(clicks != null){
				b.addClickHandler(clicks.get(index));
				if(clicks.size()>=titles.size()){
					index++;
				}
			}
			
			rButtons.add(b);
		}
		
		part1.add(this.title);
		for(RadioButton rb : rButtons){
			
			part2.add(rb);
		}
	}
	
	
	public void clear(){
		int index = 0;
		rButtons.get(index).setValue(true);
		index++;
		for(;index<rButtons.size();index++){
			rButtons.get(index).setValue(false);
		}
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#getValue()
	 */
	@Override
	public Object getValue() {
		if(this.associatedObjects!=null){
			for(RadioButton rb : rButtons){
				if(rb.getValue().booleanValue()) return this.associatedObjects.get(rb.getText());
			}
		}
		for(RadioButton rb : rButtons){
			if(rb.getValue().booleanValue()) return rb.getText();
		}
		return null;
	}

}
