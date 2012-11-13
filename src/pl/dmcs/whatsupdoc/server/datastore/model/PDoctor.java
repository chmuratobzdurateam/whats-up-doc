package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import pl.dmcs.whatsupdoc.shared.PAddress;
import pl.dmcs.whatsupdoc.shared.POpeningHours;
import pl.dmcs.whatsupdoc.shared.Speciality;

import com.google.appengine.datanucleus.annotations.Owned;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PDoctor extends PUser{
	@Persistent
	private Speciality speciality;
	
	@Persistent(dependent = "true")
	@Owned
	private PAddress consultingRoomAddress;
	
	@Persistent(dependent = "true")
	@Owned
	private ArrayList<POpeningHours> openingHours; 
	
	public PDoctor(){
	}

	/**
	 * @return the speciality
	 */
	public Speciality getSpeciality() {
		return speciality;
	}

	/**
	 * @param speciality the speciality to set
	 */
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	/**
	 * @return the consultingRoomAddress
	 */
	public PAddress getConsultingRoomAddress() {
		return consultingRoomAddress;
	}

	/**
	 * @param consultingRoomAddress the consultingRoomAddress to set
	 */
	public void setConsultingRoomAddress(PAddress consultingRoomAddress) {
		this.consultingRoomAddress = consultingRoomAddress;
	}

	/**
	 * @return the openingHours
	 */
	public ArrayList<POpeningHours> getOpeningHours() {
		return openingHours;
	}

	/**
	 * @param openingHours the openingHours to set
	 */
	public void setOpeningHours(ArrayList<POpeningHours> openingHours) {
		this.openingHours = openingHours;
	}
}
