package com.eCommerce.eCommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "discount")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Discount.findAll", query = "SELECT d FROM Discount d"),
        @NamedQuery(name = "Discount.findByIddiscount", query = "SELECT d FROM Discount d WHERE d.iddiscount = :iddiscount"),
        @NamedQuery(name = "Discount.findByName", query = "SELECT d FROM Discount d WHERE d.name = :name"),
        @NamedQuery(name = "Discount.findByDescription", query = "SELECT d FROM Discount d WHERE d.description = :description"),
        @NamedQuery(name = "Discount.findByDiscountPercent", query = "SELECT d FROM Discount d WHERE d.discountPercent = :discountPercent"),
        @NamedQuery(name = "Discount.findByActive", query = "SELECT d FROM Discount d WHERE d.active = :active")})
public class Discount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddiscount")
    private Integer iddiscount;
    @Basic(optional = true)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "discount_percent")
    private int discountPercent;
    @Basic(optional = false)
    @Column(name = "active")
    private short active;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "discountId")
    private List<Product> productList;

    public Discount() {
    }

    public Discount(Integer iddiscount) {
        this.iddiscount = iddiscount;
    }

    public Discount(Integer iddiscount, String name, int discountPercent, short active) {
        this.iddiscount = iddiscount;
        this.name = name;
        this.discountPercent = discountPercent;
        this.active = active;
    }

    public Integer getIddiscount() {
        return iddiscount;
    }

    public void setIddiscount(Integer iddiscount) {
        this.iddiscount = iddiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddiscount != null ? iddiscount.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discount)) {
            return false;
        }
        Discount other = (Discount) object;
        if ((this.iddiscount == null && other.iddiscount != null) || (this.iddiscount != null && !this.iddiscount.equals(other.iddiscount))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eCommerce.eCommerce.model.Discount[ iddiscount=" + iddiscount + " ]";
    }

}
