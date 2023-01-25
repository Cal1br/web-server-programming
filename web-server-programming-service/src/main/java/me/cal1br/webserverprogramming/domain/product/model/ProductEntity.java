package me.cal1br.webserverprogramming.domain.product.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cal1br.webserverprogramming.api.product.model.ProductCategory;
import me.cal1br.webserverprogramming.base.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity(name = "product")
@EqualsAndHashCode(callSuper = true)
public class ProductEntity extends BaseEntity {
    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;
    @Column(name = "product_description", length = 2000)
    private String productDescription;
    @Column(name = "image")
    private String image;
    @Column(name = "price_buy", nullable = false)
    private Double priceBuy;
    @Column(name = "price_sell", nullable = false)
    private Double priceSell;
    @Column(name = "product_count", nullable = false)
    private Integer productCount;
    @Column(name = "product_category")
    private ProductCategory productCategory;
    @Column(name = "sku_code", unique = true, nullable = false)
    private String skuCode;
}