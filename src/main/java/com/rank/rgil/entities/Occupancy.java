/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.rank.rgil.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "occupancy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Occupancy.findAll", query = "SELECT o FROM Occupancy o"),
    @NamedQuery(name = "Occupancy.findBySlNo", query = "SELECT o FROM Occupancy o WHERE o.slNo = :slNo"),
    @NamedQuery(name = "Occupancy.findByName", query = "SELECT o FROM Occupancy o WHERE o.name = :name"),
    @NamedQuery(name = "Occupancy.findByCode", query = "SELECT o FROM Occupancy o WHERE o.code = :code"),
    @NamedQuery(name = "Occupancy.findByType", query = "SELECT o FROM Occupancy o WHERE o.type = :type"),
    @NamedQuery(name = "Occupancy.findByOccupancy", query = "SELECT o FROM Occupancy o WHERE o.occupancy = :occupancy"),
    @NamedQuery(name = "Occupancy.findByRiskOccupancy", query = "SELECT o FROM Occupancy o WHERE o.riskOccupancy = :riskOccupancy"),
    @NamedQuery(name = "Occupancy.findByRiskCode", query = "SELECT o FROM Occupancy o WHERE o.riskCode = :riskCode"),
    @NamedQuery(name = "Occupancy.findByRateCode", query = "SELECT o FROM Occupancy o WHERE o.rateCode = :rateCode"),
    @NamedQuery(name = "Occupancy.findByRate", query = "SELECT o FROM Occupancy o WHERE o.rate = :rate"),
    @NamedQuery(name = "Occupancy.findByHazard", query = "SELECT o FROM Occupancy o WHERE o.hazard = :hazard"),
    @NamedQuery(name = "Occupancy.findByRiskGroup", query = "SELECT o FROM Occupancy o WHERE o.riskGroup = :riskGroup"),
    @NamedQuery(name = "Occupancy.findByOtherRate", query = "SELECT o FROM Occupancy o WHERE o.otherRate = :otherRate"),
    @NamedQuery(name = "Occupancy.findByTabRowIndex", query = "SELECT o FROM Occupancy o WHERE o.tabRowIndex = :tabRowIndex"),
    @NamedQuery(name = "Occupancy.findByDatStartDate", query = "SELECT o FROM Occupancy o WHERE o.datStartDate = :datStartDate"),
    @NamedQuery(name = "Occupancy.findByDatEndDate", query = "SELECT o FROM Occupancy o WHERE o.datEndDate = :datEndDate"),
    @NamedQuery(name = "Occupancy.findByStfiRate", query = "SELECT o FROM Occupancy o WHERE o.stfiRate = :stfiRate"),
    @NamedQuery(name = "Occupancy.findByHazardGrade", query = "SELECT o FROM Occupancy o WHERE o.hazardGrade = :hazardGrade"),
    @NamedQuery(name = "Occupancy.findByBldGrade", query = "SELECT o FROM Occupancy o WHERE o.bldGrade = :bldGrade"),
    @NamedQuery(name = "Occupancy.findByContentsRate", query = "SELECT o FROM Occupancy o WHERE o.contentsRate = :contentsRate"),
    @NamedQuery(name = "Occupancy.findByNumCumBuildingRate", query = "SELECT o FROM Occupancy o WHERE o.numCumBuildingRate = :numCumBuildingRate"),
    @NamedQuery(name = "Occupancy.findByNumCumContentRate", query = "SELECT o FROM Occupancy o WHERE o.numCumContentRate = :numCumContentRate"),
    @NamedQuery(name = "Occupancy.findByTxtIibCode", query = "SELECT o FROM Occupancy o WHERE o.txtIibCode = :txtIibCode"),
    @NamedQuery(name = "Occupancy.findByNumBurRate", query = "SELECT o FROM Occupancy o WHERE o.numBurRate = :numBurRate"),
    @NamedQuery(name = "Occupancy.findByNumSmBsmBurnRate", query = "SELECT o FROM Occupancy o WHERE o.numSmBsmBurnRate = :numSmBsmBurnRate"),
    @NamedQuery(name = "Occupancy.findByNumUmScuiBurnRate", query = "SELECT o FROM Occupancy o WHERE o.numUmScuiBurnRate = :numUmScuiBurnRate"),
    @NamedQuery(name = "Occupancy.findByNumCoBurnRate", query = "SELECT o FROM Occupancy o WHERE o.numCoBurnRate = :numCoBurnRate"),
    @NamedQuery(name = "Occupancy.findByNumFlexaRate", query = "SELECT o FROM Occupancy o WHERE o.numFlexaRate = :numFlexaRate"),
    @NamedQuery(name = "Occupancy.findByTxtOccupancySubCategory", query = "SELECT o FROM Occupancy o WHERE o.txtOccupancySubCategory = :txtOccupancySubCategory"),
    @NamedQuery(name = "Occupancy.findByNumDiscountPer", query = "SELECT o FROM Occupancy o WHERE o.numDiscountPer = :numDiscountPer"),
    @NamedQuery(name = "Occupancy.findByYnGicAction", query = "SELECT o FROM Occupancy o WHERE o.ynGicAction = :ynGicAction")})
public class Occupancy implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long slNo;
    private String name;
    private String code;
    private String type;
    private String occupancy;
    private String riskOccupancy;
    private BigInteger riskCode;
    private BigInteger rateCode;
    private String rate;
    private String hazard;
    private String riskGroup;
    private String otherRate;
    private BigInteger tabRowIndex;
    private Date datStartDate;
    private Date datEndDate;
    private String stfiRate;
    private BigInteger hazardGrade;
    private String bldGrade;
    private String contentsRate;
    private String numCumBuildingRate;
    private String numCumContentRate;
    private String txtIibCode;
    private String numBurRate;
    private String numSmBsmBurnRate;
    private String numUmScuiBurnRate;
    private String numCoBurnRate;
    private String numFlexaRate;
    private String txtOccupancySubCategory;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double numDiscountPer;
    private String ynGicAction;

    public Occupancy() {
    }

    public Occupancy(Long slNo) {
        this.slNo = slNo;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "sl_no")
    public Long getSlNo() {
        return slNo;
    }

    public void setSlNo(Long slNo) {
        this.slNo = slNo;
    }

@Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

@Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

@Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

@Column(name = "occupancy")
    public String getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

@Column(name = "risk_occupancy")
    public String getRiskOccupancy() {
        return riskOccupancy;
    }

    public void setRiskOccupancy(String riskOccupancy) {
        this.riskOccupancy = riskOccupancy;
    }

@Column(name = "risk_code")
    public BigInteger getRiskCode() {
        return riskCode;
    }

    public void setRiskCode(BigInteger riskCode) {
        this.riskCode = riskCode;
    }

@Column(name = "rate_code")
    public BigInteger getRateCode() {
        return rateCode;
    }

    public void setRateCode(BigInteger rateCode) {
        this.rateCode = rateCode;
    }

@Column(name = "rate")
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

@Column(name = "hazard")
    public String getHazard() {
        return hazard;
    }

    public void setHazard(String hazard) {
        this.hazard = hazard;
    }

@Column(name = "risk_group")
    public String getRiskGroup() {
        return riskGroup;
    }

    public void setRiskGroup(String riskGroup) {
        this.riskGroup = riskGroup;
    }

@Column(name = "other_rate")
    public String getOtherRate() {
        return otherRate;
    }

    public void setOtherRate(String otherRate) {
        this.otherRate = otherRate;
    }

@Column(name = "tab_row_index")
    public BigInteger getTabRowIndex() {
        return tabRowIndex;
    }

    public void setTabRowIndex(BigInteger tabRowIndex) {
        this.tabRowIndex = tabRowIndex;
    }

@Column(name = "dat_start_date")
    @Temporal(TemporalType.DATE)
    public Date getDatStartDate() {
        return datStartDate;
    }

    public void setDatStartDate(Date datStartDate) {
        this.datStartDate = datStartDate;
    }

@Column(name = "dat_end_date")
    @Temporal(TemporalType.DATE)
    public Date getDatEndDate() {
        return datEndDate;
    }

    public void setDatEndDate(Date datEndDate) {
        this.datEndDate = datEndDate;
    }

@Column(name = "stfi_rate")
    public String getStfiRate() {
        return stfiRate;
    }

    public void setStfiRate(String stfiRate) {
        this.stfiRate = stfiRate;
    }

@Column(name = "hazard_grade")
    public BigInteger getHazardGrade() {
        return hazardGrade;
    }

    public void setHazardGrade(BigInteger hazardGrade) {
        this.hazardGrade = hazardGrade;
    }

@Column(name = "bld_grade")
    public String getBldGrade() {
        return bldGrade;
    }

    public void setBldGrade(String bldGrade) {
        this.bldGrade = bldGrade;
    }

@Column(name = "contents_rate")
    public String getContentsRate() {
        return contentsRate;
    }

    public void setContentsRate(String contentsRate) {
        this.contentsRate = contentsRate;
    }

@Column(name = "num_cum_building_rate")
    public String getNumCumBuildingRate() {
        return numCumBuildingRate;
    }

    public void setNumCumBuildingRate(String numCumBuildingRate) {
        this.numCumBuildingRate = numCumBuildingRate;
    }

@Column(name = "num_cum_content_rate")
    public String getNumCumContentRate() {
        return numCumContentRate;
    }

    public void setNumCumContentRate(String numCumContentRate) {
        this.numCumContentRate = numCumContentRate;
    }

@Column(name = "txt_iib_code")
    public String getTxtIibCode() {
        return txtIibCode;
    }

    public void setTxtIibCode(String txtIibCode) {
        this.txtIibCode = txtIibCode;
    }

@Column(name = "num_bur_rate")
    public String getNumBurRate() {
        return numBurRate;
    }

    public void setNumBurRate(String numBurRate) {
        this.numBurRate = numBurRate;
    }

@Column(name = "num_sm_bsm_burn_rate")
    public String getNumSmBsmBurnRate() {
        return numSmBsmBurnRate;
    }

    public void setNumSmBsmBurnRate(String numSmBsmBurnRate) {
        this.numSmBsmBurnRate = numSmBsmBurnRate;
    }

@Column(name = "num_um_scui_burn_rate")
    public String getNumUmScuiBurnRate() {
        return numUmScuiBurnRate;
    }

    public void setNumUmScuiBurnRate(String numUmScuiBurnRate) {
        this.numUmScuiBurnRate = numUmScuiBurnRate;
    }

@Column(name = "num_co_burn_rate")
    public String getNumCoBurnRate() {
        return numCoBurnRate;
    }

    public void setNumCoBurnRate(String numCoBurnRate) {
        this.numCoBurnRate = numCoBurnRate;
    }

@Column(name = "num_flexa_rate")
    public String getNumFlexaRate() {
        return numFlexaRate;
    }

    public void setNumFlexaRate(String numFlexaRate) {
        this.numFlexaRate = numFlexaRate;
    }

@Column(name = "txt_occupancy_sub_category")
    public String getTxtOccupancySubCategory() {
        return txtOccupancySubCategory;
    }

    public void setTxtOccupancySubCategory(String txtOccupancySubCategory) {
        this.txtOccupancySubCategory = txtOccupancySubCategory;
    }

@Column(name = "num_discount_per")
    public Double getNumDiscountPer() {
        return numDiscountPer;
    }

    public void setNumDiscountPer(Double numDiscountPer) {
        this.numDiscountPer = numDiscountPer;
    }

@Column(name = "yn_gic_action")
    public String getYnGicAction() {
        return ynGicAction;
    }

    public void setYnGicAction(String ynGicAction) {
        this.ynGicAction = ynGicAction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slNo != null ? slNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Occupancy)) {
            return false;
        }
        Occupancy other = (Occupancy) object;
        if ((this.slNo == null && other.slNo != null) || (this.slNo != null && !this.slNo.equals(other.slNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rank.rgil.entities.Occupancy[ slNo=" + slNo + " ]";
    }
    
}
