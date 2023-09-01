package rocha.andre.cloudvendor.domain.User.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import rocha.andre.cloudvendor.domain.User.User;
import rocha.andre.cloudvendor.domain.User.UserDto;
import rocha.andre.cloudvendor.infra.security.TokenJwtDto;
import rocha.andre.cloudvendor.infra.security.TokenService;

@Component
public class PerformLoginUseCase {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    TokenService tokenService;

    public TokenJwtDto performLogin(UserDto data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        //está chamando authenticateService
        Authentication authentication = manager.authenticate(authenticationToken);
        User userAuthenticated = (User) authentication.getPrincipal();

        String tokenJwt = tokenService.generateJwtToken(userAuthenticated);

        return new TokenJwtDto(tokenJwt);
    }
}
