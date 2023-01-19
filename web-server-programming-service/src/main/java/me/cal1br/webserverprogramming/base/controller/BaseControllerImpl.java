package me.cal1br.webserverprogramming.base.controller;

import me.cal1br.webserverprogramming.api.base.controller.BaseController;
import me.cal1br.webserverprogramming.api.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.api.base.model.BaseDTO;

import java.util.Collections;
import java.util.List;

public abstract class BaseControllerImpl<DTO extends BaseDTO, FILTER extends BaseFilter>
        implements BaseController<DTO, FILTER> {
    public List<DTO> findByFilter(FILTER filter) {
        return Collections.emptyList(); //TODO
    }

    public List<DTO> saveAll(List<DTO> dtos) {
        return Collections.emptyList(); //TODO
    }
}
