
package com.vidyo.portal.user.v1_1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InPointToPointCallFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class InPointToPointCallFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InPointToPointCallFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InPointToPointCallFault_Exception(String message, InPointToPointCallFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public InPointToPointCallFault_Exception(String message, InPointToPointCallFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.portal.user.v1_1.InPointToPointCallFault
     */
    public InPointToPointCallFault getFaultInfo() {
        return faultInfo;
    }

}
