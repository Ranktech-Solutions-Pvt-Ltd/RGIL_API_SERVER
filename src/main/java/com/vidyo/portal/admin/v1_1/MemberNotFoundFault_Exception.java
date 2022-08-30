
package com.vidyo.portal.admin.v1_1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "MemberNotFoundFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class MemberNotFoundFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private MemberNotFoundFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public MemberNotFoundFault_Exception(String message, MemberNotFoundFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public MemberNotFoundFault_Exception(String message, MemberNotFoundFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.portal.admin.v1_1.MemberNotFoundFault
     */
    public MemberNotFoundFault getFaultInfo() {
        return faultInfo;
    }

}