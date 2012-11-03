/**
 * 
 */
package pl.dmcs.whatsupdoc.client.providers;

import pl.dmcs.whatsupdoc.client.ContentManager;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 29-10-2012
 * @author Jakub Jeleński, jjelenski90@gmail.com
 * 
 * 
 */
public class BodyProvider implements ContentProviderInt {
	
	protected VerticalPanel mainPanel;
	private ContentManager cm;
	
	/**
	 * Default constructor for BodyProvider
	 */
	public BodyProvider(ContentManager cm) {
		this.cm = cm;
		this.mainPanel = new VerticalPanel();
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

}
