package me.cal1br.webserverprogramming.api.product.filter;

import lombok.Data;
import me.cal1br.webserverprogramming.api.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.api.product.model.ProductCategory;

import java.util.List;

@Data
public class ProductFilter extends BaseFilter {
    private String productName;
    private List<ProductCategory> productCategory;
    private String skuCode;
}
