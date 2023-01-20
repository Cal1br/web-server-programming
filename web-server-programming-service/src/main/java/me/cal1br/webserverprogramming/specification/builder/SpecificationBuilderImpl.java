package me.cal1br.webserverprogramming.specification.builder;

import me.cal1br.webserverprogramming.specification.GroupType;
import me.cal1br.webserverprogramming.specification.PredicateHolder;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.time.Duration;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SpecificationBuilderImpl<T> implements SpecificationBuilder<T>, SpecificationBuilderInnerBuilder<T> {
    private final Root<T> root;
    private final CriteriaQuery<?> query;
    private final CriteriaBuilder cb;
    private final List<PredicateHolder> list;
    private PredicateHolder current;

    public SpecificationBuilderImpl(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
        this.root = root;
        this.query = query;
        this.cb = cb;

        list = new LinkedList<>();
        current = new PredicateHolder(cb, GroupType.AND);
        list.add(current);

        query.orderBy();
    }


    public SpecificationBuilder<T> gAnd() {
        current = new PredicateHolder(cb, GroupType.AND);
        list.add(current);
        return this;
    }

    public SpecificationBuilder<T> gOr() {
        current = new PredicateHolder(cb, GroupType.OR);
        list.add(current);
        return this;
    }

    @Override
    public SpecificationBuilder<T> not() {
        current.invertNext();
        return this;
    }

    public <Y> SpecificationBuilder<T> eq(SingularAttribute<? super T, Y> column, Y value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.equal(root.get(column), value));
        return this;
    }

    @Override
    public <Y> SpecificationBuilder<T> in(final SingularAttribute<? super T, Y> column, final Collection<Y> values) {
        if (CollectionUtils.isEmpty(values)) {
            return this;
        }
        current.addPredicate(cb.in(root.get(column)).in(values));
        return this;
    }

    public SpecificationBuilder<T> startsWith(SingularAttribute<? super T, String> column, String value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.like(root.get(column), cb.literal(value + "%")));
        return this;
    }

    public SpecificationBuilder<T> startsWithIgnoreCase(SingularAttribute<? super T, String> column, String value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.like(cb.lower(root.get(column)), cb.lower(cb.literal(value + "%"))));
        return this;
    }

    public SpecificationBuilder<T> contains(SingularAttribute<? super T, String> column, String value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.like(root.get(column), cb.literal("%" + value + "%")));
        return this;
    }

    public SpecificationBuilder<T> containsIgnoreCase(SingularAttribute<? super T, String> column, String value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.like(cb.lower(root.get(column)), cb.lower(cb.literal("%" + value + "%"))));
        return this;
    }


    public <Y extends Number> SpecificationBuilder<T> gt(SingularAttribute<? super T, Y> column, Y value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.gt(root.get(column), value));
        return this;
    }

    public <FCK extends Number & Comparable<FCK>> SpecificationBuilder<T> gte(SingularAttribute<? super T, FCK> column, FCK value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.greaterThanOrEqualTo(
                        root.get(column),
                        value
                )
        );
        return this;
    }

    public <Y extends Number & Comparable<Y>> SpecificationBuilder<T> lt(SingularAttribute<? super T, Y> column, Y value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.lessThan(root.get(column), value));
        return this;
    }

    public <Y extends Number & Comparable<Y>> SpecificationBuilder<T> lte(SingularAttribute<? super T, Y> column, Y value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.lessThanOrEqualTo(root.get(column), value));
        return this;
    }

    public <Y> SpecificationBuilder<T> ltDate(SingularAttribute<? super T, Y> column, Y value) {

        return this;
    }

    public <Y> SpecificationBuilder<T> gtDate(SingularAttribute<? super T, Y> column, Y value) {
        return this;
    }

    public <Y> SpecificationBuilder<T> dateEqualsAround(SingularAttribute<? super T, Y> column, Y value, final Duration duration) {
        //todo
        return this;
    }



    @Override
    public SpecificationBuilderInnerBuilder<T> inner() {
        return this;
    }

    @Override
    public SpecificationBuilder<T> and() {
        current = current.enterInner(GroupType.AND);
        return this;
    }

    @Override
    public SpecificationBuilder<T> or() {
        current = current.enterInner(GroupType.OR);
        return this;
    }

    @Override
    public SpecificationBuilder<T> exit() {
        current = current.exitInner();
        return this;
    }
}
