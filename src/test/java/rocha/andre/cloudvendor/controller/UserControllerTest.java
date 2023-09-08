package rocha.andre.cloudvendor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import rocha.andre.cloudvendor.domain.User.UseCase.PerformLoginUseCase;
import rocha.andre.cloudvendor.domain.User.User;
import rocha.andre.cloudvendor.domain.User.UserDto;
import rocha.andre.cloudvendor.domain.User.UserDtoReturn;
import rocha.andre.cloudvendor.infra.security.TokenJwtDto;
import rocha.andre.cloudvendor.infra.security.TokenService;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
    @MockBean
    private PerformLoginUseCase performLoginUseCase;

    @MockBean
    private TokenService tokenService;


    @Test
    @DisplayName("It should return code 200 after creating a user, and return its info")
    void createUserScenario1() throws Exception {
        //given
        UserDtoReturn expectedUserDtoReturn = new UserDtoReturn(1L, "andre@email.com");
        when(createUserUseCase.createUser(any()))
                .thenReturn(expectedUserDtoReturn);

        //when
        var response = mvc.perform(
                post("/login/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJacksonTester.write(
                                new UserDto("andre@email.com", "123")
                        ).getJson()
                        ))
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var expectedJson = userDtoReturnJacksonTester.write(
                expectedUserDtoReturn
        ).getJson();

        //then
        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }

    @Test
    @DisplayName("It should return a valid JWT token after login")
    void performLoginScenario1() throws Exception {
        //given
        var user = new User(1L,"andre@email.com", "123");
        var expectedJwt = tokenService.generateJwtToken(user);
        var tokenJWT = new TokenJwtDto(expectedJwt);

        when(performLoginUseCase.performLogin(any()))
                .thenReturn(tokenJWT);

        ObjectMapper objectMapper = new ObjectMapper();
        String tokenJson = objectMapper.writeValueAsString(tokenJWT);

        //when
        var response = mvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userDtoJacksonTester.write(
                                                new UserDto("andre@email.com", "123")
                                        ).getJson()
                                ))
                .andReturn()
                .getResponse();

        //then
        assertThat(response.getContentAsString()).isEqualTo(tokenJson);
    }

    @Test
    @DisplayName("It should return a valid login within the JWT token after login")
    void performLoginScenario2() throws Exception {
        //given
        var user = new User(1L,"andre@email.com", "123");
        var expectedJwt = tokenService.generateJwtToken(user);
        var tokenJWT = new TokenJwtDto(expectedJwt);

        when(tokenService.getSubject(anyString()))
                .thenReturn(user.getLogin());

        when(performLoginUseCase.performLogin(any()))
                .thenReturn(tokenJWT);

        //when
        var response = mvc.perform(
                        post("/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userDtoJacksonTester.write(
                                                new UserDto("andre@email.com", "123")
                                        ).getJson()
                                ))
                .andReturn()
                .getResponse();

        var expectedLogin = user.getLogin();
        var actualLogin = tokenService.getSubject(response.getContentAsString());

        //then
        assertThat(actualLogin).isEqualTo(expectedLogin);
    }
}
