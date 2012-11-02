package pl.dmcs.whatsupdoc.server.datastore.model;

import java.util.ArrayList;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import pl.dmcs.whatsupdoc.shared.Address;
import pl.dmcs.whatsupdoc.shared.OpeningHours;
import pl.dmcs.whatsupdoc.shared.Speciality;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PDoctor extends PUser{
	private Speciality speciality;
	private Address consultingRoomAddress;
	private ArrayList<OpeningHours> openingHours; 
	
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
	public Address getConsultingRoomAddress() {
		return consultingRoomAddress;
	}

	/**
	 * @param consultingRoomAddress the consultingRoomAddress to set
	 */
	public void setConsultingRoomAddress(Address consultingRoomAddress) {
		this.consultingRoomAddress = consultingRoomAddress;
	}

	/**
	 * @return the openingHours
	 */
	public ArrayList<OpeningHours> getOpeningHours() {
		return openingHours;
	}

	/**
	 * @param openingHours the openingHours to set
	 */
	public void setOpeningHours(ArrayList<OpeningHours> openingHours) {
		this.openingHours = openingHours;
	}
}
