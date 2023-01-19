package me.cal1br.webserverprogramming.api.base.filter;

import lombok.Data;

import java.util.List;

@Data
public abstract class BaseFilter {
    private List<Long> idList;
    private List<ColumnOrder> columnOrderList;
    private long offset;
    private long limit;
}