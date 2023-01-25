package me.cal1br.webserverprogramming.api.user.controller;


import me.cal1br.webserverprogramming.api.base.controller.BaseController;
import me.cal1br.webserverprogramming.api.user.filter.UserFilter;
import me.cal1br.webserverprogramming.api.user.model.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;


@RequestMapping(
        path = "user",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
public interface UserController extends BaseController<UserDTO, UserFilter> {
    @PostMapping(path = "/register")
    String register(@RequestBody @Valid UserDTO dto);
    @GetMapping(path = "/login")
    String login(@RequestParam String username, @RequestParam String password);

}
