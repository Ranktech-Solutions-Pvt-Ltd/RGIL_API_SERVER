package com.rank.rgil.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "call_records")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallRecords.findAll", query = "SELECT c FROM CallRecords c")})
public class CallRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String customerId;
    private Timestamp recordTime;
    private String externalPlaybackLink;
    private String conferenceId;
    private String recorderId;
    private Timestamp recorderStartTime;
    private Timestamp recorderEndTime;
    private CallMst callId;
    private EmployeeMst empId;
    private String chatText;
    private String roomName;
    private Boolean isRecordingStop;

    public CallRecords() {
    }

    public CallRecords(Long id) {
        this.id = id;
    }

    public CallRecords(Long id, String customerId, Timestamp recordTime, Timestamp recorderStartTime, Timestamp recorderEndTime) {
        this.id = id;
        this.customerId = customerId;
        this.recordTime = recordTime;
        this.recorderStartTime = recorderStartTime;
        this.recorderEndTime = recorderEndTime;
    }

      @Id
    @SequenceGenerator(name = "callrecordsSeq", sequenceName = "CALL_RECORDS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "callrecordsSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //@Basic(optional = false)
    @Column(name = "customer_id")
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    //@Basic(optional = true)
    @Column(name = "record_time")
    public Timestamp getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }

    @Column(name = "external_playback_link")
    public String getExternalPlaybackLink() {
        return externalPlaybackLink;
    }

    public void setExternalPlaybackLink(String externalPlaybackLink) {
        this.externalPlaybackLink = externalPlaybackLink;
    }

    @Column(name = "conference_id")
    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Column(name = "recorder_id")
    public String getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(String recorderId) {
        this.recorderId = recorderId;
    }

    //@Basic(optional = true)
    @Column(name = "recorder_start_time")
    public Timestamp getRecorderStartTime() {
        return recorderStartTime;
    }

    public void setRecorderStartTime(Timestamp recorderStartTime) {
        this.recorderStartTime = recorderStartTime;
    }

    //@Basic(optional = true)
    @Column(name = "recorder_end_time")
     public Timestamp getRecorderEndTime() {
        return recorderEndTime;
    }

    public void setRecorderEndTime(Timestamp recorderEndTime) {
        this.recorderEndTime = recorderEndTime;
    }

    @JoinColumn(name = "call_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public CallMst getCallId() {
        return callId;
    }

    public void setCallId(CallMst callId) {
        this.callId = callId;
    }

    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EmployeeMst getEmpId() {
        return empId;
    }

    public void setEmpId(EmployeeMst empId) {
        this.empId = empId;
    }

    @Column(name = "chat_text")
    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    @Column(name = "room_name")
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
     @Column(name = "is_recording_stop")
    public Boolean getIsRecordingStop() {
        return isRecordingStop;
    }

    public void setIsRecordingStop(Boolean isRecordingStop) {
        this.isRecordingStop = isRecordingStop;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallRecords)) {
            return false;
        }
        CallRecords other = (CallRecords) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.rgil.entities.CallRecords[ id=" + id + " ]";
    }

}
