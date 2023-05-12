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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "group_members", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    //Find Members By group id
    @NamedQuery(name = "GroupMembers.findAllMemberByGroupId", query = "SELECT g FROM GroupMembers g WHERE g.groupid.groupid = :groupid AND g.isMember = true"),
    
    //Find Members By group id
    @NamedQuery(name = "GroupMembers.findGroupsByUserId", query = "SELECT g FROM GroupMembers g WHERE g.memberid.userid = :userid AND g.isMember = true"),
    
    //check group and member
    @NamedQuery(name = "GroupMembers.checkMember", query = "SELECT g FROM GroupMembers g WHERE g.groupid.groupid = :groupid AND g.memberid.userid = :userid AND g.isMember = true"),
    
    @NamedQuery(name = "GroupMembers.findAll", query = "SELECT g FROM GroupMembers g"),
    @NamedQuery(name = "GroupMembers.findByGmId", query = "SELECT g FROM GroupMembers g WHERE g.gmId = :gmId"),
    @NamedQuery(name = "GroupMembers.findByIsMember", query = "SELECT g FROM GroupMembers g WHERE g.isMember = :isMember"),
    @NamedQuery(name = "GroupMembers.findByBecamemember", query = "SELECT g FROM GroupMembers g WHERE g.becamemember = :becamemember")})
public class GroupMembers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gm_id", nullable = false)
    private Integer gmId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_member", nullable = false)
    private boolean isMember;
    @Basic(optional = false)
    @NotNull
    @Column(name = "becamemember", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date becamemember;
    @JoinColumn(name = "groupid", referencedColumnName = "groupid", nullable = false)
    @ManyToOne(optional = false)
    private Groups groupid;
    @JoinColumn(name = "memberid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User memberid;

    public GroupMembers() {
    }

    public GroupMembers(Integer gmId) {
        this.gmId = gmId;
    }

    public GroupMembers(Integer gmId, boolean isMember, Date becamemember) {
        this.gmId = gmId;
        this.isMember = isMember;
        this.becamemember = becamemember;
    }

    public Integer getGmId() {
        return gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public boolean getIsMember() {
        return isMember;
    }

    public void setIsMember(boolean isMember) {
        this.isMember = isMember;
    }

    public Date getBecamemember() {
        return becamemember;
    }

    public void setBecamemember(Date becamemember) {
        this.becamemember = becamemember;
    }

    public Groups getGroupid() {
        return groupid;
    }

    public void setGroupid(Groups groupid) {
        this.groupid = groupid;
    }

    public User getMemberid() {
        return memberid;
    }

    public void setMemberid(User memberid) {
        this.memberid = memberid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gmId != null ? gmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupMembers)) {
            return false;
        }
        GroupMembers other = (GroupMembers) object;
        if ((this.gmId == null && other.gmId != null) || (this.gmId != null && !this.gmId.equals(other.gmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GroupMembers[ gmId=" + gmId + " ]";
    }
    
}
