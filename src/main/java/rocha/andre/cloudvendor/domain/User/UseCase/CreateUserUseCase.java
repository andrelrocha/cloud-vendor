package rocha.andre.cloudvendor.domain.User.UseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import rocha.andre.cloudvendor.domain.User.User;
import rocha.andre.cloudvendor.domain.User.UserDto;
import rocha.andre.cloudvendor.domain.User.UserDtoReturn;
import rocha.andre.cloudvendor.domain.User.UserRepository;
import rocha.andre.cloudvendor.infra.exceptions.ValidationException;

@Component
public class CreateUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDtoReturn createUser(UserDto data) {
        boolean userExists = userRepository.userExistsByLogin(data.login());

        if (userExists) {
            throw new ValidationException("Email or user creation already exists in our database");
        }

        User newUser = new User(data);
        String encodedPassword = bCryptPasswordEncoder.encode(data.password());
        newUser.setPassword(encodedPassword);

        userRepository.save(newUser);

        return new UserDtoReturn(newUser);
    }
}
