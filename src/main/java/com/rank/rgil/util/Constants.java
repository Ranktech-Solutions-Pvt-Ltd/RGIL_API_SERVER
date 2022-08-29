package com.rank.rgil.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service("vidyoConstants")
public class Constants {

    public static String vidyoportal;
    public static String vidyoportalAdminServiceWSDL;
    public static String vidyoportalUserServiceWSDL;
    public static String vidyoportalReplayServiceWSDL;
    public static String recorderPrefix;
    public static String adminUserId;
    public static String adminPwd;
    public static String superadminUserId;
    public static String superadminPwd;
    public static String portalExtention;
    public static String agentLoginMaxLimit;
    public static String scheduleTime;
    public static String callCountCheck;
    public static String callTimeOutMiliSec;
    public static String additionalScheduleStatus;
    public static String dockerUrl;
    public static String socketHostPublic;
    public static String socketHostPrivate;
    public static String forwordProxyHost;
    public static String forwordProxyPort;
    public static String vidyoIOKey;
    public static String vidyoIOAppID;
    public static String vidyoIOUserName;
    public static String smtp_host;
    public static String from_address;
    public static String password;
    public static String port;
    public static String ocrUrl;
    public static String riskInspectionExpiryTime;
    public static String smartZoneExpiryTime;
    public static String szGetUser;
    public static String szGetDetails;
    public static String szSendData;
    public static String FILE_CONVERT_URL;
    public static String FILE_SERVER_URL;
    public static String FILE_DOWNLOAD_URL;
    public static String FILE_DOWNLOAD_ALL_URL;
    public static String FILE_UPLOAD_URL;
    public static final String userTypeCust = "CUSTOMER";
    public static final String userTypeEmp = "EMPLOYEE";
    public static String RMS_API_BASE_URL;
    public static String RMS_API_AUTH;
    public static String SZ_API_BASE_URL;
    public static String SZ_API_AUTH;
    // public static final String contextName="kuber";

    private static final Logger logger = LogManager.getLogger(Constants.class);

    public Constants() {
        addUrlConstants();
    }

    // private void addUrlConstants() {
    // String projectHome = System.getenv("rgil_Vidyoconnect_HOME"); // it will be
    // FUTURE GENERALI HOME
    // if (null == projectHome) {
    //
    // logger.info("rgil_Vidyoconnect_HOME not set properly. Please do correction.
    // So initializing Vidyo Portal with Default 'rank1.sandboxga.vidyo.com'.");
    // } else {
    // String vidyoPortalProps = projectHome + File.separator + "configuration" +
    // File.separator + "vidyoConstants.properties";
    //
    // File vidyoPropsFile = new File(vidyoPortalProps);
    // if (vidyoPropsFile.exists()) {
    // Properties properties = new ManagedProperties();
    // try {
    // properties.load(new FileInputStream(vidyoPropsFile));
    // } catch (IOException e) {
    // logger.error("Error:vidyoPropsFile" + e.getMessage());
    // }
    //
    // if (properties.size() > 0) {
    // vidyoportal = properties.getProperty("vidyoportal").trim();
    // vidyoportalAdminServiceWSDL =
    // properties.getProperty("vidyoportalAdminServiceWSDL").trim();
    // vidyoportalUserServiceWSDL =
    // properties.getProperty("vidyoportalUserServiceWSDL").trim();
    // vidyoportalReplayServiceWSDL =
    // properties.getProperty("vidyoportalReplayServiceWSDL").trim();
    // adminUserId = properties.getProperty("adminUserId").trim();
    // adminPwd = properties.getProperty("adminPwd").trim();
    // superadminUserId = properties.getProperty("superadminUserId").trim();
    // superadminPwd = properties.getProperty("superadminPwd").trim();
    // portalExtention = properties.getProperty("portalExtention").trim();
    //
    // dockerUrl = properties.getProperty("dockerUrl").trim();
    // logger.info("dockerUrl=====" + dockerUrl);
    // agentLoginMaxLimit = properties.getProperty("agentLoginMaxLimit").trim();
    // scheduleTime = properties.getProperty("scheduleTime").trim();
    // callCountCheck = properties.getProperty("callCountCheck").trim();
    // callTimeOutMiliSec = properties.getProperty("callTimeOutMiliSec").trim();
    // additionalScheduleStatus =
    // properties.getProperty("additionalScheduleStatus").trim();
    // socketHostPublic = properties.getProperty("socketHostPublic").trim();
    // vidyoIOKey = properties.getProperty("vidyoIOKey").trim();
    // vidyoIOAppID = properties.getProperty("vidyoIOAppID").trim();
    // vidyoIOUserName = properties.getProperty("vidyoIOUserName").trim();
    // smtp_host = properties.getProperty("smtp_host").trim();
    // from_address = properties.getProperty("from_address").trim();
    // password = properties.getProperty("password").trim();
    // ocrUrl = properties.getProperty("ocrUrl").trim();
    // riskInspectionExpiryTime =
    // properties.getProperty("riskInspectionExpiryTime").trim();
    // smartZoneExpiryTime = properties.getProperty("smartZoneExpiryTime").trim();
    //
    // szGetDetails = properties.getProperty("szGetDetails").trim();
    // szGetUser = properties.getProperty("szGetUser").trim();
    // szSendData = properties.getProperty("szSendData").trim();
    // FILE_SERVER_URL = properties.getProperty("FILE_SERVER_URL");
    //
    // FILE_CONVERT_URL = FILE_SERVER_URL + "/rest/file/videoFileConvert";
    // FILE_DOWNLOAD_URL = FILE_SERVER_URL + "/fileDownload?fileName=";
    // FILE_UPLOAD_URL = FILE_SERVER_URL + "/rest/file/multipleFileUpload";
    // FILE_DOWNLOAD_ALL_URL = FILE_SERVER_URL + "/fileDownloadAll";
    // logger.info("Property set was succesful............................");
    //
    // }
    // } else {
    // logger.error("Property set was not succesful
    // ................................");
    //
    // }
    // }
    // }
    private void addUrlConstants() {
        String env = System.getenv("JAVA_ENV"); // set environment variable "JAVA_ENV" for isolating environments [
                                                // allowed: dev/development, uat/staging, prod/production]
        /******DB******
        FOR DB, SET ENVIRONMENT VARIABLES: RGIL_DB_ADDR, RGIL_DB, RGIL_DB_USER, RGIL_DB_PASS
        **************/
        
        if (env == null) {
            logger.warn("JAVA_ENV is not set. \"dev\" will be used by default.");
            env = "dev"; // default environment will be null
        }

        logger.info("CURRENT ENVIRONMENT: " + env);

        /***************** DEVELOPMENT **********************/
        if (env.equalsIgnoreCase("dev") || env.equalsIgnoreCase("development")) {
            vidyoportal = "ranktechsolutions.platform.vidyo.io";
            vidyoportalAdminServiceWSDL = "https://ranktechsolutions.platform.vidyo.io/services/v1_1/VidyoPortalAdminService?wsdl";
            vidyoportalUserServiceWSDL = "https://ranktechsolutions.platform.vidyo.io/services/v1_1/VidyoPortalUserService?wsdl";
            vidyoportalReplayServiceWSDL = "https://ranktechsolutions.replay.platform.vidyo.io/replay/services/VidyoReplayContentManagementService?wsdl";
            recorderPrefix = "001";
            adminUserId = "rgiladmin";
            adminPwd = "Rgil@1234$";
            portalExtention = "23119";

            socketHostPublic = "https://rgiluat.ranktechsolutions.com:3001";
            socketHostPrivate = "https://rgiluat.ranktechsolutions.com:3001";

            smtp_host = "smtp.office365.com";
            from_address = "ranktechdeveloperteam@ranktechsolutions.com";
            password = "2020@Welcome";
            port = "587";

            riskInspectionExpiryTime = "24000";
            smartZoneExpiryTime = "24000"; // 1000 days

            FILE_SERVER_URL = "https://rgiluat.ranktechsolutions.com:8444/FileServer";
            FILE_DOWNLOAD_URL = "https://rgiluat.ranktechsolutions.com:8444/FileServer/fileDownload?fileName=";

            RMS_API_BASE_URL = "https://avenue.reliancegeneral.co.in/RantechIntegration";
            RMS_API_AUTH = "65000e4d22b7493dbafc859b0ec3c2c3";
            SZ_API_BASE_URL = "https://szuat.reliancegeneral.co.in:100/api/VideoCall";
            SZ_API_AUTH = "9K6S$QBE#KJ3";
        }
        /***************** UAT **********************/
        else if (env.equalsIgnoreCase("uat") || env.equalsIgnoreCase("staging")) {
            vidyoportal = "ranktechsolutions.platform.vidyo.io";
            vidyoportalAdminServiceWSDL = "https://ranktechsolutions.platform.vidyo.io/services/v1_1/VidyoPortalAdminService?wsdl";
            vidyoportalUserServiceWSDL = "https://ranktechsolutions.platform.vidyo.io/services/v1_1/VidyoPortalUserService?wsdl";
            vidyoportalReplayServiceWSDL = "https://ranktechsolutions.replay.platform.vidyo.io/replay/services/VidyoReplayContentManagementService?wsdl";
            recorderPrefix = "001";
            adminUserId = "rgiladmin";
            adminPwd = "Rgil@1234$";
            portalExtention = "23119";

            socketHostPublic = "https://rgiluat.ranktechsolutions.com:3001";
            socketHostPrivate = "https://rgiluat.ranktechsolutions.com:3001";

            smtp_host = "smtp.office365.com";
            from_address = "ranktechdeveloperteam@ranktechsolutions.com";
            password = "2020@Welcome";
            port = "587";

            riskInspectionExpiryTime = "24000";
            smartZoneExpiryTime = "24000"; // 1000 days

            FILE_SERVER_URL = "https://rgiluat.ranktechsolutions.com:8444/FileServer";
            FILE_DOWNLOAD_URL = "https://rgiluat.ranktechsolutions.com:8444/FileServer/fileDownload?fileName=";

            RMS_API_BASE_URL = "https://avenue.reliancegeneral.co.in/RantechIntegration";
            RMS_API_AUTH = "65000e4d22b7493dbafc859b0ec3c2c3";
            SZ_API_BASE_URL = "https://szuat.reliancegeneral.co.in:100/api/VideoCall";
            SZ_API_AUTH = "9K6S$QBE#KJ3";

        }
        /***************** PRODUCTION **********************/
        else if (env.equalsIgnoreCase("prod") || env.equalsIgnoreCase("production")) {
            vidyoportal = "rgil.platform.vidyo.io";
            vidyoportalAdminServiceWSDL = "https://rgil.platform.vidyo.io/services/v1_1/VidyoPortalAdminService?wsdl";
            vidyoportalUserServiceWSDL = "https://rgil.platform.vidyo.io/services/v1_1/VidyoPortalUserService?wsdl";
            vidyoportalReplayServiceWSDL = "https://recording.rgic.in/replay/services/VidyoReplayContentManagementService?wsdl";
            recorderPrefix = "01";
            adminUserId = "rgiladmin";
            adminPwd = "Rgil@1234$";
            portalExtention = "23190";

            socketHostPublic = "https://interaction.rgic.in:3000";
            socketHostPrivate = "http://10.190.0.17:3000";

            smtp_host = "smtp.gmail.com";
            from_address = "rankconsultancy.developerteam@gmail.com";
            password = "rankconsultancy";

            riskInspectionExpiryTime = "72";
            smartZoneExpiryTime = "24";

            FILE_SERVER_URL = "http://10.160.0.15:8010/FileServer";
            FILE_DOWNLOAD_URL = "https://interaction.rgic.in/FileServer/fileDownload?fileName=";

            RMS_API_BASE_URL = "https://Avenue.reliancegeneral.co.in/RantechIntegrationProd";
            RMS_API_AUTH = "65000e4d22b7493dbafc859b0ec3c2c3";
            SZ_API_BASE_URL = "https://smartzone.reliancegeneral.co.in/api/VideoCall";
            SZ_API_AUTH = "9K6S$QBE#KJ3";

        }

        /**********************************************/

        superadminUserId = "superadmin";
        superadminPwd = "123";

        dockerUrl = "";
        logger.info("dockerUrl=====" + dockerUrl);
        agentLoginMaxLimit = "25";
        scheduleTime = "2";
        callCountCheck = "yes";
        callTimeOutMiliSec = "30000";
        additionalScheduleStatus = "Rejected by RM@Rejected by BM@Rejected by Customer@Rejected by Agent@Customer didn\\u0092t join@BM didn\\u0092t join@RM didn\\u0092t join@Agent didn\\u0092t join@Network issue at RM@Network issue at BM@Network issue at Customer@Network issue at Agent";

        vidyoIOKey = "9afaefa12ceb445bab28610ee4e9d139";
        vidyoIOAppID = "6623e7.vidyo.io";
        vidyoIOUserName = "ram";

        ocrUrl = "http://36.255.3.107:5000/";

        szGetDetails = "";
        szGetUser = "";
        szSendData = "";

        FILE_CONVERT_URL = FILE_SERVER_URL + "/rest/file/videoFileConvert";
        FILE_UPLOAD_URL = FILE_SERVER_URL + "/rest/file/multipleFileUpload";
        FILE_DOWNLOAD_ALL_URL = FILE_SERVER_URL + "/fileDownloadAll";

        forwordProxyHost = "10.160.0.14";
        forwordProxyPort = "8899";

        logger.info("Property set was succesful............................");
    }

}
