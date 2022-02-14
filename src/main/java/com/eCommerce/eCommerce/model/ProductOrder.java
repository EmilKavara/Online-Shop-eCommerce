package com.eCommerce.eCommerce.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "product_order")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "ProductOrder.findAll", query = "SELECT p FROM ProductOrder p"),
        @NamedQuery(name = "ProductOrder.findByIdproductOrder", query = "SELECT p FROM ProductOrder p WHERE p.idproductOrder = :idproductOrder"),
        @NamedQuery(name = "ProductOrder.findByQuantity", query = "SELECT p FROM ProductOrder p WHERE p.quantity = :quantity"),
        @NamedQuery(name = "ProductOrder.findByTotal", query = "SELECT p FROM ProductOrder p WHERE p.total = :total")})
public class ProductOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproduct_order")
    private Integer idproductOrder;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "order_id", referencedColumnName = "idorder", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Orders orderId;
    @JoinColumn(name = "product_id", referencedColumnName = "idproduct")
    @ManyToOne(optional = false)
    private Product productId;

    public ProductOrder() {
    }

    public ProductOrder(Integer idproductOrder) {
        this.idproductOrder = idproductOrder;
    }

    public ProductOrder(Integer idproductOrder, int quantity, BigDecimal total) {
        this.idproductOrder = idproductOrder;
        this.quantity = quantity;
        this.total = total;
    }

    public Integer getIdproductOrder() {
        return idproductOrder;
    }

    public void setIdproductOrder(Integer idproductOrder) {
        this.idproductOrder = idproductOrder;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproductOrder != null ? idproductOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductOrder)) {
            return false;
        }
        ProductOrder other = (ProductOrder) object;
        if ((this.idproductOrder == null && other.idproductOrder != null) || (this.idproductOrder != null && !this.idproductOrder.equals(other.idproductOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eCommerce.eCommerce.model.ProductOrder[ idproductOrder=" + idproductOrder + " ]";
    }

}
