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
 * @author LENOVO
 */
@Entity
@Table(name = "user_work", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserWork.findByUserId", query = "SELECT u FROM UserWork u WHERE u.userid.userid = :userid"),
    @NamedQuery(name = "UserWork.findAll", query = "SELECT u FROM UserWork u"),
    @NamedQuery(name = "UserWork.findByUwId", query = "SELECT u FROM UserWork u WHERE u.uwId = :uwId"),
    @NamedQuery(name = "UserWork.findByCompanyname", query = "SELECT u FROM UserWork u WHERE u.companyname = :companyname"),
    @NamedQuery(name = "UserWork.findByJoiningdate", query = "SELECT u FROM UserWork u WHERE u.joiningdate = :joiningdate"),
    @NamedQuery(name = "UserWork.findByEndingdate", query = "SELECT u FROM UserWork u WHERE u.endingdate = :endingdate"),
    @NamedQuery(name = "UserWork.findByCompanyaddress", query = "SELECT u FROM UserWork u WHERE u.companyaddress = :companyaddress")})
public class UserWork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "uw_id", nullable = false)
    private Integer uwId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "companyname", nullable = false, length = 1000)
    private String companyname;
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
    @Column(name = "companyaddress", nullable = false, length = 1000)
    private String companyaddress;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User userid;

    public UserWork() {
    }

    public UserWork(Integer uwId) {
        this.uwId = uwId;
    }

    public UserWork(Integer uwId, String companyname, Date joiningdate, Date endingdate, String companyaddress) {
        this.uwId = uwId;
        this.companyname = companyname;
        this.joiningdate = joiningdate;
        this.endingdate = endingdate;
        this.companyaddress = companyaddress;
    }

    public Integer getUwId() {
        return uwId;
    }

    public void setUwId(Integer uwId) {
        this.uwId = uwId;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
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
        hash += (uwId != null ? uwId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserWork)) {
            return false;
        }
        UserWork other = (UserWork) object;
        if ((this.uwId == null && other.uwId != null) || (this.uwId != null && !this.uwId.equals(other.uwId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserWork[ uwId=" + uwId + " ]";
    }
    
}
