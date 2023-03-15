package me.cal1br.webserverprogramming.api.base.filter;

import lombok.Data;

@Data
public class ColumnOrder {
    private String columnName;
    private Order order;
}
