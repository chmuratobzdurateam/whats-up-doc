/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import pl.dmcs.whatsupdoc.client.ContentManager;
import pl.dmcs.whatsupdoc.client.fields.BreadcrumbField;

/**
 * 22-11-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class BreadcrumbProvider extends BodyProvider {

	private List<BreadcrumbField> fields; 
	
	/**
	 * @param cm
	 */
	public BreadcrumbProvider(ContentManager cm) {
		super(cm);
		fields = new ArrayList<BreadcrumbField>();
	}
	
	public void addField(boolean begginingImg, String name, BodyProvider body){
		Button button = new Button(name);
		button.addClickHandler(new ChangeBody(body, fields.size()));
		fields.add(new BreadcrumbField(begginingImg, button));
		reCreatePanel();
	}

	public void clearAfter(int index){
		fields.subList(index+1, fields.size()).clear();
		reCreatePanel();
	}
	
	private void reCreatePanel(){
		this.mainPanel.clear();
		for(BreadcrumbField bF : fields){
			this.mainPanel.add(bF.returnContent());
		}
	}
	
	public void clearAll(){
		fields.clear();
		reCreatePanel();
	}
	
	class ChangeBody implements ClickHandler{

		private BodyProvider body;
		private int number;
		
		ChangeBody(BodyProvider body, int number){
			this.body = body;
			this.number = number;
		}
		
		/* (non-Javadoc)
		 * @see com.google.gwt.event.dom.client.ClickHandler#onClick(com.google.gwt.event.dom.client.ClickEvent)
		 */
		@Override
		public void onClick(ClickEvent event) {
			body.setCm(getCm());
			getCm().setBody(body);
			clearAfter(number);
			getCm().drawContent();
			
		}
		
	}
}
