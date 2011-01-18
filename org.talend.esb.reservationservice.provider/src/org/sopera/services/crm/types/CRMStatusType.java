
package org.sopera.services.crm.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CRMStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CRMStatusType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NONE"/>
 *     &lt;enumeration value="NORMAL"/>
 *     &lt;enumeration value="GOLD"/>
 *     &lt;enumeration value="PLATIN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CRMStatusType")
@XmlEnum
public enum CRMStatusType {

    NONE,
    NORMAL,
    GOLD,
    PLATIN;

    public String value() {
        return name();
    }

    public static CRMStatusType fromValue(String v) {
        return valueOf(v);
    }

}
