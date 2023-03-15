package me.cal1br.webserverprogramming.domain.user.repository;

import me.cal1br.webserverprogramming.api.user.filter.UserFilter;
import me.cal1br.webserverprogramming.base.repository.FilterRepository;
import me.cal1br.webserverprogramming.domain.user.model.UserEntity;

public interface UserFilterRepository extends FilterRepository<UserEntity, UserFilter> {
}
