/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "state", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    // Find Id by State Name
    @NamedQuery(name = "State.findIdByStatename", query = "SELECT s.stateid FROM State s WHERE s.statename = :statename"),
    
    @NamedQuery(name = "State.findAll", query = "SELECT s FROM State s"),
    @NamedQuery(name = "State.findByStateid", query = "SELECT s FROM State s WHERE s.stateid = :stateid"),
    @NamedQuery(name = "State.findByStatename", query = "SELECT s FROM State s WHERE s.statename = :statename"),
    @NamedQuery(name = "State.findByIsactive", query = "SELECT s FROM State s WHERE s.isactive = :isactive")})
public class State implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stateid", nullable = false)
    private Integer stateid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "statename", nullable = false, length = 1000)
    private String statename;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive", nullable = false)
    private boolean isactive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateid")
    private Collection<City> cityCollection;
    @JoinColumn(name = "countryid", referencedColumnName = "countryid", nullable = false)
    @ManyToOne(optional = false)
    private Country countryid;
    @OneToMany(mappedBy = "stateid")
    private Collection<User> userCollection;

    public State() {
    }

    public State(Integer stateid) {
        this.stateid = stateid;
    }

    public State(Integer stateid, String statename, boolean isactive) {
        this.stateid = stateid;
        this.statename = statename;
        this.isactive = isactive;
    }

    public Integer getStateid() {
        return stateid;
    }

    public void setStateid(Integer stateid) {
        this.stateid = stateid;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<City> getCityCollection() {
        return cityCollection;
    }

    public void setCityCollection(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }

    public Country getCountryid() {
        return countryid;
    }

    public void setCountryid(Country countryid) {
        this.countryid = countryid;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateid != null ? stateid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof State)) {
            return false;
        }
        State other = (State) object;
        if ((this.stateid == null && other.stateid != null) || (this.stateid != null && !this.stateid.equals(other.stateid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.State[ stateid=" + stateid + " ]";
    }
    
}
