
package com.vidyo.portal.user.v1_1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "PrefixNotConfiguredFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class PrefixNotConfiguredFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PrefixNotConfiguredFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PrefixNotConfiguredFault_Exception(String message, PrefixNotConfiguredFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public PrefixNotConfiguredFault_Exception(String message, PrefixNotConfiguredFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.portal.user.v1_1.PrefixNotConfiguredFault
     */
    public PrefixNotConfiguredFault getFaultInfo() {
        return faultInfo;
    }

}
