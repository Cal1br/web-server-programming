package me.cal1br.webserverprogramming.specification.builder;

public interface SpecificationBuilderInnerBuilder<T> {


    /**
     *
     * @return
     */
    SpecificationBuilder<T> and();

    /**
     *
     * @return
     */
    SpecificationBuilder<T> or();

    /**
     * Used to exit inner specification
     * @throws IllegalStateException if not currently in an inner grouping
     * @return
     */
    SpecificationBuilder<T> exit();

}

