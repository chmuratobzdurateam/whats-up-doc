package pl.dmcs.whatsupdoc.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OpeningHours implements IsSerializable{
	private Integer openHour;
	private Integer openMinute;
	private Integer closeHour;
	private Integer closeMinute;
	private Integer dayNumber;
	private Boolean isOpened;
	
	public OpeningHours(){
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
