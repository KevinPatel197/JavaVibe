/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "user_contact_info", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserContactInfo.findByUserId", query = "SELECT u FROM UserContactInfo u WHERE u.userid.userid = :userid"),
    @NamedQuery(name = "UserContactInfo.findAll", query = "SELECT u FROM UserContactInfo u"),
    @NamedQuery(name = "UserContactInfo.findByUciId", query = "SELECT u FROM UserContactInfo u WHERE u.uciId = :uciId"),
    @NamedQuery(name = "UserContactInfo.findByWebsite", query = "SELECT u FROM UserContactInfo u WHERE u.website = :website"),
    @NamedQuery(name = "UserContactInfo.findByLanguage", query = "SELECT u FROM UserContactInfo u WHERE u.language = :language"),
    @NamedQuery(name = "UserContactInfo.findByIntrestedIn", query = "SELECT u FROM UserContactInfo u WHERE u.intrestedIn = :intrestedIn"),
    @NamedQuery(name = "UserContactInfo.findByFbLink", query = "SELECT u FROM UserContactInfo u WHERE u.fbLink = :fbLink"),
    @NamedQuery(name = "UserContactInfo.findByInstaLink", query = "SELECT u FROM UserContactInfo u WHERE u.instaLink = :instaLink"),
    @NamedQuery(name = "UserContactInfo.findByBio", query = "SELECT u FROM UserContactInfo u WHERE u.bio = :bio")})
public class UserContactInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uci_id", nullable = false)
    private Integer uciId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "website", nullable = false, length = 1000)
    private String website;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "language", nullable = false, length = 1000)
    private String language;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "intrested_in", nullable = false, length = 1000)
    private String intrestedIn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "fb_link", nullable = false, length = 1000)
    private String fbLink;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "insta_link", nullable = false, length = 1000)
    private String instaLink;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "bio", nullable = false, length = 2000)
    private String bio;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User userid;

    public UserContactInfo() {
    }

    public UserContactInfo(Integer uciId) {
        this.uciId = uciId;
    }

    public UserContactInfo(Integer uciId, String website, String language, String intrestedIn, String fbLink, String instaLink, String bio) {
        this.uciId = uciId;
        this.website = website;
        this.language = language;
        this.intrestedIn = intrestedIn;
        this.fbLink = fbLink;
        this.instaLink = instaLink;
        this.bio = bio;
    }

    public Integer getUciId() {
        return uciId;
    }

    public void setUciId(Integer uciId) {
        this.uciId = uciId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIntrestedIn() {
        return intrestedIn;
    }

    public void setIntrestedIn(String intrestedIn) {
        this.intrestedIn = intrestedIn;
    }

    public String getFbLink() {
        return fbLink;
    }

    public void setFbLink(String fbLink) {
        this.fbLink = fbLink;
    }

    public String getInstaLink() {
        return instaLink;
    }

    public void setInstaLink(String instaLink) {
        this.instaLink = instaLink;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
        hash += (uciId != null ? uciId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserContactInfo)) {
            return false;
        }
        UserContactInfo other = (UserContactInfo) object;
        if ((this.uciId == null && other.uciId != null) || (this.uciId != null && !this.uciId.equals(other.uciId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserContactInfo[ uciId=" + uciId + " ]";
    }
    
}
