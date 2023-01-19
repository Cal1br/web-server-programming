package me.cal1br.webserverprogramming.api.product.controller;

import me.cal1br.webserverprogramming.api.base.controller.BaseController;
import me.cal1br.webserverprogramming.api.product.filter.ProductFilter;
import me.cal1br.webserverprogramming.api.product.model.ProductDTO;

public interface ProductController extends BaseController<ProductDTO, ProductFilter> {
}
