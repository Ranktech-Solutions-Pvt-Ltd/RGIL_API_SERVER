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
@Table(name = "risk_inspection")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RiskInspection.findAll", query = "SELECT r FROM RiskInspection r"),
    @NamedQuery(name = "RiskInspection.findById", query = "SELECT r FROM RiskInspection r WHERE r.id = :id"),
    @NamedQuery(name = "RiskInspection.findBySrfNo", query = "SELECT r FROM RiskInspection r WHERE r.srfNo = :srfNo"),
    @NamedQuery(name = "RiskInspection.findByRequestDate", query = "SELECT r FROM RiskInspection r WHERE r.requestDate = :requestDate"),
    @NamedQuery(name = "RiskInspection.findByRequestSource", query = "SELECT r FROM RiskInspection r WHERE r.requestSource = :requestSource"),
    @NamedQuery(name = "RiskInspection.findBySalesManagerName", query = "SELECT r FROM RiskInspection r WHERE r.salesManagerName = :salesManagerName"),
    @NamedQuery(name = "RiskInspection.findBySalesManagerEmployeeId", query = "SELECT r FROM RiskInspection r WHERE r.salesManagerEmployeeId = :salesManagerEmployeeId"),
    @NamedQuery(name = "RiskInspection.findBySalesManagerEmail", query = "SELECT r FROM RiskInspection r WHERE r.salesManagerEmail = :salesManagerEmail"),
    @NamedQuery(name = "RiskInspection.findBySalesManagerMobile", query = "SELECT r FROM RiskInspection r WHERE r.salesManagerMobile = :salesManagerMobile"),
    @NamedQuery(name = "RiskInspection.findByLocation", query = "SELECT r FROM RiskInspection r WHERE r.location = :location"),
    @NamedQuery(name = "RiskInspection.findByIntermediaryId", query = "SELECT r FROM RiskInspection r WHERE r.intermediaryId = :intermediaryId"),
    @NamedQuery(name = "RiskInspection.findByIntermediaryName", query = "SELECT r FROM RiskInspection r WHERE r.intermediaryName = :intermediaryName"),
    @NamedQuery(name = "RiskInspection.findByIntermediaryEmail", query = "SELECT r FROM RiskInspection r WHERE r.intermediaryEmail = :intermediaryEmail"),
    @NamedQuery(name = "RiskInspection.findByIntermediaryMobile", query = "SELECT r FROM RiskInspection r WHERE r.intermediaryMobile = :intermediaryMobile"),
    @NamedQuery(name = "RiskInspection.findByVerticalName", query = "SELECT r FROM RiskInspection r WHERE r.verticalName = :verticalName"),
    @NamedQuery(name = "RiskInspection.findByCompanyName", query = "SELECT r FROM RiskInspection r WHERE r.companyName = :companyName"),
    @NamedQuery(name = "RiskInspection.findByCompanyContactPersonName", query = "SELECT r FROM RiskInspection r WHERE r.companyContactPersonName = :companyContactPersonName"),
    @NamedQuery(name = "RiskInspection.findByCompanyContactNumber", query = "SELECT r FROM RiskInspection r WHERE r.companyContactNumber = :companyContactNumber"),
    @NamedQuery(name = "RiskInspection.findByClientContactNumber", query = "SELECT r FROM RiskInspection r WHERE r.clientContactNumber = :clientContactNumber"),
    @NamedQuery(name = "RiskInspection.findByCustomerEmail", query = "SELECT r FROM RiskInspection r WHERE r.customerEmail = :customerEmail"),
    @NamedQuery(name = "RiskInspection.findByInspectionSiteAddress", query = "SELECT r FROM RiskInspection r WHERE r.inspectionSiteAddress = :inspectionSiteAddress"),
    @NamedQuery(name = "RiskInspection.findByPincode", query = "SELECT r FROM RiskInspection r WHERE r.pincode = :pincode"),
    @NamedQuery(name = "RiskInspection.findByState", query = "SELECT r FROM RiskInspection r WHERE r.state = :state"),
    @NamedQuery(name = "RiskInspection.findByClientStatus", query = "SELECT r FROM RiskInspection r WHERE r.clientStatus = :clientStatus"),
    @NamedQuery(name = "RiskInspection.findBySumInsured", query = "SELECT r FROM RiskInspection r WHERE r.sumInsured = :sumInsured"),
    @NamedQuery(name = "RiskInspection.findByInspectionDate", query = "SELECT r FROM RiskInspection r WHERE r.inspectionDate = :inspectionDate"),
    @NamedQuery(name = "RiskInspection.findByInspectionTime", query = "SELECT r FROM RiskInspection r WHERE r.inspectionTime = :inspectionTime"),
    @NamedQuery(name = "RiskInspection.findByTypeOfPolicy", query = "SELECT r FROM RiskInspection r WHERE r.typeOfPolicy = :typeOfPolicy"),
    @NamedQuery(name = "RiskInspection.findByInspectionConductedBy", query = "SELECT r FROM RiskInspection r WHERE r.inspectionConductedBy = :inspectionConductedBy")})
public class RiskInspection implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String srfNo;
    private String requestDate;
    private String requestSource;
    private String salesManagerName;
    private String salesManagerEmployeeId;
    private String salesManagerEmail;
    private String salesManagerMobile;
    private String location;
    private String intermediaryId;
    private String intermediaryName;
    private String intermediaryEmail;
    private String intermediaryMobile;
    private String verticalName;
    private String companyName;
    private String companyContactPersonName;
    private String companyContactNumber;
    private String clientContactNumber;
    private String customerEmail;
    private String inspectionSiteAddress;
    private String pincode;
    private String state;
    private String clientStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private String sumInsured;
    private String inspectionDate;
    private String inspectionTime;
    private String typeOfPolicy;
    private String inspectionConductedBy;

    public RiskInspection() {
    }

    public RiskInspection(Long id) {
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

@Column(name = "srf_no")
    public String getSrfNo() {
        return srfNo;
    }

    public void setSrfNo(String srfNo) {
        this.srfNo = srfNo;
    }

@Column(name = "request_date")
    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

@Column(name = "request_source")
    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }

@Column(name = "sales_manager_name")
    public String getSalesManagerName() {
        return salesManagerName;
    }

    public void setSalesManagerName(String salesManagerName) {
        this.salesManagerName = salesManagerName;
    }

@Column(name = "sales_manager_employee_id")
    public String getSalesManagerEmployeeId() {
        return salesManagerEmployeeId;
    }

    public void setSalesManagerEmployeeId(String salesManagerEmployeeId) {
        this.salesManagerEmployeeId = salesManagerEmployeeId;
    }

@Column(name = "sales_manager_email")
    public String getSalesManagerEmail() {
        return salesManagerEmail;
    }

    public void setSalesManagerEmail(String salesManagerEmail) {
        this.salesManagerEmail = salesManagerEmail;
    }

@Column(name = "sales_manager_mobile")
    public String getSalesManagerMobile() {
        return salesManagerMobile;
    }

    public void setSalesManagerMobile(String salesManagerMobile) {
        this.salesManagerMobile = salesManagerMobile;
    }

@Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

@Column(name = "intermediary_id")
    public String getIntermediaryId() {
        return intermediaryId;
    }

    public void setIntermediaryId(String intermediaryId) {
        this.intermediaryId = intermediaryId;
    }

@Column(name = "intermediary_name")
    public String getIntermediaryName() {
        return intermediaryName;
    }

    public void setIntermediaryName(String intermediaryName) {
        this.intermediaryName = intermediaryName;
    }

@Column(name = "intermediary_email")
    public String getIntermediaryEmail() {
        return intermediaryEmail;
    }

    public void setIntermediaryEmail(String intermediaryEmail) {
        this.intermediaryEmail = intermediaryEmail;
    }

@Column(name = "intermediary_mobile")
    public String getIntermediaryMobile() {
        return intermediaryMobile;
    }

    public void setIntermediaryMobile(String intermediaryMobile) {
        this.intermediaryMobile = intermediaryMobile;
    }

@Column(name = "vertical_name")
    public String getVerticalName() {
        return verticalName;
    }

    public void setVerticalName(String verticalName) {
        this.verticalName = verticalName;
    }

@Column(name = "company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

@Column(name = "company_contact_person_name")
    public String getCompanyContactPersonName() {
        return companyContactPersonName;
    }

    public void setCompanyContactPersonName(String companyContactPersonName) {
        this.companyContactPersonName = companyContactPersonName;
    }

@Column(name = "company_contact_number")
    public String getCompanyContactNumber() {
        return companyContactNumber;
    }

    public void setCompanyContactNumber(String companyContactNumber) {
        this.companyContactNumber = companyContactNumber;
    }

@Column(name = "client_contact_number")
    public String getClientContactNumber() {
        return clientContactNumber;
    }

    public void setClientContactNumber(String clientContactNumber) {
        this.clientContactNumber = clientContactNumber;
    }

@Column(name = "customer_email")
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

@Column(name = "inspection_site_address")
    public String getInspectionSiteAddress() {
        return inspectionSiteAddress;
    }

    public void setInspectionSiteAddress(String inspectionSiteAddress) {
        this.inspectionSiteAddress = inspectionSiteAddress;
    }

@Column(name = "pincode")
    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

@Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

@Column(name = "client_status")
    public String getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
    }

@Column(name = "sum_insured")
    public String getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(String sumInsured) {
        this.sumInsured = sumInsured;
    }


@Column(name = "inspection_date")
    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

@Column(name = "inspection_time")
    public String getInspectionTime() {
        return inspectionTime;
    }

    public void setInspectionTime(String inspectionTime) {
        this.inspectionTime = inspectionTime;
    }

@Column(name = "type_of_policy")
    public String getTypeOfPolicy() {
        return typeOfPolicy;
    }

    public void setTypeOfPolicy(String typeOfPolicy) {
        this.typeOfPolicy = typeOfPolicy;
    }

@Column(name = "inspection_conducted_by")
    public String getInspectionConductedBy() {
        return inspectionConductedBy;
    }

    public void setInspectionConductedBy(String inspectionConductedBy) {
        this.inspectionConductedBy = inspectionConductedBy;
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
        if (!(object instanceof RiskInspection)) {
            return false;
        }
        RiskInspection other = (RiskInspection) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rank.rgil.entities.RiskInspection[ id=" + id + " ]";
    }
    
}
