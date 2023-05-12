/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "ads", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ads.findAll", query = "SELECT a FROM Ads a"),
    @NamedQuery(name = "Ads.findByAdsId", query = "SELECT a FROM Ads a WHERE a.adsId = :adsId"),
    @NamedQuery(name = "Ads.findByAdstype", query = "SELECT a FROM Ads a WHERE a.adstype = :adstype"),
    @NamedQuery(name = "Ads.findByPrice", query = "SELECT a FROM Ads a WHERE a.price = :price"),
    @NamedQuery(name = "Ads.findByTimelimit", query = "SELECT a FROM Ads a WHERE a.timelimit = :timelimit"),
    @NamedQuery(name = "Ads.findByDescription", query = "SELECT a FROM Ads a WHERE a.description = :description"),
    @NamedQuery(name = "Ads.findByIsRemoved", query = "SELECT a FROM Ads a WHERE a.isRemoved = :isRemoved")})
public class Ads implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ads_id", nullable = false)
    private Integer adsId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "adstype", nullable = false, length = 1000)
    private String adstype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price", nullable = false)
    private int price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "timelimit", nullable = false, length = 255)
    private String timelimit;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "description", nullable = false, length = 1000)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adsid")
    private Collection<AdsUser> adsUserCollection;

    public Ads() {
    }

    public Ads(Integer adsId) {
        this.adsId = adsId;
    }

    public Ads(Integer adsId, String adstype, int price, String timelimit, String description, boolean isRemoved) {
        this.adsId = adsId;
        this.adstype = adstype;
        this.price = price;
        this.timelimit = timelimit;
        this.description = description;
        this.isRemoved = isRemoved;
    }

    public Integer getAdsId() {
        return adsId;
    }

    public void setAdsId(Integer adsId) {
        this.adsId = adsId;
    }

    public String getAdstype() {
        return adstype;
    }

    public void setAdstype(String adstype) {
        this.adstype = adstype;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<AdsUser> getAdsUserCollection() {
        return adsUserCollection;
    }

    public void setAdsUserCollection(Collection<AdsUser> adsUserCollection) {
        this.adsUserCollection = adsUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adsId != null ? adsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ads)) {
            return false;
        }
        Ads other = (Ads) object;
        if ((this.adsId == null && other.adsId != null) || (this.adsId != null && !this.adsId.equals(other.adsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ads[ adsId=" + adsId + " ]";
    }
    
}
