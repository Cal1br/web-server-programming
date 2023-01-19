package me.cal1br.webserverprogramming.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;

public class PredicateHolder {

    private final List<Predicate> predicateList = new LinkedList<>();
    private final CriteriaBuilder criteriaBuilder;
    private final GroupTypes group;

    public PredicateHolder(final CriteriaBuilder criteriaBuilder, final GroupTypes group) {
        this.criteriaBuilder = criteriaBuilder;
        this.group = group;
    }

    public PredicateHolder addPredicate(Predicate predicate) {
        predicateList.add(predicate);
        return this;
    }

    public Predicate toPredicate() {
        return group.resolve(criteriaBuilder,predicateList);
    }
}
