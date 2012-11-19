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
	}

	public void setText(String text){
		this.text.setText(text);
	}
}