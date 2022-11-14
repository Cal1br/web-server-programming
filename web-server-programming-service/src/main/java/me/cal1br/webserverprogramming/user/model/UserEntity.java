package me.cal1br.webserverprogramming.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    private long id;
    private String username;
    private String password;

}
