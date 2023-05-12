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
@Table(name = "likes", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    //IsLiked Post
    @NamedQuery(name = "Likes.isliked", query = "SELECT l FROM Likes l WHERE l.postid.postid = :postid AND l.senderid.userid = :senderid"),
    
    //Likecount
    @NamedQuery(name = "Likes.likecount", query = "SELECT l FROM Likes l WHERE l.postid.postid = :postid"),
    
    @NamedQuery(name = "Likes.findAll", query = "SELECT l FROM Likes l"),
    @NamedQuery(name = "Likes.findByLikeid", query = "SELECT l FROM Likes l WHERE l.likeid = :likeid"),
    @NamedQuery(name = "Likes.findByLikeDate", query = "SELECT l FROM Likes l WHERE l.likeDate = :likeDate"),
    @NamedQuery(name = "Likes.findByIsRemoved", query = "SELECT l FROM Likes l WHERE l.isRemoved = :isRemoved")})
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "likeid", nullable = false)
    private Integer likeid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "like_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date likeDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;
    @JoinColumn(name = "postid", referencedColumnName = "postid", nullable = false)
    @ManyToOne(optional = false)
    private Post postid;
    @JoinColumn(name = "receiverid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User receiverid;
    @JoinColumn(name = "senderid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User senderid;

    public Likes() {
    }

    public Likes(Integer likeid) {
        this.likeid = likeid;
    }

    public Likes(Integer likeid, Date likeDate, boolean isRemoved) {
        this.likeid = likeid;
        this.likeDate = likeDate;
        this.isRemoved = isRemoved;
    }

    public Integer getLikeid() {
        return likeid;
    }

    public void setLikeid(Integer likeid) {
        this.likeid = likeid;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public Post getPostid() {
        return postid;
    }

    public void setPostid(Post postid) {
        this.postid = postid;
    }

    public User getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(User receiverid) {
        this.receiverid = receiverid;
    }

    public User getSenderid() {
        return senderid;
    }

    public void setSenderid(User senderid) {
        this.senderid = senderid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (likeid != null ? likeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Likes)) {
            return false;
        }
        Likes other = (Likes) object;
        if ((this.likeid == null && other.likeid != null) || (this.likeid != null && !this.likeid.equals(other.likeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Likes[ likeid=" + likeid + " ]";
    }
    
}
