
package com.modetransportation.batch.model.ar;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;
import org.joda.time.LocalDate;
import com.modetransportation.batch.util.LocalDateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"chargetype",
		"chargetypecode",
		"companynumber",
		"division",
		"businessline",
		"task",
		"glaccount",
		"location",
		"project",
		"invoicenumber",
		"invoicetype",
		"financialparty",
		"financialpartyexternalid",
		"menumber",
		"transactiontype",
		"accountingdate",
		"duedate",
		"chargeamount",
		"currencycode",
		"terms",
		"referencetype1",
		"referencevalue1",
		"referencenumber",
		"origincountry",
		"destinationcountry",
		"masterblawbnumber",
		"houseblawbnumber",
		"carrier"
})
@XmlRootElement(name = "ACCOUNTING_EVENT")
@Entity
@Table(name="AR_Event",  uniqueConstraints={
		@UniqueConstraint(columnNames={"ReferenceNumber","FinancialPartyExternalId","ChargeTypeCode", "ChargeAmount"})})
public class AREvent {

	@Id
	@XmlTransient
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column(name = "ChargeType")
	@XmlElement(name = "CHARGE_TYPE", required = true)
	protected String chargetype;
	@Column(name = "ChargeTypeCode")
	@XmlElement(name = "CHARGE_TYPE_CODE", required = true)
	protected String chargetypecode;
	@Column(name = "CompanyNumber")
	@XmlElement(name = "COMPANY_NUMBER")
	protected byte companynumber;
	@Column(name = "Division")
	@XmlElement(name = "DIVISION")
	protected byte division;
	@Column(name = "BusinessLine")
	@XmlElement(name = "BUSINESS_LINE")
	protected byte businessline;
	@Column(name = "Task")
	@XmlElement(name = "TASK")
	protected short task;
	@Column(name = "GLAccount")
	@XmlElement(name = "GL_ACCOUNT")
	protected short glaccount;
	@Column(name = "Location")
	@XmlElement(name = "LOCATION")
	protected byte location;
	@Column(name = "Project")
	@XmlElement(name = "PROJECT", required = true)
	protected String project;
	@Column(name = "InvoiceNumber")
	@XmlElement(name = "INVOICE_NUMBER")
	protected LocalDate invoicenumber;
	@Column(name = "InvoiceType")
	@XmlElement(name = "INVOICE_TYPE", required = true)
	protected String invoicetype;
	@Column(name = "FinancialParty")
	@XmlElement(name = "FINANCIAL_PARTY", required = true)
	protected String financialparty;
	@Column(name = "FinancialPartyExternalId")
	@XmlElement(name = "FINANCIAL_PARTY_EXTERNAL_ID", required = true)
	protected String financialpartyexternalid;
	@Column(name = "MeNumber")
	@XmlElement(name = "ME_NUMBER", required = true)
	protected String menumber;
	@Column(name = "TransactionType")
	@XmlElement(name = "TRANSACTION_TYPE", required = true)
	protected String transactiontype;
	@Column(name = "AccountingDate")
	@XmlElement(name = "ACCOUNTING_DATE", required = true)
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
	protected LocalDate accountingdate;
	@Column(name = "DueDate")
	@XmlElement(name = "DUE_DATE", required = true)
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
	protected LocalDate duedate;
	@Column(name = "ChargeAmount")
	@XmlElement(name = "CHARGE_AMOUNT", required = true)
	protected BigDecimal chargeamount;
	@Column(name = "CurrencyCode")
	@XmlElement(name = "CURRENCY_CODE", required = true)
	protected String currencycode;
	@Column(name = "Terms")
	@XmlElement(name = "TERMS", required = true)
	protected String terms;
	@Column(name = "ReferenceType1")
	@XmlElement(name = "REFERENCE_TYPE_1", required = true)
	protected String referencetype1;
	@Column(name = "ReferenceValue1")
	@XmlElement(name = "REFERENCE_VALUE_1", required = true)
	protected String referencevalue1;
	@Column(name = "ReferenceNumber")
	@XmlElement(name = "REFERENCE_NUMBER", required = true)
	protected String referencenumber;
	@Column(name = "OriginCountry")
	@XmlElement(name = "ORIGIN_COUNTRY", required = true)
	protected String origincountry;
	@Column(name = "DestinationCountry")
	@XmlElement(name = "DESTINATION_COUNTRY", required = true)
	protected String destinationcountry;
	@Column(name = "MasterBlawbNumber")
	@XmlElement(name = "MASTER_BLAWB_NUMBER", required = true)
	protected String masterblawbnumber;
	@Column(name = "HouseBlawbNumber")
	@XmlElement(name = "HOUSE_BLAWB_NUMBER")
	protected short houseblawbnumber;
	@Column(name = "Carrier")
	@XmlElement(name = "CARRIER")
	protected byte carrier;
    @XmlTransient
    @Column(name="Void")
    protected String voided;
    @XmlTransient
    protected String reversal;
    @XmlTransient
    protected String processed;
    
    public String getProcessed() {
		return processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}
	

    public String getVoided() {
		return voided;
	}

	public void setVoided(String voided) {
		this.voided = voided;
	}

	public String getReversal() {
		return reversal;
	}

	public void setReversal(String reversal) {
		this.reversal = reversal;
	}
	/**
	 * Gets the value of the chargetype property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCHARGETYPE() {
		return chargetype;
	}

	/**
	 * Sets the value of the chargetype property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCHARGETYPE(String value) {
		this.chargetype = value;
	}

	/**
	 * Gets the value of the chargetypecode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCHARGETYPECODE() {
		return chargetypecode;
	}

	/**
	 * Sets the value of the chargetypecode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCHARGETYPECODE(String value) {
		this.chargetypecode = value;
	}

	/**
	 * Gets the value of the companynumber property.
	 * 
	 */
	public byte getCOMPANYNUMBER() {
		return companynumber;
	}

	/**
	 * Sets the value of the companynumber property.
	 * 
	 */
	public void setCOMPANYNUMBER(byte value) {
		this.companynumber = value;
	}

	/**
	 * Gets the value of the division property.
	 * 
	 */
	public byte getDIVISION() {
		return division;
	}

	/**
	 * Sets the value of the division property.
	 * 
	 */
	public void setDIVISION(byte value) {
		this.division = value;
	}

	/**
	 * Gets the value of the businessline property.
	 * 
	 */
	public byte getBUSINESSLINE() {
		return businessline;
	}

	/**
	 * Sets the value of the businessline property.
	 * 
	 */
	public void setBUSINESSLINE(byte value) {
		this.businessline = value;
	}

	/**
	 * Gets the value of the task property.
	 * 
	 */
	public short getTASK() {
		return task;
	}

	/**
	 * Sets the value of the task property.
	 * 
	 */
	public void setTASK(short value) {
		this.task = value;
	}

	/**
	 * Gets the value of the glaccount property.
	 * 
	 */
	public short getGLACCOUNT() {
		return glaccount;
	}

	/**
	 * Sets the value of the glaccount property.
	 * 
	 */
	public void setGLACCOUNT(short value) {
		this.glaccount = value;
	}

	/**
	 * Gets the value of the location property.
	 * 
	 */
	public byte getLOCATION() {
		return location;
	}

	/**
	 * Sets the value of the location property.
	 * 
	 */
	public void setLOCATION(byte value) {
		this.location = value;
	}

	/**
	 * Gets the value of the project property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getPROJECT() {
		return project;
	}

	/**
	 * Sets the value of the project property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setPROJECT(String value) {
		this.project = value;
	}

	/**
	 * Gets the value of the invoicenumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	public LocalDate getINVOICENUMBER() {
		return invoicenumber;
	}


	public void setINVOICENUMBER(LocalDate value) {
		this.invoicenumber = value;
	}

	/**
	 * Gets the value of the invoicetype property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getINVOICETYPE() {
		return invoicetype;
	}

	/**
	 * Sets the value of the invoicetype property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setINVOICETYPE(String value) {
		this.invoicetype = value;
	}

	/**
	 * Gets the value of the financialparty property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getFINANCIALPARTY() {
		return financialparty;
	}

	/**
	 * Sets the value of the financialparty property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setFINANCIALPARTY(String value) {
		this.financialparty = value;
	}

	/**
	 * Gets the value of the financialpartyexternalid property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getFINANCIALPARTYEXTERNALID() {
		return financialpartyexternalid;
	}

	/**
	 * Sets the value of the financialpartyexternalid property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setFINANCIALPARTYEXTERNALID(String value) {
		this.financialpartyexternalid = value;
	}

	/**
	 * Gets the value of the menumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getMENUMBER() {
		return menumber;
	}

	/**
	 * Sets the value of the menumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setMENUMBER(String value) {
		this.menumber = value;
	}

	/**
	 * Gets the value of the transactiontype property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getTRANSACTIONTYPE() {
		return transactiontype;
	}

	/**
	 * Sets the value of the transactiontype property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTRANSACTIONTYPE(String value) {
		this.transactiontype = value;
	}


	public LocalDate getACCOUNTINGDATE() {
		return accountingdate;
	}


	public void setACCOUNTINGDATE(LocalDate value) {
		this.accountingdate = value;
	}


	public LocalDate getDUEDATE() {
		return duedate;
	}

	public void setDUEDATE(LocalDate value) {
		this.duedate = value;
	}

	/**
	 * Gets the value of the chargeamount property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link BigDecimal }
	 *     
	 */
	public BigDecimal getCHARGEAMOUNT() {
		return chargeamount;
	}

	/**
	 * Sets the value of the chargeamount property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link BigDecimal }
	 *     
	 */
	public void setCHARGEAMOUNT(BigDecimal value) {
		this.chargeamount = value;
	}

	/**
	 * Gets the value of the currencycode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getCURRENCYCODE() {
		return currencycode;
	}

	/**
	 * Sets the value of the currencycode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCURRENCYCODE(String value) {
		this.currencycode = value;
	}

	/**
	 * Gets the value of the terms property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getTERMS() {
		return terms;
	}

	/**
	 * Sets the value of the terms property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTERMS(String value) {
		this.terms = value;
	}

	/**
	 * Gets the value of the referencetype1 property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getREFERENCETYPE1() {
		return referencetype1;
	}

	/**
	 * Sets the value of the referencetype1 property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setREFERENCETYPE1(String value) {
		this.referencetype1 = value;
	}

	/**
	 * Gets the value of the referencevalue1 property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getREFERENCEVALUE1() {
		return referencevalue1;
	}

	/**
	 * Sets the value of the referencevalue1 property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setREFERENCEVALUE1(String value) {
		this.referencevalue1 = value;
	}

	/**
	 * Gets the value of the referencenumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getREFERENCENUMBER() {
		return referencenumber;
	}

	/**
	 * Sets the value of the referencenumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setREFERENCENUMBER(String value) {
		this.referencenumber = value;
	}

	/**
	 * Gets the value of the origincountry property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getORIGINCOUNTRY() {
		return origincountry;
	}

	/**
	 * Sets the value of the origincountry property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setORIGINCOUNTRY(String value) {
		this.origincountry = value;
	}

	/**
	 * Gets the value of the destinationcountry property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getDESTINATIONCOUNTRY() {
		return destinationcountry;
	}

	/**
	 * Sets the value of the destinationcountry property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setDESTINATIONCOUNTRY(String value) {
		this.destinationcountry = value;
	}

	/**
	 * Gets the value of the masterblawbnumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getMASTERBLAWBNUMBER() {
		return masterblawbnumber;
	}

	/**
	 * Sets the value of the masterblawbnumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setMASTERBLAWBNUMBER(String value) {
		this.masterblawbnumber = value;
	}

	/**
	 * Gets the value of the houseblawbnumber property.
	 * 
	 */
	public short getHOUSEBLAWBNUMBER() {
		return houseblawbnumber;
	}

	/**
	 * Sets the value of the houseblawbnumber property.
	 * 
	 */
	public void setHOUSEBLAWBNUMBER(short value) {
		this.houseblawbnumber = value;
	}

	/**
	 * Gets the value of the carrier property.
	 * 
	 */
	public byte getCARRIER() {
		return carrier;
	}

	/**
	 * Sets the value of the carrier property.
	 * 
	 */
	public void setCARRIER(byte value) {
		this.carrier = value;
	}

}
