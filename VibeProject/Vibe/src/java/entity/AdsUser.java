/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "ads_user", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdsUser.findAll", query = "SELECT a FROM AdsUser a"),
    @NamedQuery(name = "AdsUser.findByAuId", query = "SELECT a FROM AdsUser a WHERE a.auId = :auId"),
    @NamedQuery(name = "AdsUser.findByAdscontent", query = "SELECT a FROM AdsUser a WHERE a.adscontent = :adscontent"),
    @NamedQuery(name = "AdsUser.findByDescription", query = "SELECT a FROM AdsUser a WHERE a.description = :description"),
    @NamedQuery(name = "AdsUser.findByLink", query = "SELECT a FROM AdsUser a WHERE a.link = :link"),
    @NamedQuery(name = "AdsUser.findByStartdate", query = "SELECT a FROM AdsUser a WHERE a.startdate = :startdate"),
    @NamedQuery(name = "AdsUser.findByEnddate", query = "SELECT a FROM AdsUser a WHERE a.enddate = :enddate"),
    @NamedQuery(name = "AdsUser.findByIsRemoved", query = "SELECT a FROM AdsUser a WHERE a.isRemoved = :isRemoved"),
    @NamedQuery(name = "AdsUser.findByIsExpried", query = "SELECT a FROM AdsUser a WHERE a.isExpried = :isExpried")})
public class AdsUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "au_id", nullable = false)
    private Integer auId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "adscontent", nullable = false, length = 1000)
    private String adscontent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "description", nullable = false, length = 1000)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "link", nullable = false, length = 1000)
    private String link;
    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "enddate", nullable = false, length = 100)
    private String enddate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_expried", nullable = false)
    private boolean isExpried;
    @JoinColumn(name = "adsid", referencedColumnName = "ads_id", nullable = false)
    @ManyToOne(optional = false)
    private Ads adsid;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User userid;

    public AdsUser() {
    }

    public AdsUser(Integer auId) {
        this.auId = auId;
    }

    public AdsUser(Integer auId, String adscontent, String description, String link, String enddate, boolean isRemoved, boolean isExpried) {
        this.auId = auId;
        this.adscontent = adscontent;
        this.description = description;
        this.link = link;
        this.enddate = enddate;
        this.isRemoved = isRemoved;
        this.isExpried = isExpried;
    }

    public Integer getAuId() {
        return auId;
    }

    public void setAuId(Integer auId) {
        this.auId = auId;
    }

    public String getAdscontent() {
        return adscontent;
    }

    public void setAdscontent(String adscontent) {
        this.adscontent = adscontent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public boolean getIsExpried() {
        return isExpried;
    }

    public void setIsExpried(boolean isExpried) {
        this.isExpried = isExpried;
    }

    public Ads getAdsid() {
        return adsid;
    }

    public void setAdsid(Ads adsid) {
        this.adsid = adsid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (auId != null ? auId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdsUser)) {
            return false;
        }
        AdsUser other = (AdsUser) object;
        if ((this.auId == null && other.auId != null) || (this.auId != null && !this.auId.equals(other.auId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AdsUser[ auId=" + auId + " ]";
    }
    
}
