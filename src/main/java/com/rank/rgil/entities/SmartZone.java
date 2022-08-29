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
@Table(name = "smart_zone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SmartZone.findAll", query = "SELECT s FROM SmartZone s"),
    @NamedQuery(name = "SmartZone.findById", query = "SELECT s FROM SmartZone s WHERE s.id = :id"),
    @NamedQuery(name = "SmartZone.findBySzReferenceNo", query = "SELECT s FROM SmartZone s WHERE s.szReferenceNo = :szReferenceNo"),
    @NamedQuery(name = "SmartZone.findByUserName", query = "SELECT s FROM SmartZone s WHERE s.userName = :userName"),
    @NamedQuery(name = "SmartZone.findByAgentCode", query = "SELECT s FROM SmartZone s WHERE s.agentCode = :agentCode"),
    @NamedQuery(name = "SmartZone.findByAgentName", query = "SELECT s FROM SmartZone s WHERE s.agentName = :agentName"),
    @NamedQuery(name = "SmartZone.findByAgentMobileNo", query = "SELECT s FROM SmartZone s WHERE s.agentMobileNo = :agentMobileNo"),
    @NamedQuery(name = "SmartZone.findByAgentEmailId", query = "SELECT s FROM SmartZone s WHERE s.agentEmailId = :agentEmailId"),
    @NamedQuery(name = "SmartZone.findByStrPurpose", query = "SELECT s FROM SmartZone s WHERE s.strPurpose = :strPurpose"),
    @NamedQuery(name = "SmartZone.findByCustomerName", query = "SELECT s FROM SmartZone s WHERE s.customerName = :customerName"),
    @NamedQuery(name = "SmartZone.findByCustomerEmail", query = "SELECT s FROM SmartZone s WHERE s.customerEmail = :customerEmail"),
    @NamedQuery(name = "SmartZone.findByCustomerMobile", query = "SELECT s FROM SmartZone s WHERE s.customerMobile = :customerMobile"),
    @NamedQuery(name = "SmartZone.findByStartDateTime", query = "SELECT s FROM SmartZone s WHERE s.startDateTime = :startDateTime"),
    @NamedQuery(name = "SmartZone.findByEndDateTime", query = "SELECT s FROM SmartZone s WHERE s.endDateTime = :endDateTime")})
public class SmartZone implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "sz_reference_no")
    private String szReferenceNo;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "agent_code")
    private String agentCode;
    @Column(name = "agent_name")
    private String agentName;
    @Column(name = "agent_mobile_no")
    private String agentMobileNo;
    @Column(name = "agent_email_id")
    private String agentEmailId;
    @Column(name = "str_purpose")
    private String strPurpose;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "customer_mobile")
    private String customerMobile;
    @Column(name = "start_date_time")
    private String startDateTime;
    @Column(name = "end_date_time")
    private String endDateTime;

    public SmartZone() {
    }

    public SmartZone(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSzReferenceNo() {
        return szReferenceNo;
    }

    public void setSzReferenceNo(String szReferenceNo) {
        this.szReferenceNo = szReferenceNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentMobileNo() {
        return agentMobileNo;
    }

    public void setAgentMobileNo(String agentMobileNo) {
        this.agentMobileNo = agentMobileNo;
    }

    public String getAgentEmailId() {
        return agentEmailId;
    }

    public void setAgentEmailId(String agentEmailId) {
        this.agentEmailId = agentEmailId;
    }

    public String getStrPurpose() {
        return strPurpose;
    }

    public void setStrPurpose(String strPurpose) {
        this.strPurpose = strPurpose;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
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
        if (!(object instanceof SmartZone)) {
            return false;
        }
        SmartZone other = (SmartZone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rank.rgil.entities.SmartZone[ id=" + id + " ]";
    }
    
}
