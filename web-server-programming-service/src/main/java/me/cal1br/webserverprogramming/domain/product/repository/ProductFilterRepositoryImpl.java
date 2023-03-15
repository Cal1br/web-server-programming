package me.cal1br.webserverprogramming.domain.product.repository;

import me.cal1br.webserverprogramming.api.product.filter.ProductFilter;
import me.cal1br.webserverprogramming.base.repository.BaseFilterRepositoryImpl;
import me.cal1br.webserverprogramming.domain.product.model.ProductEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
public class ProductFilterRepositoryImpl
        extends BaseFilterRepositoryImpl<ProductEntity, ProductFilter, ProductRepository>
        implements ProductFilterRepository {
    public ProductFilterRepositoryImpl(@Lazy final ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    protected Specification<ProductEntity> getSpecification(final ProductFilter filter) {
        filter.setColumnOrderList(translateColumns(ProductEntity.class, filter.getColumnOrderList()));
        return new ProductSpecification(filter);
    }
}
