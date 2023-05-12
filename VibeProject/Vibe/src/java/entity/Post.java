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
@Table(name = "post", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    //Post by userId
    @NamedQuery(name = "Post.postShowAllByUserId", query = "SELECT p FROM Post p WHERE p.userid.userid = :userid AND p.isDeleted = false ORDER BY p.postid DESC"),
    
    //Post by groupId
    @NamedQuery(name = "Post.postShowAllByGroupId", query = "SELECT p FROM Post p WHERE p.groupid.groupid = :groupid AND p.isDeleted = false ORDER BY p.postid DESC"),
    
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p WHERE p.isDeleted = false ORDER BY p.postid DESC"),
    @NamedQuery(name = "Post.findByPostid", query = "SELECT p FROM Post p WHERE p.postid = :postid"),
    @NamedQuery(name = "Post.findByPost", query = "SELECT p FROM Post p WHERE p.post = :post"),
    @NamedQuery(name = "Post.findByCaption", query = "SELECT p FROM Post p WHERE p.caption = :caption"),
    @NamedQuery(name = "Post.findByUploadDate", query = "SELECT p FROM Post p WHERE p.uploadDate = :uploadDate"),
    @NamedQuery(name = "Post.findByIsDeleted", query = "SELECT p FROM Post p WHERE p.isDeleted = :isDeleted"),
    @NamedQuery(name = "Post.findByLikecount", query = "SELECT p FROM Post p WHERE p.likecount = :likecount"),
    @NamedQuery(name = "Post.findByPosttype", query = "SELECT p FROM Post p WHERE p.posttype = :posttype")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "postid", nullable = false)
    private Integer postid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "post", nullable = false, length = 1000)
    private String post;
    @Size(max = 1000)
    @Column(name = "caption", length = 1000)
    private String caption;
    @Column(name = "upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;
    @Column(name = "likecount")
    private Integer likecount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "posttype", nullable = false, length = 45)
    private String posttype;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postid")
    private Collection<Comments> commentsCollection;
    @JoinColumn(name = "groupid", referencedColumnName = "groupid")
    @ManyToOne
    private Groups groupid;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User userid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "postid")
    private Collection<Likes> likesCollection;

    public Post() {
    }

    public Post(Integer postid) {
        this.postid = postid;
    }

    public Post(Integer postid, String post, boolean isDeleted, String posttype) {
        this.postid = postid;
        this.post = post;
        this.isDeleted = isDeleted;
        this.posttype = posttype;
    }

    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public String getPosttype() {
        return posttype;
    }

    public void setPosttype(String posttype) {
        this.posttype = posttype;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    public Groups getGroupid() {
        return groupid;
    }

    public void setGroupid(Groups groupid) {
        this.groupid = groupid;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Likes> getLikesCollection() {
        return likesCollection;
    }

    public void setLikesCollection(Collection<Likes> likesCollection) {
        this.likesCollection = likesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postid != null ? postid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.postid == null && other.postid != null) || (this.postid != null && !this.postid.equals(other.postid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Post[ postid=" + postid + " ]";
    }
    
}
