package me.cal1br.webserverprogramming.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.function.BiFunction;

public enum GroupTypes {
    AND((criteriaBuilder, predicates) ->
            criteriaBuilder.and(predicates.toArray(new Predicate[]{}))
    ),
    OR((criteriaBuilder, predicates) ->
            criteriaBuilder.or(predicates.toArray(new Predicate[]{})));

    private final BiFunction<CriteriaBuilder, List<Predicate>, Predicate> func;

    GroupTypes(final BiFunction<CriteriaBuilder, List<Predicate>, Predicate> func) {
        this.func = func;
    }

    public Predicate resolve(CriteriaBuilder cb, List<Predicate> list) {
        return func.apply(cb, list);
    }

}
