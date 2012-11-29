/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import pl.dmcs.whatsupdoc.client.ContentManager;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class BodyProvider implements ContentProviderInt {
	
	protected FlowPanel mainPanel;
	private ContentManager cm;
	private Label writeLabel;
	
	/**
	 * Default constructor for BodyProvider
	 */
	public BodyProvider(ContentManager cm) {
		this.cm = cm;
		this.mainPanel = new FlowPanel();
		this.writeLabel = new Label("Proszę czekać, trwa wczytywanie danych.");
		writeLabel.setStyleName("wait");
		
		this.mainPanel.setStyleName("mainPanel");
	}

	/* (non-Javadoc)
	 * @see pl.dmcs.whatsupdoc.client.providers.ContentProviderInt#getContent()
	 */
	@Override
	public Panel getContent() {
		return this.mainPanel;
	}

	/**
	 * @return the cm
	 */
	public ContentManager getCm() {
		return cm;
	}

	/**
	 * @param cm the cm to set
	 */
	public void setCm(ContentManager cm) {
		this.cm = cm;
	}
	
	/**
	 * Add and render wait label
	 */
	public void drawWaitContent(){
		this.mainPanel.add(writeLabel);
		cm.drawContent();
		this.mainPanel.clear();
	}

}
