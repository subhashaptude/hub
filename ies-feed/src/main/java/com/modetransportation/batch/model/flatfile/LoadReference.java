package com.modetransportation.batch.model.flatfile;

public class LoadReference {
	
	private final int recordId = 6;
	private String loadNbr;
	private String expenseKey;
	private String shipmentRefNumber;
	private String shipmentRefQualifier;
	
	
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
	public String getShipmentRefNumber() {
		return shipmentRefNumber;
	}
	public void setShipmentRefNumber(String shipmentRefNumber) {
		this.shipmentRefNumber = shipmentRefNumber;
	}
	public String getShipmentRefQualifier() {
		return shipmentRefQualifier;
	}
	public void setShipmentRefQualifier(String shipmentRefQualifier) {
		this.shipmentRefQualifier = shipmentRefQualifier;
	}
	public int getRecordId() {
		return recordId;
	}
	
	
	

}
