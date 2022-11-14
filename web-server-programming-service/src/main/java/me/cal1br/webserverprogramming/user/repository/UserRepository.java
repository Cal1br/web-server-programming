package me.cal1br.webserverprogramming.user.repository;

import me.cal1br.webserverprogramming.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Long, UserEntity> {
}
