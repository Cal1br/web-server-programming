package me.cal1br.webserverprogramming.api.base.controller;

import me.cal1br.webserverprogramming.api.base.filter.BaseFilter;
import me.cal1br.webserverprogramming.api.base.model.BaseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface BaseController<DTO extends BaseDTO, FILTER extends BaseFilter> {

    @PostMapping(path = "/filter")
    List<DTO> findByFilter(FILTER filter);

    @PostMapping(path = "/save")
    List<DTO> saveAll(@Valid @RequestBody List<DTO> dtoList);
}
