package com.rank.rgil.vidyo.util;

import com.vidyo.portal.contentmanage.Record;
import com.vidyo.portal.contentmanage.RecordsSearchResponse;
import java.util.List;

public class WebServiceClient {

    public static void main(String[] args) throws Exception {

        //String userURL = "https://ranktechSolutions.vidyocloud.com/services/v1_1/VidyoPortalUserService?wsdl";
        String userId = "admin";
        String password = "#1Indusind1/";
        password = "Password@123";

        //WebServiceClient web = new WebServiceClient();
        //VidyoAccessUser vidyoAccessUser = new VidyoAccessUser(userURL);
//        String ss=vidyoAccessUser.vidyoPortalRegistration("apu1234", "apurba", "apurba.maity@gmail.com", userURL, "admin", "Password@123", 124456l);
//        System.out.println("kkkkkk "+ss);
        // vidyoAccessUser.createRoom("admin", "Password@123", userURL);"admin", "Password@123", userURL, "admin8069600"
        //vidyoAccessUser.stopRecording("admin8069600", userId, password, userURL, "XZ0m4SqY6S");
        String replay = "https://vcreplay.ranktechSolutions.com/replay/services/VidyoReplayContentManagementService?wsdl";
        VidyoAccessReplay vidyoAccessReplay = new VidyoAccessReplay();
        RecordsSearchResponse recordsSearchResponse = vidyoAccessReplay.searchRecord(userId, password, replay, "admin8611424");
        System.out.println("recordsSearchResponse:" + recordsSearchResponse.getRecords());
        List<Record> records = recordsSearchResponse.getRecords();
        for (Record record : records) {
            System.out.println("record:" + record.getExternalPlaybackLink());
        }
        
        
        System.out.println("DONE..");
    }

//    public int searchRecord(String userId, String password, String replayportalurl) throws Exception {
//        vidyoReplayContentManagementService = new VidyoReplayContentManagementService(new URL(replayportalurl));
//        vidyoReplayContentManagementServicePortType = vidyoReplayContentManagementService.getVidyoReplayContentManagementServicePort();
//
//        URL url = new URL(replayportalurl);
//
//        QName qname = new QName("http://replay.vidyo.com/apiservice", "VidyoReplayContentManagementService");
//        Service service = Service.create(url, qname);
//        vidyoReplayContentManagementServicePortType = service.getPort(VidyoReplayContentManagementServicePortType.class);
//        String portalUrl1 = replayportalurl;
//        if (portalUrl1.contains("?wsdl")) {
//            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
//        }
//
//        BindingProvider bp = (BindingProvider) vidyoReplayContentManagementServicePortType;
//        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
//        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
//        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
//
//        RecordsSearchRequest searchRequest = new RecordsSearchRequest();
////        searchRequest.setRoomFilter(roomName);
////        searchRequest.setUsernameFilter(userId);
////        searchRequest.setSortBy(SortBy.DATE);
////        searchRequest.setLimit(1);
//
//        RecordsSearchResponse searchResponse = vidyoReplayContentManagementServicePortType.recordsSearch(searchRequest);
////        System.out.println(" searchResponse.getMyVideosCount() " + searchResponse.getMyVideosCount());
////        List searchRecord = new ArrayList();
////        String playBackLink = searchResponse.getRecords().get(0).getExternalPlaybackLink();
////        int recordID = searchResponse.getRecords().get(0).getId();
////        Date dateCreated = searchResponse.getRecords().get(0).getDateCreated().toGregorianCalendar().getTime();
////
////        searchRecord.add(playBackLink);
////        searchRecord.add(recordID);
////        searchRecord.add(dateCreated);
//
//        return searchResponse.getMyVideosCount();
//    }
}
