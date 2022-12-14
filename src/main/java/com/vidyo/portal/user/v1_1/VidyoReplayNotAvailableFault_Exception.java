
package com.vidyo.portal.user.v1_1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "VidyoReplayNotAvailableFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class VidyoReplayNotAvailableFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private VidyoReplayNotAvailableFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public VidyoReplayNotAvailableFault_Exception(String message, VidyoReplayNotAvailableFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public VidyoReplayNotAvailableFault_Exception(String message, VidyoReplayNotAvailableFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.portal.user.v1_1.VidyoReplayNotAvailableFault
     */
    public VidyoReplayNotAvailableFault getFaultInfo() {
        return faultInfo;
    }

}
