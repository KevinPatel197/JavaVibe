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
@Table(name = "comments", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    //Comment by postid
    @NamedQuery(name = "Comments.findByPostid", query = "SELECT c FROM Comments c WHERE c.postid.postid = :postid"),
    
    @NamedQuery(name = "Comments.findAll", query = "SELECT c FROM Comments c"),
    @NamedQuery(name = "Comments.findByCommentid", query = "SELECT c FROM Comments c WHERE c.commentid = :commentid"),
    @NamedQuery(name = "Comments.findByComment", query = "SELECT c FROM Comments c WHERE c.comment = :comment"),
    @NamedQuery(name = "Comments.findByCommentDate", query = "SELECT c FROM Comments c WHERE c.commentDate = :commentDate"),
    @NamedQuery(name = "Comments.findByIsRemoved", query = "SELECT c FROM Comments c WHERE c.isRemoved = :isRemoved")})
public class Comments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "commentid", nullable = false)
    private Integer commentid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "comment", nullable = false, length = 1000)
    private String comment;
    @Column(name = "comment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;
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

    public Comments() {
    }

    public Comments(Integer commentid) {
        this.commentid = commentid;
    }

    public Comments(Integer commentid, String comment, boolean isRemoved) {
        this.commentid = commentid;
        this.comment = comment;
        this.isRemoved = isRemoved;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
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
        hash += (commentid != null ? commentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        if ((this.commentid == null && other.commentid != null) || (this.commentid != null && !this.commentid.equals(other.commentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Comments[ commentid=" + commentid + " ]";
    }
    
}
