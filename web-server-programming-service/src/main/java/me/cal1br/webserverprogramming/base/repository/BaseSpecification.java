package me.cal1br.webserverprogramming.base.repository;

import me.cal1br.webserverprogramming.api.base.filter.ColumnOrder;
import me.cal1br.webserverprogramming.base.model.BaseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseSpecification<ENTITY extends BaseEntity> {
    public List<Order> resolveOrder(List<ColumnOrder> columnOrders, Root<ENTITY> root, CriteriaBuilder cb) {
        final List<Order> orderList = new LinkedList<>();
        for (final ColumnOrder columnOrder : columnOrders) {
            switch (columnOrder.getOrder()) {
                case ASCENDING:
                    orderList.add(cb.asc(root.get(columnOrder.getColumnName())));
                    break;
                case DESCENDING:
                    orderList.add(cb.desc(root.get(columnOrder.getColumnName())));
            }
        }
        return orderList;
    }
}
