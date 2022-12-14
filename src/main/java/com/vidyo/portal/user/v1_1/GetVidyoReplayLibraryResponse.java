
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
 *         &lt;element name="authToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vidyoReplayLibraryUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "authToken",
    "vidyoReplayLibraryUrl"
})
@XmlRootElement(name = "GetVidyoReplayLibraryResponse")
public class GetVidyoReplayLibraryResponse {

    @XmlElement(required = true)
    protected String authToken;
    @XmlElement(required = true)
    protected String vidyoReplayLibraryUrl;

    /**
     * Gets the value of the authToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the value of the authToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthToken(String value) {
        this.authToken = value;
    }

    /**
     * Gets the value of the vidyoReplayLibraryUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVidyoReplayLibraryUrl() {
        return vidyoReplayLibraryUrl;
    }

    /**
     * Sets the value of the vidyoReplayLibraryUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVidyoReplayLibraryUrl(String value) {
        this.vidyoReplayLibraryUrl = value;
    }

}
