package com.modetransportation.batch.model.flatfile;

public class LoadStop {

	private final int recordId = 8;
	private String loadNbr;
	private String expenseKey;
	private String Activity;
	private String locationDomain;
	private String locationId;
	private String stopReason;
	private String stopSequence;
	private String arrivalTime;
	private String departureTime;
	private String stopDistance;
	private String stopDistanceUOM;
	
	
	public String getLoadNbr() {
		return loadNbr;
	}
	public void setLoadNbr(String loadNbr) {
		this.loadNbr = loadNbr;
	}
	public String getExpenseKey() {
		return expenseKey;
	}
	public void setExpenseKey(String expenseKey) {
		this.expenseKey = expenseKey;
	}
	public String getActivity() {
		return Activity;
	}
	public void setActivity(String activity) {
		Activity = activity;
	}
	public String getLocationDomain() {
		return locationDomain;
	}
	public void setLocationDomain(String locationDomain) {
		this.locationDomain = locationDomain;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getStopReason() {
		return stopReason;
	}
	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}
	public String getStopSequence() {
		return stopSequence;
	}
	public void setStopSequence(String stopSequence) {
		this.stopSequence = stopSequence;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getStopDistance() {
		return stopDistance;
	}
	public void setStopDistance(String stopDistance) {
		this.stopDistance = stopDistance;
	}
	public String getStopDistanceUOM() {
		return stopDistanceUOM;
	}
	public void setStopDistanceUOM(String stopDistanceUOM) {
		this.stopDistanceUOM = stopDistanceUOM;
	}
	public int getRecordId() {
		return recordId;
	}

	
	
	
}
