package pl.dmcs.whatsupdoc.client;

import pl.dmcs.whatsupdoc.client.model.User;
import pl.dmcs.whatsupdoc.client.providers.BodyProvider;
import pl.dmcs.whatsupdoc.client.providers.BreadcrumbProvider;
import pl.dmcs.whatsupdoc.client.providers.DoctorMenuProvider;
import pl.dmcs.whatsupdoc.client.providers.LoginProvider;
import pl.dmcs.whatsupdoc.client.providers.MenuProvider;
import pl.dmcs.whatsupdoc.client.providers.PatientMenuProvider;
import pl.dmcs.whatsupdoc.client.providers.VerifierMenuProvider;
import pl.dmcs.whatsupdoc.client.services.AuthenticationService;
import pl.dmcs.whatsupdoc.client.services.AuthenticationServiceAsync;
import pl.dmcs.whatsupdoc.shared.ContentType;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WhatsUpDoc implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		final AuthenticationServiceAsync auth = GWT.create(AuthenticationService.class);
		PanelsManager p = new PanelsManager();
		final ContentManager cm = new ContentManager(ContentType.AUTHORIZATION, p);
		BodyProvider b = new BodyProvider(cm);
		cm.setBody(b);
		b.drawWaitContent();
		
		auth.getCurrentLoggedInUser(new AsyncCallback<User>() {
			
			@Override
			public void onSuccess(User result) {
				MenuProvider mp;
				BreadcrumbProvider bF = new BreadcrumbProvider(cm);
				cm.setBreadcrumb(bF);
				BodyProvider body;
				if(result ==  null){
					body = new LoginProvider(cm);
					bF.addField(false, "Strona logowania", body);
					mp = new MenuProvider(cm);
				}else{
					body = new BodyProvider(cm);
					switch(result.getUserType()){
					case DOCTOR:
						mp = new DoctorMenuProvider(cm);
						break;
						
					case PATIENT:
						mp = new PatientMenuProvider(cm, result.getPESEL());
						break;
						
					case VERIFIER:
						mp = new VerifierMenuProvider(cm);
						break;
						
					default:
						mp = new MenuProvider(cm);
					}
				}
				cm.setMenu(mp);
				cm.setBody(body);
				cm.drawContent();
				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
