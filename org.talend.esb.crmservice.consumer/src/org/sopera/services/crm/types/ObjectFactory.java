
package org.sopera.services.crm.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.sopera.services.crm.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LoginUser_QNAME = new QName("http://services.sopera.org/crm/types", "LoginUser");
    private final static QName _CustomerDetails_QNAME = new QName("http://services.sopera.org/crm/types", "CustomerDetails");
    private final static QName _RYLCStatus_QNAME = new QName("http://services.sopera.org/crm/types", "RYLCStatus");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.sopera.services.crm.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomerDetailsType }
     * 
     */
    public CustomerDetailsType createCustomerDetailsType() {
        return new CustomerDetailsType();
    }

    /**
     * Create an instance of {@link RYLCStatusType }
     * 
     */
    public RYLCStatusType createRYLCStatusType() {
        return new RYLCStatusType();
    }

    /**
     * Create an instance of {@link LoginUserType }
     * 
     */
    public LoginUserType createLoginUserType() {
        return new LoginUserType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginUserType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.sopera.org/crm/types", name = "LoginUser")
    public JAXBElement<LoginUserType> createLoginUser(LoginUserType value) {
        return new JAXBElement<LoginUserType>(_LoginUser_QNAME, LoginUserType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerDetailsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.sopera.org/crm/types", name = "CustomerDetails")
    public JAXBElement<CustomerDetailsType> createCustomerDetails(CustomerDetailsType value) {
        return new JAXBElement<CustomerDetailsType>(_CustomerDetails_QNAME, CustomerDetailsType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RYLCStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.sopera.org/crm/types", name = "RYLCStatus")
    public JAXBElement<RYLCStatusType> createRYLCStatus(RYLCStatusType value) {
        return new JAXBElement<RYLCStatusType>(_RYLCStatus_QNAME, RYLCStatusType.class, null, value);
    }

}
