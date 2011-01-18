
package org.sopera.services.reservation.types;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.sopera.services.crm.types.CustomerDetailsType;


/**
 * <p>Java class for ConfirmationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConfirmationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="customer" type="{http://services.sopera.org/crm/types}CustomerDetailsType"/>
 *         &lt;element name="car" type="{http://services.sopera.org/reservation/types}RESCarType"/>
 *         &lt;element name="fromDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creditPoints" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConfirmationType", propOrder = {
    "reservationId",
    "customer",
    "car",
    "fromDate",
    "toDate",
    "creditPoints",
    "description"
})
public class ConfirmationType {

    @XmlElement(required = true)
    protected String reservationId;
    @XmlElement(required = true)
    protected CustomerDetailsType customer;
    @XmlElement(required = true)
    protected RESCarType car;
    @XmlElement(required = true)
    protected String fromDate;
    @XmlElement(required = true)
    protected String toDate;
    @XmlElement(required = true)
    protected BigInteger creditPoints;
    protected String description;

    /**
     * Gets the value of the reservationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * Sets the value of the reservationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservationId(String value) {
        this.reservationId = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerDetailsType }
     *     
     */
    public CustomerDetailsType getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerDetailsType }
     *     
     */
    public void setCustomer(CustomerDetailsType value) {
        this.customer = value;
    }

    /**
     * Gets the value of the car property.
     * 
     * @return
     *     possible object is
     *     {@link RESCarType }
     *     
     */
    public RESCarType getCar() {
        return car;
    }

    /**
     * Sets the value of the car property.
     * 
     * @param value
     *     allowed object is
     *     {@link RESCarType }
     *     
     */
    public void setCar(RESCarType value) {
        this.car = value;
    }

    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromDate(String value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the toDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * Sets the value of the toDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToDate(String value) {
        this.toDate = value;
    }

    /**
     * Gets the value of the creditPoints property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCreditPoints() {
        return creditPoints;
    }

    /**
     * Sets the value of the creditPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCreditPoints(BigInteger value) {
        this.creditPoints = value;
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

}
