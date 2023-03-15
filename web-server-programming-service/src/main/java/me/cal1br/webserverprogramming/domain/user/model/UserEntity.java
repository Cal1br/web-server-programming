package me.cal1br.webserverprogramming.domain.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cal1br.webserverprogramming.base.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phoneNumber")
    private String phoneNumber;
}
