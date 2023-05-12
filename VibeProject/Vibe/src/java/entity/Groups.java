/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "groups", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    //group show all by userid
    @NamedQuery(name = "Groups.findAllByUserId", query = "SELECT g FROM Groups g WHERE g.adminid.userid = :userid AND g.isDeleted = false"),
    
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g Where g.adminid.userid != :userid AND g.isDeleted = false"),
    
    @NamedQuery(name = "Groups.findAllInAdmin", query = "SELECT g FROM Groups g"),
    @NamedQuery(name = "Groups.findByGroupid", query = "SELECT g FROM Groups g WHERE g.groupid = :groupid"),
    @NamedQuery(name = "Groups.findByGroupname", query = "SELECT g FROM Groups g WHERE g.groupname = :groupname"),
    @NamedQuery(name = "Groups.findByDescription", query = "SELECT g FROM Groups g WHERE g.description = :description"),
    @NamedQuery(name = "Groups.findByMemberscount", query = "SELECT g FROM Groups g WHERE g.memberscount = :memberscount"),
    @NamedQuery(name = "Groups.findByCreateDate", query = "SELECT g FROM Groups g WHERE g.createDate = :createDate"),
    @NamedQuery(name = "Groups.findByIsDeleted", query = "SELECT g FROM Groups g WHERE g.isDeleted = :isDeleted")})
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "groupid", nullable = false)
    private Integer groupid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "groupname", nullable = false, length = 100)
    private String groupname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "description", nullable = false, length = 2000)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "memberscount", nullable = false)
    private int memberscount;
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupid")
    private Collection<GroupMembers> groupMembersCollection;
    @JoinColumn(name = "adminid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User adminid;
    @OneToMany(mappedBy = "groupid")
    private Collection<Post> postCollection;
    @OneToMany(mappedBy = "groupid")
    private Collection<ActivityFeed> activityFeedCollection;

    public Groups() {
    }

    public Groups(Integer groupid) {
        this.groupid = groupid;
    }

    public Groups(Integer groupid, String groupname, String description, int memberscount, boolean isDeleted) {
        this.groupid = groupid;
        this.groupname = groupname;
        this.description = description;
        this.memberscount = memberscount;
        this.isDeleted = isDeleted;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMemberscount() {
        return memberscount;
    }

    public void setMemberscount(int memberscount) {
        this.memberscount = memberscount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<GroupMembers> getGroupMembersCollection() {
        return groupMembersCollection;
    }

    public void setGroupMembersCollection(Collection<GroupMembers> groupMembersCollection) {
        this.groupMembersCollection = groupMembersCollection;
    }

    public User getAdminid() {
        return adminid;
    }

    public void setAdminid(User adminid) {
        this.adminid = adminid;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<ActivityFeed> getActivityFeedCollection() {
        return activityFeedCollection;
    }

    public void setActivityFeedCollection(Collection<ActivityFeed> activityFeedCollection) {
        this.activityFeedCollection = activityFeedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupid != null ? groupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.groupid == null && other.groupid != null) || (this.groupid != null && !this.groupid.equals(other.groupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Groups[ groupid=" + groupid + " ]";
    }
    
}
