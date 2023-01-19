package me.cal1br.webserverprogramming.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

/**
 * Specification builder using metamodel
 * <p>
 * Starts with and
 *
 * @param <T>
 */
public class SpecificationBuilder<T> {
    private final Root<T> root;
    private final CriteriaQuery<?> query;
    private final CriteriaBuilder cb;
    private final List<PredicateHolder> list;
    private PredicateHolder current;

    public SpecificationBuilder(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
        this.root = root;
        this.query = query;
        this.cb = cb;

        list = new LinkedList<>();
        current = new PredicateHolder(cb, GroupTypes.AND);
        list.add(current);
    }

    public SpecificationBuilder<T> and() {
        current = new PredicateHolder(cb, GroupTypes.AND);
        list.add(current);
        return this;
    }

    public SpecificationBuilder<T> or() {
        current = new PredicateHolder(cb, GroupTypes.OR);
        list.add(current);
        return this;
    }

    public <Y> SpecificationBuilder<T> eq(SingularAttribute<? super T, Y> column, Y value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.equal(root.get(column), value));
        return this;
    }

    public <Y extends String> SpecificationBuilder<T> startsWith(SingularAttribute<? super T, String> column, Y value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.like(root.get(column), cb.literal(value + "%")));
        return this;
    }

    public <Y extends String> SpecificationBuilder<T> startsWithIgnoreCase(SingularAttribute<? super T, String> column, Y value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.like(cb.lower(root.get(column)), cb.lower(cb.literal(value + "%"))));
        return this;
    }

    public <Y extends String> SpecificationBuilder<T> contains(SingularAttribute<? super T, String> column, Y value) {
        if (value == null) {
            return this;
        }
        current.addPredicate(cb.like(root.get(column), cb.literal("%" + value + "%")));
        return this;
    }

    public <Y extends String> SpecificationBuilder<T> containsIgnoreCase(SingularAttribute<? super T, String> column, Y value) {
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
                (Expression<FCK>)root.get(column),
                        value
                )
        );
        return this;
    }

    public <Y extends Number> SpecificationBuilder<T> lt(SingularAttribute<? super T, Y> column, Y value) {
        if (value == null) {
            return this;
        }

        current.addPredicate(cb.lt(root.get(column), value));

        return this;
    }

    public <Y> SpecificationBuilder<T> lte(SingularAttribute<? super T, Y> column, Y value) {
        if (value == null) {
            return this;
        }
        //todo!
        //root.get(column)
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
}
