package me.cal1br.webserverprogramming.specification.builder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;
import java.time.Duration;
import java.util.Collection;

/**
 * Specification builder using metamodel
 * <p>
 * Starts with and
 *
 * @param <T>
 */
public interface SpecificationBuilder<T> {

    static <X> SpecificationBuilder<X> init(final Root<X> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
        return new SpecificationBuilderImpl<>(root, query, cb);
    }

    SpecificationBuilder<T> gAnd();

    SpecificationBuilder<T> gOr();

    /**
     * Inverts the next predicate
     * @return the builder
     */
    SpecificationBuilder<T> not();

    <Y> SpecificationBuilder<T> eq(SingularAttribute<? super T, Y> column, Y value);
    <Y> SpecificationBuilder<T> in(SingularAttribute<? super T, Y> column, Collection<Y> value);

    SpecificationBuilder<T> startsWith(SingularAttribute<? super T, String> column, String value);

    SpecificationBuilder<T> startsWithIgnoreCase(SingularAttribute<? super T, String> column, String value);

    SpecificationBuilder<T> contains(SingularAttribute<? super T, String> column, String value);

    SpecificationBuilder<T> containsIgnoreCase(SingularAttribute<? super T, String> column, String value);

    <Y extends Number> SpecificationBuilder<T> gt(SingularAttribute<? super T, Y> column, Y value);

    <Y extends Number & Comparable<Y>> SpecificationBuilder<T> gte(SingularAttribute<? super T, Y> column, Y value);

    <Y extends Number & Comparable<Y>> SpecificationBuilder<T> lt(SingularAttribute<? super T, Y> column, Y value);

    <Y extends Number & Comparable<Y>> SpecificationBuilder<T> lte(SingularAttribute<? super T, Y> column, Y value);

    <Y> SpecificationBuilder<T> ltDate(SingularAttribute<? super T, Y> column, Y value);

    <Y> SpecificationBuilder<T> gtDate(SingularAttribute<? super T, Y> column, Y value);

    <Y> SpecificationBuilder<T> dateEqualsAround(SingularAttribute<? super T, Y> column, Y value, final Duration duration);

    SpecificationBuilderInnerBuilder<T> inner();

}
