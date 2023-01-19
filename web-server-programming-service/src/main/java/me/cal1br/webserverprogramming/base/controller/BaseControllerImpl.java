package me.cal1br.webserverprogramming.base.controller;

import me.cal1br.webserverprogramming.api.base.controller.BaseController;
import me.cal1br.webserverprogramming.api.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.api.base.model.BaseDTO;
import me.cal1br.webserverprogramming.base.service.BaseService;

import java.util.Collections;
import java.util.List;

public abstract class BaseControllerImpl<DTO extends BaseDTO, FILTER extends BaseFilter, SERVICE extends BaseService>
        implements BaseController<DTO, FILTER> {

    private final SERVICE service;

    protected BaseControllerImpl(final SERVICE service) {
        this.service = service;
    }

    public List<DTO> findByFilter(FILTER filter) {
        return service.findAll(filter);
        //return Collections.emptyList(); //TODO
    }

    public List<DTO> saveAll(List<DTO> dtos) {
        return Collections.emptyList(); //TODO
    }
}