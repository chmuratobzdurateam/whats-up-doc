/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.SelectField;
import pl.dmcs.whatsupdoc.client.fields.SelectFieldType;
import pl.dmcs.whatsupdoc.shared.Symptom;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
				
				mainPanel.clear();
				mainPanel.add(symptomSelectPanel);
				mainPanel.add(topDrugLabel);
				
				topOneDrug.add(new Label());
				topOneDrug.add(new Label());
				topOneDrug.add(new Label());
				topTwoDrug.add(new Label());
				topTwoDrug.add(new Label());
				topTwoDrug.add(new Label());
				topThreeDrug.add(new Label());
				topThreeDrug.add(new Label());
				topThreeDrug.add(new Label());
				
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
