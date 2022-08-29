/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rank.rgil.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "call_notes_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallNotesDtl.findAll", query = "SELECT c FROM CallNotesDtl c"),
    @NamedQuery(name = "CallNotesDtl.findById", query = "SELECT c FROM CallNotesDtl c WHERE c.id = :id"),
    @NamedQuery(name = "CallNotesDtl.findByCallId", query = "SELECT c FROM CallNotesDtl c WHERE c.callId = :callId"),
    @NamedQuery(name = "CallNotesDtl.findByAboutThePlant", query = "SELECT c FROM CallNotesDtl c WHERE c.aboutThePlant = :aboutThePlant"),
    @NamedQuery(name = "CallNotesDtl.findByConstructionDetails", query = "SELECT c FROM CallNotesDtl c WHERE c.constructionDetails = :constructionDetails"),
    @NamedQuery(name = "CallNotesDtl.findByProcessDetails", query = "SELECT c FROM CallNotesDtl c WHERE c.processDetails = :processDetails"),
    @NamedQuery(name = "CallNotesDtl.findByStorageFacilities", query = "SELECT c FROM CallNotesDtl c WHERE c.storageFacilities = :storageFacilities"),
    @NamedQuery(name = "CallNotesDtl.findBySecurity", query = "SELECT c FROM CallNotesDtl c WHERE c.security = :security"),
    @NamedQuery(name = "CallNotesDtl.findByElectricalInstallation", query = "SELECT c FROM CallNotesDtl c WHERE c.electricalInstallation = :electricalInstallation"),
    @NamedQuery(name = "CallNotesDtl.findByDetectionSystem", query = "SELECT c FROM CallNotesDtl c WHERE c.detectionSystem = :detectionSystem"),
    @NamedQuery(name = "CallNotesDtl.findByUtilities", query = "SELECT c FROM CallNotesDtl c WHERE c.utilities = :utilities"),
    @NamedQuery(name = "CallNotesDtl.findByObservations", query = "SELECT c FROM CallNotesDtl c WHERE c.observations = :observations"),
    @NamedQuery(name = "CallNotesDtl.findByOthers", query = "SELECT c FROM CallNotesDtl c WHERE c.others = :others"),
    @NamedQuery(name = "CallNotesDtl.findByTypeOfOccupancyForFireLob", query = "SELECT c FROM CallNotesDtl c WHERE c.typeOfOccupancyForFireLob = :typeOfOccupancyForFireLob")})
public class CallNotesDtl implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Long callId;
    private String aboutThePlant;
    private String constructionDetails;
    private String processDetails;
    private String storageFacilities;
    private String security;
    private String electricalInstallation;
    private String detectionSystem;
    private String utilities;
    private String observations;
    private String others;
    private String typeOfOccupancyForFireLob;

    public CallNotesDtl() {
    }

    public CallNotesDtl(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

@Column(name = "call_id")
    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

@Column(name = "about_the_plant")
    public String getAboutThePlant() {
        return aboutThePlant;
    }

    public void setAboutThePlant(String aboutThePlant) {
        this.aboutThePlant = aboutThePlant;
    }

@Column(name = "construction_details")
    public String getConstructionDetails() {
        return constructionDetails;
    }

    public void setConstructionDetails(String constructionDetails) {
        this.constructionDetails = constructionDetails;
    }

@Column(name = "process_details")
    public String getProcessDetails() {
        return processDetails;
    }

    public void setProcessDetails(String processDetails) {
        this.processDetails = processDetails;
    }

@Column(name = "storage_facilities")
    public String getStorageFacilities() {
        return storageFacilities;
    }

    public void setStorageFacilities(String storageFacilities) {
        this.storageFacilities = storageFacilities;
    }

@Column(name = "security")
    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

@Column(name = "electrical_installation")
    public String getElectricalInstallation() {
        return electricalInstallation;
    }

    public void setElectricalInstallation(String electricalInstallation) {
        this.electricalInstallation = electricalInstallation;
    }

@Column(name = "detection_system")
    public String getDetectionSystem() {
        return detectionSystem;
    }

    public void setDetectionSystem(String detectionSystem) {
        this.detectionSystem = detectionSystem;
    }

@Column(name = "utilities")
    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

@Column(name = "observations")
    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

@Column(name = "others")
    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

@Column(name = "type_of_occupancy_for_fire_lob")
    public String getTypeOfOccupancyForFireLob() {
        return typeOfOccupancyForFireLob;
    }

    public void setTypeOfOccupancyForFireLob(String typeOfOccupancyForFireLob) {
        this.typeOfOccupancyForFireLob = typeOfOccupancyForFireLob;
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
        if (!(object instanceof CallNotesDtl)) {
            return false;
        }
        CallNotesDtl other = (CallNotesDtl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rank.rgil.entities.CallNotesDtl[ id=" + id + ", occupency="+typeOfOccupancyForFireLob+", aboutThePlan="+aboutThePlant+" ]";
    }
    
}
