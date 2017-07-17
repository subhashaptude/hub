
package com.modetransportation.batch.model.ap;

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
    "origapinternal",
    "financialpartyexternalid",
    "invoicenumber",
    "transactiontype",
    "accountingdate",
    "duedate",
    "chargeamount",
    "currencycode",
    "terms",
    "shipmentreference",
    "origincountry",
    "masterblawbnumber",
    "houseblawbnumber",
    "typeofmove",
    "carrier"
})
@XmlRootElement(name = "ACCOUNTING_EVENT")
@Entity
@Table(name="AP_Event",  uniqueConstraints={
@UniqueConstraint(columnNames={"shipmentreference","financialpartyexternalid","chargetypecode", "chargeamount"})})
public class APEvent {

	@Id
	@XmlTransient
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
    @XmlElement(name = "CHARGE_TYPE", required = true)
    protected String chargetype;
    @XmlElement(name = "CHARGE_TYPE_CODE", required = true)
    protected String chargetypecode;
    @XmlElement(name = "COMPANY_NUMBER")
    protected byte companynumber;
    @XmlElement(name = "DIVISION")
    protected byte division;
    @XmlElement(name = "BUSINESS_LINE")
    protected byte businessline;
    @XmlElement(name = "TASK")
    protected short task;
    @XmlElement(name = "GL_ACCOUNT")
    protected short glaccount;
    @XmlElement(name = "LOCATION")
    protected byte location;
    @XmlElement(name = "PROJECT", required = true)
    protected String project;
    @XmlElement(name = "ORIG_AP_INTERNAL", required = true)
    protected String origapinternal;
    @XmlElement(name = "FINANCIAL_PARTY_EXTERNAL_ID", required = true)
    protected String financialpartyexternalid;
    @XmlElement(name = "INVOICE_NUMBER", required = true)
    protected String invoicenumber;
    @XmlElement(name = "TRANSACTION_TYPE", required = true)
    protected String transactiontype;
    @XmlElement(name = "ACCOUNTING_DATE", required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
    protected LocalDate accountingdate;
    @XmlElement(name = "DUE_DATE", required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
    protected LocalDate duedate;
    @XmlElement(name = "CHARGE_AMOUNT", required = true)
    protected BigDecimal chargeamount;
    @XmlElement(name = "CURRENCY_CODE", required = true)
    protected String currencycode;
    @XmlElement(name = "TERMS", required = true)
    protected String terms;
    @XmlElement(name = "SHIPMENT_REFERENCE", required = true)
    protected String shipmentreference;
    @XmlElement(name = "ORIGIN_COUNTRY", required = true)
    protected String origincountry;
    @XmlElement(name = "MASTER_BLAWB_NUMBER", required = true)
    protected String masterblawbnumber;
    @XmlElement(name = "HOUSE_BLAWB_NUMBER", required = true)
    protected String houseblawbnumber;
    @XmlElement(name = "TYPE_OF_MOVE", required = true)
    protected String typeofmove;
    @XmlElement(name = "CARRIER", required = true)
    protected String carrier;
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
     * Gets the value of the origapinternal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORIGAPINTERNAL() {
        return origapinternal;
    }

    /**
     * Sets the value of the origapinternal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORIGAPINTERNAL(String value) {
        this.origapinternal = value;
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
     * Gets the value of the invoicenumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINVOICENUMBER() {
        return invoicenumber;
    }

    /**
     * Sets the value of the invoicenumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINVOICENUMBER(String value) {
        this.invoicenumber = value;
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
     * Gets the value of the shipmentreference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSHIPMENTREFERENCE() {
        return shipmentreference;
    }

    /**
     * Sets the value of the shipmentreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSHIPMENTREFERENCE(String value) {
        this.shipmentreference = value;
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
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHOUSEBLAWBNUMBER() {
        return houseblawbnumber;
    }

    /**
     * Sets the value of the houseblawbnumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHOUSEBLAWBNUMBER(String value) {
        this.houseblawbnumber = value;
    }

    /**
     * Gets the value of the typeofmove property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPEOFMOVE() {
        return typeofmove;
    }

    /**
     * Sets the value of the typeofmove property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPEOFMOVE(String value) {
        this.typeofmove = value;
    }

    /**
     * Gets the value of the carrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCARRIER() {
        return carrier;
    }

    /**
     * Sets the value of the carrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCARRIER(String value) {
        this.carrier = value;
    }

}
