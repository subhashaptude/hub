package com.modetransportation.batch.model.flatfile;

public class InvoiceCharge {

	private final int recordId = 4;
	private String delta7Number;
	private String sequenceNumber;
	private String currency;
	private String monetaryAmount;
	private String specialChargeDesc;
	private String specialChargeCode;
	private String description;
	private String rate;
	private String rateQualifier;
	private String quantity;
	public String getDelta7Number() {
		return delta7Number;
	}
	public void setDelta7Number(String delta7Number) {
		this.delta7Number = delta7Number;
	}
	public String getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getMonetaryAmount() {
		return monetaryAmount;
	}
	public void setMonetaryAmount(String monetaryAmount) {
		this.monetaryAmount = monetaryAmount;
	}
	public String getSpecialChargeDesc() {
		return specialChargeDesc;
	}
	public void setSpecialChargeDesc(String specialChargeDesc) {
		this.specialChargeDesc = specialChargeDesc;
	}
	public String getSpecialChargeCode() {
		return specialChargeCode;
	}
	public void setSpecialChargeCode(String specialChargeCode) {
		this.specialChargeCode = specialChargeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getRateQualifier() {
		return rateQualifier;
	}
	public void setRateQualifier(String rateQualifier) {
		this.rateQualifier = rateQualifier;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public int getRecordId() {
		return recordId;
	}

	
	
}
