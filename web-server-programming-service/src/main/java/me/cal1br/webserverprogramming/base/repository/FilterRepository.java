package me.cal1br.webserverprogramming.base.repository;

import me.cal1br.webserverprogramming.api.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.base.model.BaseEntity;

import java.util.List;

public interface FilterRepository<ENTITY extends BaseEntity, FILTER extends BaseFilter> {

    List<ENTITY> findByFilter(FILTER filter);
    List<ENTITY> countByFilter(FILTER filter);

}
