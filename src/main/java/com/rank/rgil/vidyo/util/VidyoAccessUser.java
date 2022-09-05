package com.rank.rgil.vidyo.util;

import com.vidyo.portal.user.v1_1.ControlMeetingFault_Exception;
import com.vidyo.portal.user.v1_1.CreateRoomRequest;
import com.vidyo.portal.user.v1_1.CreateRoomResponse;
import com.vidyo.portal.user.v1_1.CreateRoomURLRequest;
import com.vidyo.portal.user.v1_1.DeleteRoomRequest;
import com.vidyo.portal.user.v1_1.Entity;
import com.vidyo.portal.user.v1_1.Filter;
import com.vidyo.portal.user.v1_1.GeneralFault_Exception;
import com.vidyo.portal.user.v1_1.GetConferenceIDRequest;
import com.vidyo.portal.user.v1_1.GetConferenceIDResponse;
import com.vidyo.portal.user.v1_1.GetEntityByRoomKeyRequest;
import com.vidyo.portal.user.v1_1.GetEntityByRoomKeyResponse;
import com.vidyo.portal.user.v1_1.GetParticipantsRequest;
import com.vidyo.portal.user.v1_1.GetParticipantsResponse;
import com.vidyo.portal.user.v1_1.GetRecordingProfilesRequest;
import com.vidyo.portal.user.v1_1.InPointToPointCallFault_Exception;
import com.vidyo.portal.user.v1_1.InvalidArgumentFault_Exception;
import com.vidyo.portal.user.v1_1.JoinConferenceRequest;
import com.vidyo.portal.user.v1_1.JoinConferenceResponse;
import com.vidyo.portal.user.v1_1.LeaveConferenceRequest;
import com.vidyo.portal.user.v1_1.LockRoomRequest;
import com.vidyo.portal.user.v1_1.LogInRequest;
import com.vidyo.portal.user.v1_1.LogInResponse;
import com.vidyo.portal.user.v1_1.MyAccountRequest;
import com.vidyo.portal.user.v1_1.MyAccountResponse;
import com.vidyo.portal.user.v1_1.NotLicensedFault_Exception;
import com.vidyo.portal.user.v1_1.PauseRecordingRequest;
import com.vidyo.portal.user.v1_1.PauseRecordingResponse;
import com.vidyo.portal.user.v1_1.ResourceNotAvailableFault_Exception;
import com.vidyo.portal.user.v1_1.ResumeRecordingRequest;
import com.vidyo.portal.user.v1_1.ResumeRecordingResponse;
import com.vidyo.portal.user.v1_1.SearchByEntityIDRequest;
import com.vidyo.portal.user.v1_1.SearchByEntityIDResponse;
import com.vidyo.portal.user.v1_1.SearchMyContactsRequest;
import com.vidyo.portal.user.v1_1.SearchRequest;
import com.vidyo.portal.user.v1_1.SearchResponse;
import com.vidyo.portal.user.v1_1.SeatLicenseExpiredFault_Exception;
import com.vidyo.portal.user.v1_1.StartRecordingRequest;
import com.vidyo.portal.user.v1_1.StartRecordingResponse;
import com.vidyo.portal.user.v1_1.StopRecordingRequest;
import com.vidyo.portal.user.v1_1.StopRecordingResponse;
import com.vidyo.portal.user.v1_1.UnlockRoomRequest;
import com.vidyo.portal.user.v1_1.VidyoPortalUserService;
import com.vidyo.portal.user.v1_1.VidyoPortalUserServicePortType;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Value;

public class VidyoAccessUser implements Serializable {

	@Value("${portalExtention}")
    public String portalExtention;
	@Value("${vidyoportalUserServiceWSDL}")
    public String vidyoportalUserServiceWSDL;
	@Value("${recorderPrefix}")
    public String recorderPrefix;

    VidyoPortalUserService vidyoUserService;
    VidyoPortalUserServicePortType vidyoPortalUserServicePort;
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(VidyoAccessUser.class);

    public VidyoAccessUser(String portalUrl) {
        try {
            if (!portalUrl.contains("?wsdl")) {
                portalUrl = portalUrl + "?wsdl";
            }

            vidyoUserService = new VidyoPortalUserService(new URL(portalUrl));
            vidyoPortalUserServicePort = vidyoUserService.getVidyoPortalUserServicePort();
        } catch (MalformedURLException e) {
            logger.error("Error VidyoAccessUser:" + e.getMessage());
        }
    }

    public VidyoAccessUser() {

    }

    public String getRoomLinkUrl(String userId, String password, String portalUrl) throws Exception {

        String roomURL;

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        if (portalUrl.contains("?wsdl")) {
            portalUrl = portalUrl.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        MyAccountRequest myAccountRequest = new MyAccountRequest();
        MyAccountResponse response = vidyoPortalUserServicePort.myAccount(myAccountRequest);
        roomURL = response.getEntity().getRoomMode().getRoomURL().getValue();

        return roomURL;

    }

    public MyAccountResponse getMyaccount(String userId, String password, String portalUrl) throws Exception {

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        if (portalUrl.contains("?wsdl")) {
            portalUrl = portalUrl.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        MyAccountRequest myAccountRequest = new MyAccountRequest();
        MyAccountResponse response = vidyoPortalUserServicePort.myAccount(myAccountRequest);

        return response;

    }

    public boolean vidyoPortalLogin(String userId, String password, String portalurl) {
        boolean logged = false;
        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            LogInRequest logInRequest = new LogInRequest();
            LogInResponse response = vidyoPortalUserServicePort.logIn(logInRequest);

            if (response != null) {
                logged = true;
            }

        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception | WebServiceException e) {
            logger.error("Error vidyoPortalLogin:" + e.getMessage());
            logged = false;
        }
        return logged;
    }

    public String getEntityID(String roomURL, String userId, String password, String portalurl) throws Exception {
        String strEntityID;

        String[] tempArr = roomURL.split("=");
        String roomKey;
        roomKey = tempArr[1];

        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        GetEntityByRoomKeyRequest getEntityByRoomKeyRequest = new GetEntityByRoomKeyRequest();
        getEntityByRoomKeyRequest.setRoomKey(roomKey);
        GetEntityByRoomKeyResponse roomKeyResponse = vidyoPortalUserServicePort.getEntityByRoomKey(getEntityByRoomKeyRequest);

        strEntityID = roomKeyResponse.getEntity().getEntityID();

        return strEntityID;

    }

    public int startRecording(String entityID, String userId, String password, String portalurl) throws ControlMeetingFault_Exception, GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, ResourceNotAvailableFault_Exception, SeatLicenseExpiredFault_Exception, MalformedURLException {
        int recorder = 0;
        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        StartRecordingRequest startRecordingRequest = new StartRecordingRequest();
        startRecordingRequest.setConferenceID(entityID);
        startRecordingRequest.setRecorderPrefix(recorderPrefix);
        startRecordingRequest.setWebcast(true);

        StartRecordingResponse recordingResponse = vidyoPortalUserServicePort.startRecording(startRecordingRequest);
        recordingResponse.getOK();
        recorder = 1;

        return recorder;
    }

    public int pauseRecording(String entityID, int recorderId, String userId, String password, String portalurl) throws ControlMeetingFault_Exception, GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, ResourceNotAvailableFault_Exception, SeatLicenseExpiredFault_Exception, MalformedURLException {
        int recorder = 0;
        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        PauseRecordingRequest pauseRecordingRequest = new PauseRecordingRequest();
        pauseRecordingRequest.setConferenceID(entityID);
        pauseRecordingRequest.setRecorderID(recorderId);

        PauseRecordingResponse recordingResponse = vidyoPortalUserServicePort.pauseRecording(pauseRecordingRequest);
        if (recordingResponse.getOK().equals("OK")) {
            recorder = 1;
        }

        return recorder;
    }
    
    public int resumeRecording(String entityID, int recorderId, String userId, String password, String portalurl) throws ControlMeetingFault_Exception, GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, ResourceNotAvailableFault_Exception, SeatLicenseExpiredFault_Exception, MalformedURLException {
        int recorder = 0;
        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        ResumeRecordingRequest resumeRecordingRequest = new ResumeRecordingRequest();
        resumeRecordingRequest.setConferenceID(entityID);
        resumeRecordingRequest.setRecorderID(recorderId);

        ResumeRecordingResponse recordingResponse = vidyoPortalUserServicePort.resumeRecording(resumeRecordingRequest);
        if (recordingResponse.getOK().equals("OK")) {
            recorder = 1;
        }

        return recorder;
    }

    public String stopRecording(String roomURL, String userId, String password, String portalurl, String entityID) throws MalformedURLException, ControlMeetingFault_Exception, GeneralFault_Exception, InvalidArgumentFault_Exception, NotLicensedFault_Exception, SeatLicenseExpiredFault_Exception {
        String strOK = "";

        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
        getParticipantsRequest.setConferenceID(entityID);

        GetParticipantsResponse participantResponse = vidyoPortalUserServicePort.getParticipants(getParticipantsRequest);

        Integer recorderID = null;
        if (participantResponse.getRecorderID() != null) {
            recorderID = participantResponse.getRecorderID().getValue();
        }
        if (recorderID != null) {
            if (recorderID != 0) {
                stop(userId, password, portalurl, entityID, recorderID);
            }
        }

        return strOK;

    }

    public void stop(String userId, String password, String portalurl, String entityID, int recorderID) {
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            StopRecordingRequest stopRecordingRequest = new StopRecordingRequest();
            stopRecordingRequest.setConferenceID(entityID);
            stopRecordingRequest.setRecorderID(recorderID);

            StopRecordingResponse response = vidyoPortalUserServicePort.stopRecording(stopRecordingRequest);
            String strOK = response.getOK();
        } catch (ControlMeetingFault_Exception | MalformedURLException | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int vidyoPortalOnlineUserCount(String portalUrl, String adminid, String adminpassword) {
        int count = 0;
        try {

            URL url = new URL(portalUrl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, adminid);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, adminpassword);

            SearchRequest searchRequest = new SearchRequest();
            Filter filter = new Filter();

            searchRequest.setFilter(filter);
            SearchResponse response = vidyoPortalUserServicePort.search(searchRequest);
            List<Entity> entityList = response.getEntity();
            for (Entity entityList1 : entityList) {
                if (entityList1.getMemberStatus().equalsIgnoreCase("Online")) {
                    count++;
                }
            }

        } catch (MalformedURLException | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception e) {
            logger.error("vidyoPortalOnlineUserCount " + e.getMessage());
        }
        return count;
    }

    public String join(String userId, String password, String conferenceid, String portalurl) throws Exception {
        String returnStatus;

        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        JoinConferenceRequest joinConferenceRequest = new JoinConferenceRequest();
        joinConferenceRequest.setConferenceID(conferenceid);

        JoinConferenceResponse joinConferenceResponse = vidyoPortalUserServicePort.joinConference(joinConferenceRequest);
        returnStatus = joinConferenceResponse.getOK();
        logger.info("Join conference successfully==========>" + conferenceid);
        return returnStatus;
    }

    public boolean leaveConference(String userId, String password, String portalurl, String conferenceid) throws Exception {
        boolean logged = false;
        try {

            String participantid = getParticipantid(userId, password, portalurl, conferenceid);

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            LeaveConferenceRequest leaveConferenceRequest = new LeaveConferenceRequest();
            leaveConferenceRequest.setConferenceID(conferenceid);
            leaveConferenceRequest.setParticipantID(participantid);
            vidyoPortalUserServicePort.leaveConference(leaveConferenceRequest);
        } catch (WebServiceException e) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, e);
            logged = false;
        } catch (ControlMeetingFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logged;
    }

    public String getParticipantid(String userId, String password, String portalurl, String conferenceid) throws Exception {
        String participantid = "";
        String extension = this.getMyaccount(userId, password, portalurl).getEntity().getExtension();
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
            getParticipantsRequest.setConferenceID(conferenceid);
            GetParticipantsResponse participantResponse = vidyoPortalUserServicePort.getParticipants(getParticipantsRequest);
            List<Entity> participantlist = participantResponse.getEntity();

            for (Entity participantlist1 : participantlist) {
                if (participantlist1.getExtension().equalsIgnoreCase(extension)) {
                    participantid = (String) participantlist1.getParticipantID().getValue();
                }
            }

        } catch (ControlMeetingFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participantid;
    }

    public void createRoomURL(String userId, String password, String roomId, String portalurl) {
        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            CreateRoomURLRequest createRoomURLRequest = new CreateRoomURLRequest();
            createRoomURLRequest.setRoomID(roomId);
            vidyoPortalUserServicePort.createRoomURL(createRoomURLRequest);

        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String createRoom(String userId, String password, String portalUrl, String agentName) throws Exception {
        String roomlink;
        String entityid;
        String ret;

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalUrl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        CreateRoomRequest createRoomRequest = new CreateRoomRequest();
        Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
        createRoomRequest.setName(agentName.concat(number.toString()));
        String extension = "";
        if (portalUrl.trim().equalsIgnoreCase(vidyoportalUserServiceWSDL.trim())) {
            extension = portalExtention.concat(number.toString());
        }

        createRoomRequest.setExtension(extension);

        CreateRoomResponse createRoomResponse = vidyoPortalUserServicePort.createRoom(createRoomRequest);
        roomlink = (String) createRoomResponse.getEntity().getRoomMode().getRoomURL().getValue();
        entityid = createRoomResponse.getEntity().getEntityID();

        ret = roomlink + "," + entityid + "," + agentName + "" + number;

        return ret;
    }

    public void deleteRoom(String userId, String password, String portalUrl, String roomId) {

        try {
            URL url = new URL(portalUrl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            DeleteRoomRequest deleteRoomRequest = new DeleteRoomRequest();
            deleteRoomRequest.setRoomID(roomId);

            vidyoPortalUserServicePort.deleteRoom(deleteRoomRequest);

        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Entity> getAllRoomsByOwnerId(String userId, String password, String portalUrl, String ownerid) throws Exception {

        List<Entity> entityList;

        URL url = new URL(portalUrl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalUrl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        SearchByEntityIDRequest searchByEntityIDRequest = new SearchByEntityIDRequest();
        searchByEntityIDRequest.setEntityID(ownerid);

        SearchByEntityIDResponse searchByEntityIDResponse = vidyoPortalUserServicePort.searchByEntityID(searchByEntityIDRequest);
        entityList = searchByEntityIDResponse.getEntity();

        return entityList;

    }

    public String getConferenceId(String userId, String password, String portalUrl) {

        String conferenceId = "";
        try {
            URL url = new URL(portalUrl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetConferenceIDRequest getConferenceIDRequest = new GetConferenceIDRequest();

            GetConferenceIDResponse getConferenceIDResponse;
            try {
                getConferenceIDResponse = vidyoPortalUserServicePort.getConferenceID(getConferenceIDRequest);
                conferenceId = (String) getConferenceIDResponse.getConferenceID().getValue();
            } catch (InPointToPointCallFault_Exception ex) {
                Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (GeneralFault_Exception | MalformedURLException | NotLicensedFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conferenceId;

    }

    public void getRecordingProfiles(String userId, String password, String portalUrl) {

        try {
            URL url = new URL(portalUrl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetRecordingProfilesRequest getRecordingProfilesRequest = new GetRecordingProfilesRequest();

            vidyoPortalUserServicePort.getRecordingProfiles(getRecordingProfilesRequest);

        } catch (GeneralFault_Exception | MalformedURLException | NotLicensedFault_Exception | InvalidArgumentFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public GetParticipantsResponse getParticipants(String entityID, String userId, String password, String portalurl) {

        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
            getParticipantsRequest.setConferenceID(entityID);
            GetParticipantsResponse participantResponse = vidyoPortalUserServicePort.getParticipants(getParticipantsRequest);

            return participantResponse;
        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception | ControlMeetingFault_Exception e) {
            logger.error("Error:getParticipants==============>" + e.getMessage());
            return null;
        }

    }

    public void seachMyContacts(String userId, String password, String portalurl) {
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            SearchMyContactsRequest searchMyContactsRequest = new SearchMyContactsRequest();

            vidyoPortalUserServicePort.searchMyContacts(searchMyContactsRequest);
        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void lockRoom(String userId, String password, String portalurl, String roomId) throws Exception {
        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        LockRoomRequest lockRoomRequest = new LockRoomRequest();
        lockRoomRequest.setRoomID(roomId);
        vidyoPortalUserServicePort.lockRoom(lockRoomRequest);

    }

    public void unLockRoom(String userId, String password, String portalurl, String roomId) throws Exception {
        URL url = new URL(portalurl);

        QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
        Service service = Service.create(url, qname);
        vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
        String portalUrl1 = portalurl;
        if (portalUrl1.contains("?wsdl")) {
            portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
        }

        BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

        UnlockRoomRequest unlockRoomRequest = new UnlockRoomRequest();
        unlockRoomRequest.setRoomID(roomId);

        vidyoPortalUserServicePort.unlockRoom(unlockRoomRequest);

    }

    public List<Entity> getParticipantidList(String userId, String password, String portalurl, String conferenceid) throws Exception {
        List<Entity> participantlist = new ArrayList<>();
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
            getParticipantsRequest.setConferenceID(conferenceid);
            GetParticipantsResponse participantResponse = vidyoPortalUserServicePort.getParticipants(getParticipantsRequest);
            participantlist = participantResponse.getEntity();

        } catch (ControlMeetingFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participantlist;
    }

    public boolean forceLeaveConference(String userId, String password, String portalurl, String conferenceid, String participantid) throws Exception {
        boolean logged = false;
        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/user/v1_1", "VidyoPortalUserService");
            Service service = Service.create(url, qname);
            vidyoPortalUserServicePort = service.getPort(VidyoPortalUserServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalUserServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            LeaveConferenceRequest leaveConferenceRequest = new LeaveConferenceRequest();
            leaveConferenceRequest.setConferenceID(conferenceid);
            leaveConferenceRequest.setParticipantID(participantid);
            vidyoPortalUserServicePort.leaveConference(leaveConferenceRequest);
        } catch (WebServiceException e) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, e);
            logged = false;
        } catch (ControlMeetingFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            Logger.getLogger(VidyoAccessUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logged;
    }
}
