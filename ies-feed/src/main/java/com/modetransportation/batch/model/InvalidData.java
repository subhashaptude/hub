package com.modetransportation.batch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InvalidData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	protected int id;
	@Column(name="FailedRecord")
	protected String failedRecord;
	@Column(name="RecordCreatedDate")
	protected String recordCreatedDate;
	@Column(name="ReasonCode")
	protected int reasonCode;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFailedRecord() {
		return failedRecord;
	}
	public void setFailedRecord(String failedRecord) {
		this.failedRecord = failedRecord;
	}
	public int getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(int reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getRecordCreatedDate() {
		return recordCreatedDate;
	}
	public void setRecordCreatedDate(String recordCreatedDate) {
		this.recordCreatedDate = recordCreatedDate;
	}
	
	
	
	
	

}
