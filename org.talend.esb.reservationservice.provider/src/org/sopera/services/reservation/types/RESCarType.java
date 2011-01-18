
package org.sopera.services.reservation.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RESCarType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RESCarType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="carId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="designModel" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="class" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rateDay" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rateWeekend" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="securityGuarantee" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RESCarType", propOrder = {
    "carId",
    "designModel",
    "clazz",
    "brand",
    "rateDay",
    "rateWeekend",
    "securityGuarantee"
})
public class RESCarType {

    @XmlElement(required = true)
    protected String carId;
    @XmlElement(required = true)
    protected String designModel;
    @XmlElement(name = "class", required = true)
    protected String clazz;
    @XmlElement(required = true)
    protected String brand;
    @XmlElement(required = true)
    protected String rateDay;
    @XmlElement(required = true)
    protected String rateWeekend;
    @XmlElement(required = true)
    protected String securityGuarantee;

    /**
     * Gets the value of the carId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarId() {
        return carId;
    }

    /**
     * Sets the value of the carId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarId(String value) {
        this.carId = value;
    }

    /**
     * Gets the value of the designModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignModel() {
        return designModel;
    }

    /**
     * Sets the value of the designModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignModel(String value) {
        this.designModel = value;
    }

    /**
     * Gets the value of the clazz property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrand(String value) {
        this.brand = value;
    }

    /**
     * Gets the value of the rateDay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateDay() {
        return rateDay;
    }

    /**
     * Sets the value of the rateDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateDay(String value) {
        this.rateDay = value;
    }

    /**
     * Gets the value of the rateWeekend property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateWeekend() {
        return rateWeekend;
    }

    /**
     * Sets the value of the rateWeekend property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateWeekend(String value) {
        this.rateWeekend = value;
    }

    /**
     * Gets the value of the securityGuarantee property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityGuarantee() {
        return securityGuarantee;
    }

    /**
     * Sets the value of the securityGuarantee property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityGuarantee(String value) {
        this.securityGuarantee = value;
    }

}
