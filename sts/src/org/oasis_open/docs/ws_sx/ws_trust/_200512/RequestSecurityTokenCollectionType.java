
package org.oasis_open.docs.ws_sx.ws_trust._200512;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         The RequestSecurityTokenCollection (RSTC) element is used to provide multiple RST requests. 
 *         One or more RSTR elements in an RSTRC element are returned in the response to the RequestSecurityTokenCollection.
 *       
 * 
 * <p>Java class for RequestSecurityTokenCollectionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestSecurityTokenCollectionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RequestSecurityToken" type="{http://docs.oasis-open.org/ws-sx/ws-trust/200512/}RequestSecurityTokenType" maxOccurs="unbounded" minOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestSecurityTokenCollectionType", propOrder = {
    "requestSecurityToken"
})
public class RequestSecurityTokenCollectionType {

    @XmlElement(name = "RequestSecurityToken", required = true)
    protected List<RequestSecurityTokenType> requestSecurityToken;

    /**
     * Gets the value of the requestSecurityToken property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requestSecurityToken property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequestSecurityToken().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RequestSecurityTokenType }
     * 
     * 
     */
    public List<RequestSecurityTokenType> getRequestSecurityToken() {
        if (requestSecurityToken == null) {
            requestSecurityToken = new ArrayList<RequestSecurityTokenType>();
        }
        return this.requestSecurityToken;
    }

}
