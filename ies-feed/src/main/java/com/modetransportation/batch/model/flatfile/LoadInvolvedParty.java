package com.modetransportation.batch.model.flatfile;

public class LoadInvolvedParty {

	private final int recordId = 7;
	private String moveNbr;
	private String expenseKey;
	private String involvedPartyQualifier;
	private String involvedPartyDomain;
	private String involvedPartyId;
	
	public String getMoveNbr() {
		return moveNbr;
	}
	public void setMoveNbr(String moveNbr) {
		this.moveNbr = moveNbr;
	}
	public String getExpenseKey() {
		return expenseKey;
	}
	public void setExpenseKey(String expenseKey) {
		this.expenseKey = expenseKey;
	}
	public String getInvolvedPartyQualifier() {
		return involvedPartyQualifier;
	}
	public void setInvolvedPartyQualifier(String involvedPartyQualifier) {
		this.involvedPartyQualifier = involvedPartyQualifier;
	}
	public String getInvolvedPartyDomain() {
		return involvedPartyDomain;
	}
	public void setInvolvedPartyDomain(String involvedPartyDomain) {
		this.involvedPartyDomain = involvedPartyDomain;
	}
	public String getInvolvedPartyId() {
		return involvedPartyId;
	}
	public void setInvolvedPartyId(String involvedPartyId) {
		this.involvedPartyId = involvedPartyId;
	}
	public int getRecordId() {
		return recordId;
	}
	
	
	
}
