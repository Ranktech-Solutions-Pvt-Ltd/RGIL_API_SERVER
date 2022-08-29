/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rank.rgil.entities; 

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "module_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModuleMst.findAll", query = "SELECT c FROM ModuleMst c")})
public class ModuleMst implements Serializable {


    private static final long serialVersionUID = 1L;
	
	private Long id;

    private String name;

    private String phoneNo;

    private String email;

    private boolean activeFlg;

    private boolean deleteFlg;
    
    private String promoLink;
    
    private Collection<EmployeeMst> employeeMstCollection;


    

    @Id
    @SequenceGenerator(name = "moduleMstSeq", sequenceName = "MODULE_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "moduleMstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic(optional = false)
    @Column(name = "phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }   

    @Column(name = "promo_link")
    public String getPromoLink() {
        return promoLink;
    }

    public void setPromoLink(String promoLink) {
        this.promoLink = promoLink;
    }
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduleId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<EmployeeMst> getEmployeeMstCollection() {
        return employeeMstCollection;
    }

    public void setEmployeeMstCollection(Collection<EmployeeMst> employeeMstCollection) {
        this.employeeMstCollection = employeeMstCollection;
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
        if (!(object instanceof ModuleMst)) {
            return false;
        }
        ModuleMst other = (ModuleMst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rank.rgil.entities.ModuleMst[ id=" + id + " ]";
    }

    public ModuleMst() {
    }

@Column(name = "active_flg")
    public boolean getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(boolean activeFlg) {
        this.activeFlg = activeFlg;
    }

@Column(name = "delete_flg")
    public boolean getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }




}
