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
 * @author LENOVO
 */
@Entity
@Table(name = "country", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    // Find Id by Country Name
    @NamedQuery(name = "Country.findIdByCountryname", query = "SELECT c.countryid FROM Country c WHERE c.countryname = :countryname"),
    
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c"),
    @NamedQuery(name = "Country.findByCountryid", query = "SELECT c FROM Country c WHERE c.countryid = :countryid"),
    @NamedQuery(name = "Country.findBySortname", query = "SELECT c FROM Country c WHERE c.sortname = :sortname"),
    @NamedQuery(name = "Country.findByCountryname", query = "SELECT c FROM Country c WHERE c.countryname = :countryname"),
    @NamedQuery(name = "Country.findByPhonecode", query = "SELECT c FROM Country c WHERE c.phonecode = :phonecode"),
    @NamedQuery(name = "Country.findByIsactive", query = "SELECT c FROM Country c WHERE c.isactive = :isactive")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "countryid", nullable = false)
    private Integer countryid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "sortname", nullable = false, length = 3)
    private String sortname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "countryname", nullable = false, length = 1000)
    private String countryname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phonecode", nullable = false)
    private int phonecode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive", nullable = false)
    private boolean isactive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countryid")
    private Collection<State> stateCollection;
    @OneToMany(mappedBy = "countryid")
    private Collection<User> userCollection;

    public Country() {
    }

    public Country(Integer countryid) {
        this.countryid = countryid;
    }

    public Country(Integer countryid, String sortname, String countryname, int phonecode, boolean isactive) {
        this.countryid = countryid;
        this.sortname = sortname;
        this.countryname = countryname;
        this.phonecode = phonecode;
        this.isactive = isactive;
    }

    public Integer getCountryid() {
        return countryid;
    }

    public void setCountryid(Integer countryid) {
        this.countryid = countryid;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public int getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(int phonecode) {
        this.phonecode = phonecode;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<State> getStateCollection() {
        return stateCollection;
    }

    public void setStateCollection(Collection<State> stateCollection) {
        this.stateCollection = stateCollection;
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
        hash += (countryid != null ? countryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.countryid == null && other.countryid != null) || (this.countryid != null && !this.countryid.equals(other.countryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Country[ countryid=" + countryid + " ]";
    }
    
}
