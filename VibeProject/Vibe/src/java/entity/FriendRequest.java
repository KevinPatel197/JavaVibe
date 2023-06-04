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
@Table(name = "friend_request", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    //Find Request by Sender ID
    @NamedQuery(name = "FriendRequest.findBySenderId", query = "SELECT f FROM FriendRequest f WHERE f.senderid.userid = :senderid AND f.status = :status"),
    
    //Find Request by Receiver ID
    @NamedQuery(name = "FriendRequest.findByReceiverId", query = "SELECT f FROM FriendRequest f WHERE f.receiverid.userid = :receiverid AND f.status = :status"),
    
    //Find Request by Receiver ID
    @NamedQuery(name = "FriendRequest.checkStatus", query = "SELECT f from FriendRequest f where f.senderid.userid = :senderid AND f.receiverid.userid = :receiverid"),
    
    //Update friend-request status to unfriend
    @NamedQuery(name = "FriendRequest.statusUnfriend", query = "SELECT f from FriendRequest f where f.status = 'accepted' AND (f.senderid.userid = :senderId AND f.receiverid.userid = :receiverId) OR (f.senderid.userid = :receiverId AND f.receiverid.userid = :senderId)"),
    
    @NamedQuery(name = "FriendRequest.findAll", query = "SELECT f FROM FriendRequest f"),
    @NamedQuery(name = "FriendRequest.findByFrId", query = "SELECT f FROM FriendRequest f WHERE f.frId = :frId"),
    @NamedQuery(name = "FriendRequest.findByStatus", query = "SELECT f FROM FriendRequest f WHERE f.status = :status"),
    @NamedQuery(name = "FriendRequest.findByRequestdate", query = "SELECT f FROM FriendRequest f WHERE f.requestdate = :requestdate")})
public class FriendRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fr_id", nullable = false)
    private Integer frId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "status", nullable = false, length = 100)
    private String status;
    @Column(name = "requestdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestdate;
    @JoinColumn(name = "receiverid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User receiverid;
    @JoinColumn(name = "senderid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User senderid;

    public FriendRequest() {
    }

    public FriendRequest(Integer frId) {
        this.frId = frId;
    }

    public FriendRequest(Integer frId, String status) {
        this.frId = frId;
        this.status = status;
    }

    public Integer getFrId() {
        return frId;
    }

    public void setFrId(Integer frId) {
        this.frId = frId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(Date requestdate) {
        this.requestdate = requestdate;
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
        hash += (frId != null ? frId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FriendRequest)) {
            return false;
        }
        FriendRequest other = (FriendRequest) object;
        if ((this.frId == null && other.frId != null) || (this.frId != null && !this.frId.equals(other.frId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FriendRequest[ frId=" + frId + " ]";
    }
    
}
