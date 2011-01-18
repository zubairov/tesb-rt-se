
package org.sopera.services.reservation.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RESStatucCodeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RESStatucCodeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="FAILED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RESStatucCodeType")
@XmlEnum
public enum RESStatucCodeType {

    OK,
    FAILED;

    public String value() {
        return name();
    }

    public static RESStatucCodeType fromValue(String v) {
        return valueOf(v);
    }

}
