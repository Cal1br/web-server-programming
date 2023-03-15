package me.cal1br.webserverprogramming.domain.product.service;

import me.cal1br.webserverprogramming.api.product.filter.ProductFilter;
import me.cal1br.webserverprogramming.api.product.model.ProductDTO;
import me.cal1br.webserverprogramming.base.service.BaseServiceImpl;
import me.cal1br.webserverprogramming.domain.product.model.ProductEntity;
import me.cal1br.webserverprogramming.domain.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductDTO, ProductEntity, ProductFilter, ProductRepository>
        implements ProductService {

    @Autowired
    public ProductServiceImpl(final ProductRepository repository) {
        super(repository, ProductDTO.class, ProductEntity.class);
    }
}
