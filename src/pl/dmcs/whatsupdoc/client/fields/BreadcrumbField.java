/**
 * 
 */
package pl.dmcs.whatsupdoc.client.fields;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;

/**
 * 22-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class BreadcrumbField extends Field {

	private Button button;
	
	/**
	 * @param begginingImg - if true render arrow, otherwise don't
	 * @param button - button which render page
	 */
	public BreadcrumbField(boolean begginingImg, Button button) {
		super(2, Arrays.asList(new String[] {"arrowDiv","buttonDiv"}));
		if(begginingImg){
			Image img = new Image("images/Strzalka-NavigacjaDolna.png");
			img.setStyleName("arrow");
			subDiv.get(0).add(img);
		}
		button.setStyleName("button");
		this.button = button;
		subDiv.get(1).add(button);
	}
	
	public void addClickHandler(ClickHandler c){
		this.button.addClickHandler(c);
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#getValue()
	 */
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.fields.Field#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}
