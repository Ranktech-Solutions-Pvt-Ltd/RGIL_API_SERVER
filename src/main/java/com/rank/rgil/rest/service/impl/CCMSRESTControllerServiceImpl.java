/**
 *
 */
package com.rank.rgil.rest.service.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rank.rgil.entities.CallDtl;
import com.rank.rgil.entities.CallMst;
import com.rank.rgil.entities.CallRecords;
import com.rank.rgil.entities.CustomerMst;
import com.rank.rgil.entities.EmployeeActivityDtl;
import com.rank.rgil.entities.EmployeeCallStatus;
import com.rank.rgil.entities.EmployeeMst;
import com.rank.rgil.entities.EmployeeTypeMst;
import com.rank.rgil.entities.ModuleMst;
import com.rank.rgil.entities.ReasonMst;
import com.rank.rgil.entities.TenancyEmployeeMap;
import com.rank.rgil.exception.CCMSRestException;
import com.rank.rgil.response.AgentLoginResponse;
import com.rank.rgil.response.CustomerDto;
import com.rank.rgil.rest.service.CCMSRESTControllerService;
import com.rank.rgil.service.CallDtlService;
import com.rank.rgil.service.CallMstService;
import com.rank.rgil.service.CallRecordsService;
import com.rank.rgil.service.CustomerMstService;
import com.rank.rgil.service.EmployeeActivityDtlService;
import com.rank.rgil.service.EmployeeCallStatusService;
import com.rank.rgil.service.EmployeeLoginService;
import com.rank.rgil.service.EmployeeMstService;
import com.rank.rgil.service.EmployeeTypeMstService;
import com.rank.rgil.service.ModuleMstService;
import com.rank.rgil.service.ReasonMstService;
import com.rank.rgil.service.TenancyEmployeeMapService;
import com.rank.rgil.util.Constants;
import com.rank.rgil.util.CustomConvert;
import com.rank.rgil.util.ThreadSafeSimpleDateFormat;
import com.rank.rgil.vidyo.util.VidyoAccessAdmin;
import com.rank.rgil.vidyo.util.VidyoAccessUser;
import com.vidyo.portal.user.v1_1.Entity;
import com.vidyo.portal.user.v1_1.MyAccountResponse;

import java.util.logging.Level;

/**
 * @author Sukanta
 * @since 30-Nov-2018
 * @Purpose this Class object will be used by the Rest Controller
 */
@Service("rgilRESTControllerServiceImpl")
public class CCMSRESTControllerServiceImpl implements CCMSRESTControllerService {

    private static final Logger logger = LogManager.getLogger(CCMSRESTControllerService.class);

    //private final boolean FALSE_STATUS = false;
    private final String MSG_SUCCESS = "SUCCESS";
  
    @Autowired
    private EmployeeLoginService employeeLoginService;

    @Autowired
    private ReasonMstService reasonMstService;

    @Autowired
    private EmployeeActivityDtlService employeeActivityDtlService;

    @Autowired
    private EmployeeCallStatusService employeeCallStatusService;

    @Autowired
    private TenancyEmployeeMapService tenancyEmployeeMapService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private CallMstService callMstService;

    @Autowired
    private CallRecordsService callRecordsService;

    @Autowired
    private CallDtlService callDtlService;

    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;

    @Autowired
    private ModuleMstService moduleMstService;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.rank.rgil.rest.service.CCMSRESTControllerService#getAgentLoginResponse(
     * String credential)
     */
    @Override
    public AgentLoginResponse getAgentLoginResponse(String credential) throws Exception {//chayan
        EmployeeMst employeeMst = null;
        AgentLoginResponse agentLoginResponse = null;

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String loginId = cred.getString("loginId");
            // String loginPassword = cred.getString("password");

            if (loginId == null || loginId.isEmpty() || loginId == "") {
                CCMSRestException rgilRestException = new CCMSRestException();
                rgilRestException.setErrorCode("418");
                rgilRestException.setErrorMessage("Please Provide loginId!");
                throw rgilRestException;

            }

            // if (loginPassword == null || loginPassword.isEmpty() || loginPassword == "")
            // {
            // CCMSRestException rgilRestException = new CCMSRestException();
            // rgilRestException.setErrorCode("418");
            // rgilRestException.setErrorMessage("Please Provide password!");
            // throw rgilRestException;
            // }
            if (!userCheck(loginId + "_RGIL")) {
                employeeMst = employeeLoginService.checkLogin(loginId, "123", "");

                /**
                 * check if login credential is valid or not
                 */
                if (employeeMst != null) {
                    if (employeeMst.getModuleId().getId().equals(Long.parseLong("2"))) {

                        Date date = new Date();

                        /**
                         * @purpose: Find in employee_activity_dtl table if any
                         *           previous activity made by this agent is ended or not.
                         *           Consider only the last most activity.If not ended,
                         *           then give a end time and save it in the
                         *           employee_activity_dtl table. If login is not ended
                         *           then Logout must not be ended. Because every
                         *           successful logout associated with a login end time in
                         *           the table.
                         */
                        /**
                         * Start of login activity check and save
                         */
                        // =======================Delete Room=============================
                        // =======================End Delete Room=========================
                        EmployeeActivityDtl employeeActivityDtl = null;
                        List<EmployeeActivityDtl> employeeActivityDtlList = employeeActivityDtlService
                                .findLastNonEndedActivityByType(employeeMst.getId(), "login");
                        if(employeeActivityDtlList.size()!=0){
                            employeeActivityDtl=employeeActivityDtlList.get(0);
                        }
                        Long resLoginId = Long.parseLong("0");
                        ReasonMst reasonMst = reasonMstService.findByReasonCd("SOUT").get(0);
                        if (null != employeeActivityDtl) {
                            resLoginId = employeeActivityDtl.getId();
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                            employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl);

                            /**
                             * Start of logout activity against the login
                             * activity
                             */
                            employeeActivityDtl = new EmployeeActivityDtl();
                            employeeActivityDtl.setEmpId(employeeMst);
                            employeeActivityDtl.setActivity("logout");
                            employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                            employeeActivityDtl.setRespectiveLoginId(resLoginId);
                            employeeActivityDtl.setReasonId(reasonMst);
                            employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                            employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());

                            employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl);
                            /**
                             * End of logout activity
                             */

                        }
                        /**
                         * End of login activity check and save
                         */

                        /**
                         * Start of Self View activity check and save
                         */
                        employeeActivityDtlList = employeeActivityDtlService
                                .findLastNonEndedActivityByType(employeeMst.getId(), "Self View");
                        if(employeeActivityDtlList.size()!=0){
                            employeeActivityDtl=employeeActivityDtlList.get(0);
                        }
                        if (employeeActivityDtl != null) {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(employeeActivityDtl);
                        }
                        /**
                         * End of Self View activity check and save
                         */

                        /**
                         * Start of Not Ready activity check and save
                         */
                        employeeActivityDtlList = employeeActivityDtlService
                                .findLastNonEndedActivityByType(employeeMst.getId(), "not ready");
                        if(employeeActivityDtlList.size()!=0){
                            employeeActivityDtl=employeeActivityDtlList.get(0);
                        }
                        if (employeeActivityDtl != null) {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                            employeeActivityDtlService.save(employeeActivityDtl);
                        }
                        /**
                         * End of Not Ready activity check and save
                         */

                        /**
                         * Start of Next Call activity check and save
                         */
                        employeeActivityDtlList = employeeActivityDtlService
                                .findLastNonEndedActivityByType(employeeMst.getId(), "NEXT CALL");
                        if(employeeActivityDtlList.size()!=0){
                            employeeActivityDtl=employeeActivityDtlList.get(0);
                        }
                        if (employeeActivityDtl != null) {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                            employeeActivityDtlService.save(employeeActivityDtl);

                        }
                        /**
                         * End of Next Call activity check and save
                         */

                        /**
                         * Start of Call Started activity check and save
                         */
                        employeeActivityDtlList = employeeActivityDtlService
                                .findLastNonEndedActivityByType(employeeMst.getId(), "Call Started");
                        if(employeeActivityDtlList.size()!=0){
                            employeeActivityDtl=employeeActivityDtlList.get(0);
                        }
                        if (employeeActivityDtl != null) {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                            employeeActivityDtlService.save(employeeActivityDtl);

                        }
                        /**
                         * End of Call Started activity check and save
                         */

                        /**
                         * Start of Hang Up activity check and save
                         */
                        employeeActivityDtlList = employeeActivityDtlService
                                .findLastNonEndedActivityByType(employeeMst.getId(), "Hang Up");
                        if(employeeActivityDtlList.size()!=0){
                            employeeActivityDtl=employeeActivityDtlList.get(0);
                        }
                        if (employeeActivityDtl != null) {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                            employeeActivityDtlService.save(employeeActivityDtl);

                        }
                        /**
                         * End of Hang Up activity check and save
                         */

                        /**
                         * @purpose: Update the Call Status of this employee to
                         *           false. Since not available for this employee, as not
                         *           ready.
                         */
                        List<EmployeeCallStatus> empCallStatusList = employeeCallStatusService
                                .findByEmpId(employeeMst);
                        for (EmployeeCallStatus callStatus : empCallStatusList) {
                            // callStatus.setCallCount(0);
                            //callStatus.setStatus(false);
                            employeeCallStatusService.delete(callStatus);
                        }

                        /**
                         * @purpose: Update the Forward Call Active Flag of this
                         *           employee to false. Since, for fresh login this is a
                         *           certain.
                         */
                        // List<ForwardedCall> forwardedCallList = forwardedCallService
                        //         .findActiveForwardedCallByEmployeeMstList(employeeMst);
                        // for (ForwardedCall forwardedCall : forwardedCallList) {
                        //     forwardedCall.setForwardStatus("NotPicked");
                        //     forwardedCall.setActiveFlg(false);
                        //     forwardedCall.setDeleteFlg(true);
                        //     forwardedCallService.save(forwardedCall);
                        // }

                        logger.info(">>>>Logout Successfully>>> Employee UserId: " + employeeMst.getLoginId()
                                + " Employee Name:" + employeeMst.getFirstName() + " " + employeeMst.getMidName() + " "
                                + employeeMst.getLastName() + ".");

                        // /**
                        //  * @purpose: Remove the non existent employee from the
                        //  *           list which is set through the scheduler.
                        //  */
                        // if (!CallScheduler.listCallEmp.isEmpty()) {
                        //     for (CallEmployeeMap cem : CallScheduler.listCallEmp) {
                        //         if (employeeMst != null && employeeMst.getId() == cem.getEmployeeId().longValue()) {
                        //             CallScheduler.listCallEmp.remove(cem);
                        //             break;
                        //         }
                        //     }
                        // }

                        /**
                         * @purpose: Search in reason_mst table for type Login.
                         *           If any record found then fetch the first record if no
                         *           record found, then create a new ReasonMst entity and
                         *           assign as type Login and set required fields.
                         *           ReasonMst object will be used to store value in
                         *           employee activity detail table.
                         */
                        List<ReasonMst> lReasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("Login");
                        if (null == lReasonMstList || lReasonMstList.isEmpty()) {
                            reasonMst = new ReasonMst((long) 0, "Login", true, false);
                            reasonMst.setReasonCd("LN001");
                            reasonMst.setReasonDesc("Login First Time - Day Start.");
                        } else {
                            reasonMst = lReasonMstList.get(0);
                        }

                        /**
                         * @purpose: Create a new activity with fresh login and
                         *           start time of the employee.
                         */
                        employeeActivityDtl = new EmployeeActivityDtl();
                        employeeActivityDtl.setEmpId(employeeMst);
                        employeeActivityDtl.setActivity("login");
                        try {
                            employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        } catch (ParseException e) {
                            logger.info("Error:" + e.getMessage());
                        }
                        employeeActivityDtl.setReasonId(reasonMst);
                        employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                        employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                        employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl);
                        
                        /**
                         * @purpose: Create a new AgentLoginResponse object in
                         *           order to send the response.
                         */
                        agentLoginResponse = new AgentLoginResponse();
                        agentLoginResponse.setSuccMsg("Success");
                        agentLoginResponse.setValidateMsg("");

                        agentLoginResponse.setEmpId(employeeMst.getId());
                        agentLoginResponse.setFirstname(employeeMst.getFirstName());
                        agentLoginResponse.setLastName(employeeMst.getLastName());
                        agentLoginResponse.setLoginId(employeeMst.getLoginId());
                        agentLoginResponse.setEmpType(employeeMst.getEmpTypId().getTypeName());
                        agentLoginResponse.setSocketHostPublic(Constants.socketHostPublic);
                        /**
                         * return from the method, if success
                         */
                        return agentLoginResponse;
                    } else {
                        agentLoginResponse = new AgentLoginResponse();
                        agentLoginResponse.setSuccMsg("The credential is not valid for smart zone!");
                        return agentLoginResponse;
                        // CCMSRestException rgilRestException = new CCMSRestException();
                        // rgilRestException.setErrorCode("418");
                        // rgilRestException.setErrorMessage("The credential is not valid for smart zone!");
                        // throw rgilRestException;
                    }
                } else {    
                    CCMSRestException rgilRestException = new CCMSRestException();
                    rgilRestException.setErrorCode("418");
                    rgilRestException.setErrorMessage("Please Provide valid login credential!");
                    throw rgilRestException;
                }
            } else {
                logger.info("This is duplicate login for the agent");
                employeeMst = employeeLoginService.checkLogin(loginId, "123", "");

                /**
                 * check if login credential is valid or not
                 */
                if (employeeMst != null) {
                    if (employeeMst.getModuleId().getId().equals(Long.parseLong("2"))) {
                        agentLoginResponse = new AgentLoginResponse();
                        agentLoginResponse.setSuccMsg("Success");
                        agentLoginResponse.setValidateMsg("");

                        agentLoginResponse.setEmpId(employeeMst.getId());
                        agentLoginResponse.setFirstname(employeeMst.getFirstName());
                        agentLoginResponse.setLastName(employeeMst.getLastName());
                        agentLoginResponse.setLoginId(employeeMst.getLoginId());
                        agentLoginResponse.setEmpType(employeeMst.getEmpTypId().getTypeName());
                        agentLoginResponse.setSocketHostPublic(Constants.socketHostPublic);
                        /**
                         * return from the method, if success
                         */
                        return agentLoginResponse;
                    } else {
                        agentLoginResponse = new AgentLoginResponse();
                        agentLoginResponse.setSuccMsg("The credential is not valid for smart zone!");
                        return agentLoginResponse;
                        // CCMSRestException rgilRestException = new CCMSRestException();
                        // rgilRestException.setErrorCode("418");
                        // rgilRestException.setErrorMessage("The credential is not valid for smart zone!");
                        // throw rgilRestException;
                    }
                } else {
                    CCMSRestException rgilRestException = new CCMSRestException();
                    rgilRestException.setErrorCode("418");
                    rgilRestException.setErrorMessage("Please Provide valid login credential!");
                    throw rgilRestException;
                }
            }

        } else {
            CCMSRestException rgilRestException = new CCMSRestException();
            rgilRestException.setErrorCode("418");
            rgilRestException.setErrorMessage("Please Provide login credential!");
            throw rgilRestException;
        }
    }

    

    public Boolean userCheck(String uId) {

        Boolean exists = false;
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(Constants.socketHostPrivate + "/checkUser/" + uId);

        HttpResponse response1;
        String response = "Failure";
        try {
            response1 = httpclient.execute(httpget);
            HttpEntity responseEntity = response1.getEntity();

            if (responseEntity != null) {
                response = EntityUtils.toString(responseEntity);
            }
            System.out.println("Response==========" + response);
            if (response.equals("EXISTS")) {
                exists = true;
            }
        } catch (IOException | org.apache.http.ParseException ex) {
            logger.error("Error===" + ex);
        }
        return exists;
    }

    @Override
    public AgentLoginResponse deleteRooms(String credential) throws Exception {//chayan
        AgentLoginResponse alr = new AgentLoginResponse();
        alr.setSuccMsg("Failure");
        try {
            VidyoAccessUser vidyoAccessUser = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);
            MyAccountResponse myAccountResponse = vidyoAccessUser.getMyaccount("rgiladmin", "Rgil@1234$",
                    Constants.vidyoportalUserServiceWSDL);
            String ownerid = "";

            if (myAccountResponse != null) {
                if (myAccountResponse.getEntity() != null) {
                    ownerid = (String) myAccountResponse.getEntity().getOwnerID().getValue();
                }
            }
            // request.getSession().setAttribute("ormAgentMaster", null);

            if (!"".equals(ownerid.trim())) {
                try {
                    List<Entity> entities = vidyoAccessUser.getAllRoomsByOwnerId("rgiladmin", "Rgil@1234$",
                            Constants.vidyoportalUserServiceWSDL, ownerid);
                    int count = 0;
                    for (Entity entitie : entities) {
                        if (!entitie.getEntityID().equals(ownerid)) {
                            if (entitie.getRoomStatus().equalsIgnoreCase("Empty")) {
                                // if (entitie.getDisplayName().contains(employeeMst.getLoginId())) {
                                logger.info("Delete----------------------------");
                                count++;
                                VidyoAccessUser vidyoAccessUser1 = new VidyoAccessUser(
                                        Constants.vidyoportalUserServiceWSDL);
                                vidyoAccessUser1.deleteRoom("rgiladmin", "Rgil@1234$",
                                        Constants.vidyoportalUserServiceWSDL, entitie.getEntityID());
                                // }
                            }
                        }
                        if (count > 5000) {
                            break;
                        }
                    }
                    alr.setSuccMsg("Successfull");
                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }
            }
        } catch (Exception eex) {
            logger.error(eex.getMessage());
        }
        return alr;
    }

    @Override
    public String getActivityClean(){//chayan
        employeeActivityDtlService.deleteAll();
        return "ok";
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * com.rank.rgil.rest.service.CCMSRESTControllerService#getAgentReadyResponse(
     * String credential)
     */
    @Override
    public AgentLoginResponse getAgentReadyResponse(String credential) throws Exception {//chayan

        AgentLoginResponse agentLoginResponse = null;
        String room = null;
        String token = null;
        String entityId = null;
        Boolean access= false;
        EmployeeActivityDtl employeeActivityDtl = null;
        Timestamp time = CustomConvert.javaDateToTimeStamp(new Date());

        /**
         * @Purpose To check whether this method parameter credential is null or
         *          not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String employeeId = cred.getString("empId");

            if (employeeId != null && employeeId.length() > 0) {
                // Long empId = Long.parseLong(employeeId);
                EmployeeMst empMst = null;
                List<EmployeeMst> emlist=employeeMstService.findByLoginId(employeeId);
                if(emlist.size()!=0){
                    empMst=emlist.get(0);
                }
                EmployeeCallStatus empCallStatus = null;

                List<EmployeeCallStatus> empClStatusList = null;
                List<TenancyEmployeeMap> currentAgent = null;

                /**
                 * @purpose: Check whether return object as empMst is null or
                 *           not, if not null then generate a random token and a roomId
                 *           for this employee and make this employee ready to take the
                 *           call.
                 */
                if (null != empMst) {
                    Boolean flagg=true;
                    Long id=employeeActivityDtlService.findMaxIdByEmpId(empMst.getId());
                    if(id!=null){
                        Optional<EmployeeActivityDtl> employeeActivityDtlOp = employeeActivityDtlService.findById(id);
                        employeeActivityDtl=employeeActivityDtlOp.get();
                        if(employeeActivityDtl.getActivity().equals("Call Started") || employeeActivityDtl.getActivity().equals("HOLD CALL")){
                            flagg=false;
                        }
                    }
                    
                    if(flagg){
                        if(employeeActivityDtl!=null){
                            if(!employeeActivityDtl.getActivity().equals("NEXT CALL")){
                                /**
                                 * @purpose empClStatusList object is used to find if this
                                 *          employee's has any entry in the employee_call_status
                                 *          table or not. If found the update the existing to true
                                 *          else create a new callStatus entity and set the status to
                                 *          true and save it to the employee_call_status table.
                                 */
                                empClStatusList = employeeCallStatusService.findByEmpId(empMst);

                                currentAgent = tenancyEmployeeMapService.findByEmpId(empMst);
                                for (TenancyEmployeeMap currentAgent1 : currentAgent) {
                                    room=currentAgent1.getRoomName();
                                    entityId=currentAgent1.getEntityId();
                                    token=currentAgent1.getRoomLink();
                                    break;
                                }

                                VidyoAccessAdmin accessAdmin = new VidyoAccessAdmin();
                                if(entityId!=null){
                                    access=accessAdmin.getroom(empMst.getVidyoUserId(), empMst.getVidyoPasswd(),
                                    Constants.vidyoportalAdminServiceWSDL, entityId, empMst.getLoginId());
                                }
                                if(!access){
                                    /**
                                     * @purpose currentAgent is list of tenancy_employee_map
                                     *          table objects. It is in general single entry against each
                                     *          logged in employee is valid. Based on this the multiple
                                     *          employee with same credential is detected.
                                     */
                                    //currentAgent = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empMst.getId());

                                    /**
                                     * @purpose To generate a resourceId as room and token as
                                     *          token as follows
                                     */
                                    VidyoAccessUser vidyoAccessUser = new VidyoAccessUser();
                                    String ret = vidyoAccessUser.createRoom(empMst.getVidyoUserId(), empMst.getVidyoPasswd(),
                                            Constants.vidyoportalUserServiceWSDL, empMst.getLoginId());

                                    token = ret.split(",")[0].substring(ret.split(",")[0].lastIndexOf("/") + 1);
                                    entityId = ret.split(",")[1];
                                    room = ret.split(",")[2];
                                    logger.info("New token for " + empMst.getLoginId() + " :" + token);
                                    /**
                                     * End of token generation
                                     */ 
                                }            
                            }else{
                                currentAgent = tenancyEmployeeMapService.findByEmpId(empMst);
                                for (TenancyEmployeeMap currentAgent1 : currentAgent) {
                                    room=currentAgent1.getRoomName();
                                    entityId=currentAgent1.getEntityId();
                                    token=currentAgent1.getRoomLink();
                                    break;
                                }
                            }
                        }
                    } else {
                        CCMSRestException rgilRestException = new CCMSRestException();
                        rgilRestException.setErrorCode("418");
                        rgilRestException.setErrorMessage("The Agent is Already In a Call!");
                        throw rgilRestException;
                    }
                } else {
                    CCMSRestException rgilRestException = new CCMSRestException();
                    rgilRestException.setErrorCode("418");
                    rgilRestException.setErrorMessage("Please Provide valid credential!");
                    throw rgilRestException;
                }

                /**
                 * @purpose To check whether there exist an entry corresponding
                 *          to this employee in tenancy_employee_map table or not. If
                 *          found then update with the above generated resourceId and
                 *          token and if not found, then create a tenancyEmployeeMap
                 *          object and set it true and save it in the
                 *          tenancy_employee_map table.
                 */
                if(employeeActivityDtl!=null){
                    if(!employeeActivityDtl.getActivity().equals("NEXT CALL")){
                        if(!access){
                            if (!currentAgent.isEmpty() && currentAgent.size() > 0) {
                                for (TenancyEmployeeMap currentAgent1 : currentAgent) {
                                    currentAgent1.setRoomName(room);
                                    currentAgent1.setEntityId(entityId);
                                    currentAgent1.setRoomLink(token);
                                    tenancyEmployeeMapService.save(currentAgent1);
                                }
                            } else {
                                TenancyEmployeeMap tenancyEmployeeMap = new TenancyEmployeeMap();
                                tenancyEmployeeMap.setEmpId(empMst);
                                tenancyEmployeeMap.setRoomLink(token);
                                tenancyEmployeeMap.setEntityId(entityId);
                                tenancyEmployeeMap.setRoomName(room);
                                tenancyEmployeeMapService.save(tenancyEmployeeMap);
                            }
                        }
                    }
                }

                /**
                 * @purpose: Create a new AgentLoginResponse object in order to
                 *           send the response.
                 */
                agentLoginResponse = new AgentLoginResponse();
                agentLoginResponse.setRoomLink(token);

                /**
                 * @purpose To check whether there exist an entry corresponding
                 *          to this employee in employee_call_status table or not. If
                 *          found then update with the status as true and if not found,
                 *          then create a call status object and set it true and save it
                 *          in the employee_call_status.
                 */
                if(employeeActivityDtl!=null){
                    if(!employeeActivityDtl.getActivity().equals("NEXT CALL")){
                        if (!empClStatusList.isEmpty()) {
                            int count=1;
                            for (EmployeeCallStatus empstatus : empClStatusList) {
                                if(empClStatusList.size()==count){
                                    empCallStatus = empstatus;
                                    empCallStatus.setStatus(true);
                                    empCallStatus.setReadyTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    employeeCallStatusService.save(empCallStatus);
                                }else{
                                    employeeCallStatusService.delete(empstatus);
                                }
                                count++;
                            }
                            
                        } else {
                            empCallStatus = new EmployeeCallStatus();
                            empCallStatus.setEmpId(empMst);
                            empCallStatus.setStatus(true);
                            empCallStatus.setReadyTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeCallStatusService.save(empCallStatus);
                        }

                        /**
                         * @purpose To find out the last non ended activity of this
                         *          employee by type "not ready". If found then update the record
                         *          with the end time.
                         */
                        List<EmployeeActivityDtl> employeeActivityDtlList = employeeActivityDtlService
                                .findLastNonEndedActivityByType(empMst.getId(), "not ready");
                        if(employeeActivityDtlList.size()!=0){
                            employeeActivityDtl=employeeActivityDtlList.get(0);
                        }
                        if (employeeActivityDtl != null) {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(employeeActivityDtl);
                        }

                        /**
                         * @purpose To find out the last non ended activity of this
                         *          employee by type "Hang Up". If found then update the record
                         *          with the end time.
                         */
                        employeeActivityDtlList = employeeActivityDtlService
                                .findLastNonEndedActivityByType(empMst.getId(), "Hang Up");
                        if(employeeActivityDtlList.size()!=0){
                            employeeActivityDtl=employeeActivityDtlList.get(0);
                        }
                        if (employeeActivityDtl != null) {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(employeeActivityDtl);
                        }

                        /**
                         * @purpose To find out the last non ended activity of this
                         *          employee by type "Self View". If found then update the record
                         *          with the end time.
                         */
                        employeeActivityDtlList = employeeActivityDtlService
                                .findLastNonEndedActivityByType(empMst.getId(), "Self View");
                        if(employeeActivityDtlList.size()!=0){
                            employeeActivityDtl=employeeActivityDtlList.get(0);
                        }
                        if (employeeActivityDtl != null) {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(employeeActivityDtl);
                        }

                        /**
                         * @purpose To create a new EmployeeActivityDtl object to add a
                         *          row in the employee_activity_table as "NEXT CALL" activity
                         *          and reason as "Not Ready" with reason code as "NRDFT"
                         */
                        employeeActivityDtl = new EmployeeActivityDtl();
                        employeeActivityDtl.setActivity("NEXT CALL");
                        employeeActivityDtl.setEmpId(empMst);

                        ReasonMst reasonmst = reasonMstService.findByType("Not Ready").get(0);
                        employeeActivityDtl.setReasonId(reasonmst);
                        employeeActivityDtl.setReasonCd(reasonmst.getReasonCd());
                        employeeActivityDtl.setReasonDesc(reasonmst.getReasonDesc());
                        
                        employeeActivityDtl.setStartTime(time);
                        employeeActivityDtlService.save(employeeActivityDtl);
                    }
                }

                agentLoginResponse.setTimeStamp(time.toString());
                agentLoginResponse.setSuccMsg("Success");

                /**
                 * return from the method, if success
                 */
                return agentLoginResponse;
            } else {
                CCMSRestException rgilRestException = new CCMSRestException();
                rgilRestException.setErrorCode("418");
                rgilRestException.setErrorMessage("Please Provide valid credential!");
                throw rgilRestException;
            }

        } else {
            CCMSRestException rgilRestException = new CCMSRestException();
            rgilRestException.setErrorCode("418");
            rgilRestException.setErrorMessage("Please Provide valid credential!");
            throw rgilRestException;
        }

    }

    @Override
    public List<CustomerDto> getCallLogResponse(String callInfo) throws Exception {//chayan
        List<CustomerDto> customerDtoList = new ArrayList<>();

        if (callInfo != null && callInfo.length() > 0) {
            JSONObject cred = new JSONObject(callInfo);
            String employeeId = cred.getString("empId");

            if (employeeId != null && employeeId.length() > 0) {
                // Long empId = Long.parseLong(employeeId);
                EmployeeMst emp = null;
                List<EmployeeMst> emlist=employeeMstService.findByLoginId(employeeId);
                if(emlist.size()!=0){
                    emp=emlist.get(0);
                }
                if (emp != null) {
                    List<CallMst> cmList = callMstService.findByEmpId(emp.getId());
                    logger.info("Call Log Count===" + cmList.size());
                    if (cmList != null) {
                        if (!cmList.isEmpty()) {
                            int count = 0;
                            for (CallMst cm : cmList) {
                                logger.info("Call Status===" + cm.getCallStatus());
                                // if (count < 15) {
                                CallDtl cd = callDtlService.findCurrentCallAgent(cm.getId());
                                CustomerDto customerDto = new CustomerDto();
                                CustomerMst custMst = customerMstService.findByCustId(cm.getCustId()).get(0);

                                if (custMst != null) {
                                    if (custMst.getFirstName() != null) {
                                        customerDto.setCustomerName(custMst.getFirstName());
                                    } else {
                                        customerDto.setCustomerName(null);
                                    }
                                    ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat(
                                            "dd-MM-yyyy HH:mm:ss");
                                    customerDto.setCallDate(dateFormat.format(cm.getStartTime()));
                                    customerDto.setCellPhone(custMst.getCellPhone().toString());
                                    customerDto.setEmail(custMst.getEmail());
                                    customerDto.setRefNumber(custMst.getNatinality());
                                    if (cd != null) {
                                        if (cd.getAgentComments() != null) {
                                            if (cd.getAgentComments().equals("Not Recieved By Agent")) {
                                                customerDto.setCallStatus("Missed");
                                            } else {
                                                customerDto.setCallStatus(cd.getAgentComments());
                                            }
                                        }

                                        CallRecords cr = callRecordsService.findByCallId(cm).get(0);
                                        if (cr != null) {
                                            logger.info("Link====" + cr.getExternalPlaybackLink());
                                            if (cr.getExternalPlaybackLink() != null) {
                                                if (cr.getExternalPlaybackLink().equalsIgnoreCase("Not saved")) {
                                                    customerDto.setCallRecordLink("#");
                                                    customerDto.setRenderDownloadLink(false);
                                                } else if (cr.getExternalPlaybackLink().equalsIgnoreCase("Not Found")) {
                                                    customerDto.setCallRecordLink("##");
                                                    customerDto.setRenderDownloadLink(false);
                                                } else {
                                                    customerDto.setRenderDownloadLink(true);
                                                    customerDto.setCallRecordLink(cr.getExternalPlaybackLink());
                                                }
                                            } else {
                                                customerDto.setCallRecordLink("#");
                                            }
                                        } else {
                                            customerDto.setRenderDownloadLink(false);
                                            customerDto.setCallRecordLink("##");
                                        }

                                        logger.info("Link=Saved===" + customerDto.getCallRecordLink());
                                        logger.info("Renderer=Saved===" + customerDto.getRenderDownloadLink());
                                        customerDtoList.add(customerDto);
                                        count++;
                                    } else {
                                        if (cm.getCallStatus().equals("No Agent Found")) {
                                            customerDto.setCallStatus("Missed");
                                        }
                                        customerDto.setRenderDownloadLink(false);
                                        customerDto.setCallRecordLink("##");
                                        customerDtoList.add(customerDto);
                                        count++;
                                    }
                                }
                                // }
                            }
                        }
                    }
                }

            }

        }
        return customerDtoList;
    }

    

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.rank.rgil.rest.service.CCMSRESTControllerService#getAgentLogoutResponse(
     * String callInfo)
     */
    @Override
    public AgentLoginResponse getAgentLogoutResponse(String callInfo) throws Exception {//chayan

        // String callId = null;
        String agentId = null;
        AgentLoginResponse agentLoginResponse = null;
        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("empId")) {
                agentId = callInformation.getString("empId");
                logger.info("Agent id found");
            } else {
                CCMSRestException rgilRestException = new CCMSRestException();
                rgilRestException.setErrorCode("418");
                rgilRestException.setErrorMessage("Please Provide valid information!");
                throw rgilRestException;
            }

        } else {
            CCMSRestException rgilRestException = new CCMSRestException();
            rgilRestException.setErrorCode("418");
            rgilRestException.setErrorMessage("Please Provide valid information!");
            throw rgilRestException;
        }

        if (agentId != null && agentId.length() > 0) {

            EmployeeMst employeeMaster = null;
            List<EmployeeMst> emlist=employeeMstService.findByLoginId(agentId);
            if(emlist.size()!=0){
                employeeMaster=emlist.get(0);
            }
            List<ReasonMst> lReasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("Logout");
            agentLoginResponse = new AgentLoginResponse();
            ReasonMst reasonMst;

            if (null == lReasonMstList || lReasonMstList.isEmpty()) {
                reasonMst = new ReasonMst((long) 0, "login", true, false);
                reasonMst.setReasonCd("LN001");
                reasonMst.setReasonDesc("Login First Time - Day Start.");
            } else {
                reasonMst = lReasonMstList.get(0);
            }
            if (employeeMaster != null) {
                List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusService
                        .findByEmpId(employeeMaster);
                for (EmployeeCallStatus employeeCallStatusList1 : employeeCallStatusList) {
                    //employeeCallStatusList1.setStatus(false);
                    employeeCallStatusService.delete(employeeCallStatusList1);
                }
                Date date = new Date();
                EmployeeActivityDtl employeeActivityDtl = null;
                List<EmployeeActivityDtl> employeeActivityDtlList = employeeActivityDtlService
                        .findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                if(employeeActivityDtlList.size()!=0){
                    employeeActivityDtl=employeeActivityDtlList.get(0);
                }
                Long resLoginId = Long.parseLong("0");
                if (null != employeeActivityDtl) {

                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl);
                    resLoginId = employeeActivityDtl.getId();

                    employeeActivityDtl = new EmployeeActivityDtl();
                    employeeActivityDtl.setEmpId(employeeMaster);
                    employeeActivityDtl.setActivity("logout");
                    employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl.setRespectiveLoginId(resLoginId);
                    employeeActivityDtl.setReasonId(reasonMst);
                    employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                    employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                    employeeActivityDtlService.save(employeeActivityDtl);
                }
                List<TenancyEmployeeMap> tenancyEmployeeMaplst = tenancyEmployeeMapService
                        .findByEmpId(employeeMaster);
                tenancyEmployeeMapService.deleteAllInBatch(tenancyEmployeeMaplst);
                // for (TenancyEmployeeMap tenancyEmployeeMaplst1 : tenancyEmployeeMaplst) {
                //     tenancyEmployeeMapService.deleteTenancyEmployeeMap(tenancyEmployeeMaplst1);
                // }

                agentLoginResponse.setSuccMsg(this.MSG_SUCCESS);
                agentLoginResponse.setValidateMsg("");
                // =======================Delete Room=============================
                try {
                    VidyoAccessUser vidyoAccessUser = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);
                    MyAccountResponse myAccountResponse = vidyoAccessUser.getMyaccount(employeeMaster.getVidyoUserId(),
                            employeeMaster.getVidyoPasswd(), Constants.vidyoportalUserServiceWSDL);
                    String ownerid = "";

                    if (myAccountResponse != null) {
                        if (myAccountResponse.getEntity() != null) {
                            ownerid = (String) myAccountResponse.getEntity().getOwnerID().getValue();
                        }
                    }
                    // request.getSession().setAttribute("ormAgentMaster", null);

                    if (!"".equals(ownerid.trim())) {
                        try {
                            List<Entity> entities = vidyoAccessUser.getAllRoomsByOwnerId(
                                    employeeMaster.getVidyoUserId(), employeeMaster.getVidyoPasswd(),
                                    Constants.vidyoportalUserServiceWSDL, ownerid);
                            for (Entity entitie : entities) {
                                if (!entitie.getEntityID().equals(ownerid)) {
                                    if (entitie.getRoomStatus().equalsIgnoreCase("Empty")) {
                                        if (entitie.getDisplayName().contains(employeeMaster.getLoginId())) {
                                            logger.info("delete----------------");
                                            VidyoAccessUser vidyoAccessUser1 = new VidyoAccessUser(
                                                    Constants.vidyoportalUserServiceWSDL);
                                            vidyoAccessUser1.deleteRoom(employeeMaster.getVidyoUserId(),
                                                    employeeMaster.getVidyoPasswd(),
                                                    Constants.vidyoportalUserServiceWSDL, entitie.getEntityID());
                                        }
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            logger.error(ex.getMessage());
                        }
                    }
                } catch (Exception eex) {
                    logger.error(eex.getMessage());
                }
                // =======================End Delete Room=========================
                logger.info(">>>>Logout Successfully>>> Employee UserId: " + employeeMaster.getLoginId()
                        + " Employee Name:" + employeeMaster.getFirstName() + " " + employeeMaster.getMidName() + " "
                        + employeeMaster.getLastName() + ".");
            } else {
                CCMSRestException rgilRestException = new CCMSRestException();
                rgilRestException.setErrorCode("418");
                rgilRestException.setErrorMessage("Please Provide valid information!");
                logger.info(
                        ">>>>Logout Successfully, but NO User Details found in this session, OR is requesting again after Logout.");
                throw rgilRestException;

            }
            return agentLoginResponse;
        } else {
            CCMSRestException rgilRestException = new CCMSRestException();
            rgilRestException.setErrorCode("418");
            rgilRestException.setErrorMessage("Please Provide valid information!");
            throw rgilRestException;
        }
    }

    @Override
    public AgentLoginResponse getAgentNotReadyResponse(String callInfo) throws Exception {//chayan
        logger.info("Not Ready......");
        String agentId = null;
        AgentLoginResponse agentLoginResponse = null;
        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("empId")) {
                agentId = callInformation.getString("empId");
                logger.info("Agent id found= " + agentId);
            } else {
                CCMSRestException rgilRestException = new CCMSRestException();
                rgilRestException.setErrorCode("418");
                rgilRestException.setErrorMessage("Please Provide valid information!");
                throw rgilRestException;
            }

        } else {
            CCMSRestException rgilRestException = new CCMSRestException();
            rgilRestException.setErrorCode("418");
            rgilRestException.setErrorMessage("Please Provide valid information!");
            throw rgilRestException;
        }

        agentLoginResponse = new AgentLoginResponse();
        EmployeeMst empMst = null;
        List<EmployeeMst> emlist=employeeMstService.findByLoginId(agentId);
        if(emlist.size()!=0){
            empMst=emlist.get(0);
        }
        if (empMst != null) {
            EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
            List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findByEmpId(empMst);
            if (empClStatusList != null) {
                int count=1;
                for (EmployeeCallStatus empstatus : empClStatusList) {
                    if(empClStatusList.size()==count){
                        empCallStatus = empstatus;
                        empCallStatus.setStatus(false);
                        employeeCallStatusService.save(empCallStatus);
                    }else{
                        employeeCallStatusService.delete(empstatus);
                    }
                    count++;
                }
            } else {
                empCallStatus.setEmpId(empMst);
                empCallStatus.setStatus(false);
                employeeCallStatusService.save(empCallStatus);
            }

            EmployeeActivityDtl employeeActivityDtl = null;
            List<EmployeeActivityDtl> employeeActivityDtlList = employeeActivityDtlService
                    .findLastNonEndedActivityByType(empMst.getId(), "NEXT CALL");
            if(employeeActivityDtlList.size()!=0){
                employeeActivityDtl=employeeActivityDtlList.get(0);
            }
            if (employeeActivityDtl != null) {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtlService.save(employeeActivityDtl);

            }

            ReasonMst reasonMst = reasonMstService.findByType("Not Ready").get(0);
            if (reasonMst != null) {
                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("not ready");
                employeeActivityDtl.setEmpId(empMst);
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                employeeActivityDtlService.save(employeeActivityDtl);
                agentLoginResponse.setSuccMsg(MSG_SUCCESS);
            }

            // =======================Delete Room=============================
            try {
                VidyoAccessUser vidyoAccessUser = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);
                MyAccountResponse myAccountResponse = vidyoAccessUser.getMyaccount(empMst.getVidyoUserId(),
                        empMst.getVidyoPasswd(), Constants.vidyoportalUserServiceWSDL);
                String ownerid = "";

                if (myAccountResponse != null) {
                    if (myAccountResponse.getEntity() != null) {
                        ownerid = (String) myAccountResponse.getEntity().getOwnerID().getValue();
                    }
                }
                // request.getSession().setAttribute("ormAgentMaster", null);

                if (!"".equals(ownerid.trim())) {
                    try {
                        List<Entity> entities = vidyoAccessUser.getAllRoomsByOwnerId(empMst.getVidyoUserId(),
                                empMst.getVidyoPasswd(), Constants.vidyoportalUserServiceWSDL, ownerid);
                        for (Entity entitie : entities) {
                            if (!entitie.getEntityID().equals(ownerid)) {
                                if (entitie.getRoomStatus().equalsIgnoreCase("Empty")) {
                                    if (entitie.getDisplayName().contains(empMst.getLoginId())) {
                                        logger.info("delete----------------");
                                        VidyoAccessUser vidyoAccessUser1 = new VidyoAccessUser(
                                                Constants.vidyoportalUserServiceWSDL);
                                        vidyoAccessUser1.deleteRoom(empMst.getVidyoUserId(), empMst.getVidyoPasswd(),
                                                Constants.vidyoportalUserServiceWSDL, entitie.getEntityID());
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }
                }
            } catch (Exception eex) {
                logger.error(eex.getMessage());
            }
            // =======================End Delete Room=========================

            return agentLoginResponse;

        } else {
            CCMSRestException rgilRestException = new CCMSRestException();
            rgilRestException.setErrorCode("418");
            rgilRestException.setErrorMessage("Please Provide valid information!");
            throw rgilRestException;
        }
    }

    
    @Override
    public String getHelthCheck() {//chayan
        return "Ok";
    }

    @Override
    public String getUsersFromRGIL() {//chayan
        getuseronly();
        return "OK";
    }

    public void getuseronly() {
        logger.info("Get User Started=====================");
        
        HttpClient httpclient = new DefaultHttpClient();
        
        HttpGet httpget = new HttpGet(Constants.SZ_API_BASE_URL + "/GetUsers");
        httpget.addHeader("Authorization", Constants.SZ_API_AUTH);

        HttpResponse response1;
        String response = "Failure";
        try {
            response1 = httpclient.execute(httpget);
            HttpEntity responseEntity = response1.getEntity();

            if (responseEntity != null) {
                response = EntityUtils.toString(responseEntity);
            }
            System.out.println("Response==========" + response);
            if (!response.equals("Failure")) {
                JSONArray jsonArr = new JSONArray(response);
                for (int i = 0; i < jsonArr.length(); i++) {
                    JSONObject jsonObj = jsonArr.getJSONObject(i);
                    String uid = jsonObj.getString("UserName");
                    System.out.println("Key : User name Value : " + uid);
                    EmployeeMst em = null;
                    List<EmployeeMst> emlist=employeeMstService.findByLoginId(uid);
                    if(emlist.size()!=0){
                        em=emlist.get(0);
                    }
                    //EmployeeMst creatorEm = employeeMstService.findByLoginId("admin").get(0);
                    if (em != null) {
                        em.setFirstName(jsonObj.getString("FirstName"));
                        em.setLastName(jsonObj.getString("LastName"));
                        em.setEmail(jsonObj.getString("EmailID"));
                        em.setCellPhone(Long.parseLong(jsonObj.getString("MobileNo")));
                        employeeMstService.save(em);
                    } else { 
                        em = new EmployeeMst();
                        EmployeeTypeMst etm = employeeTypeMstService.findByTypeName("Agent").get(0);
                        ModuleMst moduleMst = moduleMstService.findByName("Smart Zone").get(0);
                        em.setActiveFlg(true);
                        em.setEmpTypId(etm);
                        em.setFirstName(jsonObj.getString("FirstName"));
                        em.setLastName(jsonObj.getString("LastName"));
                        em.setEmail(jsonObj.getString("EmailID"));
                        em.setLoginId(uid);
                        em.setLoginPasswd("123");
                        em.setCellPhone(Long.parseLong(jsonObj.getString("MobileNo")));
                        em.setVidyoUserId(Constants.adminUserId);
                        em.setVidyoPasswd(Constants.adminPwd);
                        em.setDeactivateFlg(false);
                        em.setModuleId(moduleMst);
                        em.setHomePhone(Long.parseLong("0"));
                        em.setOfficePhone(Long.parseLong("0"));
                        em.setAddrsLine1("India");
                        em.setCountry("India");
                        employeeMstService.save(em);
                    }
                }
            }
        } catch (IOException ex) {
            logger.error("Error===" + ex);
        } catch (JSONException ex) {
            java.util.logging.Logger.getLogger(CCMSRESTControllerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
