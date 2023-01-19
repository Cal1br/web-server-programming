package me.cal1br.webserverprogramming.domain.user.repository;

import me.cal1br.webserverprogramming.api.user.filter.UserFilter;
import me.cal1br.webserverprogramming.domain.user.model.UserEntity;
import me.cal1br.webserverprogramming.specification.SpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<UserEntity> {

    private final UserFilter filter;

    public UserSpecification(final UserFilter filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(final Root<UserEntity> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
        final SpecificationBuilder<UserEntity> specBuilder = new SpecificationBuilder<>(root, query, criteriaBuilder);

        return null;
    }
}
