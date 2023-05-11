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
 * @author LENOVO
 */
@Entity
@Table(name = "events", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    //Suggestedevents
    @NamedQuery(name = "Events.suggestedevents", query = "SELECT e FROM Events e WHERE e.eventid NOT IN(SELECT ec.eventid.eventid FROM EventUsercount ec WHERE ec.userid.userid = :userid AND ec.isInterested = true) AND e.hostid.userid != :userid"),
    
    //Subscribedevents
    @NamedQuery(name = "Events.subscribedevents", query = "SELECT e FROM Events e WHERE e.eventid IN(SELECT ec.eventid.eventid FROM EventUsercount ec WHERE ec.userid.userid = :userid AND ec.isInterested = true)"),

    //Hostedevents
    @NamedQuery(name = "Events.findByHostid", query = "SELECT e FROM Events e WHERE e.hostid.userid = :hostid"),
    
    @NamedQuery(name = "Events.findAll", query = "SELECT e FROM Events e"),
    @NamedQuery(name = "Events.findByEventid", query = "SELECT e FROM Events e WHERE e.eventid = :eventid"),
    @NamedQuery(name = "Events.findByEventname", query = "SELECT e FROM Events e WHERE e.eventname = :eventname"),
    @NamedQuery(name = "Events.findByPost", query = "SELECT e FROM Events e WHERE e.post = :post"),
    @NamedQuery(name = "Events.findByEventstartdate", query = "SELECT e FROM Events e WHERE e.eventstartdate = :eventstartdate"),
    @NamedQuery(name = "Events.findByEventenddate", query = "SELECT e FROM Events e WHERE e.eventenddate = :eventenddate"),
    @NamedQuery(name = "Events.findByEventinfo", query = "SELECT e FROM Events e WHERE e.eventinfo = :eventinfo"),
    @NamedQuery(name = "Events.findByVenue", query = "SELECT e FROM Events e WHERE e.venue = :venue"),
    @NamedQuery(name = "Events.findByType", query = "SELECT e FROM Events e WHERE e.type = :type"),
    @NamedQuery(name = "Events.findByFees", query = "SELECT e FROM Events e WHERE e.fees = :fees"),
    @NamedQuery(name = "Events.findByMode", query = "SELECT e FROM Events e WHERE e.mode = :mode"),
    @NamedQuery(name = "Events.findByGuestcount", query = "SELECT e FROM Events e WHERE e.guestcount = :guestcount"),
    @NamedQuery(name = "Events.findByRegDate", query = "SELECT e FROM Events e WHERE e.regDate = :regDate"),
    @NamedQuery(name = "Events.findByIsRemoved", query = "SELECT e FROM Events e WHERE e.isRemoved = :isRemoved")})
public class Events implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "eventid", nullable = false)
    private Integer eventid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "eventname", nullable = false, length = 1000)
    private String eventname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "post", nullable = false, length = 1000)
    private String post;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eventstartdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date eventstartdate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eventenddate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date eventenddate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "eventinfo", nullable = false, length = 1000)
    private String eventinfo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "venue", nullable = false, length = 1000)
    private String venue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "type", nullable = false, length = 1000)
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fees", nullable = false)
    private int fees;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "mode", nullable = false, length = 1000)
    private String mode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "guestcount", nullable = false)
    private int guestcount;
    @Column(name = "reg_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_removed", nullable = false)
    private boolean isRemoved;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventid")
    private Collection<EventUsercount> eventUsercountCollection;
    @JoinColumn(name = "hostid", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private User hostid;

    public Events() {
    }

    public Events(Integer eventid) {
        this.eventid = eventid;
    }

    public Events(Integer eventid, String eventname, String post, Date eventstartdate, Date eventenddate, String eventinfo, String venue, String type, int fees, String mode, int guestcount, boolean isRemoved) {
        this.eventid = eventid;
        this.eventname = eventname;
        this.post = post;
        this.eventstartdate = eventstartdate;
        this.eventenddate = eventenddate;
        this.eventinfo = eventinfo;
        this.venue = venue;
        this.type = type;
        this.fees = fees;
        this.mode = mode;
        this.guestcount = guestcount;
        this.isRemoved = isRemoved;
    }

    public Integer getEventid() {
        return eventid;
    }

    public void setEventid(Integer eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getEventstartdate() {
        return eventstartdate;
    }

    public void setEventstartdate(Date eventstartdate) {
        this.eventstartdate = eventstartdate;
    }

    public Date getEventenddate() {
        return eventenddate;
    }

    public void setEventenddate(Date eventenddate) {
        this.eventenddate = eventenddate;
    }

    public String getEventinfo() {
        return eventinfo;
    }

    public void setEventinfo(String eventinfo) {
        this.eventinfo = eventinfo;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getGuestcount() {
        return guestcount;
    }

    public void setGuestcount(int guestcount) {
        this.guestcount = guestcount;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<EventUsercount> getEventUsercountCollection() {
        return eventUsercountCollection;
    }

    public void setEventUsercountCollection(Collection<EventUsercount> eventUsercountCollection) {
        this.eventUsercountCollection = eventUsercountCollection;
    }

    public User getHostid() {
        return hostid;
    }

    public void setHostid(User hostid) {
        this.hostid = hostid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventid != null ? eventid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Events)) {
            return false;
        }
        Events other = (Events) object;
        if ((this.eventid == null && other.eventid != null) || (this.eventid != null && !this.eventid.equals(other.eventid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Events[ eventid=" + eventid + " ]";
    }
    
}
