
package com.modetransportation.batch.model;

import java.math.BigDecimal;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.LocalDate;
import com.modetransportation.batch.model.Container.Charges.Charge;
import com.modetransportation.batch.model.Container.Contents.Content;
import com.modetransportation.batch.util.LocalDateAdapter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"equipmentInitial",
		"equipmentNumber",
		"sealNumber1",
		"sealNumber2",
		"sealNumber3",
		"equipmentTypeCode",
		"contents",
		"charges"
})

@Entity
@XmlRootElement(name = "Container")
public class Container {

	@Id
	@XmlTransient
	@Column(name="ContainerId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int containerId;
	@Column(name = "EquipmentInitial")
	@XmlElement(name = "EquipmentInitial", required = true)
	protected String equipmentInitial;
	@Column(name = "EquipmentNumber")
	@XmlElement(name = "EquipmentNumber", required = true)
	protected String equipmentNumber;
	@Column(name = "SealNumber1")
	@XmlElement(name = "SealNumber1")
	protected String sealNumber1;
	@Column(name = "SealNumber2")
	@XmlElement(name = "SealNumber2")
	protected String sealNumber2;
	@Column(name = "SealNumber3")
	@XmlElement(name = "SealNumber3")
	protected String sealNumber3;
	@Column(name = "EquipmentTypeCode")
	@XmlElement(name = "EquipmentTypeCode", required = true)
	protected String equipmentTypeCode;
	@Transient
	@XmlElement(name = "Contents")
	protected Container.Contents contents;
	@Transient
	@XmlElement(name = "Charges")
	protected Container.Charges charges;

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "container")
	private Set<Charge> chargeSet = new HashSet<Charge>(0); 
	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "container")
	private Set<Content> contentSet = new HashSet<Content>(0); 

	@XmlTransient
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
	 * Gets the value of the equipmentInitial property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */

	public String getEquipmentInitial() {
		return equipmentInitial;
	}

	/**
	 * Sets the value of the equipmentInitial property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setEquipmentInitial(String value) {
		this.equipmentInitial = value;
	}

	/**
	 * Gets the value of the equipmentNumber property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getEquipmentNumber() {
		return equipmentNumber;
	}

	/**
	 * Sets the value of the equipmentNumber property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setEquipmentNumber(String value) {
		this.equipmentNumber = value;
	}

	/**
	 * Gets the value of the sealNumber1 property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getSealNumber1() {
		return sealNumber1;
	}

	/**
	 * Sets the value of the sealNumber1 property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setSealNumber1(String value) {
		this.sealNumber1 = value;
	}

	/**
	 * Gets the value of the sealNumber2 property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getSealNumber2() {
		return sealNumber2;
	}

	/**
	 * Sets the value of the sealNumber2 property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setSealNumber2(String value) {
		this.sealNumber2 = value;
	}

	/**
	 * Gets the value of the sealNumber3 property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getSealNumber3() {
		return sealNumber3;
	}

	/**
	 * Sets the value of the sealNumber3 property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setSealNumber3(String value) {
		this.sealNumber3 = value;
	}

	/**
	 * Gets the value of the equipmentTypeCode property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link String }
	 *     
	 */
	public String getEquipmentTypeCode() {
		return equipmentTypeCode;
	}

	/**
	 * Sets the value of the equipmentTypeCode property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link String }
	 *     
	 */
	public void setEquipmentTypeCode(String value) {
		this.equipmentTypeCode = value;
	}

	/**
	 * Gets the value of the contents property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Container.Contents }
	 *     
	 */
	public Container.Contents getContents() {
		return contents;
	}

	/**
	 * Sets the value of the contents property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Container.Contents }
	 *     
	 */
	public void setContents(Container.Contents value) {
		this.contents = value;
	}

	/**
	 * Gets the value of the charges property.
	 * 
	 * @return
	 *     possible object is
	 *     {@link Container.Charges }
	 *     
	 */
	public Container.Charges getCharges() {
		return charges;
	}

	/**
	 * Sets the value of the charges property.
	 * 
	 * @param value
	 *     allowed object is
	 *     {@link Container.Charges }
	 *     
	 */
	public void setCharges(Container.Charges value) {
		this.charges = value;
	}



	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {
			"charge"
	})
	public static class Charges {

		@XmlElement(name = "Charge", required = true)
		protected List<Container.Charges.Charge> charge;


		public List<Container.Charges.Charge> getCharge() {
			if (charge == null) {
				charge = new ArrayList<Container.Charges.Charge>();
			}
			return this.charge;
		}


		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = {
				"chargeType",
				"chargeStatus",
				"invoiceNumber",
				"invoiceDate",
				"terms",
				"dueDate",
				"vendorCode",
				"vendorName",
				"comments",
				"customerCode",
				"customerName",
				"chargeCode",
				"description",
				"basis",
				"quantityInvoiced",
				"rate",
				"chargeAmount",
				"currency",
				"paymentMethod",
				"division",
				"businessLine",
				"glAccount"
		})

		@Entity
		@Table(name="Container_Charge")
		public static class Charge {

			@Id
			@Column(name="ChargeId")
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			@XmlTransient
			protected int chargeId;
			@Column(name = "ChargeType")
			@XmlElement(name = "ChargeType", required = true)
			protected String chargeType;
			@Column(name = "ChargeStatus")
			@XmlElement(name = "ChargeStatus", required = true)
			protected String chargeStatus;
			@Column(name = "InvoiceNumber")
			@XmlElement(name = "InvoiceNumber", required = true)
			protected String invoiceNumber;
			@Column(name = "InvoiceDate")
			@XmlElement(name = "InvoiceDate", required = true)
			@XmlSchemaType(name = "date")
			@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
			protected LocalDate invoiceDate;
			@Column(name = "Terms")
			@XmlElement(name = "Terms", required = true)
			protected String terms;
			@Column(name = "DueDate")
			@XmlElement(name = "DueDate", required = true)
			@XmlSchemaType(name = "date")
			@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
			protected LocalDate dueDate;
			@Column(name = "VendorCode")
			@XmlElement(name = "VendorCode")
			protected String vendorCode;
			@Column(name = "VendorName")
			@XmlElement(name = "VendorName")
			protected String vendorName;
			@Column(name = "Comments")
			@XmlElement(name = "Comments")
			protected String comments;
			@Column(name = "CustomerCode")
			@XmlElement(name = "CustomerCode")
			protected String customerCode;
			@Column(name = "CustomerName")
			@XmlElement(name = "CustomerName")
			protected String customerName;
			@Column(name = "ChargeCode")
			@XmlElement(name = "ChargeCode", required = true)
			protected String chargeCode;
			@Column(name = "Description")
			@XmlElement(name = "Description", required = true)
			protected String description;
			@Column(name = "Basis")
			@XmlElement(name = "Basis")
			protected String basis;
			@Column(name = "QuantityInvoiced")
			@XmlElement(name = "QuantityInvoiced")
			protected BigDecimal quantityInvoiced;
			@Column(name = "Rate")
			@XmlElement(name = "Rate")
			protected BigDecimal rate;
			@Column(name = "ChargeAmount")
			@XmlElement(name = "ChargeAmount", required = true)
			protected BigDecimal chargeAmount;
			@Column(name = "Currency")
			@XmlElement(name = "Currency", required = true)
			protected String currency;
			@Column(name = "PaymentMethod")
			@XmlElement(name = "PaymentMethod")
			protected String paymentMethod;
			@Column(name = "Division")
			@XmlElement(name = "Division", required = true)
			protected String division;
			@Column(name = "BusinessLine")
			@XmlElement(name = "BusinessLine", required = true)
			protected String businessLine;
			@Column(name = "GL_Account")
			@XmlElement(name = "GL_Account", required = true)
			protected String glAccount;

			@XmlTransient
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "ContainerId", nullable = false)
			protected Container container;

			public Container getContainer() {
				return container;
			}

			public void setContainer(Container container) {
				this.container = container;
			}

			/**
			 * Gets the value of the chargeType property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
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


			public LocalDate getInvoiceDate() {
				return invoiceDate;
			}


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

			public LocalDate getDueDate() {
				return dueDate;
			}

			public void setDueDate(LocalDate value) {
				this.dueDate = value;
			}

			/**
			 * Gets the value of the vendorCode property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
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
			 * Gets the value of the comments property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
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
			 * Gets the value of the chargeCode property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link String }
			 *     
			 */
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
			 * Gets the value of the rate property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
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

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {
			"content"
	})
	public static class Contents {

		@XmlElement(name = "Content", required = true)
		protected List<Container.Contents.Content> content;

		public List<Container.Contents.Content> getContent() {
			if (content == null) {
				content = new ArrayList<Container.Contents.Content>();
			}
			return this.content;
		}


		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = {
				"quantityShipped",
				"unitOfMeasure",
				"description",
				"grossWeight",
				"netWeight",
				"weightUnit",
				"volumeWeight",
				"volume",
				"volumeUnit",
				"scheduleBNumber",
				"htsNumber",
				"value",
				"currency",
				"quantityPackagingUnits",
				"masterBillDescription"
		})
		@Entity
		@Table(name="Container_Content")
		public static class Content {
			
			@Id
			@Column(name="ChargeId")
			@GeneratedValue(strategy = GenerationType.IDENTITY)
			@XmlTransient
			protected int chargeId;

			@XmlElement(name = "QuantityShipped", required = true)
			@Column(name = "QuantityShipped")
			protected BigDecimal quantityShipped;
			@XmlElement(name = "UnitOfMeasure", required = true)
			@Column(name = "UnitOfMeasure")
			protected String unitOfMeasure;
			@XmlElement(name = "Description", required = true)
			@Column(name = "Description")
			protected String description;
			@XmlElement(name = "GrossWeight")
			@Column(name = "GrossWeight")
			protected BigDecimal grossWeight;
			@XmlElement(name = "NetWeight")
			@Column(name = "NetWeight")
			protected BigDecimal netWeight;
			@XmlElement(name = "WeightUnit")
			@Column(name = "WeightUnit")
			protected String weightUnit;
			@XmlElement(name = "VolumeWeight")
			@Column(name = "VolumeWeight")
			protected BigDecimal volumeWeight;
			@XmlElement(name = "Volume")
			@Column(name = "Volume")
			protected BigDecimal volume;
			@XmlElement(name = "VolumeUnit")
			@Column(name = "VolumeUnit")
			protected String volumeUnit;
			@XmlElement(name = "ScheduleBNumber")
			@Column(name = "ScheduleBNumber")
			protected String scheduleBNumber;
			@Column(name = "HTSNumber")
			@XmlElement(name = "HTSNumber")
			protected String htsNumber;
			@XmlElement(name = "Value")
			@Column(name = "Value")
			protected BigDecimal value;
			@XmlElement(name = "Currency")
			@Column(name = "Currency")
			protected String currency;
			@XmlElement(name = "QuantityPackagingUnits")
			@Column(name = "QuantityPackagingUnits")
			protected BigInteger quantityPackagingUnits;
			@XmlElement(name = "MasterBillDescription")
			@Column(name = "MasterBillDescription")
			protected String masterBillDescription;

			@XmlTransient
			@ManyToOne(fetch = FetchType.LAZY)
			@JoinColumn(name = "ContainerId", nullable = false)
			protected Container container;

			public Container getContainer() {
				return container;
			}

			public void setContainer(Container container) {
				this.container = container;
			}
			
			
			/**
			 * Gets the value of the quantityShipped property.
			 * 
			 * @return
			 *     possible object is
			 *     {@link BigDecimal }
			 *     
			 */
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

}
