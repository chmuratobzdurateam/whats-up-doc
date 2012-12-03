/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import java.util.Arrays;

import pl.dmcs.whatsupdoc.shared.FieldVerifier;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * 11-11-2012
 * @author Adrian �pionek, adrian.spionek@gmail.com
 * 
 * 
 */
public class SearchInputField extends Field implements FieldConstraint {

	private SearchFieldType myType;
	private Label title, error;
	private TextBox input;
	private ButtonStatusField searchButton;
	
	/**
	 * @param title - label for field
	 * @param type - what type of information this box is going to look for
	 */
	public SearchInputField(String title, SearchFieldType type){
		super(4, Arrays.asList(new String[]{"title", "input", "error"}));
		this.title = new Label(title);
		this.title.setStyleName("title");
		this.myType = type;
		
		this.input = new TextBox();
		
		this.input.setStyleName("textBox");
		this.error = new Label();
		this.error.setStyleName("error");
		subDiv.get(0).add(this.title);
		subDiv.get(1).add(this.input);
		subDiv.get(2).add(this.error);
	}
	
	@Override
	public boolean checkConstraint() {
		error.setText("");
		switch (this.myType) {
			case PESEL_SEARCH:
				if(FieldVerifier.isValidPESEL(this.input.getText())){
					return true;
				}else{
					error.setText("Podano nieprawidłowy format numeru PESEL.");
					return false;
				}
	
			default:
				return false;
			
		}

	}
	
	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#clear()
	 */
	@Override
	public void clear(){
		error.setText("");
		input.setText("");
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#getValue()
	 */
	@Override
	public String getValue() {
		return this.input.getText();
	}

}
