
package com.vidyo.portal.user.v1_1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "AccessRestrictedFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class AccessRestrictedFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private AccessRestrictedFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public AccessRestrictedFault_Exception(String message, AccessRestrictedFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public AccessRestrictedFault_Exception(String message, AccessRestrictedFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.portal.user.v1_1.AccessRestrictedFault
     */
    public AccessRestrictedFault getFaultInfo() {
        return faultInfo;
    }

}
