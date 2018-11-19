package com.example.spi.localize2socialize.domain;

import com.example.spi.localize2socialize.domain.enumeration.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Spi on 2018. 04. 07..
 */

@Entity
@Table(name = "Orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Order_Product",
            joinColumns = {@JoinColumn(name = "Order_id")},
            inverseJoinColumns = {@JoinColumn(name = "Product_id")})
    private List<Product> productList;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Account account;

    public Order() {
    }

    public Order(Account account) {
        this.orderStatus = OrderStatus.PROCESSING;
        this.productList = new ArrayList<>();
        this.account = account;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Account getAccount() {
        return account;
    }

    public void setCustome(Account account) {
        this.account = account;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


}
