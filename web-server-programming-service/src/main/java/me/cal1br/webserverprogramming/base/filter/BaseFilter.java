package me.cal1br.webserverprogramming.base.filter;

import lombok.Data;

import java.util.List;

@Data
public class BaseFilter {
    private List<Long> idList;
    private long offset;
    private long limit;
}
