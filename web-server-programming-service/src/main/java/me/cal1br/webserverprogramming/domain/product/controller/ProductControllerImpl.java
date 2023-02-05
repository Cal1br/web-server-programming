package me.cal1br.webserverprogramming.domain.product.controller;

import me.cal1br.webserverprogramming.api.product.controller.ProductController;
import me.cal1br.webserverprogramming.api.product.filter.ProductFilter;
import me.cal1br.webserverprogramming.api.product.model.ProductDTO;
import me.cal1br.webserverprogramming.base.controller.BaseControllerImpl;
import me.cal1br.webserverprogramming.domain.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductControllerImpl extends BaseControllerImpl<ProductDTO, ProductFilter, ProductService> implements ProductController {

    @Autowired
    public ProductControllerImpl(final ProductService service) {
        super(service);
    }
}
