package com.example.spi.localize2socialize.domain;

import com.example.spi.localize2socialize.domain.enumeration.Brand;
import com.example.spi.localize2socialize.domain.enumeration.ProductCategory;
import com.example.spi.localize2socialize.domain.enumeration.ProductType;
import com.example.spi.localize2socialize.domain.enumeration.ProductSize;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Spi on 2018. 04. 06..
 */

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private ProductSize productSize;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    public Product(){}

    public Product(String name,
                   int price,
                   Brand brand,
                   ProductSize productSize,
                   ProductType productType,
                   ProductCategory productCategory){
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.productSize = productSize;
        this.productType = productType;
        this.productCategory = productCategory;
    }

    public Product(Product product){
        this.name = product.name;
        this.price = product.price;
        this.brand = product.brand;
        this.productSize = product.productSize;
        this.productType = product.productType;
        this.productCategory = product.productCategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
