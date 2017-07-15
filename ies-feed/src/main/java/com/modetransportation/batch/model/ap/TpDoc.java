
package com.modetransportation.batch.model.ap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tpCTRL",
    "payload"
})
@XmlRootElement(name = "tpDoc")
public class TpDoc {

    @XmlElement(required = true)
    protected TpCTRL tpCTRL;
    @XmlElement(name = "PAYLOAD", required = true)
    protected PAYLOAD payload;

    /**
     * Gets the value of the tpCTRL property.
     * 
     * @return
     *     possible object is
     *     {@link TpCTRL }
     *     
     */
    public TpCTRL getTpCTRL() {
        return tpCTRL;
    }

    /**
     * Sets the value of the tpCTRL property.
     * 
     * @param value
     *     allowed object is
     *     {@link TpCTRL }
     *     
     */
    public void setTpCTRL(TpCTRL value) {
        this.tpCTRL = value;
    }

    /**
     * Gets the value of the payload property.
     * 
     * @return
     *     possible object is
     *     {@link PAYLOAD }
     *     
     */
    public PAYLOAD getPAYLOAD() {
        return payload;
    }

    /**
     * Sets the value of the payload property.
     * 
     * @param value
     *     allowed object is
     *     {@link PAYLOAD }
     *     
     */
    public void setPAYLOAD(PAYLOAD value) {
        this.payload = value;
    }

}
