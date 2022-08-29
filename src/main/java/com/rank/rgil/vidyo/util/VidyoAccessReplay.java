package com.rank.rgil.vidyo.util;

import com.rank.rgil.util.Constants;
import com.vidyo.portal.contentmanage.DeleteRecordRequest;
import com.vidyo.portal.contentmanage.DeleteRecordResponse;
import com.vidyo.portal.contentmanage.RecordsSearchRequest;
import com.vidyo.portal.contentmanage.RecordsSearchResponse;
import com.vidyo.portal.contentmanage.SortBy;
import com.vidyo.portal.contentmanage.VidyoReplayContentManagementService;
import com.vidyo.portal.contentmanage.VidyoReplayContentManagementServicePortType;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author vivekananda
 */
public class VidyoAccessReplay implements Serializable {

    VidyoReplayContentManagementService vidyoReplayContentManagementService;
    VidyoReplayContentManagementServicePortType vidyoReplayContentManagementServicePortType;
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(VidyoAccessReplay.class);

    public VidyoAccessReplay(String portalUrl) {
        try {
            if (!portalUrl.contains("?wsdl")) {
                portalUrl = portalUrl + "?wsdl";
            }
            vidyoReplayContentManagementService = new VidyoReplayContentManagementService(new URL(portalUrl));
            vidyoReplayContentManagementServicePortType = vidyoReplayContentManagementService.getVidyoReplayContentManagementServicePort();
        } catch (MalformedURLException e) {
            logger.error("Error:" + e.getMessage());
        }
    }

    public VidyoAccessReplay() {

    }

    public List searchRecordOld(String userId, String password, String replayportalurl, String roomName) throws Exception {

        Map<String, Object> requestContext = ((BindingProvider) vidyoReplayContentManagementServicePortType).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, replayportalurl);
        Map<String, List<String>> requestHeaders = new HashMap<>();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, userId);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);

        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setRoomFilter(roomName);
        searchRequest.setUsernameFilter(userId);
        searchRequest.setSortBy(SortBy.DATE);
        searchRequest.setLimit(1);

        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);
        List searchRecord = new ArrayList();
        String playBackLink = searchResponse.getRecords().get(0).getExternalPlaybackLink();
        int recordID = searchResponse.getRecords().get(0).getId();
        Date dateCreated = searchResponse.getRecords().get(0).getDateCreated().toGregorianCalendar().getTime();

        searchRecord.add(playBackLink);
        searchRecord.add(recordID);
        searchRecord.add(dateCreated);

        return searchRecord;
    }

    public RecordsSearchResponse searchRecord(String userId, String password, String replayportalurl, String roomName) throws Exception {
        logger.info("In searchRecord");
        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }
        logger.info("New Portal Url " + portalUrl1);
        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        logger.info("After authentication in search record");
        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setRoomFilter(roomName);
        searchRequest.setUsernameFilter(userId);
        searchRequest.setSortBy(SortBy.DATE);
        searchRequest.setLimit(1);

        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);
        logger.info("After authentication in searchResponse search record" + searchResponse);
//        List searchRecord = new ArrayList();
//        
//         logger.info("After authentication in searchResponse.getRecords() search record"+searchResponse.getRecords());
//        
//        String playBackLink = searchResponse.getRecords().get(0).getExternalPlaybackLink();
//        int recordID = searchResponse.getRecords().get(0).getId();
//        Date dateCreated = searchResponse.getRecords().get(0).getDateCreated().toGregorianCalendar().getTime();
//         logger.info("After authentication search record         recordID="+recordID+" dateCreated ="+dateCreated);
//        searchRecord.add(playBackLink);
//        searchRecord.add(recordID);
//        searchRecord.add(dateCreated);
//        logger.info("After authentication search record         searchRecord"+searchRecord);
        return searchResponse;
    }

    public List searchRecordStartingOld(String userId, String password, String replayportalurl) throws Exception {

        Map<String, Object> requestContext = ((BindingProvider) vidyoReplayContentManagementServicePortType).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, replayportalurl);
        Map<String, List<String>> requestHeaders = new HashMap<>();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, userId);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);

        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setUsernameFilter(userId);
        searchRequest.setSortBy(SortBy.DATE);
        searchRequest.setLimit(1);

        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);
        List searchRecord = new ArrayList();
        String playBackLink = searchResponse.getRecords().get(0).getExternalPlaybackLink();
        int recordID = searchResponse.getRecords().get(0).getId();
        Date dateCreated = searchResponse.getRecords().get(0).getDateCreated().toGregorianCalendar().getTime();
        searchRecord.add(playBackLink);
        searchRecord.add(recordID);
        searchRecord.add(dateCreated);

        return searchRecord;
    }

    public RecordsSearchResponse searchRecordStarting(String userId, String password, String replayportalurl) throws Exception {
        logger.info(" searchRecordStarting ");
        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }
        logger.info("New Portal Url " + portalUrl1);
        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        logger.info(" After Authentication");
        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setUsernameFilter(userId);
        searchRequest.setSortBy(SortBy.DATE);
        searchRequest.setLimit(1);

        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);
        logger.info(" searchResponse " + searchResponse);
//        List searchRecord = new ArrayList();
//        logger.info(" searchResponse.getRecords() "+searchResponse.getRecords());
//        String playBackLink = searchResponse.getRecords().get(0).getExternalPlaybackLink();
//        int recordID = searchResponse.getRecords().get(0).getId();
//        Date dateCreated = searchResponse.getRecords().get(0).getDateCreated().toGregorianCalendar().getTime();
//        logger.info(" recordID "+recordID+" dateCreated "+dateCreated);
//        searchRecord.add(playBackLink);
//        searchRecord.add(recordID);
//        searchRecord.add(dateCreated);
//        
//        logger.info(" searchRecord "+searchRecord);
        return searchResponse;
    }

    public RecordsSearchResponse searchRecordScheduleOld(String userId, String password, String replayportalurl, String roomName) throws Exception {

        Map<String, Object> requestContext = ((BindingProvider) vidyoReplayContentManagementServicePortType).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, replayportalurl);
        Map<String, List<String>> requestHeaders = new HashMap<>();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, userId);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setRoomFilter(roomName);
        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);

        return searchResponse;
    }

    public RecordsSearchResponse searchRecordSchedule(String userId, String password, String replayportalurl, String roomName) throws Exception {
        logger.info("in searchRecordSchedule userId " + userId + " password " + password + " replayportalurl " + replayportalurl + " roomName " + roomName);
        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }
        logger.info("searchRecordSchedule New Portal Url " + portalUrl1);
        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
        logger.info("After authentication in searchRecordSchedule");
        RecordsSearchRequest parameter = new RecordsSearchRequest();
        parameter.setRoomFilter(roomName);

        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(parameter);
        logger.info("in searchRecordSchedule searchResponse" + searchResponse);
        return searchResponse;
    }

    public DeleteRecordResponse deleteRecordOld(String userId, String password, String replayportalurl, int Id) throws Exception {

        Map<String, Object> requestContext = ((BindingProvider) vidyoReplayContentManagementServicePortType).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, replayportalurl);
        Map<String, List<String>> requestHeaders = new HashMap<>();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, userId);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
        DeleteRecordRequest deleteRequest = new DeleteRecordRequest();
        deleteRequest.setId(Id);
        DeleteRecordResponse deleteRecordResponse = vidyoReplayContentManagementServicePortType.deleteRecord(deleteRequest);

        return deleteRecordResponse;
    }

    public DeleteRecordResponse deleteRecord(String userId, String password, String replayportalurl, int Id) throws Exception {

        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }
        logger.info("New Portal Url " + portalUrl1);
        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        DeleteRecordRequest deleteRequest = new DeleteRecordRequest();
        deleteRequest.setId(Id);
        DeleteRecordResponse deleteRecordResponse = vidyoReplayContentManagementServicePortType.deleteRecord(deleteRequest);

        return deleteRecordResponse;
    }

    public RecordsSearchResponse searchRecordScheduleByRoomOwnerOld(String userId, String password, String replayportalurl, String roomOwner) throws Exception {

        Map<String, Object> requestContext = ((BindingProvider) vidyoReplayContentManagementServicePortType).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, replayportalurl);
        Map<String, List<String>> requestHeaders = new HashMap<>();
        requestContext.put(BindingProvider.USERNAME_PROPERTY, userId);
        requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, requestHeaders);
        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setUsernameFilter(roomOwner);
        searchRequest.setLimit(1000);
        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);

        return searchResponse;
    }

    public RecordsSearchResponse searchRecordScheduleByRoomOwner(String userId, String password, String replayportalurl, String roomOwner) throws Exception {

        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }
        logger.info("New Portal Url " + portalUrl1);
        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
        searchRequest.setUsernameFilter(roomOwner);
        searchRequest.setLimit(1000);
        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);

        return searchResponse;
    }

    public RecordsSearchResponse searchRecordByRoomName(String userId, String password, String roomName) throws Exception {
        String replayportalurl = Constants.vidyoportalReplayServiceWSDL;

        URL url = new URL(replayportalurl);

        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
        Service service = Service.create(url, qname);
        VidyoReplayContentManagementServicePortType vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
        String portalUrl1 = replayportalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        RecordsSearchRequest searchRequest = new com.vidyo.portal.contentmanage.RecordsSearchRequest();
        searchRequest.setRoomFilter(roomName);
        searchRequest.setUsernameFilter(userId);
        searchRequest.setSortBy(SortBy.DATE);
        searchRequest.setLimit(1);

        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);
        logger.info("After authentication in searchResponse search record" + searchResponse.getRecords().size());
//        System.out.println("After authentication in searchResponse search record" + searchResponse.getRecords().size());

        return searchResponse;
    }
}
