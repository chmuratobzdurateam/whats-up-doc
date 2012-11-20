/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.Iterator;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.SelectField;
import pl.dmcs.whatsupdoc.client.fields.SelectFieldType;
import pl.dmcs.whatsupdoc.client.model.Treatment;
import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.client.services.AuthenticationServiceAsync;
import pl.dmcs.whatsupdoc.client.services.TreatmentService;
import pl.dmcs.whatsupdoc.client.services.TreatmentServiceAsync;
import pl.dmcs.whatsupdoc.shared.Disease;
import pl.dmcs.whatsupdoc.shared.Medicine;
import pl.dmcs.whatsupdoc.shared.Symptom;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * 29-10-2012
 * @author Adrian �pionek, adrian.spionek@gmail.com
 * 
 * 
 */
public class AddRecognitionProvider extends BodyProvider{
	
	private Button addRecognition;
	private Button addSymptom;
	
	private Label doctorFullName, doctorFullNameLabel;
	private Label patientFullName, patientFullNameLabel;
	private FlowPanel patientAndDoctorLabel;
	
	private String doctorKeyString, doctorFullNameString;
	
	private SelectField diseaseSelect;
	private ArrayList<SelectField> medicineSelectFieldList;
	private ArrayList<SelectField> symptomSelectFieldList;
	
	ArrayList<String> medicineList = new ArrayList<String>();
	ArrayList<String> symptomList = new ArrayList<String>();
	ArrayList<String> diseaseList = new ArrayList<String>();
	
	
	/**
	 * @param cm CoontentManager of this class
	 */
	public AddRecognitionProvider(ContentManager contentManager,final String patientKeyString,String patientFullName){
		super(contentManager);
		
		final AuthenticationServiceAsync authService = GWT.create(AuthenticationService.class);
		final TreatmentServiceAsync treatService = GWT.create(TreatmentService.class);
		
		/* initializing medicine , diseases and symptom lists*/
		for(Medicine d: Medicine.values()){
			medicineList.add(d.toString());
		}
		
		for(Symptom d: Symptom.values()){
			symptomList.add(d.toString());
		}
		
		for(Disease d: Disease.values()){
			diseaseList.add(d.toString());
		}
		
		
		doctorFullNameLabel = new Label("Lekarz:");
		doctorFullNameLabel.setText("Lekarz:");
		patientFullNameLabel = new Label("Pacjent:");
		patientFullNameLabel.setText("Pacjent:");
		this.patientFullName = new Label(patientFullName);
		this.patientFullName.setText(patientFullName);
		
		diseaseSelect = new SelectField("Choroba:",1,SelectFieldType.SINGLE_SELECT,diseaseList);
		medicineSelectFieldList = new ArrayList<SelectField>();
		symptomSelectFieldList = new ArrayList<SelectField>();
		medicineSelectFieldList.add(new SelectField("Lek:",1,SelectFieldType.MULTIPLE_SELECT,medicineList));
		symptomSelectFieldList.add(new SelectField("Objaw:",1,SelectFieldType.SINGLE_SELECT,symptomList));
		
		this.patientFullName.setStyleName("patientFullName");
		doctorFullNameLabel.setStyleName("doctorFullNameLabel");
		patientFullNameLabel.setStyleName("patientFullNameLabel");
		
		/*Retriving currently logged in doctor info*/
		authService.getCurrentLoggedInUser(new AsyncCallback<User>() {
			@Override
			public void onSuccess(User result) {
				
				if(result==null){
					
					/* null means there is no logged user*/
					LoginProvider login = new LoginProvider(getCm());
					getCm().setBody(login);
					getCm().drawContent();
					
				}
				/*if user is logged in */
				else{
					/* Retreive doctorKeyString which is required with patientKeyString to add recognition
					 * also retriving full doctor name for label */
					doctorFullNameString =result.getName()+" "+result.getSurname();
					doctorKeyString = result.getKeyString();
					doctorFullName = new Label(doctorFullNameString);
					doctorFullName.setText(doctorFullNameString);
					doctorFullName.setStyleName("doctorFullName");
					patientAndDoctorLabel.insert(doctorFullName, 1);
					getCm().drawContent();
				}
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
				/* for now i assume that user is not logged*/
				LoginProvider login = new LoginProvider(getCm());
				getCm().setBody(login);
				getCm().drawContent();
				
				caught.printStackTrace();
				
			}
		});
		
		addSymptom = new Button("Dodaj Objaw");
		addSymptom.setStyleName("addSymptom");
		addSymptom.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				mainPanel.remove(addRecognition);
				mainPanel.remove(addSymptom);
				SelectField medicineField;
				SelectField symptomField;
				
				if(symptomSelectFieldList.size() % 2 == 1){
					symptomField = new SelectField("Objaw:",5,SelectFieldType.SINGLE_SELECT,symptomList);
					medicineField = new SelectField("Lek:",5,SelectFieldType.MULTIPLE_SELECT,medicineList);
				}else{
					symptomField = new SelectField("Objaw:",5,SelectFieldType.SINGLE_SELECT,symptomList);
					medicineField = new SelectField("Lek:",5,SelectFieldType.MULTIPLE_SELECT,medicineList);
				}
				
				symptomSelectFieldList.add(symptomField);
				medicineSelectFieldList.add(medicineField);
				
				mainPanel.add(symptomField.returnContent());
				mainPanel.add(medicineField.returnContent());
				mainPanel.add(addSymptom);
				mainPanel.add(addRecognition);
				getCm().drawContent();
				
			}
		});
		
		
		
		addRecognition = new Button("Dodaj Rozpoznanie");
		addRecognition.addClickHandler(new ClickHandler() {
			
			@SuppressWarnings("unchecked")
			@Override
			public void onClick(ClickEvent event) {
				
				ArrayList<Treatment> treatmentList = new ArrayList<Treatment>();
				
				Iterator<SelectField> symptomIterator = symptomSelectFieldList.iterator();
				Iterator<SelectField> medicineIterator = medicineSelectFieldList.iterator();
				while(symptomIterator.hasNext()){
					
					Treatment treatment = new Treatment();
					treatment.setSymptom(Symptom.getSymptom((String)symptomIterator.next().getValue()));
					
					ArrayList<Medicine> medicineList = new ArrayList<Medicine>();
					for(String s: ((ArrayList<String>)medicineIterator.next().getValue())){
						
							medicineList.add(Medicine.getMedicine(s));
					}
					treatment.setMedicine(medicineList);
					treatmentList.add(treatment);
					
				}
				
				Disease disease = Disease.getDisease((String) diseaseSelect.getValue());
		
				
				treatService.addRecognition(patientKeyString, doctorKeyString, disease, treatmentList, new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						
						caught.printStackTrace();
						
					}

					@Override
					public void onSuccess(Boolean result) {
						
						if(result){
							
							/* success */
							
						}else{
							
							/* failure */
							
						}
						
					}
				});
				
			}
		});
		addRecognition.setStyleName("addRecognitionButton");

		/* i will put these labels into FlowPanel so it will be easier to put that blue line under them */
		patientAndDoctorLabel = new FlowPanel();
		patientAndDoctorLabel.setStyleName("patientAndDoctorFullNameInfo");
		patientAndDoctorLabel.add(doctorFullNameLabel);
		patientAndDoctorLabel.add(patientFullNameLabel);
		patientAndDoctorLabel.add(this.patientFullName);
		
		/* this button is the last item of this boddy */
		mainPanel.add(patientAndDoctorLabel);
		mainPanel.add(diseaseSelect.returnContent());
		mainPanel.add(symptomSelectFieldList.get(0).returnContent());
		mainPanel.add(medicineSelectFieldList.get(0).returnContent());
		mainPanel.add(addSymptom);
		mainPanel.add(addRecognition);
		
		

		
		getCm().drawContent();
	}

}
