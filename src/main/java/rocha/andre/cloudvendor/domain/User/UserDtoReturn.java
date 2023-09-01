package rocha.andre.cloudvendor.domain.User;

public record UserDtoReturn (Long id,
                             String login) {

    public UserDtoReturn(User user) {
        this(user.getId(), user.getLogin());
    }
}
