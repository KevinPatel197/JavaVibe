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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "event_usercount", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    //Subscribed User
    @NamedQuery(name = "EventUsercount.findsubscribe", query = "SELECT e FROM EventUsercount e WHERE e.eventid.eventid = :eventid AND e.userid.userid = :userid "),
    
    //EventUserCount
    @NamedQuery(name = "EventUsercount.findByEventId", query = "SELECT e FROM EventUsercount e WHERE e.eventid.eventid = :eventid AND e.isInterested = true"),
    
    @NamedQuery(name = "EventUsercount.findAll", query = "SELECT e FROM EventUsercount e"),
    @NamedQuery(name = "EventUsercount.findByEucId", query = "SELECT e FROM EventUsercount e WHERE e.eucId = :eucId"),
    @NamedQuery(name = "EventUsercount.findByIsInterested", query = "SELECT e FROM EventUsercount e WHERE e.isInterested = :isInterested")})
public class EventUsercount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "euc_id", nullable = false)
    private Integer eucId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_interested", nullable = false)
    private boolean isInterested;
    @JoinColumn(name = "eventid", referencedColumnName = "eventid", nullable = false)
    @ManyToOne(optional = false)
    private Events eventid;
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User userid;

    public EventUsercount() {
    }

    public EventUsercount(Integer eucId) {
        this.eucId = eucId;
    }

    public EventUsercount(Integer eucId, boolean isInterested) {
        this.eucId = eucId;
        this.isInterested = isInterested;
    }

    public Integer getEucId() {
        return eucId;
    }

    public void setEucId(Integer eucId) {
        this.eucId = eucId;
    }

    public boolean getIsInterested() {
        return isInterested;
    }

    public void setIsInterested(boolean isInterested) {
        this.isInterested = isInterested;
    }

    public Events getEventid() {
        return eventid;
    }

    public void setEventid(Events eventid) {
        this.eventid = eventid;
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
        hash += (eucId != null ? eucId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EventUsercount)) {
            return false;
        }
        EventUsercount other = (EventUsercount) object;
        if ((this.eucId == null && other.eucId != null) || (this.eucId != null && !this.eucId.equals(other.eucId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.EventUsercount[ eucId=" + eucId + " ]";
    }
    
}
