
package com.vidyo.portal.user.v1_1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "VidyoPortalUserService", targetNamespace = "http://portal.vidyo.com/user/v1_1", wsdlLocation = "file:/D:/VidyoPortalUserService.wsdl")
public class VidyoPortalUserService
    extends Service
{

    private final static URL VIDYOPORTALUSERSERVICE_WSDL_LOCATION;
    private final static WebServiceException VIDYOPORTALUSERSERVICE_EXCEPTION;
    private final static QName VIDYOPORTALUSERSERVICE_QNAME = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/D:/VidyoPortalUserService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        VIDYOPORTALUSERSERVICE_WSDL_LOCATION = url;
        VIDYOPORTALUSERSERVICE_EXCEPTION = e;
    }

    public VidyoPortalUserService() {
        super(__getWsdlLocation(), VIDYOPORTALUSERSERVICE_QNAME);
    }

    public VidyoPortalUserService(WebServiceFeature... features) {
        super(__getWsdlLocation(), VIDYOPORTALUSERSERVICE_QNAME, features);
    }

    public VidyoPortalUserService(URL wsdlLocation) {
        super(wsdlLocation, VIDYOPORTALUSERSERVICE_QNAME);
    }

    public VidyoPortalUserService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, VIDYOPORTALUSERSERVICE_QNAME, features);
    }

    public VidyoPortalUserService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public VidyoPortalUserService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns VidyoPortalUserServicePortType
     */
    @WebEndpoint(name = "VidyoPortalUserServicePort")
    public VidyoPortalUserServicePortType getVidyoPortalUserServicePort() {
        return super.getPort(new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserServicePort"), VidyoPortalUserServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns VidyoPortalUserServicePortType
     */
    @WebEndpoint(name = "VidyoPortalUserServicePort")
    public VidyoPortalUserServicePortType getVidyoPortalUserServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserServicePort"), VidyoPortalUserServicePortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (VIDYOPORTALUSERSERVICE_EXCEPTION!= null) {
            throw VIDYOPORTALUSERSERVICE_EXCEPTION;
        }
        return VIDYOPORTALUSERSERVICE_WSDL_LOCATION;
    }

}
