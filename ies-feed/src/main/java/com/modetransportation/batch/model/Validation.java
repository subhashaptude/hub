package com.modetransportation.batch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Validation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	protected int id;
	@Column(name="FieldName")
	protected String fieldName;
	@Column(name="ClassName")
	protected String className;
	@Column(name="ValidationType")
	protected ValidationType validationType;
	@Column(name="CorrespondingFieldName")
	protected String correspondingFieldName;
	@Column(name="CorrespondingClassName")
	protected String correspondingClassName;
	@Column(name="Active")
	protected String active;
	@Column(name="Description")
	protected String description;
	@Column(name="GroupName")
	protected String groupName;
	@Column(name="Expression")
	protected String expression;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCorrespondingFieldName() {
		return correspondingFieldName;
	}
	public void setCorrespondingFieldName(String correspondingFieldName) {
		this.correspondingFieldName = correspondingFieldName;
	}
	public String getCorrespondingClassName() {
		return correspondingClassName;
	}
	public void setCorrespondingClassName(String correspondingClassName) {
		this.correspondingClassName = correspondingClassName;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public ValidationType getValidationType() {
		return validationType;
	}
	public void setValidationType(ValidationType validationType) {
		this.validationType = validationType;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	
	
	

}
