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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "feedback_query_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeedbackQueryMst.findAll", query = "SELECT f FROM FeedbackQueryMst f")})
public class FeedbackQueryMst implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "feedbackQueryMstSeq", sequenceName = "feedback_Query_Mst_Seq", allocationSize = 1)
    @GeneratedValue(generator = "feedbackQueryMstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "feedback_query")
    private String feedbackQuery;
    @Basic(optional = false)
    @Column(name = "active_flg")
    private boolean activeFlg;
    @Basic(optional = false)
    @Column(name = "delete_flg")
    private boolean deleteFlg;
    @Basic(optional = false)
    @Column(name = "selected_flg")
    private boolean selectedFlg;

    public FeedbackQueryMst() {
    }

    public FeedbackQueryMst(Long id) {
        this.id = id;
    }

    public FeedbackQueryMst(Long id, String feedbackQuery, boolean activeFlg, boolean deleteFlg, boolean selectedFlg) {
        this.id = id;
        this.feedbackQuery = feedbackQuery;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
        this.selectedFlg = selectedFlg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedbackQuery() {
        return feedbackQuery;
    }

    public void setFeedbackQuery(String feedbackQuery) {
        this.feedbackQuery = feedbackQuery;
    }

    public boolean getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(boolean activeFlg) {
        this.activeFlg = activeFlg;
    }

    public boolean getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public boolean getSelectedFlg() {
        return selectedFlg;
    }

    public void setSelectedFlg(boolean selectedFlg) {
        this.selectedFlg = selectedFlg;
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
        if (!(object instanceof FeedbackQueryMst)) {
            return false;
        }
        FeedbackQueryMst other = (FeedbackQueryMst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rank.rgil.entities.FeedbackQueryMst[ id=" + id + " ]";
    }
    
}
