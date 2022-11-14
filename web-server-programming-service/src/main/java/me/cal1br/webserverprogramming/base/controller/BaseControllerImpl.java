package me.cal1br.webserverprogramming.base.controller;

import me.cal1br.webserverprogramming.base.model.BaseDTO;

import java.util.Collections;
import java.util.List;

public abstract class BaseControllerImpl<DTO extends BaseDTO, FILTER> {
    protected List<DTO> findByFilter(FILTER filter) {
        return Collections.emptyList(); //TODO
    }

    protected List<DTO> saveAll(List<DTO> dtos) {
        return Collections.emptyList(); //TODO
    }
}
