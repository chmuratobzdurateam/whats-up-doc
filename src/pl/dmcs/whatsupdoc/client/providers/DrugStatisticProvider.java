/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.List;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.ButtonStatusField;
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
	
	private ButtonStatusField searchButtonStatusField;

	private SelectField symptomSelectField;
	private FlowPanel symptomSelectPanel;
	private FlowPanel topDrugLabel;
	
	ArrayList<String> symptomList = new ArrayList<String>();
	
	
	
	/**
	 * @param cm CoontentManager of this class
	 */
	public DrugStatisticProvider(ContentManager contentManager){
		super(contentManager);
		
		final TreatmentServiceAsync treatService = GWT.create(TreatmentService.class);
		
		/* preparing flowPanels */
		symptomSelectPanel = new FlowPanel();
		symptomSelectPanel.setStyleName("symptom");
		/* ************ */
		
		/* preparing labels for topDrugsLabel */
		topDrugLabel = new FlowPanel();
		topDrugLabel.setStyleName("drugStatisticLabel");
		Label label = new Label("Lek:");
		topDrugLabel.add(label);
		label = new Label("Wyleczone (%):");
		topDrugLabel.add(label);
		label = new Label("Średnia długość leczenia:");
		topDrugLabel.add(label);
		label = new Label("Ilość przebadanych:");
		topDrugLabel.add(label);
		/* ***************************** */
		
		/* preparing symptom select List */
		
		for(Symptom d: Symptom.values()){
			symptomList.add(d.toString());
		}
		symptomSelectField = new SelectField("Objaw:",1,SelectFieldType.SINGLE_SELECT,symptomList);
		
		/* ************************ */
		
		
		/* preparing search button (it will find and add to mainPanel top3 drugs */
		searchButtonStatusField = new ButtonStatusField("wyszukaj");
		searchButtonStatusField.setErrorMessage("Nie znaleziono żadnych statystyk.");
		searchButtonStatusField.setProgressMessage("Trwa wczytywanie statystyk...");
		searchButtonStatusField.setColumnStyle("buttonColumnStatistic");
		
		searchButtonStatusField.getButton().addClickHandler( new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				/* clearing view and setting up again  basic info first*/
				mainPanel.clear();
				mainPanel.add(symptomSelectPanel);
				mainPanel.add(topDrugLabel);
				/* ********************************** */
				searchButtonStatusField.showProgressMessage();
				/* retreiving data of TOP 3 drugs for selected symptom */
				Symptom symptom = Symptom.getSymptom((String) symptomSelectField.getValue());
				System.out.print(symptom.toString());
				treatService.getTopMedicineRates(symptom, 3, new AsyncCallback<List<MedicineRate>>(){

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						searchButtonStatusField.showErrorMessage();
						
						
					}

					@Override
					public void onSuccess(List<MedicineRate> result) {
						
						
						if(!result.isEmpty()){
							/* retreiving info f top 3 */
							int i = 0;
							Label label;
							FlowPanel drugStatistic;
							for (MedicineRate r : result){
								drugStatistic = new FlowPanel();
								label = new Label(r.getMedicine().toString());
								drugStatistic.add(label);
								label = new Label(Float.toString(r.getSuccessTreatmentRate()*100));
								drugStatistic.add(label);
								label = new Label(Float.toString(r.getAverageTreatmentLength()));
								drugStatistic.add(label);
								label = new Label(Integer.toString(r.getTreatmentsNumber()));
								drugStatistic.add(label);
								
								if(i%2==1){
									drugStatistic.setStyleName("drugStatisticOdd");
								}else{
									drugStatistic.setStyleName("drugStatisticEven");
								}
								i++;
							mainPanel.add(drugStatistic);
								
							}
							
							/* *********************** */
							searchButtonStatusField.hideMessage();
							getCm().drawContent();

							
						}else{
							
							searchButtonStatusField.getErrorMessage();
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
		symptomSelectPanel.add(searchButtonStatusField.returnContent());
		/* ********************** */
		mainPanel.add(symptomSelectPanel);
		getCm().drawContent();
	}

}
