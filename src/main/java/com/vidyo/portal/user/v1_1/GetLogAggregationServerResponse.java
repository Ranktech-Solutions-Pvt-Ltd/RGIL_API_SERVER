
package com.vidyo.portal.user.v1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="logServer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "logServer"
})
@XmlRootElement(name = "getLogAggregationServerResponse")
public class GetLogAggregationServerResponse {

    @XmlElement(required = true)
    protected String logServer;

    /**
     * Gets the value of the logServer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLogServer() {
        return logServer;
    }

    /**
     * Sets the value of the logServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLogServer(String value) {
        this.logServer = value;
    }

}