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
@Table(name = "city", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    //Find ID by City Name
    @NamedQuery(name = "City.findIdByCityname", query = "SELECT c.cityid FROM City c WHERE c.cityname = :cityname"),
    
    @NamedQuery(name = "City.findAll", query = "SELECT c FROM City c"),
    @NamedQuery(name = "City.findByCityid", query = "SELECT c FROM City c WHERE c.cityid = :cityid"),
    @NamedQuery(name = "City.findByCityname", query = "SELECT c FROM City c WHERE c.cityname = :cityname"),
    @NamedQuery(name = "City.findByIsactive", query = "SELECT c FROM City c WHERE c.isactive = :isactive")})
public class City implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cityid", nullable = false)
    private Integer cityid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "cityname", nullable = false, length = 1000)
    private String cityname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive", nullable = false)
    private boolean isactive;
    @JoinColumn(name = "stateid", referencedColumnName = "stateid", nullable = false)
    @ManyToOne(optional = false)
    private State stateid;
    @OneToMany(mappedBy = "cityid")
    private Collection<User> userCollection;

    public City() {
    }

    public City(Integer cityid) {
        this.cityid = cityid;
    }

    public City(Integer cityid, String cityname, boolean isactive) {
        this.cityid = cityid;
        this.cityname = cityname;
        this.isactive = isactive;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public State getStateid() {
        return stateid;
    }

    public void setStateid(State stateid) {
        this.stateid = stateid;
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
        hash += (cityid != null ? cityid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.cityid == null && other.cityid != null) || (this.cityid != null && !this.cityid.equals(other.cityid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.City[ cityid=" + cityid + " ]";
    }
    
}
