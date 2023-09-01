package rocha.andre.cloudvendor.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocha.andre.cloudvendor.domain.User.User;
import rocha.andre.cloudvendor.domain.User.UserRepository;


@Component
public class AuthenticateUserWithValidJwt {

    @Autowired
    private UserRepository userRepository;

    public User findUserAuthenticated(String login) {
        return (User) userRepository.findByLogin(login);
    }

}