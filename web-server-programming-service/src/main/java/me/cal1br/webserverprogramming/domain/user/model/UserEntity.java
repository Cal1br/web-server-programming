package me.cal1br.webserverprogramming.domain.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cal1br.webserverprogramming.base.model.BaseEntity;
import me.cal1br.webserverprogramming.specification.order.OrderName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

}
