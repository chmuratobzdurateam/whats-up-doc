/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;

/**
 * 06-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class SelectField extends Field {
	
	private Label title;
	private ListBox select;
	private Map<String, Object> itemMap;
	private SelectFieldType myType;
	
	/**
	 * Constructor for SelectField without mapping objects.
	 * 
	 * @param title - title label of our SelectField
	 * @param visibleCount - items visible at once in our box
	 * @param type - is it single select box or multiple select box
	 * @param itemsNames - strings to print in select box
	 * 
	 * @see SelectField(String, int, SelectField, List, List)
	 */
	public SelectField(String title, int visibleCount, SelectFieldType type, List<String> itemsNames) {
		this(title, visibleCount, type, itemsNames, null);
	}
	
	/**
	 * Full constructor.
	 * 
	 * @param title - title label of our SelectField
	 * @param visibleCount - items visible at once in our box
	 * @param type - is it single select box or multiple select box
	 * @param itemsNames - strings to print in select box
	 * @param items - items associated with names given above(what will be returned when selected), if items is null or less than names
	 * then getValue will return selected name 
	 */
	public SelectField(String title, int visibleCount, SelectFieldType type, List<String> itemsNames, List<Object> items) {
		if(items != null && itemsNames.size()==items.size()){
			itemMap = new HashMap<String, Object>(itemsNames.size());
			Iterator<String> name = itemsNames.iterator();
			Iterator<Object> object = items.iterator();
			while(object.hasNext()){
				itemMap.put(name.next(), object.next());
			}
		}
		
		this.myType = type;
		
		this.title = new Label(title);
		this.title.setStyleName("title");
		
		if(this.myType==SelectFieldType.MULTIPLE_SELECT){
			this.select = new ListBox(true);
		}else{
			this.select = new ListBox(false);
		}
		this.select.setStyleName("selectBox");
		
		if(visibleCount<=1){
			this.select.setVisibleItemCount(1);
		}else{
			this.select.setVisibleItemCount(visibleCount);
		}
		
		for(String name : itemsNames){
			this.select.addItem(name);
		}
		
		part1.add(this.title);
		part2.add(select);
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#getValue()
	 */
	@Override
	public Object getValue() {
		switch(myType){
		case SINGLE_SELECT:
			int index = select.getSelectedIndex();
			if(index<0 || index>select.getItemCount()) return null;
			if(itemMap!=null){
				return itemMap.get(select.getValue(index));
			}else{
				return select.getValue(index);
			}
			
		case MULTIPLE_SELECT:
			List<Object> returnList = new ArrayList<Object>();
			for(index = 0; index<select.getItemCount();index++){
				if(select.isItemSelected(index)){
					if(itemMap!=null){
						returnList.add(itemMap.get(select.getValue(index)));
					}else{
						returnList.add(select.getValue(index));
					}
				}
			}
			
		default:
			return null;
				
		}
		
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#clear()
	 */
	@Override
	public void clear() {
		if(select.getItemCount()>0) this.select.setSelectedIndex(0);
	}

}
