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
@Table(name = "user_skills", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSkills.findByUserId", query = "SELECT u FROM UserSkills u WHERE u.userid.userid = :userid"),
    @NamedQuery(name = "UserSkills.findAll", query = "SELECT u FROM UserSkills u"),
    @NamedQuery(name = "UserSkills.findByUsId", query = "SELECT u FROM UserSkills u WHERE u.usId = :usId"),
    @NamedQuery(name = "UserSkills.findBySkillname", query = "SELECT u FROM UserSkills u WHERE u.skillname = :skillname"),
    @NamedQuery(name = "UserSkills.findBySkillinfo", query = "SELECT u FROM UserSkills u WHERE u.skillinfo = :skillinfo"),
    @NamedQuery(name = "UserSkills.findBySkillportfolio", query = "SELECT u FROM UserSkills u WHERE u.skillportfolio = :skillportfolio")})
public class UserSkills implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "us_id", nullable = false)
    private Integer usId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "skillname", nullable = false, length = 1000)
    private String skillname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "skillinfo", nullable = false, length = 1000)
    private String skillinfo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "skillportfolio", nullable = false, length = 1000)
    private String skillportfolio;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User userid;

    public UserSkills() {
    }

    public UserSkills(Integer usId) {
        this.usId = usId;
    }

    public UserSkills(Integer usId, String skillname, String skillinfo, String skillportfolio) {
        this.usId = usId;
        this.skillname = skillname;
        this.skillinfo = skillinfo;
        this.skillportfolio = skillportfolio;
    }

    public Integer getUsId() {
        return usId;
    }

    public void setUsId(Integer usId) {
        this.usId = usId;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public String getSkillinfo() {
        return skillinfo;
    }

    public void setSkillinfo(String skillinfo) {
        this.skillinfo = skillinfo;
    }

    public String getSkillportfolio() {
        return skillportfolio;
    }

    public void setSkillportfolio(String skillportfolio) {
        this.skillportfolio = skillportfolio;
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
        hash += (usId != null ? usId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSkills)) {
            return false;
        }
        UserSkills other = (UserSkills) object;
        if ((this.usId == null && other.usId != null) || (this.usId != null && !this.usId.equals(other.usId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.UserSkills[ usId=" + usId + " ]";
    }
    
}
