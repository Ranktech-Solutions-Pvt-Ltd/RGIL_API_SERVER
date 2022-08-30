
package com.vidyo.portal.admin.v1_1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InvalidArgumentFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class InvalidArgumentFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InvalidArgumentFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public InvalidArgumentFault_Exception(String message, InvalidArgumentFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public InvalidArgumentFault_Exception(String message, InvalidArgumentFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.portal.admin.v1_1.InvalidArgumentFault
     */
    public InvalidArgumentFault getFaultInfo() {
        return faultInfo;
    }

}