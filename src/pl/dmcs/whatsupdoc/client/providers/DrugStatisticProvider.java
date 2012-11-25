/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.List;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.SelectField;
import pl.dmcs.whatsupdoc.client.fields.SelectFieldType;
import pl.dmcs.whatsupdoc.client.model.MedicineRate;
import pl.dmcs.whatsupdoc.client.services.TreatmentService;
import pl.dmcs.whatsupdoc.client.services.TreatmentServiceAsync;
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
public class DrugStatisticProvider extends BodyProvider{
	
	private Button search;

	private SelectField symptomSelectField;
	private FlowPanel symptomSelectPanel;
	private FlowPanel topOneDrug;
	private FlowPanel topTwoDrug;
	private FlowPanel topThreeDrug;
	private FlowPanel topDrugLabel;
	
	ArrayList<String> symptomList = new ArrayList<String>();
	
	
	
	/**
	 * @param cm CoontentManager of this class
	 */
	public DrugStatisticProvider(ContentManager contentManager,final String patientKeyString,String patientFullName){
		super(contentManager);
		
		final TreatmentServiceAsync treatService = GWT.create(TreatmentService.class);
		
		/* preparing flowPanels */
		symptomSelectPanel = new FlowPanel();
		topOneDrug = new FlowPanel();
		topOneDrug.setStyleName("topDrugs");
		topTwoDrug = new FlowPanel();
		topTwoDrug.setStyleName("topDrugs");
		topThreeDrug = new FlowPanel();
		topThreeDrug.setStyleName("topDrugs");
		topDrugLabel = new FlowPanel();
		topDrugLabel.setStyleName("topDrugsLabel");
		symptomSelectPanel.setStyleName("symptom");
		/* ************ */
		
		/* preparing labels for topDrugsLabel */
		Label label = new Label("Lek:");
		label.setStyleName("drugStatistic");
		topDrugLabel.add(label);
		label = new Label("Wyleczone (%):");
		label.setStyleName("drugStatistic");
		topDrugLabel.add(label);
		label = new Label("Średnia długość leczenia:");
		label.setStyleName("drugStatistic");
		topDrugLabel.add(label);
		/* ***************************** */
		
		/* preparing symptom select List */
		
		for(Symptom d: Symptom.values()){
			symptomList.add(d.toString());
		}
		symptomSelectField = new SelectField("Objaw:",1,SelectFieldType.SINGLE_SELECT,symptomList);
		
		/* ************************ */
		
		
		/* preparing search button (it will find and add to mainPanel top3 drugs */
		search = new Button("wyszukaj");
		search.setStyleName("wyszukaj");
		search.addClickHandler( new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				/* clearing view and setting up again  basic info first*/
				mainPanel.clear();
				mainPanel.add(symptomSelectPanel);
				mainPanel.add(topDrugLabel);
				/* ********************************** */
				
				/* retreiving data of TOP 3 drugs for selected symptom */
				Symptom symptom = Symptom.getSymptom((String) symptomSelectField.getValue());
				treatService.getTopMedicineRates(symptom, 3, new AsyncCallback<List<MedicineRate>>(){

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						
					}

					@Override
					public void onSuccess(List<MedicineRate> result) {
						
						
						if(!result.isEmpty()){
							/* retreiving info f top 3 */
							MedicineRate info = result.get(0);
							topOneDrug.add(new Label(info.getMedicine().toString()));
							topOneDrug.add(new Label(Float.toString(info.getSuccessTreatmentRate()*100)));
							topOneDrug.add(new Label(Float.toString(info.getAverageTreatmentLength())));
							
							info = result.get(1);
							topTwoDrug.add(new Label(info.getMedicine().toString()));
							topTwoDrug.add(new Label(Float.toString(info.getSuccessTreatmentRate()*100)));
							topTwoDrug.add(new Label(Float.toString(info.getAverageTreatmentLength())));
							
							info = result.get(2);
							topThreeDrug.add(new Label(info.getMedicine().toString()));
							topThreeDrug.add(new Label(Float.toString(info.getSuccessTreatmentRate()*100)));
							topThreeDrug.add(new Label(Float.toString(info.getAverageTreatmentLength())));
							/* *********************** */
							
							/* adding all label "drugStatistic" stylename */
							for(FlowPanel p: new FlowPanel[]{topOneDrug, topTwoDrug, topThreeDrug}){
								
								for (int i=0 ; i< p.getWidgetCount(); i++){
									((Label)p.getWidget(i)).setStyleName("drugStatistic");
								}
								
							}
							/* ******************** */
							
							/* adding them to main Panel */
							mainPanel.add(topOneDrug);
							mainPanel.add(topTwoDrug);
							mainPanel.add(topThreeDrug);
							/* ********************* */
							
							getCm().drawContent();
						}else{
							
							
							/* actualy there is no action for else.. the list is empty */
							
						}
						
						
					}
					
				});
				/* ********************** */
				

				/* predrawing view basic info only*/
				getCm().drawContent();
				/* ************************ */
				
			}
		});
		
		/* **************************************** */
		
		
		/* adding symptom selection and button to flow panel */
		symptomSelectPanel.add(symptomSelectField.returnContent());
		symptomSelectPanel.add(search);
		/* ********************** */
		
		mainPanel.add(symptomSelectPanel);
		getCm().drawContent();
	}

}
