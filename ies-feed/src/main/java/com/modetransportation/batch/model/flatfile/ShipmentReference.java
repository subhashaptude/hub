package com.modetransportation.batch.model.flatfile;

public class ShipmentReference {
	
	private static final int recordId = 2;
	private String delta7Number;
	private String referenceQualifier;
	private String referenceNbr;
	
	public int getRecordId() {
		return recordId;
	}

	public String getDelta7Number() {
		return delta7Number;
	}
	public void setDelta7Number(String delta7Number) {
		this.delta7Number = delta7Number;
	}
	public String getReferenceQualifier() {
		return referenceQualifier;
	}
	public void setReferenceQualifier(String referenceQualifier) {
		this.referenceQualifier = referenceQualifier;
	}
	public String getReferenceNbr() {
		return referenceNbr;
	}
	public void setReferenceNbr(String referenceNbr) {
		this.referenceNbr = referenceNbr;
	}

	
	

}
