package com.modetransportation.batch.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Party")
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PartyId")
	protected int partyId;
    @Column(name = "PartyType")
    protected String partyType;
    @Column(name = "PartyCode")
    protected String partyCode;
    @Column(name = "Name")
    protected String name;
    @Column(name = "Address1")
    protected String address1;
    @Column(name = "Address2")
    protected String address2;
    @Column(name = "CityName")
    protected String cityName;
    @Column(name = "StateOrProvinceCode")
    protected String stateOrProvinceCode;
    @Column(name = "PostalCode")
    protected String postalCode;
    @Column(name = "CountryCode")
    protected String countryCode;
    @Column(name = "IdCode")
    protected String idCode;
    @Column(name = "IdCodeQualifier")
    protected String idCodeQualifier;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FileNumber", nullable = false)
	protected Shipment shipment;

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	/**
	 * Gets the value of the partyType property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "PartyType", required = true)
	public String getPartyType() {
		return partyType;
	}

	/**
	 * Sets the value of the partyType property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setPartyType(String value) {
		this.partyType = value;
	}

	/**
	 * Gets the value of the partyCode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "PartyCode")
	public String getPartyCode() {
		return partyCode;
	}

	/**
	 * Sets the value of the partyCode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setPartyCode(String value) {
		this.partyCode = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "Name", required = true)
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the address1 property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "Address1")
	public String getAddress1() {
		return address1;
	}

	/**
	 * Sets the value of the address1 property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setAddress1(String value) {
		this.address1 = value;
	}

	/**
	 * Gets the value of the address2 property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "Address2")
	public String getAddress2() {
		return address2;
	}

	/**
	 * Sets the value of the address2 property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setAddress2(String value) {
		this.address2 = value;
	}

	/**
	 * Gets the value of the cityName property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "CityName", required = true)
	public String getCityName() {
		return cityName;
	}

	/**
	 * Sets the value of the cityName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCityName(String value) {
		this.cityName = value;
	}

	/**
	 * Gets the value of the stateOrProvinceCode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "StateOrProvinceCode")
	public String getStateOrProvinceCode() {
		return stateOrProvinceCode;
	}

	/**
	 * Sets the value of the stateOrProvinceCode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setStateOrProvinceCode(String value) {
		this.stateOrProvinceCode = value;
	}

	/**
	 * Gets the value of the postalCode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "PostalCode")
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the value of the postalCode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setPostalCode(String value) {
		this.postalCode = value;
	}

	/**
	 * Gets the value of the countryCode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "CountryCode")
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the value of the countryCode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCountryCode(String value) {
		this.countryCode = value;
	}

	/**
	 * Gets the value of the idCode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "IdCode")
	public String getIdCode() {
		return idCode;
	}

	/**
	 * Sets the value of the idCode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setIdCode(String value) {
		this.idCode = value;
	}

	/**
	 * Gets the value of the idCodeQualifier property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "IdCodeQualifier")
	public String getIdCodeQualifier() {
		return idCodeQualifier;
	}

	/**
	 * Sets the value of the idCodeQualifier property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setIdCodeQualifier(String value) {
		this.idCodeQualifier = value;
	}

}
