
package com.vidyo.portal.user.v1_1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "SeatLicenseExpiredFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class SeatLicenseExpiredFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private SeatLicenseExpiredFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public SeatLicenseExpiredFault_Exception(String message, SeatLicenseExpiredFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public SeatLicenseExpiredFault_Exception(String message, SeatLicenseExpiredFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.portal.user.v1_1.SeatLicenseExpiredFault
     */
    public SeatLicenseExpiredFault getFaultInfo() {
        return faultInfo;
    }

}
