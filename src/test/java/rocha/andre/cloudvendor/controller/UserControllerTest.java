package rocha.andre.cloudvendor.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import rocha.andre.cloudvendor.domain.User.UseCase.CreateUserUseCase;
import rocha.andre.cloudvendor.domain.User.UserDto;
import rocha.andre.cloudvendor.domain.User.UserDtoReturn;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<UserDto> userDtoJacksonTester;
    @Autowired
    private JacksonTester<UserDtoReturn> userDtoReturnJacksonTester;

    @MockBean
    private CreateUserUseCase createUserUseCase;


    @Test
    @DisplayName("It should return code 200 after creating a user, and return its info")
    void createUserScenario1() throws Exception {
        UserDtoReturn expectedUserDtoReturn = new UserDtoReturn(1L, "andre@email.com");
        when(createUserUseCase.createUser(any(UserDto.class)))
                .thenReturn(expectedUserDtoReturn);

        var response = mvc.perform(
                post("/login/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJacksonTester.write(
                                new UserDto("andre", "123")
                        ).getJson()
                        ))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var expectedJson = userDtoReturnJacksonTester.write(
                expectedUserDtoReturn
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}
