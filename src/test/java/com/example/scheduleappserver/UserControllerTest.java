package com.example.scheduleappserver;

import com.example.scheduleappserver.dto.user.LoginRequestDto;
import com.example.scheduleappserver.dto.user.UserRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("회원가입 controller 테스트")
    void signupControllerTest() throws Exception {
        // given
        UserRequestDto requestDto = new UserRequestDto("nickname", "username2", "password", false, null);
        String requestBody = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions resultActions = mvc.perform(post("/api/signup")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("잘못된 형식의 비밀번호 확인")
    void incorrectFormatPasswordCheck() throws Exception{
        //given
        UserRequestDto requestDto = new UserRequestDto("nickname", "username", "pass", false, null);
        String requestBody = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions resultActions = mvc.perform(post("/api/signup")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("잘못된 형식의 username 확인")
    void incorrectFormatUsernameCheck() throws Exception{
        //given
        UserRequestDto requestDto = new UserRequestDto("nickname", "user", "password", false, null);
        String requestBody = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions resultActions = mvc.perform(post("/api/signup")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("login controller test")
    void loginControllerTest() throws Exception{
        // given
        LoginRequestDto requestDto = new LoginRequestDto("username", "password");
        String requestBody = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions resultActions = mvc.perform(get("/api/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @Disabled
    @DisplayName("login 잘못된 형식 테스트")
    void incorrectFormatLoginCheck() throws Exception {
        //given
        LoginRequestDto requestDto = new LoginRequestDto("username", "passwor");
        String requestBody = objectMapper.writeValueAsString(requestDto);

        // when
        ResultActions resultActions = mvc.perform(get("/api/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isInternalServerError())
                .andExpect((ResultMatcher) jsonPath("message", is("비밀번호를 다시확인해 주세요")));
    }

}
