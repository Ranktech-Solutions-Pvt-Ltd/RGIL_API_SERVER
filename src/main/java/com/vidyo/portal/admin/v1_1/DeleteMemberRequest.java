
package com.vidyo.portal.admin.v1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="memberID" type="{http://portal.vidyo.com/admin/v1_1}EntityID"/>
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
    "memberID"
})
@XmlRootElement(name = "DeleteMemberRequest")
public class DeleteMemberRequest {

    protected int memberID;

    /**
     * Gets the value of the memberID property.
     * 
     */
    public int getMemberID() {
        return memberID;
    }

    /**
     * Sets the value of the memberID property.
     * 
     */
    public void setMemberID(int value) {
        this.memberID = value;
    }

}
