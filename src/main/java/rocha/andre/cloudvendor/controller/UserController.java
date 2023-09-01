package rocha.andre.cloudvendor.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocha.andre.cloudvendor.domain.User.UseCase.CreateUserUseCase;
import rocha.andre.cloudvendor.domain.User.UseCase.PerformLoginUseCase;
import rocha.andre.cloudvendor.domain.User.UserDto;
import rocha.andre.cloudvendor.domain.User.UserDtoReturn;
import rocha.andre.cloudvendor.infra.security.TokenJwtDto;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private PerformLoginUseCase performLoginUseCase;

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity performLogin(@RequestBody @Valid UserDto data) {
        TokenJwtDto tokenJwtAfterLogin = performLoginUseCase.performLogin(data);

        return ResponseEntity.ok(tokenJwtAfterLogin);
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody @Valid UserDto data) {
        UserDtoReturn newUser = createUserUseCase.createUser(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}