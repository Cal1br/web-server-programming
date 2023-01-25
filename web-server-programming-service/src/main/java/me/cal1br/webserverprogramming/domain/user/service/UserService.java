package me.cal1br.webserverprogramming.domain.user.service;

import me.cal1br.webserverprogramming.api.user.filter.UserFilter;
import me.cal1br.webserverprogramming.api.user.model.UserDTO;
import me.cal1br.webserverprogramming.base.service.BaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserService extends BaseService<UserDTO, UserFilter> {
    String register(UserDTO dto);
    String login(String username, String password);

}
