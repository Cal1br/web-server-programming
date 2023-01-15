package me.cal1br.webserverprogramming.base.service;

import me.cal1br.webserverprogramming.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.base.model.BaseDTO;

import java.util.List;

public interface BaseService<DTO extends BaseDTO, FILTER extends BaseFilter> {
    List<DTO> create(List<DTO> dtoList);
    List<DTO> update(List<DTO> dtoList);
    List<DTO> findAll(FILTER filter);
}
