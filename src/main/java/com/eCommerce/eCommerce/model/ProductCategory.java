package com.eCommerce.eCommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "product_category")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ProductCategory.findAll", query = "SELECT p FROM ProductCategory p"),
        @NamedQuery(name = "ProductCategory.findByIdproductCategory", query = "SELECT p FROM ProductCategory p WHERE p.idproductCategory = :idproductCategory"),
        @NamedQuery(name = "ProductCategory.findByName", query = "SELECT p FROM ProductCategory p WHERE p.name = :name"),
        @NamedQuery(name = "ProductCategory.findByDescription", query = "SELECT p FROM ProductCategory p WHERE p.description = :description")})

public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduct_category")
    private Integer idproductCategory;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private List<Product> productList;

    public ProductCategory() {
    }

    public ProductCategory(Integer idproductCategory) {
        this.idproductCategory = idproductCategory;
    }

    public ProductCategory(Integer idproductCategory, String name) {
        this.idproductCategory = idproductCategory;
        this.name = name;
    }

    public Integer getIdproductCategory() {
        return idproductCategory;
    }

    public void setIdproductCategory(Integer idproductCategory) {
        this.idproductCategory = idproductCategory;
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
        hash += (idproductCategory != null ? idproductCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductCategory)) {
            return false;
        }
        ProductCategory other = (ProductCategory) object;
        if ((this.idproductCategory == null && other.idproductCategory != null) || (this.idproductCategory != null && !this.idproductCategory.equals(other.idproductCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id product:" + idproductCategory + ", name product:" + name + ", description: " + description + " ]";
    }

}
