package me.cal1br.webserverprogramming.domain.product.repository;

import me.cal1br.webserverprogramming.api.product.filter.ProductFilter;
import me.cal1br.webserverprogramming.base.repository.BaseSpecification;
import me.cal1br.webserverprogramming.domain.product.model.ProductEntity;
import me.cal1br.webserverprogramming.domain.product.model.ProductEntity_;
import me.cal1br.webserverprogramming.specification.builder.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecification extends BaseSpecification<ProductEntity> implements Specification<ProductEntity> {

    private final ProductFilter filter;

    public ProductSpecification(final ProductFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(final Root<ProductEntity> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
        if(!CollectionUtils.isEmpty(filter.getColumnOrderList())){
            query.orderBy(resolveOrder(filter.getColumnOrderList(), root, criteriaBuilder));
        }
        final SpecificationBuilder<ProductEntity> builder = SpecificationBuilder.init(root, query, criteriaBuilder)
                .in(ProductEntity_.id, filter.getIdList())
                .startsWithIgnoreCase(ProductEntity_.productName, filter.getProductName())
                .containsIgnoreCase(ProductEntity_.skuCode, filter.getSkuCode())
                .in(ProductEntity_.productCategory, filter.getProductCategory());
        return builder.build();
    }
}
