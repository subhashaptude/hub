package com.modetransportation.batch.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.LocalDate;

import com.modetransportation.batch.model.Shipment.Charges.Charge;
import com.modetransportation.batch.model.Shipment.Contents.Content;
import com.modetransportation.batch.model.Shipment.Routing.Segment;
import com.modetransportation.batch.util.LocalDateAdapter;
import com.modetransportation.batch.util.ZonedDateTimeXmlAdapter;



@XmlRootElement(name = "Shipment")
@Entity
public class Shipment {

	@Column(name = "TransactionId")
	protected String transactionId;
	@Column(name = "TransactionDateTime")
	protected ZonedDateTime transactionDateTime;
	@Column(name = "TransactionSetPurpose")
	protected String transactionSetPurpose;
	@Column(name = "TransactionType")
	protected String transactionType;
	@Column(name = "ShipmentType")
	protected String shipmentType;
	@Column(name = "DocumentDate")
	protected LocalDate documentDate;
	@Id
	@Column(name = "FileNumber")
	protected String fileNumber;
	@Column(name = "MasterBillNumber")
	protected String masterBillNumber;
	@Column(name = "HouseBillNumber")
	protected String houseBillNumber;
	@Column(name = "SubHouseBillNumber")
	protected String subHouseBillNumber;
	@Column(name = "ITNumber")
	protected String itNumber;
	@Column(name = "BookingNumber")
	protected String bookingNumber;
	@Column(name = "OriginReference")
	protected String originReference;
	@Column(name = "CustomerReference")
	protected String customerReference;
	@Column(name = "CargoControlNumber")
	protected String cargoControlNumber;
	@Column(name = "Division")
	protected String division;
	@Column(name = "DivisionAlpha")
	protected String divisionAlpha;
	@Column(name = "PaymentMethod")
	protected String paymentMethod;
	@Column(name = "TransportationMethod")
	protected String transportationMethod;
	@Column(name = "TransportationTerms")
	protected String transportationTerms;
	@Column(name = "TransportationTermsLocation")
	protected String transportationTermsLocation;
	@Column(name = "TransportationTermsLocationQualifier")
	protected String transportationTermsLocationQualifier;
	@Column(name = "TypeOfMove")
	protected String typeOfMove;
	@Column(name = "TypeOfService")
	protected String typeOfService;
	@Column(name = "ServiceContract")
	protected String serviceContract;
	@Column(name = "VesselName")
	protected String vesselName;
	@Column(name = "VoyageFlightNumber")
	protected String voyageFlightNumber;
	@Column(name = "PickupDateTime")
	protected LocalDate pickupDateTime;
	@Column(name = "DepartureDateTime")
	protected LocalDate departureDateTime;
	@Column(name = "ArrivalDateTime")
	protected LocalDate arrivalDateTime;
	@Column(name = "DeliveryDateTime")
	protected LocalDate deliveryDateTime;
	@Column(name = "ImportDate")
	protected LocalDate importDate;
	@Column(name = "FIRMSCode")
	protected String firmsCode;
	@Column(name = "WarehouseLocation")
	protected String warehouseLocation;
	@Transient
	protected References references;
	@Transient
	protected Shipment.Routing routing;
	@Transient
	protected Parties parties;
	@Transient
	protected Locations locations;
	@Transient
	protected Containers containers;
	@Transient
	protected Shipment.Contents contents;
	@Column(name = "MarksNumbers")
	protected String marksNumbers;
	@Column(name = "Clause")
	protected String clause;
	@Column(name = "Remarks")
	protected String remarks;
	@Transient
	protected Shipment.Charges charges;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shipment")
	private Set<Charge> chargeSet = new HashSet<Charge>(0); 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shipment")
	private Set<Content> contentSet = new HashSet<Content>(0); 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shipment")
	private Set<Segment> segmentSet = new HashSet<Segment>(0); 

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shipment")
	private Set<Party> partySet = new HashSet<Party>(0); 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shipment")
	private Set<Container> containerSet = new HashSet<Container>(0); 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shipment")
	private Set<Location> locationSet = new HashSet<Location>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shipment")
	private Set<Reference> referenceSet = new HashSet<Reference>(0);
	
	@Column(name = "ValidationFailed")
	protected String validationFailed;
	
	public String getValidationFailed() {
		return validationFailed;
	}

	public void setValidationFailed(String validationFailed) {
		this.validationFailed = validationFailed;
	}

	public Set<Reference> getReferenceSet() {
		return referenceSet;
	}

	public void setReferenceSet(Set<Reference> referenceSet) {
		this.referenceSet = referenceSet;
	}

	public Set<Location> getLocationSet() {
		return locationSet;
	}

	public void setLocationSet(Set<Location> locationSet) {
		this.locationSet = locationSet;
	}

	public Set<Charge> getChargeSet() {
		return chargeSet;
	}

	public void setChargeSet(Set<Charge> chargeSet) {
		this.chargeSet = chargeSet;
	}

	public Set<Content> getContentSet() {
		return contentSet;
	}

	public void setContentSet(Set<Content> contentSet) {
		this.contentSet = contentSet;
	}

/*	public Set<Container> getContainerSet() {
		return containerSet;
	}

	public void setContainerSet(Set<Container> containerSet) {
		this.containerSet = containerSet;
	}*/

	public Set<Party> getPartySet() {
		return partySet;
	}

	public void setPartySet(Set<Party> partySet) {
		this.partySet = partySet;
	}

	public Set<Segment> getSegmentSet() {
		return segmentSet;
	}

	public void setSegmentSet(Set<Segment> segmentSet) {
		this.segmentSet = segmentSet;
	}

	/**
	 * Gets the value of the transactionId property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "TransactionId", required = true)
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Sets the value of the transactionId property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTransactionId(String value) {
		this.transactionId = value;
	}

	/**
	 * Gets the value of the transactionDateTime property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	@XmlElement(name = "TransactionDateTime", required = true)
	@XmlJavaTypeAdapter(type = ZonedDateTime.class, value = ZonedDateTimeXmlAdapter.class)
	public ZonedDateTime getTransactionDateTime() {
		return transactionDateTime;
	}

	/**
	 * Sets the value of the transactionDateTime property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	public void setTransactionDateTime(ZonedDateTime value) {
		this.transactionDateTime = value;
	}

	/**
	 * Gets the value of the transactionSetPurpose property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "TransactionSetPurpose", required = true)
	public String getTransactionSetPurpose() {
		return transactionSetPurpose;
	}

	/**
	 * Sets the value of the transactionSetPurpose property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTransactionSetPurpose(String value) {
		this.transactionSetPurpose = value;
	}

	/**
	 * Gets the value of the transactionType property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "TransactionType")
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * Sets the value of the transactionType property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTransactionType(String value) {
		this.transactionType = value;
	}

	/**
	 * Gets the value of the shipmentType property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "ShipmentType")
	public String getShipmentType() {
		return shipmentType;
	}

	/**
	 * Sets the value of the shipmentType property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setShipmentType(String value) {
		this.shipmentType = value;
	}

	/**
	 * Gets the value of the documentDate property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	@XmlElement(name = "DocumentDate")
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
	public LocalDate getDocumentDate() {
		return documentDate;
	}

	/**
	 * Sets the value of the documentDate property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	public void setDocumentDate(LocalDate value) {
		this.documentDate = value;
	}

	/**
	 * Gets the value of the fileNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "FileNumber", required = true)
	public String getFileNumber() {
		return fileNumber;
	}

	/**
	 * Sets the value of the fileNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setFileNumber(String value) {
		this.fileNumber = value;
	}

	/**
	 * Gets the value of the masterBillNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "MasterBillNumber", required = true)
	public String getMasterBillNumber() {
		return masterBillNumber;
	}

	/**
	 * Sets the value of the masterBillNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setMasterBillNumber(String value) {
		this.masterBillNumber = value;
	}

	/**
	 * Gets the value of the houseBillNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "HouseBillNumber")
	public String getHouseBillNumber() {
		return houseBillNumber;
	}

	/**
	 * Sets the value of the houseBillNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setHouseBillNumber(String value) {
		this.houseBillNumber = value;
	}

	/**
	 * Gets the value of the subHouseBillNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "SubHouseBillNumber")
	public String getSubHouseBillNumber() {
		return subHouseBillNumber;
	}

	/**
	 * Sets the value of the subHouseBillNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setSubHouseBillNumber(String value) {
		this.subHouseBillNumber = value;
	}

	/**
	 * Gets the value of the itNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "ITNumber")
	public String getITNumber() {
		return itNumber;
	}

	/**
	 * Sets the value of the itNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setITNumber(String value) {
		this.itNumber = value;
	}

	/**
	 * Gets the value of the bookingNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "BookingNumber")
	public String getBookingNumber() {
		return bookingNumber;
	}

	/**
	 * Sets the value of the bookingNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setBookingNumber(String value) {
		this.bookingNumber = value;
	}

	/**
	 * Gets the value of the originReference property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "OriginReference")
	public String getOriginReference() {
		return originReference;
	}

	/**
	 * Sets the value of the originReference property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setOriginReference(String value) {
		this.originReference = value;
	}

	/**
	 * Gets the value of the customerReference property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "CustomerReference")
	public String getCustomerReference() {
		return customerReference;
	}

	/**
	 * Sets the value of the customerReference property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCustomerReference(String value) {
		this.customerReference = value;
	}

	/**
	 * Gets the value of the cargoControlNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "CargoControlNumber")
	public String getCargoControlNumber() {
		return cargoControlNumber;
	}

	/**
	 * Sets the value of the cargoControlNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setCargoControlNumber(String value) {
		this.cargoControlNumber = value;
	}

	/**
	 * Gets the value of the division property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "Division")
	public String getDivision() {
		return division;
	}

	/**
	 * Sets the value of the division property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setDivision(String value) {
		this.division = value;
	}

	/**
	 * Gets the value of the divisionAlpha property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "DivisionAlpha")
	public String getDivisionAlpha() {
		return divisionAlpha;
	}

	/**
	 * Sets the value of the divisionAlpha property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setDivisionAlpha(String value) {
		this.divisionAlpha = value;
	}

	/**
	 * Gets the value of the paymentMethod property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "PaymentMethod")
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Sets the value of the paymentMethod property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setPaymentMethod(String value) {
		this.paymentMethod = value;
	}

	/**
	 * Gets the value of the transportationMethod property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "TransportationMethod")
	public String getTransportationMethod() {
		return transportationMethod;
	}

	/**
	 * Sets the value of the transportationMethod property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTransportationMethod(String value) {
		this.transportationMethod = value;
	}

	/**
	 * Gets the value of the transportationTerms property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Object }
	 *     
	 */
	@XmlElement(name = "TransportationTerms")
	public String getTransportationTerms() {
		return transportationTerms;
	}

	/**
	 * Sets the value of the transportationTerms property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Object }
	 *     
	 */
	public void setTransportationTerms(String value) {
		this.transportationTerms = value;
	}

	/**
	 * Gets the value of the transportationTermsLocation property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Object }
	 *     
	 */
	@XmlElement(name = "TransportationTermsLocation")
	public String getTransportationTermsLocation() {
		return transportationTermsLocation;
	}

	/**
	 * Sets the value of the transportationTermsLocation property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Object }
	 *     
	 */
	public void setTransportationTermsLocation(String value) {
		this.transportationTermsLocation = value;
	}

	/**
	 * Gets the value of the transportationTermsLocationQualifier property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "TransportationTermsLocationQualifier")
	public String getTransportationTermsLocationQualifier() {
		return transportationTermsLocationQualifier;
	}

	/**
	 * Sets the value of the transportationTermsLocationQualifier property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTransportationTermsLocationQualifier(String value) {
		this.transportationTermsLocationQualifier = value;
	}

	/**
	 * Gets the value of the typeOfMove property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "TypeOfMove")
	public String getTypeOfMove() {
		return typeOfMove;
	}

	/**
	 * Sets the value of the typeOfMove property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTypeOfMove(String value) {
		this.typeOfMove = value;
	}

	/**
	 * Gets the value of the typeOfService property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "TypeOfService")
	public String getTypeOfService() {
		return typeOfService;
	}

	/**
	 * Sets the value of the typeOfService property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setTypeOfService(String value) {
		this.typeOfService = value;
	}

	/**
	 * Gets the value of the serviceContract property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "ServiceContract")
	public String getServiceContract() {
		return serviceContract;
	}

	/**
	 * Sets the value of the serviceContract property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setServiceContract(String value) {
		this.serviceContract = value;
	}

	/**
	 * Gets the value of the vesselName property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "VesselName")
	public String getVesselName() {
		return vesselName;
	}

	/**
	 * Sets the value of the vesselName property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setVesselName(String value) {
		this.vesselName = value;
	}

	/**
	 * Gets the value of the voyageFlightNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "VoyageFlightNumber")
	public String getVoyageFlightNumber() {
		return voyageFlightNumber;
	}

	/**
	 * Sets the value of the voyageFlightNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setVoyageFlightNumber(String value) {
		this.voyageFlightNumber = value;
	}

	/**
	 * Gets the value of the pickupDateTime property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	@XmlElement(name = "PickupDateTime")
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
	public LocalDate getPickupDateTime() {
		return pickupDateTime;
	}

	/**
	 * Sets the value of the pickupDateTime property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	public void setPickupDateTime(LocalDate value) {
		this.pickupDateTime = value;
	}

	/**
	 * Gets the value of the departureDateTime property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	@XmlElement(name = "DepartureDateTime")
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
	public LocalDate getDepartureDateTime() {
		return departureDateTime;
	}

	/**
	 * Sets the value of the departureDateTime property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	public void setDepartureDateTime(LocalDate value) {
		this.departureDateTime = value;
	}

	/**
	 * Gets the value of the arrivalDateTime property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	@XmlElement(name = "ArrivalDateTime")
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
	public LocalDate getArrivalDateTime() {
		return arrivalDateTime;
	}

	/**
	 * Sets the value of the arrivalDateTime property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	public void setArrivalDateTime(LocalDate value) {
		this.arrivalDateTime = value;
	}

	/**
	 * Gets the value of the deliveryDateTime property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	@XmlElement(name = "DeliveryDateTime")
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
	public LocalDate getDeliveryDateTime() {
		return deliveryDateTime;
	}

	/**
	 * Sets the value of the deliveryDateTime property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	public void setDeliveryDateTime(LocalDate value) {
		this.deliveryDateTime = value;
	}

	/**
	 * Gets the value of the importDate property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	@XmlElement(name = "ImportDate")
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
	public LocalDate getImportDate() {
		return importDate;
	}

	/**
	 * Sets the value of the importDate property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link XMLGregorianCalendar }
	 *     
	 */
	public void setImportDate(LocalDate value) {
		this.importDate = value;
	}

	/**
	 * Gets the value of the firmsCode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "FIRMSCode")
	public String getFIRMSCode() {
		return firmsCode;
	}

	/**
	 * Sets the value of the firmsCode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setFIRMSCode(String value) {
		this.firmsCode = value;
	}

	/**
	 * Gets the value of the warehouseLocation property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "WarehouseLocation")
	public String getWarehouseLocation() {
		return warehouseLocation;
	}

	/**
	 * Sets the value of the warehouseLocation property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setWarehouseLocation(String value) {
		this.warehouseLocation = value;
	}

	/**
	 * Gets the value of the references property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link References }
	 *     
	 */
	@XmlElement(name = "References")
	public References getReferences() {
		return references;
	}

	/**
	 * Sets the value of the references property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link References }
	 *     
	 */
	public void setReferences(References value) {
		this.references = value;
	}

	/**
	 * Gets the value of the routing property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Shipment.Routing }
	 *     
	 */
	@XmlElement(name = "Routing")
	public Shipment.Routing getRouting() {
		return routing;
	}

	/**
	 * Sets the value of the routing property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Shipment.Routing }
	 *     
	 */
	public void setRouting(Shipment.Routing value) {
		this.routing = value;
	}

	/**
	 * Gets the value of the parties property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Parties }
	 *     
	 */
	@XmlElement(name = "Parties")
	public Parties getParties() {
		return parties;
	}

	/**
	 * Sets the value of the parties property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Parties }
	 *     
	 */
	public void setParties(Parties value) {
		this.parties = value;
	}

	/**
	 * Gets the value of the locations property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Locations }
	 *     
	 */
	@XmlElement(name = "Locations")
	public Locations getLocations() {
		return locations;
	}

	/**
	 * Sets the value of the locations property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Locations }
	 *     
	 */
	public void setLocations(Locations value) {
		this.locations = value;
	}

	/**
	 * Gets the value of the containers property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Containers }
	 *     
	 */
	@XmlElement(name = "Containers")
	public Containers getContainers() {
		return containers;
	}

	/**
	 * Sets the value of the containers property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Containers }
	 *     
	 */
	public void setContainers(Containers value) {
		this.containers = value;
	}

	/**
	 * Gets the value of the contents property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Shipment.Contents }
	 *     
	 */
	@XmlElement(name = "Contents")
	public Shipment.Contents getContents() {
		return contents;
	}

	/**
	 * Sets the value of the contents property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Shipment.Contents }
	 *     
	 */
	public void setContents(Shipment.Contents value) {
		this.contents = value;
	}

	/**
	 * Gets the value of the marksNumbers property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "MarksNumbers")
	public String getMarksNumbers() {
		return marksNumbers;
	}

	/**
	 * Sets the value of the marksNumbers property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setMarksNumbers(String value) {
		this.marksNumbers = value;
	}

	/**
	 * Gets the value of the clause property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "Clause")
	public String getClause() {
		return clause;
	}

	/**
	 * Sets the value of the clause property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setClause(String value) {
		this.clause = value;
	}

	/**
	 * Gets the value of the remarks property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	@XmlElement(name = "Remarks")
	public String getRemarks() {
		return remarks;
	}

	/**
	 * Sets the value of the remarks property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setRemarks(String value) {
		this.remarks = value;
	}

	/**
	 * Gets the value of the charges property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Shipment.Charges }
	 *     
	 */
	@XmlElement(name = "Charges")
	public Shipment.Charges getCharges() {
		return charges;
	}

	/**
	 * Sets the value of the charges property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Shipment.Charges }
	 *     
	 */
	public void setCharges(Shipment.Charges value) {
		this.charges = value;
	}




	public static class Charges {

		protected List<Shipment.Charges.Charge> charge;


		@XmlElement(name = "Charge", required = true)
		public List<Shipment.Charges.Charge> getCharge() {
			if (charge == null) {
				charge = new ArrayList<Shipment.Charges.Charge>();
			}
			return this.charge;
		}

		@Entity
		@Table(name = "Charge")
		public static class Charge {

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			@Column(name="ChargeId")
			protected int id;
			@Column(name = "ChargeType")
			protected String chargeType;
			@Column(name = "ChargeStatus")
			protected String chargeStatus;
			@Column(name = "InvoiceNumber")
			protected String invoiceNumber;
			@Column(name = "InvoiceDate")
			protected LocalDate invoiceDate;
			@Column(name = "Terms")
			protected String terms;
			@Column(name = "DueDate")
			protected LocalDate dueDate;
			@Column(name = "VendorCode")
			protected String vendorCode;
			@Column(name = "VendorName")
			protected String vendorName;
			@Column(name = "Comments")
			protected String comments;
			@Column(name = "CustomerCode")
			protected String customerCode;
			@Column(name = "CustomerName")
			protected String customerName;
			@Column(name = "ChargeCode")
			protected String chargeCode;
			@Column(name = "Description")
			protected String description;
			@Column(name = "Basis")
			protected String basis;
			@Column(name = "QuantityInvoiced")
			protected BigDecimal quantityInvoiced;
			@Column(name = "Rate")
			protected BigDecimal rate;
			@Column(name = "ChargeAmount")
			protected BigDecimal chargeAmount;
			@Column(name = "Currency")
			protected String currency;
			@Column(name = "PaymentMethod")
			protected String paymentMethod;
			@Column(name = "Division")
			protected String division;
			@Column(name = "BusinessLine")
			protected String businessLine;
			@Column(name = "GLAccount")
			protected String glAccount;
			@Column(name = "ChargeUnit")
			protected String chargeUnit;

			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "fileNumber", nullable = false)
			protected Shipment shipment;

			public Shipment getShipment() {
				return shipment;
			}

			public void setShipment(Shipment shipment) {
				this.shipment = shipment;
			}

			/**
			 * Gets the value of the chargeType property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "ChargeType", required = true)
			public String getChargeType() {
				return chargeType;
			}

			/**
			 * Sets the value of the chargeType property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setChargeType(String value) {
				this.chargeType = value;
			}

			/**
			 * Gets the value of the chargeStatus property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "ChargeStatus", required = true)
			public String getChargeStatus() {
				return chargeStatus;
			}

			/**
			 * Sets the value of the chargeStatus property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setChargeStatus(String value) {
				this.chargeStatus = value;
			}

			/**
			 * Gets the value of the invoiceNumber property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "InvoiceNumber")
			public String getInvoiceNumber() {
				return invoiceNumber;
			}

			/**
			 * Sets the value of the invoiceNumber property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setInvoiceNumber(String value) {
				this.invoiceNumber = value;
			}

			/**
			 * Gets the value of the invoiceDate property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link XMLGregorianCalendar }
			 *     
			 */
			@XmlElement(name = "InvoiceDate")
			@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
			public LocalDate getInvoiceDate() {
				return invoiceDate;
			}

			/**
			 * Sets the value of the invoiceDate property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link XMLGregorianCalendar }
			 *     
			 */
			public void setInvoiceDate(LocalDate value) {
				this.invoiceDate = value;
			}

			/**
			 * Gets the value of the terms property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "Terms")
			public String getTerms() {
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
			public void setTerms(String value) {
				this.terms = value;
			}

			/**
			 * Gets the value of the dueDate property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link XMLGregorianCalendar }
			 *     
			 */
			@XmlElement(name = "DueDate")
			@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
			public LocalDate getDueDate() {
				return dueDate;
			}

			/**
			 * Sets the value of the dueDate property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link XMLGregorianCalendar }
			 *     
			 */
			public void setDueDate(LocalDate value) {
				this.dueDate = value;
			}

			/**
			 * Gets the value of the comments property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "Comments")
			public String getComments() {
				return comments;
			}

			/**
			 * Sets the value of the comments property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setComments(String value) {
				this.comments = value;
			}

			/**
			 * Gets the value of the customerCode property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "CustomerCode")
			public String getCustomerCode() {
				return customerCode;
			}

			/**
			 * Sets the value of the customerCode property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setCustomerCode(String value) {
				this.customerCode = value;
			}

			/**
			 * Gets the value of the customerName property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "CustomerName")
			public String getCustomerName() {
				return customerName;
			}

			/**
			 * Sets the value of the customerName property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setCustomerName(String value) {
				this.customerName = value;
			}

			/**
			 * Gets the value of the vendorCode property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "VendorCode")
			public String getVendorCode() {
				return vendorCode;
			}

			/**
			 * Sets the value of the vendorCode property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setVendorCode(String value) {
				this.vendorCode = value;
			}

			/**
			 * Gets the value of the vendorName property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "VendorName")
			public String getVendorName() {
				return vendorName;
			}

			/**
			 * Sets the value of the vendorName property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setVendorName(String value) {
				this.vendorName = value;
			}

			/**
			 * Gets the value of the chargeCode property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "ChargeCode", required = true)
			public String getChargeCode() {
				return chargeCode;
			}

			/**
			 * Sets the value of the chargeCode property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setChargeCode(String value) {
				this.chargeCode = value;
			}

			/**
			 * Gets the value of the description property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "Description", required = true)
			public String getDescription() {
				return description;
			}

			/**
			 * Sets the value of the description property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setDescription(String value) {
				this.description = value;
			}

			/**
			 * Gets the value of the basis property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "Basis")
			public String getBasis() {
				return basis;
			}

			/**
			 * Sets the value of the basis property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setBasis(String value) {
				this.basis = value;
			}

			/**
			 * Gets the value of the quantityInvoiced property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
			@XmlElement(name = "QuantityInvoiced")
			public BigDecimal getQuantityInvoiced() {
				return quantityInvoiced;
			}

			/**
			 * Sets the value of the quantityInvoiced property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigDecimal }
			 *     
			 */
			public void setQuantityInvoiced(BigDecimal value) {
				this.quantityInvoiced = value;
			}

			/**
			 * Gets the value of the chargeUnit property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "ChargeUnit")
			public String getChargeUnit() {
				return chargeUnit;
			}

			/**
			 * Sets the value of the chargeUnit property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setChargeUnit(String value) {
				this.chargeUnit = value;
			}

			/**
			 * Gets the value of the rate property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
			@XmlElement(name = "Rate")
			public BigDecimal getRate() {
				return rate;
			}

			/**
			 * Sets the value of the rate property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigDecimal }
			 *     
			 */
			public void setRate(BigDecimal value) {
				this.rate = value;
			}

			/**
			 * Gets the value of the chargeAmount property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
			@XmlElement(name = "ChargeAmount")
			public BigDecimal getChargeAmount() {
				return chargeAmount;
			}

			/**
			 * Sets the value of the chargeAmount property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigDecimal }
			 *     
			 */
			public void setChargeAmount(BigDecimal value) {
				this.chargeAmount = value;
			}

			/**
			 * Gets the value of the currency property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "Currency")
			public String getCurrency() {
				return currency;
			}

			/**
			 * Sets the value of the currency property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setCurrency(String value) {
				this.currency = value;
			}

			/**
			 * Gets the value of the paymentMethod property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "PaymentMethod")
			public String getPaymentMethod() {
				return paymentMethod;
			}

			/**
			 * Sets the value of the paymentMethod property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setPaymentMethod(String value) {
				this.paymentMethod = value;
			}

			/**
			 * Gets the value of the division property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "Division")
			public String getDivision() {
				return division;
			}

			/**
			 * Sets the value of the division property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setDivision(String value) {
				this.division = value;
			}

			/**
			 * Gets the value of the businessLine property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "BusinessLine")
			public String getBusinessLine() {
				return businessLine;
			}

			/**
			 * Sets the value of the businessLine property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setBusinessLine(String value) {
				this.businessLine = value;
			}

			/**
			 * Gets the value of the glAccount property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "GL_Account")
			public String getGLAccount() {
				return glAccount;
			}

			/**
			 * Sets the value of the glAccount property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setGLAccount(String value) {
				this.glAccount = value;
			}

		}

	}

	public static class Contents {


		protected Shipment.Contents.Content content;

		/**
		 * Gets the value of the content property.
		 * 
		 * @return
		 *     possible object is
		 *     {@link Shipment.Contents.Content }
		 *     
		 */

		@XmlElement(name = "Content", required = true)
		public Shipment.Contents.Content getContent() {
			return content;
		}

		/**
		 * Sets the value of the content property.
		 * 
		 * @param value
		 *     allowed object is
		 *     {@link Shipment.Contents.Content }
		 *     
		 */
		public void setContent(Shipment.Contents.Content value) {
			this.content = value;
		}



		@Entity
		@Table(name = "Content")
		public static class Content {

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			@Column(name="ContentId")
			protected int id;
			@Column(name = "QuantityShipped")
			protected BigDecimal quantityShipped;
			@Column(name = "UnitOfMeasure")
			protected String unitOfMeasure;
			@Column(name = "Description")
			protected String description;
			@Column(name = "GrossWeight")
			protected BigDecimal grossWeight;
			@Column(name = "NetWeight")
			protected BigDecimal netWeight;
			@Column(name = "WeightUnit")
			protected String weightUnit;
			@Column(name = "VolumeWeight")
			protected BigDecimal volumeWeight;
			@Column(name = "Volume")
			protected BigDecimal volume;
			@Column(name = "VolumeUnit")
			protected String volumeUnit;
			@Column(name = "ScheduleBNumber")
			protected String scheduleBNumber;
			@Column(name = "HTSNumber")
			protected String htsNumber;
			@Column(name = "Value")
			protected BigDecimal value;
			@Column(name = "Currency")
			protected String currency;
			@Column(name = "QuantityPackagingUnits")
			protected BigInteger quantityPackagingUnits;
			@Column(name = "MasterBillDescription")
			protected String masterBillDescription;

			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "fileNumber", nullable = false)
			protected Shipment shipment;

			public Shipment getShipment() {
				return shipment;
			}

			public void setShipment(Shipment shipment) {
				this.shipment = shipment;
			}


			/**
			 * Gets the value of the quantityShipped property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
			@XmlElement(name = "QuantityShipped", required = true)
			public BigDecimal getQuantityShipped() {
				return quantityShipped;
			}

			/**
			 * Sets the value of the quantityShipped property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigDecimal }
			 *     
			 */
			public void setQuantityShipped(BigDecimal value) {
				this.quantityShipped = value;
			}

			/**
			 * Gets the value of the unitOfMeasure property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "UnitOfMeasure", required = true)
			public String getUnitOfMeasure() {
				return unitOfMeasure;
			}

			/**
			 * Sets the value of the unitOfMeasure property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setUnitOfMeasure(String value) {
				this.unitOfMeasure = value;
			}

			/**
			 * Gets the value of the description property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "Description", required = true)
			public String getDescription() {
				return description;
			}

			/**
			 * Sets the value of the description property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setDescription(String value) {
				this.description = value;
			}

			/**
			 * Gets the value of the grossWeight property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
			@XmlElement(name = "GrossWeight")
			public BigDecimal getGrossWeight() {
				return grossWeight;
			}

			/**
			 * Sets the value of the grossWeight property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigDecimal }
			 *     
			 */
			public void setGrossWeight(BigDecimal value) {
				this.grossWeight = value;
			}

			/**
			 * Gets the value of the netWeight property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
			@XmlElement(name = "NetWeight")
			public BigDecimal getNetWeight() {
				return netWeight;
			}

			/**
			 * Sets the value of the netWeight property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigDecimal }
			 *     
			 */
			public void setNetWeight(BigDecimal value) {
				this.netWeight = value;
			}

			/**
			 * Gets the value of the weightUnit property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "WeightUnit")
			public String getWeightUnit() {
				return weightUnit;
			}

			/**
			 * Sets the value of the weightUnit property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setWeightUnit(String value) {
				this.weightUnit = value;
			}

			/**
			 * Gets the value of the volumeWeight property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
			@XmlElement(name = "VolumeWeight")
			public BigDecimal getVolumeWeight() {
				return volumeWeight;
			}

			/**
			 * Sets the value of the volumeWeight property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigDecimal }
			 *     
			 */
			public void setVolumeWeight(BigDecimal value) {
				this.volumeWeight = value;
			}

			/**
			 * Gets the value of the volume property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
			@XmlElement(name = "Volume")
			public BigDecimal getVolume() {
				return volume;
			}

			/**
			 * Sets the value of the volume property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigDecimal }
			 *     
			 */
			public void setVolume(BigDecimal value) {
				this.volume = value;
			}

			/**
			 * Gets the value of the volumeUnit property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "VolumeUnit")
			public String getVolumeUnit() {
				return volumeUnit;
			}

			/**
			 * Sets the value of the volumeUnit property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setVolumeUnit(String value) {
				this.volumeUnit = value;
			}

			/**
			 * Gets the value of the scheduleBNumber property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "ScheduleBNumber")
			public String getScheduleBNumber() {
				return scheduleBNumber;
			}

			/**
			 * Sets the value of the scheduleBNumber property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setScheduleBNumber(String value) {
				this.scheduleBNumber = value;
			}

			/**
			 * Gets the value of the htsNumber property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "HTSNumber")
			public String getHTSNumber() {
				return htsNumber;
			}

			/**
			 * Sets the value of the htsNumber property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setHTSNumber(String value) {
				this.htsNumber = value;
			}

			/**
			 * Gets the value of the value property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
			@XmlElement(name = "Value")
			public BigDecimal getValue() {
				return value;
			}

			/**
			 * Sets the value of the value property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigDecimal }
			 *     
			 */
			public void setValue(BigDecimal value) {
				this.value = value;
			}

			/**
			 * Gets the value of the currency property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "Currency")
			public String getCurrency() {
				return currency;
			}

			/**
			 * Sets the value of the currency property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setCurrency(String value) {
				this.currency = value;
			}

			/**
			 * Gets the value of the quantityPackagingUnits property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigInteger }
			 *     
			 */
			@XmlElement(name = "QuantityPackagingUnits")
			public BigInteger getQuantityPackagingUnits() {
				return quantityPackagingUnits;
			}

			/**
			 * Sets the value of the quantityPackagingUnits property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link BigInteger }
			 *     
			 */
			public void setQuantityPackagingUnits(BigInteger value) {
				this.quantityPackagingUnits = value;
			}

			/**
			 * Gets the value of the masterBillDescription property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "MasterBillDescription")
			public String getMasterBillDescription() {
				return masterBillDescription;
			}

			/**
			 * Sets the value of the masterBillDescription property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setMasterBillDescription(String value) {
				this.masterBillDescription = value;
			}

		}

	}


	public static class Routing {

		protected List<Shipment.Routing.Segment> segment;


		@XmlElement(name = "Segment", required = true)
		public List<Shipment.Routing.Segment> getSegment() {
			if (segment == null) {
				segment = new ArrayList<Shipment.Routing.Segment>();
			}
			return this.segment;
		}



		@Entity
		@Table(name = "Segment")
		public static class Segment {

			@Id
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			@Column(name="ID")
			private long id;
			@Column(name="Sequence")
			protected int sequence;
			@Column(name="Carrier")
			protected String carrier;
			@Column(name="VesselName")
			protected String vesselName;
			@Column(name="VoyageFlightNumber")
			protected String voyageFlightNumber;
			@Column(name="DepartureLocation")
			protected String departureLocation;
			@Column(name="DepartureLocationQualifier")
			protected String departureLocationQualifier;
			@Column(name="DepartureDateTime")
			protected LocalDate departureDateTime;
			@Column(name="ArrivalLocation")
			protected String arrivalLocation;
			@Column(name="ArrivalLocationQualifier")
			protected String arrivalLocationQualifier;
			@Column(name="ArrivalDateTime")
			protected LocalDate arrivalDateTime;
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
			 * Gets the value of the sequence property.
			 * 
			 */
			@XmlElement(name = "Sequence")
			public int getSequence() {
				return sequence;
			}

			/**
			 * Sets the value of the sequence property.
			 * 
			 */
			public void setSequence(int value) {
				this.sequence = value;
			}

			/**
			 * Gets the value of the carrier property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "Carrier")
			public String getCarrier() {
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
			public void setCarrier(String value) {
				this.carrier = value;
			}

			/**
			 * Gets the value of the vesselName property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "VesselName")
			public String getVesselName() {
				return vesselName;
			}

			/**
			 * Sets the value of the vesselName property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setVesselName(String value) {
				this.vesselName = value;
			}

			/**
			 * Gets the value of the voyageFlightNumber property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "VoyageFlightNumber")
			public String getVoyageFlightNumber() {
				return voyageFlightNumber;
			}

			/**
			 * Sets the value of the voyageFlightNumber property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setVoyageFlightNumber(String value) {
				this.voyageFlightNumber = value;
			}

			/**
			 * Gets the value of the departureLocation property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "DepartureLocation", required = true)
			public String getDepartureLocation() {
				return departureLocation;
			}

			/**
			 * Sets the value of the departureLocation property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setDepartureLocation(String value) {
				this.departureLocation = value;
			}

			/**
			 * Gets the value of the departureLocationQualifier property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "DepartureLocationQualifier", required = true)
			public String getDepartureLocationQualifier() {
				return departureLocationQualifier;
			}

			/**
			 * Sets the value of the departureLocationQualifier property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setDepartureLocationQualifier(String value) {
				this.departureLocationQualifier = value;
			}

			/**
			 * Gets the value of the departureDateTime property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link XMLGregorianCalendar }
			 *     
			 */
			@XmlElement(name = "DepartureDateTime", required = true)
			@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
			public LocalDate getDepartureDateTime() {
				return departureDateTime;
			}

			/**
			 * Sets the value of the departureDateTime property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link XMLGregorianCalendar }
			 *     
			 */
			public void setDepartureDateTime(LocalDate value) {
				this.departureDateTime = value;
			}

			/**
			 * Gets the value of the arrivalLocation property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "ArrivalLocation", required = true)
			public String getArrivalLocation() {
				return arrivalLocation;
			}

			/**
			 * Sets the value of the arrivalLocation property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setArrivalLocation(String value) {
				this.arrivalLocation = value;
			}

			/**
			 * Gets the value of the arrivalLocationQualifier property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
			@XmlElement(name = "ArrivalLocationQualifier", required = true)
			public String getArrivalLocationQualifier() {
				return arrivalLocationQualifier;
			}

			/**
			 * Sets the value of the arrivalLocationQualifier property.
			 * 
			 * @param value
			 *     allowed object is
			 *     {@link String }
			 *     
			 */
			public void setArrivalLocationQualifier(String value) {
				this.arrivalLocationQualifier = value;
			}


			@XmlElement(name = "ArrivalDateTime", required = true)
			@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
			public LocalDate getArrivalDateTime() {
				return arrivalDateTime;
			}


			public void setArrivalDateTime(LocalDate value) {
				this.arrivalDateTime = value;
			}

		}

	}

}
