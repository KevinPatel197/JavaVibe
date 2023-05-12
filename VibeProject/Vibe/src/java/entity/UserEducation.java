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
@Table(name = "user_education", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserEducation.findByUserId", query = "SELECT u FROM UserEducation u WHERE u.userid.userid = :userid"),
    @NamedQuery(name = "UserEducation.findAll", query = "SELECT u FROM UserEducation u"),
    @NamedQuery(name = "UserEducation.findByUeId", query = "SELECT u FROM UserEducation u WHERE u.ueId = :ueId"),
    @NamedQuery(name = "UserEducation.findByInstitutename", query = "SELECT u FROM UserEducation u WHERE u.institutename = :institutename"),
    @NamedQuery(name = "UserEducation.findByJoiningdate", query = "SELECT u FROM UserEducation u WHERE u.joiningdate = :joiningdate"),
    @NamedQuery(name = "UserEducation.findByEndingdate", query = "SELECT u FROM UserEducation u WHERE u.endingdate = :endingdate"),
    @NamedQuery(name = "UserEducation.findByInstituteaddress", query = "SELECT u FROM UserEducation u WHERE u.instituteaddress = :instituteaddress")})
public class UserEducation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ue_id", nullable = false)
    private Integer ueId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "institutename", nullable = false, length = 1000)
    private String institutename;
    @Basic(optional = false)
    @NotNull
    @Column(name = "joiningdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date joiningdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "endingdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endingdate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "instituteaddress", nullable = false, length = 1000)
    private String instituteaddress;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User userid;

    public UserEducation() {
    }

    public UserEducation(Integer ueId) {
        this.ueId = ueId;
    }

    public UserEducation(Integer ueId, String institutename, Date joiningdate, Date endingdate, String instituteaddress) {
        this.ueId = ueId;
        this.institutename = institutename;
        this.joiningdate = joiningdate;
        this.endingdate = endingdate;
        this.instituteaddress = instituteaddress;
    }

    public Integer getUeId() {
        return ueId;
    }

    public void setUeId(Integer ueId) {
        this.ueId = ueId;
    }

    public String getInstitutename() {
        return institutename;
    }

    public void setInstitutename(String institutename) {
        this.institutename = institutename;
    }

    public Date getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(Date joiningdate) {
        this.joiningdate = joiningdate;
    }

    public Date getEndingdate() {
        return endingdate;
    }

    public void setEndingdate(Date endingdate) {
        this.endingdate = endingdate;
    }

    public String getInstituteaddress() {
        return instituteaddress;
    }

    public void setInstituteaddress(String instituteaddress) {
        this.instituteaddress = instituteaddress;
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
        hash += (ueId != null ? ueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEducation)) {
            return false;
        }
        UserEducation other = (UserEducation) object;
        if ((this.ueId == null && other.ueId != null) || (this.ueId != null && !this.ueId.equals(other.ueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserEducation[ ueId=" + ueId + " ]";
    }
    
}
