
package com.vidyo.portal.admin.v1_1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "EndpointBehaviorDisabledFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class EndpointBehaviorDisabledFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private EndpointBehaviorDisabledFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public EndpointBehaviorDisabledFault_Exception(String message, EndpointBehaviorDisabledFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public EndpointBehaviorDisabledFault_Exception(String message, EndpointBehaviorDisabledFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.portal.admin.v1_1.EndpointBehaviorDisabledFault
     */
    public EndpointBehaviorDisabledFault getFaultInfo() {
        return faultInfo;
    }

}
