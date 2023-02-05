package me.cal1br.webserverprogramming.domain.user.controller;

import me.cal1br.webserverprogramming.api.user.controller.UserController;
import me.cal1br.webserverprogramming.api.user.filter.UserFilter;
import me.cal1br.webserverprogramming.api.user.model.UserDTO;
import me.cal1br.webserverprogramming.base.controller.BaseControllerImpl;
import me.cal1br.webserverprogramming.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserControllerImpl extends BaseControllerImpl<UserDTO, UserFilter, UserService> implements UserController {

    public UserControllerImpl(final UserService service) {
        super(service);
    }

    @Override
    public List<UserDTO> saveAll(final List<UserDTO> userDTOS) {
        //preventing attack, flooding the database
        throw new UnsupportedOperationException();
    }

    @Override
    public ResponseEntity<String> register(@Valid final UserDTO dto) {
        return ResponseEntity.ok(getService().register(dto));
    }

    @Override
    public ResponseEntity<String> login(final String username, final String password) {
        return ResponseEntity.ok(getService().login(username, password));
    }
}
