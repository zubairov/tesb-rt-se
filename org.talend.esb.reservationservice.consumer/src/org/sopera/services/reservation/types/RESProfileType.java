
package org.sopera.services.reservation.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.sopera.services.crm.types.CRMStatusType;
import org.sopera.services.crm.types.RYLCStatusCodeType;


/**
 * <p>Java class for RESProfileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RESProfileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crmStatus" type="{http://services.sopera.org/crm/types}CRMStatusType"/>
 *         &lt;element name="rylcStatus" type="{http://services.sopera.org/crm/types}RYLCStatusCodeType"/>
 *         &lt;element name="fromDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="toDate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RESProfileType", propOrder = {
    "crmStatus",
    "rylcStatus",
    "fromDate",
    "toDate"
})
public class RESProfileType {

    @XmlElement(required = true)
    protected CRMStatusType crmStatus;
    @XmlElement(required = true)
    protected RYLCStatusCodeType rylcStatus;
    @XmlElement(required = true)
    protected String fromDate;
    @XmlElement(required = true)
    protected String toDate;

    /**
     * Gets the value of the crmStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CRMStatusType }
     *     
     */
    public CRMStatusType getCrmStatus() {
        return crmStatus;
    }

    /**
     * Sets the value of the crmStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CRMStatusType }
     *     
     */
    public void setCrmStatus(CRMStatusType value) {
        this.crmStatus = value;
    }

    /**
     * Gets the value of the rylcStatus property.
     * 
     * @return
     *     possible object is
     *     {@link RYLCStatusCodeType }
     *     
     */
    public RYLCStatusCodeType getRylcStatus() {
        return rylcStatus;
    }

    /**
     * Sets the value of the rylcStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link RYLCStatusCodeType }
     *     
     */
    public void setRylcStatus(RYLCStatusCodeType value) {
        this.rylcStatus = value;
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

}
