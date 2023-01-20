package me.cal1br.webserverprogramming.domain.user.repository;

import me.cal1br.webserverprogramming.api.base.filter.ColumnOrder;
import me.cal1br.webserverprogramming.api.user.filter.UserFilter;
import me.cal1br.webserverprogramming.base.model.BaseEntity;
import me.cal1br.webserverprogramming.base.repository.BaseFilterRepositoryImpl;
import me.cal1br.webserverprogramming.domain.user.model.UserEntity;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserFilterRepositoryImpl extends BaseFilterRepositoryImpl<UserEntity, UserFilter, UserRepository>
        implements UserFilterRepository {

    public UserFilterRepositoryImpl(@Lazy UserRepository repository) {
        super(repository);
    }

    @Override
    protected Specification<UserEntity> getSpecification(final UserFilter filter) {
        filter.setColumnOrderList(translateColumns(UserEntity.class, filter.getColumnOrderList()));

        return new UserSpecification(filter);
    }

    private List<ColumnOrder> translateColumns(Class<? extends BaseEntity> c, List<ColumnOrder> columnOrderList) {
        //todo
        return columnOrderList;
    }
}
