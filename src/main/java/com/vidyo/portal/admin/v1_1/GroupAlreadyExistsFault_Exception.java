
package com.vidyo.portal.admin.v1_1;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "GroupAlreadyExistsFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class GroupAlreadyExistsFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private GroupAlreadyExistsFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public GroupAlreadyExistsFault_Exception(String message, GroupAlreadyExistsFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public GroupAlreadyExistsFault_Exception(String message, GroupAlreadyExistsFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.vidyo.portal.admin.v1_1.GroupAlreadyExistsFault
     */
    public GroupAlreadyExistsFault getFaultInfo() {
        return faultInfo;
    }

}
