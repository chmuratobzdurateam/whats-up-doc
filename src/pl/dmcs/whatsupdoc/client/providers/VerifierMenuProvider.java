/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;

import pl.dmcs.whatsupdoc.client.ContentManager;

/**
 * 29-10-2012
 * 
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class VerifierMenuProvider extends MenuProvider {

	private Map<Bar, List<Button>> subMenus;
	private Bar currentBar;

	/**
	 * @param manager
	 *            - ContentManager type
	 */
	public VerifierMenuProvider(ContentManager manager) {
		super(manager);
		this.currentBar = Bar.HOME;
		subMenus = new HashMap<Bar, List<Button>>();

		// *********************************************************** Create empty sub-menus ********************************************

		this.subMenus.put(Bar.HOME, new ArrayList<Button>());

		// *********************************************************** Create buttons for first menu *************************************

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

		button = new Button("Użytkownicy");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				currentBar = Bar.USERS;
				
				setFirstLvlCSS((Button) event.getSource());
				
				getManager().drawContent();
			}
		});
		hFirstPanel.add(button);

		// ******************************************************* Create buttons for My Account sub-menu *******************************
		
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

		// ******************************************************* Create buttons for Users sub-menu *******************************

		list = new ArrayList<Button>(2);

		button = new Button("Dodaj użytkownika");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setSecondLvlCSS((Button) event.getSource());
				BodyProvider b = new AddUserProvider(getManager());
				getManager().setBody(b);
				getManager().drawContent();

			}
		});
		list.add(button);

		button = new Button("Edytuj użytkownika");
		button.setStyleName(CSS.get(1));
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				setSecondLvlCSS((Button) event.getSource());

			}
		});
		list.add(button);

		subMenus.put(Bar.USERS, list);

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
	private enum Bar {
		HOME, MY_ACCOUNT, USERS
	}
}
