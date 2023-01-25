package me.cal1br.webserverprogramming.base.repository;

import me.cal1br.webserverprogramming.api.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.base.model.BaseEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public abstract class BaseFilterRepositoryImpl<ENTITY extends BaseEntity, FILTER extends BaseFilter, REPOSITORY extends JpaSpecificationExecutor<ENTITY>> implements FilterRepository<ENTITY, FILTER> {

    private final REPOSITORY repository;

    protected BaseFilterRepositoryImpl(final REPOSITORY repository) {
        this.repository = repository;
    }

    @Override
    public List<ENTITY> findByFilter(final FILTER filter) {
        if(filter == null){
            throw new IllegalArgumentException("Filter cannot be null");
        }
        return this.repository.findAll(this.getSpecification(filter));
    }

    @Override
    public List<ENTITY> countByFilter(final FILTER filter) {
        //todo
        return null;
    }

    protected abstract Specification<ENTITY> getSpecification(FILTER filter);
}
