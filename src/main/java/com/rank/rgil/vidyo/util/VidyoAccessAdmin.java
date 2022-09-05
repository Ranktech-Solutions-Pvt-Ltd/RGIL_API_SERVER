package com.rank.rgil.vidyo.util;

import com.vidyo.portal.admin.v1_1.VidyoPortalAdminService;
import com.vidyo.portal.admin.v1_1.VidyoPortalAdminServicePortType;
import com.vidyo.portal.admin.v1_1.AddMemberRequest;
import com.vidyo.portal.admin.v1_1.AddMemberResponse;
import com.vidyo.portal.admin.v1_1.DeleteMemberRequest;
import com.vidyo.portal.admin.v1_1.DeleteMemberResponse;
import com.vidyo.portal.admin.v1_1.Entity;
import com.vidyo.portal.admin.v1_1.Filter;
import com.vidyo.portal.admin.v1_1.GetParticipantsRequest;
import com.vidyo.portal.admin.v1_1.GeneralFault_Exception;
import com.vidyo.portal.admin.v1_1.GetMembersRequest;
import com.vidyo.portal.admin.v1_1.GetMembersResponse;
import com.vidyo.portal.admin.v1_1.GetParticipantsResponse;
import com.vidyo.portal.admin.v1_1.GetRoomRequest;
import com.vidyo.portal.admin.v1_1.GetRoomResponse;
import com.vidyo.portal.admin.v1_1.InvalidArgumentFault_Exception;
import com.vidyo.portal.admin.v1_1.LeaveConferenceRequest;
import com.vidyo.portal.admin.v1_1.Member;
import com.vidyo.portal.admin.v1_1.MemberAlreadyExistsFault_Exception;
import com.vidyo.portal.admin.v1_1.MemberNotFoundFault_Exception;
import com.vidyo.portal.admin.v1_1.NotLicensedFault_Exception;
import com.vidyo.portal.admin.v1_1.RoomNotFoundFault_Exception;

import java.io.Serializable;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import javax.xml.namespace.QName;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class VidyoAccessAdmin implements Serializable {

    private static final Logger logger = LogManager.getLogger(VidyoAccessAdmin.class);
    

    VidyoPortalAdminService vidyoPortalAdminService;
    VidyoPortalAdminServicePortType vidyoPortalAdminServicePort;

    public VidyoAccessAdmin(String portalUrl) {
        try {
            if (!portalUrl.contains("?wsdl")) {
                portalUrl = portalUrl + "?wsdl";
            }
            vidyoPortalAdminService = new VidyoPortalAdminService(new URL(portalUrl));
            vidyoPortalAdminServicePort = vidyoPortalAdminService.getVidyoPortalAdminServicePort();
        } catch (MalformedURLException e) {
            logger.error("Error:" + e.getMessage());

        }
    }

    public VidyoAccessAdmin() {

    }

    public String vidyoPortalRegistration(String name, String displayName, String email, String portalUrl, String adminid, String adminpassword, Long numberPassword) {
        String password = "";
        String extension = "78664";
        try {
            URL url = new URL(portalUrl);

            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalUrl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, adminid);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, adminpassword);
            AddMemberRequest addMemberRequest = new AddMemberRequest();
            Member member = new Member();
            member.setName(name);
            com.vidyo.portal.admin.v1_1.ObjectFactory objectFactory = new com.vidyo.portal.admin.v1_1.ObjectFactory();

            javax.xml.bind.JAXBElement pass = objectFactory.createMemberPassword(numberPassword + "");
            member.setPassword(pass);
            member.setDisplayName(displayName);
            Random randomGenerator = new Random();
//            if (portalUrl.equalsIgnoreCase(Constants.vidyoportalAdminServiceWSDL)) {
//                extension = Constants.portalExtention;
//            }

            Integer randomPin = randomGenerator.nextInt(89999999) + 10000000;
            logger.info("In registration vidyo-------------" + "78664" + randomPin.toString());
            member.setExtension(extension + randomPin.toString());
            member.setLanguage("en");
            member.setRoleName("Normal");
            member.setGroupName("Default");
            member.setProxyName("No Proxy");
            member.setEmailAddress(email);
            member.setDescription("true");
            member.setAllowCallDirect(true);
            member.setAllowPersonalMeeting(true);
            member.setLocationTag("Default");

            addMemberRequest.setMember(member);

            try {

                AddMemberResponse response = vidyoPortalAdminServicePort.addMember(addMemberRequest);
                if (response != null) {
                    password = numberPassword + "";
                } else {
                    password = "";
                }
            } catch (com.vidyo.portal.admin.v1_1.GeneralFault_Exception | com.vidyo.portal.admin.v1_1.InvalidArgumentFault_Exception | MemberAlreadyExistsFault_Exception | com.vidyo.portal.admin.v1_1.NotLicensedFault_Exception e) {
                logger.error("Error vidyoPortalRegistration:" + e.getMessage());

                return "";
            }
        } catch (Exception e) {
            logger.info("Exception " + e);
        }
        return password;
    }

    public boolean leaveConference(String userId, String password, String portalurl, String conferenceid, String participantid) {
        boolean logged = false;
        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            LeaveConferenceRequest leaveConferenceRequest = new LeaveConferenceRequest();
            leaveConferenceRequest.setConferenceID(Integer.parseInt(conferenceid));
            leaveConferenceRequest.setParticipantID(Integer.parseInt(participantid));
            vidyoPortalAdminServicePort.leaveConference(leaveConferenceRequest);
        } catch (WebServiceException | MalformedURLException e) {
            logger.error("Error:" + e.getMessage());

            logged = false;
        } catch (GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception ex) {
            logger.error("Error:" + ex.getMessage());
        }
        return logged;
    }

    public boolean getroom(String userId, String password, String portalurl, String conferenceid, String loginId) {
        boolean logged = false;
        try {

            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetRoomRequest getRoomRequest=new GetRoomRequest();
            getRoomRequest.setRoomID(Integer.parseInt(conferenceid));

            GetRoomResponse response= vidyoPortalAdminServicePort.getRoom(getRoomRequest);
             //roomname=null;
            if (response != null) {
                String roomname=response.getRoom().getName();
                if(roomname.contains(loginId)){
                    logged=true;
                }else{
                    logged=false;
                }
            } else {
                logged = false;
            }
            
        } catch (WebServiceException | MalformedURLException e) {
            logger.error("Error:" + e.getMessage());

            logged = false;
        } catch (GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception ex) {
            logger.error("Error:" + ex.getMessage());
        } catch (RoomNotFoundFault_Exception e) {
            logger.error("Error:" + e.getMessage());
            logged = false;
        }
        return logged;
    }

    public Integer getParticipantid(String userId, String password, String portalurl, int entityId) {
        Integer participantid = null;

        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetParticipantsRequest getParticipantsRequest = new GetParticipantsRequest();
            getParticipantsRequest.setConferenceID(entityId);
            GetParticipantsResponse participantResponse = vidyoPortalAdminServicePort.getParticipants(getParticipantsRequest);
            List<Entity> participantlist = participantResponse.getEntity();

            for (Entity participantlist1 : participantlist) {
                if (participantlist1.getEntityID() == entityId) {
                    participantid = participantlist1.getParticipantID().getValue();
                }
            }

        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception ex) {
            logger.error("Error getParticipantid:" + ex.getMessage());
        }
        return participantid;
    }

    public int getMemberID(String userId, String password, String portalurl, String loginId) {

        int memberid = 0;
        try {
            URL url = new URL(portalurl);

            QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
            Service service = Service.create(url, qname);
            vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
            String portalUrl1 = portalurl;
            if (portalUrl1.contains("?wsdl")) {
                portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
            }

            BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
            bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
            bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

            GetMembersRequest getMembersRequest = new GetMembersRequest();
            Filter filter = new Filter();
            com.vidyo.portal.admin.v1_1.ObjectFactory objectFactory = new com.vidyo.portal.admin.v1_1.ObjectFactory();
            javax.xml.bind.JAXBElement logid = objectFactory.createFilterQuery(loginId);
            filter.setQuery(logid);
            getMembersRequest.setFilter(filter);

            GetMembersResponse getMembersResponse = vidyoPortalAdminServicePort.getMembers(getMembersRequest);

            if (getMembersResponse.getMember().size() > 0) {
                for (int i = 0; i < getMembersResponse.getMember().size(); i++) {
                    if (getMembersResponse.getMember().get(i).getName().equals(loginId)) {
                        memberid = getMembersResponse.getMember().get(i).getMemberID();
                        break;
                    }
                }

            }
        } catch (GeneralFault_Exception | MalformedURLException | InvalidArgumentFault_Exception | NotLicensedFault_Exception ex) {
            logger.error("Error getMemberID:" + ex.getMessage());
        }
        return memberid;
    }

    public void deleteMember(String userId, String password, String portalurl, String loginId) {

        int memberid = getMemberID(userId, password, portalurl, loginId);

        if (memberid != 0) {
            try {
                URL url = new URL(portalurl);

                QName qname = new QName("http://portal.vidyo.com/admin/v1_1", "VidyoPortalAdminService");
                Service service = Service.create(url, qname);
                vidyoPortalAdminServicePort = service.getPort(VidyoPortalAdminServicePortType.class);
                String portalUrl1 = portalurl;
                if (portalUrl1.contains("?wsdl")) {
                    portalUrl1 = portalUrl1.replaceAll("\\?wsdl", "");
                }

                BindingProvider bp = (BindingProvider) vidyoPortalAdminServicePort;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, portalUrl1);
                bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, userId);
                bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);

//            try {
                DeleteMemberRequest deleteMemberRequest = new DeleteMemberRequest();
                deleteMemberRequest.setMemberID(memberid);
                try {
                    DeleteMemberResponse deleteMemberResponse = vidyoPortalAdminServicePort.deleteMember(deleteMemberRequest);
                    logger.info("Delete Member==============>" + memberid + " " + deleteMemberResponse.getOK());

                } catch (GeneralFault_Exception | InvalidArgumentFault_Exception | MemberNotFoundFault_Exception | NotLicensedFault_Exception e) {
                }
            } catch (Exception ex) {
                logger.error("Error getMemberID:" + ex.getMessage());
            }
        } else {
            logger.info("Unable to delete employee....");
        }

    }

}
