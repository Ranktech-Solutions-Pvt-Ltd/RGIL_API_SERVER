package com.rank.rgil.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "kyc_file_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KYCFileMst.findAll", query = "SELECT c FROM KYCFileMst c")})
public class KYCFileMst implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String customerFilePath;
    private Date createdOn;
    private Date updatedOn;
    private CustomerMst customerId;
    private Long agentMstId;
    private Long callId;

    public KYCFileMst() {
    }

    public KYCFileMst(Long id) {
        this.id = id;
    }

    public KYCFileMst(Long id, Date createdOn, Date updatedOn) {
        this.id = id;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    @Id
    @SequenceGenerator(name = "KYCFilemstSeq", sequenceName = "KYC_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "KYCFilemstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "customer_file_path")
    public String getCustomerFilePath() {
        return customerFilePath;
    }

    public void setCustomerFilePath(String customerFilePath) {
        this.customerFilePath = customerFilePath;
    }

    @Basic(optional = false)
    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Basic(optional = false)
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public CustomerMst getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerMst customerId) {
        this.customerId = customerId;
    }

    @Basic(optional = true)
    @Column(name = "agent_id")
    public Long getAgentMstId() {
        return agentMstId;
    }

    public void setAgentMstId(Long agentMstId) {
        this.agentMstId = agentMstId;
    }

    @Basic(optional = true)
    @Column(name = "call_id")
    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
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
        if (!(object instanceof KYCFileMst)) {
            return false;
        }
        KYCFileMst other = (KYCFileMst) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.rgil.entities.CustomerFileMst[ id=" + id + " ]";
    }

}
