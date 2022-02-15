package com.eCommerce.eCommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Orders.findAll", query = "SELECT o FROM Orders o"),
        @NamedQuery(name = "Orders.findByIdorder", query = "SELECT o FROM Orders o WHERE o.idorder = :idorder"),
        @NamedQuery(name = "Orders.findByAmount", query = "SELECT o FROM Orders o WHERE o.amount = :amount"),
        @NamedQuery(name = "Orders.findByShippingAddress", query = "SELECT o FROM Orders o WHERE o.shippingAddress = :shippingAddress"),
        @NamedQuery(name = "Orders.findByOrderDate", query = "SELECT o FROM Orders o WHERE o.orderDate = :orderDate")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idorder")
    private Integer idorder;
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @Column(name = "shipping_address")
    private String shippingAddress;
    @Basic(optional = false)
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "orderId")
    private List<ProductOrder> productOrderList;
    @JoinColumn(name = "user_id", referencedColumnName = "iduser", nullable = false)
    @ManyToOne(optional = false)
    private User userId;

    public Orders() {
    }

    public Orders(Integer idorder) {
        this.idorder = idorder;
    }

    public Orders(int amount, String shippingAddress, Date orderDate, User iduser) {
        this.amount = amount;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
        this.userId = iduser;
    }

    public Orders(Integer idorder, int amount, String shippingAddress, Date orderDate) {
        this.idorder = idorder;
        this.amount = amount;
        this.shippingAddress = shippingAddress;
        this.orderDate = orderDate;
    }

    public Integer getIdorder() {
        return idorder;
    }

    public void setIdorder(Integer idorder) {
        this.idorder = idorder;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @XmlTransient
    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idorder != null ? idorder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.idorder == null && other.idorder != null) || (this.idorder != null && !this.idorder.equals(other.idorder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eCommerce.eCommerce.model.Order[ idorder=" + idorder + " ]";
    }

}
