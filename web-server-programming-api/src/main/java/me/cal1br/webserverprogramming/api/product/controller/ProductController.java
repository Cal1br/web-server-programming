package me.cal1br.webserverprogramming.api.product.controller;

import me.cal1br.webserverprogramming.api.base.controller.BaseController;
import me.cal1br.webserverprogramming.api.product.filter.ProductFilter;
import me.cal1br.webserverprogramming.api.product.model.ProductDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(
        path = "product",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public interface ProductController extends BaseController<ProductDTO, ProductFilter> {
}
