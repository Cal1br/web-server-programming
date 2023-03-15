package me.cal1br.webserverprogramming.domain.user.repository;

import me.cal1br.webserverprogramming.api.user.filter.UserFilter;
import me.cal1br.webserverprogramming.base.repository.BaseSpecification;
import me.cal1br.webserverprogramming.domain.user.model.UserEntity;
import me.cal1br.webserverprogramming.domain.user.model.UserEntity_;
import me.cal1br.webserverprogramming.specification.builder.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification extends BaseSpecification<UserEntity> implements Specification<UserEntity> {

    private final UserFilter filter;

    public UserSpecification(final UserFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(final Root<UserEntity> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
        if (!CollectionUtils.isEmpty(filter.getColumnOrderList())) {
            query.orderBy(resolveOrder(filter.getColumnOrderList(), root, criteriaBuilder));
        }
        final SpecificationBuilder<UserEntity> specBuilder = SpecificationBuilder.init(root, query, criteriaBuilder)
                .in(UserEntity_.id, this.filter.getIdList())
                .startsWithIgnoreCase(UserEntity_.username, filter.getUserName())
                .startsWithIgnoreCase(UserEntity_.email, filter.getEmail())
                .startsWithIgnoreCase(UserEntity_.phoneNumber, filter.getPhoneNumber());
        return specBuilder.build();
    }
}
