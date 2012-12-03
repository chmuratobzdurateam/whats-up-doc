/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import java.util.Arrays;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

/**
 * 03-12-2012
 * @author Mateusz Grabka, mgrabek26@gmail.com
 * 
 * 
 */
public class ButtonStatusField extends Field {
	
	private Label resultLabel = new Label();
	private Button button = new Button();
	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	private String progressMessage;
	private String errorMessage;
	private String correctMessage;
	
	/**
	 * @param buttonText - text that is set in button
	 */
	public ButtonStatusField(String buttonText) {
		super(1, Arrays.asList(new String[] {"buttonColumn"}));
		
		button.setText(buttonText);
		button.setStyleName("action_button");
		
		resultLabel.setStyleName("textLabel");
		
		subDiv.get(0).add(button);
		subDiv.get(0).add(resultLabel);
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
		//subDiv.get(0).setStyleName("labelColum");
	}

	/**
	 * @return the progressMessage
	 */
	public String getProgressMessage() {
		return progressMessage;
	}

	/**
	 * @param progressMessage the progressMessage to set
	 */
	public void setProgressMessage(String progressMessage) {
		this.progressMessage = progressMessage;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the correctMessage
	 */
	public String getCorrectMessage() {
		return correctMessage;
	}

	/**
	 * @param correctMessage the correctMessage to set
	 */
	public void setCorrectMessage(String correctMessage) {
		this.correctMessage = correctMessage;
	}
	
	public void showCorrectMessage(){
		resultLabel.setStyleName("correct_message");
		resultLabel.setText(getCorrectMessage());
		enable();
	}
	
	public void showErrorMessage(){
		resultLabel.setStyleName("error_message");
		resultLabel.setText(getErrorMessage());
		enable();
	}
	
	public void showProgressMessage(){
		resultLabel.setStyleName("progress_message");
		resultLabel.setText(getProgressMessage());
		disable();
	}
	
	public void hideMessage(){
		resultLabel.setText("");
		resultLabel.setStyleName("hidden");
		enable();
	}
	
	public void enable(){
		button.setEnabled(true);
	}
	
	public void disable(){
		button.setEnabled(false);
	}

	public void setColumnStyle(String style) {
		subDiv.get(0).setStyleName(style);
	}
}
