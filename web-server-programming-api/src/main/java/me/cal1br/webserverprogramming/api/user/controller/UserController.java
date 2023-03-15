package me.cal1br.webserverprogramming.api.user.controller;


import me.cal1br.webserverprogramming.api.base.controller.BaseController;
import me.cal1br.webserverprogramming.api.user.filter.UserFilter;
import me.cal1br.webserverprogramming.api.user.model.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@RequestMapping(
        path = "user"
)
public interface UserController extends BaseController<UserDTO, UserFilter> {
    @PostMapping(path = "/register")
    ResponseEntity<String> register(@RequestBody @Valid UserDTO dto);
    @GetMapping(path = "/login")
    ResponseEntity<String> login(@RequestParam String username, @RequestParam String password);

}
