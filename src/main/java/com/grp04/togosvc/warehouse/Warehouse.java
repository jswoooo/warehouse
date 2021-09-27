package com.grp04.togosvc.warehouse;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Warehouse_table")
public class Warehouse {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long productId;
    private String productName;
    private Long productQty;
    private Double location;

    @PostPersist
    public void onPostPersist(){
        StockUpdated stockUpdated = new StockUpdated();
        BeanUtils.copyProperties(this, stockUpdated);
        stockUpdated.publishAfterCommit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Long getProductQty() {
        return productQty;
    }

    public void setProductQty(Long productQty) {
        this.productQty = productQty;
    }
    public Double getLocation() {
        return location;
    }

    public void setLocation(Double location) {
        this.location = location;
    }
}