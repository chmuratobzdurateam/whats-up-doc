package pl.dmcs.whatsupdoc.client.model;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import pl.dmcs.whatsupdoc.client.fields.InputField;
import pl.dmcs.whatsupdoc.client.fields.InputFieldType;
import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.shared.TreatmentStatus;


public class SymptomTreatmentResult {

	public FlowPanel treatment_object;
	Treatment treatments;
	int status;
	InputField input;
	
	public InputField getInput() {
		return input;
	}

	public void setInput(InputField input) {
		this.input = input;
	}

	public SymptomTreatmentResult(Treatment treatment) {
		Label symptom_name, first_label, second_label;
		FlowPanel firstRowSymptoms;
		final FlowPanel secondRowsSymptoms;
		this.treatments = treatment;
		RadioButton rb0 = new RadioButton("group_"+ treatment.getSymptom().toString(), "ustapil");
		RadioButton rb1 = new RadioButton("group_"+ treatment.getSymptom().toString(), "nie ustapil");
		RadioButton rb2 = new RadioButton("group_"+ treatment.getSymptom().toString(), "nieokreślone");
		FlowPanel radio = new FlowPanel();
		radio.add(rb0);
		radio.add(rb1);
		radio.add(rb2);
		radio.setStyleName("details_label");
		symptom_name = new Label();
		symptom_name.setText(treatment.getSymptom().toString());
		symptom_name.setStyleName("details_label");

		firstRowSymptoms = new FlowPanel();

		firstRowSymptoms.add(symptom_name);
		firstRowSymptoms.add(radio);
		firstRowSymptoms.setStyleName("firstRow");

		first_label = new Label();
		first_label.setText("po");

		second_label = new Label();
		second_label.setText("dniach");
		first_label.setStyleName("details_label_second");
		second_label.setStyleName("details_label_second");
		
		input = new InputField(null, InputFieldType.NUMBER);
		input.returnContent().setStyleName("details_label_second");
		secondRowsSymptoms = new FlowPanel();
		secondRowsSymptoms.add(first_label);
		secondRowsSymptoms.add(input.returnContent());
		secondRowsSymptoms.add(second_label);
		secondRowsSymptoms.setStyleName("secondRow");

		treatment_object = new FlowPanel();
		treatment_object.add(firstRowSymptoms);
		treatment_object.add(secondRowsSymptoms);
		treatment_object.setStyleName("details");
		if(treatment.getTreatmentStatus().equals(TreatmentStatus.SUCCESSFULL))
		{
			rb0.setValue(true);
			secondRowsSymptoms.setVisible(true);
			input.setValue(treatment.getThreatmentLength().toString());
		}
		else if(treatment.getTreatmentStatus().equals(TreatmentStatus.FAILED))
		{
			rb1.setValue(true);
			secondRowsSymptoms.setVisible(false);
		}
		else if(treatment.getTreatmentStatus().equals(TreatmentStatus.UNKNOWN))
		{
			rb2.setValue(true);
			secondRowsSymptoms.setVisible(false);
		}
		else
		{
			rb2.setValue(true);
			secondRowsSymptoms.setVisible(true);
		}
		
		
		rb0.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				secondRowsSymptoms.setVisible(true);
				treatments.setTreatmentStatus(TreatmentStatus.SUCCESSFULL);
			}
		});
		rb1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				secondRowsSymptoms.setVisible(false);
				treatments.setTreatmentStatus(TreatmentStatus.FAILED);
			}
		});
		rb2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				secondRowsSymptoms.setVisible(false);
				treatments.setTreatmentStatus(TreatmentStatus.UNKNOWN);
			}
		});

	}

	public Treatment getTreatments() {
		return treatments;
	}

	public void setTreatments(Treatment treatments) {
		this.treatments = treatments;
	}

}
