package me.cal1br.webserverprogramming.specification.builder;

public interface SpecificationBuilderInner<T> extends SpecificationBuilder<T>{
    SpecificationBuilderInnerBuilder<T> exitInner();
}
