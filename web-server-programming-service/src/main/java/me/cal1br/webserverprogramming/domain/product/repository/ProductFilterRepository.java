package me.cal1br.webserverprogramming.domain.product.repository;

import me.cal1br.webserverprogramming.api.product.filter.ProductFilter;
import me.cal1br.webserverprogramming.base.repository.FilterRepository;
import me.cal1br.webserverprogramming.domain.product.model.ProductEntity;

public interface ProductFilterRepository extends FilterRepository<ProductEntity, ProductFilter> {
}
