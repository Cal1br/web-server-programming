package me.cal1br.webserverprogramming.domain.user.repository;

import me.cal1br.webserverprogramming.domain.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity>, UserFilterRepository {
    Optional<UserEntity> findByUsername(String username);
}
