/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.dmcs.whatsupdoc.client.ContentManager;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;


/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class PatientMenuProvider extends MenuProvider {
	
	private Map<Bar, List<Button> > subMenus;
	private Bar currentBar;
	
	/**
	 * @param manager - ContentManager type
	 */
	public PatientMenuProvider(ContentManager manager){
		super(manager);
		this.currentBar = Bar.HOME;
		this.subMenus = new HashMap<Bar, List<Button>>();
		
		//************************************************ Create sub-menu buttons for empty sub-menu *****************************
		
		this.subMenus.put(Bar.HOME, new ArrayList<Button>());
		this.subMenus.put(Bar.PATIENT_CARD, new ArrayList<Button>());
		this.subMenus.put(Bar.SEARCH, new ArrayList<Button>());
		
		//************************************************ Create buttons for first menu********************************************
		
		Button button = new Button("Strona Główna");
		firstLevelButton = button;
		button.setStyleName(CSS.get(0));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				currentBar = Bar.HOME;
				setFirstLvlCSS((Button) event.getSource());
				getManager().drawContent();
			}
		});
		hFirstPanel.add(button);
		
		button = new Button("Moje konto");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				currentBar = Bar.MY_ACCOUNT;
				setFirstLvlCSS((Button) event.getSource());
				getManager().drawContent();
			}
		});
		hFirstPanel.add(button);
		
		button = new Button("Moja karta pacjenta");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				currentBar = Bar.PATIENT_CARD;
				setFirstLvlCSS((Button) event.getSource());
				getManager().drawContent();
			}
		});
		hFirstPanel.add(button);
		
		button = new Button("Szukaj lekarza");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				currentBar = Bar.SEARCH;
				setFirstLvlCSS((Button) event.getSource());
				getManager().drawContent();
			}
		});
		hFirstPanel.add(button);
		
		button = new Button("Statystyki");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				currentBar = Bar.STATISTICS;
				setFirstLvlCSS((Button) event.getSource());
				getManager().drawContent();
			}
		});
		hFirstPanel.add(button);
		
		//************************************************** Create buttons for My Account sub-menu *****************************************
		
		ArrayList<Button> list = new ArrayList<Button>(2);
		
		button = new Button("Zmien haslo");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setSecondLvlCSS((Button) event.getSource());
				
			}
		});
		list.add(button);
		
		button = new Button("Zmien dane osobowe");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setSecondLvlCSS((Button) event.getSource());
				
			}
		});
		list.add(button);
		
		subMenus.put(Bar.MY_ACCOUNT, list);
		
		//************************************************** Create buttons for Form sub-menu *****************************************
		
		list = new ArrayList<Button>(2);
		
		button = new Button("Aktualne");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setSecondLvlCSS((Button) event.getSource());
				
			}
		});
		list.add(button);
		
		button = new Button("Historia");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setSecondLvlCSS((Button) event.getSource());
				
			}
		});
		list.add(button);
		
		subMenus.put(Bar.FORM, list);
		
		//************************************************** Create buttons for Statistic sub-menu *************************************
		
		list = new ArrayList<Button>(2);
		
		button = new Button("Skutecznosc leczenia");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setSecondLvlCSS((Button) event.getSource());
				
			}
		});
		list.add(button);
		
		button = new Button("Najskuteczniejsi lekarze");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setSecondLvlCSS((Button) event.getSource());
				
			}
		});
		list.add(button);
		
		subMenus.put(Bar.STATISTICS, list);
		
	}
	
	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.providers.MenuProvider#getSecondLevel()
	 */
	@Override
	public Panel getSecondLevel() {
		hSecondPanel.clear();
		for(Button button : subMenus.get(currentBar)){
			hSecondPanel.add(button);
		}
		return hSecondPanel;
	}



	/**
	 * 29-10-2012 - first menu bars
	 * @author Jakub Jeleński, jjelenski90@gmail.com
	 * 
	 * 
	 */
	private enum Bar{
		MY_ACCOUNT, PATIENT_CARD, FORM, SEARCH, STATISTICS, HOME
	}
	
}
