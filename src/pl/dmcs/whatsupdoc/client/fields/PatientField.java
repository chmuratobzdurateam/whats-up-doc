/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import java.util.ArrayList;
import java.util.Arrays;

import pl.dmcs.whatsupdoc.shared.FieldVerifier;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public class PatientField extends Field {

	private Label patient;
	private ArrayList<Button> buttonList;
	
	/**
	 * @param patient - basic patient info
	 * @param buttonList - list of buttons to operate on "selected" patient
	 */
	public PatientField(String patient, ArrayList<Button> buttonList){
		super(2, Arrays.asList(new String[]{"patient", "buttons"}));
		this.patient = new Label(patient);
		this.patient.setStyleName("patient");
		this.buttonList=buttonList;
		
		subDiv.get(0).add(this.patient);
		for (Button b: buttonList){
			subDiv.get(1).add(b);
		}
	}
	
		
	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#clear()
	 */
	
	@Override
	public void clear(){
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#getValue()
	 */
	@Override
	public String getValue() {
		return null;
	}

}
