package com.lx.povertyalleviation.pojo;

import org.springframework.stereotype.Repository;

@Repository
public class Composition {

    private Integer id;
    private String productName;
    private  String productComposition;

    @Override
    public String toString() {
        return "Composition{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productComposition='" + productComposition + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductComposition() {
        return productComposition;
    }

    public void setProductComposition(String productComposition) {
        this.productComposition = productComposition;
    }
}
