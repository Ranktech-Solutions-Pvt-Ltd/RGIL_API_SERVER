/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rank.rgil.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Vivekananda
 */
@Entity
@Table(name = "customer_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerDtl.findAll", query = "SELECT c FROM CustomerDtl c")})
public class CustomerDtl implements Serializable {
    private String customerType;

    private static final long serialVersionUID = 1L;

    private Long id;
    private String fullName;
    private Date dob;
    private String nationality;
    private String gender;
    private String maritailStatus;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation

    private String email;

    private String phoneNo;

    private String address;

    private String occupation;

    private String salary;

    private String education;
    private String customerSign;
    private String customerSignCord;
    private String customerImage;
    private String utilityBill;
    private String nationalId;

    public CustomerDtl() {
    }

    public CustomerDtl(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "customerDtlIdSeq", sequenceName = "customer_dtl_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "customerDtlIdSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "maritail_status")
    public String getMaritailStatus() {
        return maritailStatus;
    }

    public void setMaritailStatus(String maritailStatus) {
        this.maritailStatus = maritailStatus;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "occupation")
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Column(name = "salary")
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Column(name = "customer_sign")
    public String getCustomerSign() {
        return customerSign;
    }

    public void setCustomerSign(String customerSign) {
        this.customerSign = customerSign;
    }

    @Column(name = "customer_sign_cord")
    public String getCustomerSignCord() {
        return customerSignCord;
    }

    public void setCustomerSignCord(String customerSignCord) {
        this.customerSignCord = customerSignCord;
    }

    @Column(name = "customer_image", length = 250)
    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    @Column(name = "utility_bill", length = 50)
    public String getUtilityBill() {
        return utilityBill;
    }

    public void setUtilityBill(String utilityBill) {
        this.utilityBill = utilityBill;
    }

    @Column(name = "national_id", length = 50)
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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
        if (!(object instanceof CustomerDtl)) {
            return false;
        }
        CustomerDtl other = (CustomerDtl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rank.rgil.entities.CustomerDtl[ id=" + id + " ]";
    }

@Column(name = "customer_type")
    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

}
