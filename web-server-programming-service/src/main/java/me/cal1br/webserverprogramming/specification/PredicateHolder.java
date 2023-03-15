package me.cal1br.webserverprogramming.specification;

import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PredicateHolder {


    private final List<Predicate> predicateList = new LinkedList<>();
    private final CriteriaBuilder criteriaBuilder;
    private final GroupType group;
    private final PredicateHolder parentHolder; // so inner groupings are possible (x AND y) OR (z AND w)
    private boolean not = false;

    public PredicateHolder(final CriteriaBuilder criteriaBuilder, final GroupType group) {
        this.criteriaBuilder = criteriaBuilder;
        this.group = group;
        this.parentHolder = null;
    }

    public PredicateHolder(final CriteriaBuilder criteriaBuilder, final GroupType group, final PredicateHolder parentHolder) {
        this.criteriaBuilder = criteriaBuilder;
        this.group = group;
        this.parentHolder = parentHolder;
    }

    public PredicateHolder enterInner(GroupType type) {
        return new PredicateHolder(criteriaBuilder, type, this);
    }

    public PredicateHolder exitInner() {
        if (this.parentHolder == null) {
            throw new IllegalStateException("There isn't a parent group holding this group!");
        }
        this.toPredicate().ifPresent(this.parentHolder::addPredicate);
        return this.parentHolder;
    }

    public PredicateHolder addPredicate(Predicate predicate) {
        if (not) {
            not = false;
            predicateList.add(predicate.not());
        } else {
            predicateList.add(predicate);
        }
        return this;
    }

    public Optional<Predicate> toPredicate() {
        if(CollectionUtils.isEmpty(this.predicateList)){
            return Optional.empty();
        }
        return Optional.of(group.resolve(criteriaBuilder, predicateList));
    }

    public PredicateHolder invertNext() {
        this.not = true;
        return this;
    }
}
