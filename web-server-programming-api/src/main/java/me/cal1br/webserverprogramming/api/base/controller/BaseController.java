package me.cal1br.webserverprogramming.api.base.controller;

import me.cal1br.webserverprogramming.api.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.api.base.model.BaseDTO;

import java.util.List;

public interface BaseController<DTO extends BaseDTO, FILTER extends BaseFilter> {

    List<DTO> findByFilter(FILTER filter);

    List<DTO> saveAll(List<DTO> dtos);

}
