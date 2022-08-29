package com.rank.rgil.response;

import java.io.Serializable;
import java.util.List;

import org.json.JSONArray;
//import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sukanta
 * @since 05-Dec-2018
 * @Purpose the following Class is used for storing Agent login information
 */
@JsonInclude(Include.NON_NULL)
public class FileHandleDto implements Serializable {

	@JsonIgnore
	private static final long serialVersionUID = -3598925337441864675L;
	
		private JSONArray conferenceUsersDtoList;
	    private MultipartFile  file;
	    private String fileName;
	    private Long empId;
	    private String custId;
	    private Long callId;
	    private String fileSendByType;
	    private String documentTitle;
	    private String participantType;
	    private List<String> filePath;
	    private String filePaths;
		/**
		 * @return the conferenceUsersDtoList
		 */
		public org.json.JSONArray getConferenceUsersDtoList() {
			return conferenceUsersDtoList;
		}
		/**
		 * @param conferenceUsersDtoList the conferenceUsersDtoList to set
		 */
		public void setConferenceUsersDtoList(org.json.JSONArray conferenceUsersDtoList) {
			this.conferenceUsersDtoList = conferenceUsersDtoList;
		}
		/**
		 * @return the file
		 */
		public MultipartFile getFile() {
			return file;
		}
		/**
		 * @param file the file to set
		 */
		public void setFile(MultipartFile file) {
			this.file = file;
		}
		/**
		 * @return the fileName
		 */
		public String getFileName() {
			return fileName;
		}
		/**
		 * @param fileName the fileName to set
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		/**
		 * @return the empId
		 */
		public Long getEmpId() {
			return empId;
		}
		/**
		 * @param empId the empId to set
		 */
		public void setEmpId(Long empId) {
			this.empId = empId;
		}
		/**
		 * @return the custId
		 */
		public String getCustId() {
			return custId;
		}
		/**
		 * @param custId the custId to set
		 */
		public void setCustId(String custId) {
			this.custId = custId;
		}
		/**
		 * @return the fileSendByType
		 */
		public String getFileSendByType() {
			return fileSendByType;
		}
		/**
		 * @param fileSendByType the fileSendByType to set
		 */
		public void setFileSendByType(String fileSendByType) {
			this.fileSendByType = fileSendByType;
		}
		/**
		 * @return the documentTitle
		 */
		public String getDocumentTitle() {
			return documentTitle;
		}
		/**
		 * @param documentTitle the documentTitle to set
		 */
		public void setDocumentTitle(String documentTitle) {
			this.documentTitle = documentTitle;
		}
		/**
		 * @return the participantType
		 */
		public String getParticipantType() {
			return participantType;
		}
		/**
		 * @param participantType the participantType to set
		 */
		public void setParticipantType(String participantType) {
			this.participantType = participantType;
		}
		/**
		 * @return the filePath
		 */
		public List<String> getFilePath() {
			return filePath;
		}
		/**
		 * @param filePath the filePath to set
		 */
		public void setFilePath(List<String> filePath) {
			this.filePath = filePath;
		}
		/**
		 * @return the callId
		 */
		public Long getCallId() {
			return callId;
		}
		/**
		 * @param callId the callId to set
		 */
		public void setCallId(Long callId) {
			this.callId = callId;
		}
		/**
		 * @return the filePaths
		 */
		public String getFilePaths() {
			return filePaths;
		}
		/**
		 * @param filePaths the filePaths to set
		 */
		public void setFilePaths(String filePaths) {
			this.filePaths = filePaths;
		}
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "FileHandleDto [conferenceUsersDtoList=" + conferenceUsersDtoList + ", fileName=" + fileName
					+ ", empId=" + empId + ", custId=" + custId + ", callMstId=" + callId + ", fileSendByType="
					+ fileSendByType + ", documentTitle=" + documentTitle + ", participantType=" + participantType
					+ ", filePath=" + filePath + "]";
		}


}
