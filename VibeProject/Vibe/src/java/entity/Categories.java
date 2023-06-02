/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
@Table(name = "categories")
@XmlRootElement
@NamedQueries({
    
    // Find Id by Category Name
    @NamedQuery(name = "Categories.findIdByCategoryname", query = "SELECT c.catid FROM Categories c WHERE c.catname = :catname"),
    
    
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c"),
    @NamedQuery(name = "Categories.findByCatid", query = "SELECT c FROM Categories c WHERE c.catid = :catid"),
    @NamedQuery(name = "Categories.findByCatname", query = "SELECT c FROM Categories c WHERE c.catname = :catname"),
    @NamedQuery(name = "Categories.findByIsActive", query = "SELECT c FROM Categories c WHERE c.isActive = :isActive")})
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catid")
    private Integer catid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "catname")
    private String catname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isActive")
    private boolean isActive;
    @OneToMany(mappedBy = "categories")
    private Collection<Products> productsCollection;

    public Categories() {
    }

    public Categories(Integer catid) {
        this.catid = catid;
    }

    public Categories(Integer catid, String catname, boolean isActive) {
        this.catid = catid;
        this.catname = catname;
        this.isActive = isActive;
    }

    public Integer getCatid() {
        return catid;
    }

    public void setCatid(Integer catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catid != null ? catid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.catid == null && other.catid != null) || (this.catid != null && !this.catid.equals(other.catid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Categories[ catid=" + catid + " ]";
    }
    
}
