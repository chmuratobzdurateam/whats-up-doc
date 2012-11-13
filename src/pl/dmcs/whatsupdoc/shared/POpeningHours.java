package pl.dmcs.whatsupdoc.shared;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class POpeningHours implements IsSerializable{
	@Persistent
	private Integer openHour;
	@Persistent
	private Integer openMinute;
	@Persistent
	private Integer closeHour;
	@Persistent
	private Integer closeMinute;
	@Persistent
	private Integer dayNumber;
	@Persistent
	private Boolean isOpened;
	
	public POpeningHours(){
	}
	
	/**
	 * @return the openHour
	 */
	public Integer getOpenHour() {
		return openHour;
	}
	/**
	 * @param openHour the openHour to set
	 */
	public void setOpenHour(Integer openHour) {
		this.openHour = openHour;
	}
	/**
	 * @return the openMinute
	 */
	public Integer getOpenMinute() {
		return openMinute;
	}
	/**
	 * @param openMinute the openMinute to set
	 */
	public void setOpenMinute(Integer openMinute) {
		this.openMinute = openMinute;
	}
	/**
	 * @return the closeHour
	 */
	public Integer getCloseHour() {
		return closeHour;
	}
	/**
	 * @param closeHour the closeHour to set
	 */
	public void setCloseHour(Integer closeHour) {
		this.closeHour = closeHour;
	}
	/**
	 * @return the closeMinute
	 */
	public Integer getCloseMinute() {
		return closeMinute;
	}
	/**
	 * @param closeMinute the closeMinute to set
	 */
	public void setCloseMinute(Integer closeMinute) {
		this.closeMinute = closeMinute;
	}
	/**
	 * @return the dayNumber
	 */
	public Integer getDayNumber() {
		return dayNumber;
	}
	/**
	 * @param dayNumber the dayNumber (1 - Monday, 2 - Tuesday, etc..)
	 */
	public void setDayNumber(Integer dayNumber) {
		this.dayNumber = dayNumber;
	}
	/**
	 * @return the isOpened
	 */
	public Boolean isOpened() {
		return isOpened;
	}
	/**
	 * @param isOpened the isOpened to set
	 */
	public void setOpened(Boolean isOpened) {
		this.isOpened = isOpened;
	}
}
