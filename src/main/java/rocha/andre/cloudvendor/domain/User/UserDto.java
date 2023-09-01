package rocha.andre.cloudvendor.domain.User;

import jakarta.validation.constraints.NotNull;

public record UserDto(
        @NotNull
        String login,
        @NotNull
        String password
) {  }