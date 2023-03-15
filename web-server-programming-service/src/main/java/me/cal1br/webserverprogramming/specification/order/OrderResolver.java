package me.cal1br.webserverprogramming.specification.order;


import me.cal1br.webserverprogramming.base.model.BaseEntity;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.stereotype.Component;
import javax.persistence.Column;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a component that maps database column names to sortable columns, so the front end user can sort the data.
 * By abstracting the column names, to a constant value, we can keep the API of the service, nice, tidy and constant.
 * This component guarantees that any changes made to the column names, will not be reflected to the API.
 * <br/>
 * <br/>
 * The frontend sortable column names will be mapped to the {@link Column} name, except in the cases that {@link OrderName} is present.
 *
 */
@Component
public class OrderResolver {
    //todo
    Map<Class<? extends BaseEntity>, Map<String,String>> map = new HashMap<>();
}
