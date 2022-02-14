package com.eCommerce.eCommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o"),
        @NamedQuery(name = "Order.findByIdorder", query = "SELECT o FROM Order o WHERE o.idorder = :idorder"),
        @NamedQuery(name = "Order.findByAmount", query = "SELECT o FROM Order o WHERE o.amount = :amount"),
        @NamedQuery(name = "Order.findByShippingAddress", query = "SELECT o FROM Order o WHERE o.shippingAddress = :shippingAddress"),
        @NamedQuery(name = "Order.findByOrderDate", query = "SELECT o FROM Order o WHERE o.orderDate = :orderDate")})
public class Order implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private List<ProductOrder> productOrderList;
    @JoinColumn(name = "user_id", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private User userId;

    public Order() {
    }

    public Order(Integer idorder) {
        this.idorder = idorder;
    }

    public Order(Integer idorder, int amount, String shippingAddress, Date orderDate) {
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
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
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
