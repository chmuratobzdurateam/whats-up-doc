/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;

/**
 * 05-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class RadioField extends Field{
	
	private Label title;
	private List<RadioButton> rButtons;
	private Map<String, Object> associatedObjects;
	
	/**
	 * @param title - title of this input
	 * @param titles - list of names for each radio buttons
	 * @param group - name of group
	 * @see RadioField(String title, List<String> titles, String group, List<Object> obj, List<ClickHandler> clicks)   with clicks and obj = null
	 */
	public RadioField(String title, List<String> titles, String group){
		this(title, titles, group, null, null);
	}
	
	/**
	 * @param title - title of this input
	 * @param titles - list of names for each radio buttons
	 * @param group - name of group
	 * @param obj - list of object associated with given button
	 * @see RadioField(String title, List<String> titles, String group, List<Object> obj, List<ClickHandler> clicks) with clicks = null
	 */
	public RadioField(String title, List<String> titles, String group, List<Object> obj){
		this(title, titles, group, obj, null);
	}
	
	/**
	 * Create RadioField with as many buttons as titles. Each button can has object associated with it and ClickHandler.
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
	public RadioField(String title, List<String> titles, String group, List<Object> obj, List<ClickHandler> clicks){
		super(2, Arrays.asList(new String[]{"firstColumn","secondColumn"}));
		this.title = new Label(title);
		this.title.setStyleName("title");
		
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
			b.setStyleName("radioButton");
			if(clicks != null){
				b.addClickHandler(clicks.get(index));
				if(clicks.size()>=titles.size()){
					index++;
				}
			}
			
			rButtons.add(b);
		}
		
		subDiv.get(0).add(this.title);
		FlowPanel f = subDiv.get(1);
		for(RadioButton rb : rButtons){
			
			f.add(rb);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#clear()
	 */
	@Override
	public void clear(){
		for (RadioButton b : rButtons) {
			b.setValue(false);
		}
		rButtons.get(0).setValue(true);
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
