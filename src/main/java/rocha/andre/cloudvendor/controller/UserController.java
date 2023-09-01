package rocha.andre.cloudvendor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocha.andre.cloudvendor.domain.User.UseCase.PerformLoginUseCase;
import rocha.andre.cloudvendor.domain.User.UserDto;
import rocha.andre.cloudvendor.infra.security.TokenJwtDto;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private PerformLoginUseCase performLoginUseCase;

    @PostMapping
    @Transactional
    public ResponseEntity performLogin(UserDto data) {
        TokenJwtDto tokenJwtAfterLogin = performLoginUseCase.performLogin(data);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer " + tokenJwtAfterLogin);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(tokenJwtAfterLogin.toString());
    }
}