/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import java.util.Arrays;

import org.apache.commons.lang.NotImplementedException;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

/**
 * 16-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class ButtonStatusField extends Field {
	
	private Button button;
	private Label text;
	private final String startText;
	
	/**
	 * @param b - Button given button
	 * @param startText - text that is set at beginning
	 */
	public ButtonStatusField(Button b, String startText) {
		super(2, Arrays.asList(new String[] {"labelColumn", "buttonColumn"}));
		button = b;
		this.startText = startText;
		text = new Label(startText);
		text.setStyleName("textLabel");
		subDiv.get(0).add(text);
		subDiv.get(1).add(button);
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#getValue()
	 */
	@Override
	public Object getValue() {
		//throw new NotImplementedException();
		return null;
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#clear()
	 */
	@Override
	public void clear() {
		text.setText(startText);
		subDiv.get(0).setStyleName("labelColum");
	}

	/**
	 * @param text - change current label text
	 * @deprecated use instead setType
	 */
	@Deprecated
	public void setText(String text){
		this.text.setText(text);
		subDiv.get(0).setStyleName("labelColum");
	}
	
	public void setType(StatusFieldType type){
		if(type!=null){
			this.text.setText(type.toString());
			if(StatusFieldType.WAIT.equals(type)){
				subDiv.get(0).setStyleName("wait");
			}
		}
	}
}
