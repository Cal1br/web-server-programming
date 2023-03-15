package me.cal1br.webserverprogramming.base.service;

import me.cal1br.webserverprogramming.api.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.api.base.model.BaseDTO;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BaseService<DTO extends BaseDTO, FILTER extends BaseFilter> {
    Optional<DTO> findById(long id);
    List<DTO> create(List<DTO> dtoList);
    List<DTO> update(List<DTO> dtoList);
    List<DTO> findAll(FILTER filter);
    int delete(Collection<Long> idList);
}
