
package org.sopera.services.reservation.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.sopera.services.reservation.types package. 
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

    private final static QName _RESProfile_QNAME = new QName("http://services.sopera.org/reservation/types", "RESProfile");
    private final static QName _RESCarList_QNAME = new QName("http://services.sopera.org/reservation/types", "RESCarList");
    private final static QName _Reservation_QNAME = new QName("http://services.sopera.org/reservation/types", "Reservation");
    private final static QName _ReservationStatus_QNAME = new QName("http://services.sopera.org/reservation/types", "ReservationStatus");
    private final static QName _ReservationToConfirm_QNAME = new QName("http://services.sopera.org/reservation/types", "ReservationToConfirm");
    private final static QName _Confirmation_QNAME = new QName("http://services.sopera.org/reservation/types", "Confirmation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.sopera.services.reservation.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RESCarListType }
     * 
     */
    public RESCarListType createRESCarListType() {
        return new RESCarListType();
    }

    /**
     * Create an instance of {@link ConfirmationType }
     * 
     */
    public ConfirmationType createConfirmationType() {
        return new ConfirmationType();
    }

    /**
     * Create an instance of {@link ReservationType }
     * 
     */
    public ReservationType createReservationType() {
        return new ReservationType();
    }

    /**
     * Create an instance of {@link RESStatusType }
     * 
     */
    public RESStatusType createRESStatusType() {
        return new RESStatusType();
    }

    /**
     * Create an instance of {@link RESProfileType }
     * 
     */
    public RESProfileType createRESProfileType() {
        return new RESProfileType();
    }

    /**
     * Create an instance of {@link RESCarType }
     * 
     */
    public RESCarType createRESCarType() {
        return new RESCarType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RESProfileType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.sopera.org/reservation/types", name = "RESProfile")
    public JAXBElement<RESProfileType> createRESProfile(RESProfileType value) {
        return new JAXBElement<RESProfileType>(_RESProfile_QNAME, RESProfileType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RESCarListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.sopera.org/reservation/types", name = "RESCarList")
    public JAXBElement<RESCarListType> createRESCarList(RESCarListType value) {
        return new JAXBElement<RESCarListType>(_RESCarList_QNAME, RESCarListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.sopera.org/reservation/types", name = "Reservation")
    public JAXBElement<ReservationType> createReservation(ReservationType value) {
        return new JAXBElement<ReservationType>(_Reservation_QNAME, ReservationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RESStatusType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.sopera.org/reservation/types", name = "ReservationStatus")
    public JAXBElement<RESStatusType> createReservationStatus(RESStatusType value) {
        return new JAXBElement<RESStatusType>(_ReservationStatus_QNAME, RESStatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReservationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.sopera.org/reservation/types", name = "ReservationToConfirm")
    public JAXBElement<ReservationType> createReservationToConfirm(ReservationType value) {
        return new JAXBElement<ReservationType>(_ReservationToConfirm_QNAME, ReservationType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConfirmationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.sopera.org/reservation/types", name = "Confirmation")
    public JAXBElement<ConfirmationType> createConfirmation(ConfirmationType value) {
        return new JAXBElement<ConfirmationType>(_Confirmation_QNAME, ConfirmationType.class, null, value);
    }

}
