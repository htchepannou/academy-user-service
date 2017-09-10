package io.tchepannou.academy.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.user.dto.auth.AuthRequest;
import io.tchepannou.academy.user.exception.BusinessError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"classpath:/sql/clean.sql", "classpath:/sql/AuthController.sql"})
public class AuthControllerErrorIT extends ControllerITSupport{
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    //-- LOGIN
    @Test
    public void shouldReturn400WhenLoginWithNoEmail() throws Exception {
        // Given
        final AuthRequest req = new AuthRequest();
        req.setPassword("bar123");
        req.setRole("invalid-role");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void shouldReturn400WhenLoginWithNoPassword() throws Exception {
        // Given
        final AuthRequest req = new AuthRequest();
        req.setEmail("ray.sponsible@gmail.com");
        req.setRole("invalid-role");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
        ;
    }


    @Test
    public void shouldReturn400WhenLoginWithNoRole() throws Exception {
        // Given
        final AuthRequest req = new AuthRequest();
        req.setEmail("ray.sponsible@gmail.com");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    public void shouldReturn400WhenLoginWithUnknownRole() throws Exception {
        // Given
        final AuthRequest req = new AuthRequest();
        req.setEmail("ray.sponsible@gmail.com");
        req.setPassword("bar123");
        req.setRole("invalid-role");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.INVALID_ROLE.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.INVALID_ROLE.getDescription())))
        ;
    }

    @Test
    public void shouldReturn409WhenLoginWithInvalidPassword() throws Exception {
        // Given
        final AuthRequest req = new AuthRequest();
        req.setEmail("ray.sponsible@gmail.com");
        req.setPassword("invalid-password");
        req.setRole("student");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.PASWORD_MISMATCH.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.PASWORD_MISMATCH.getDescription())))
        ;
    }

    @Test
    public void shouldReturn409WhenLoginWithInvalidEmail() throws Exception {
        // Given
        final AuthRequest request = new AuthRequest();
        request.setEmail("invalid-email@gmail.com");
        request.setPassword("123bar");
        request.setRole("student");

        // When
        final String json = mapper.writeValueAsString(request);
        mockMvc
                .perform(
                        post("/academy/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.PERSON_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.PERSON_NOT_FOUND.getDescription())))
        ;
    }

    @Test
    public void shouldReturn409WhenLoginWithInvalidRole() throws Exception {
        // Given
        final AuthRequest request = new AuthRequest();
        request.setEmail("ray.sponsible@gmail.com");
        request.setPassword("123bar");
        request.setRole("teacher");

        // When
        final String json = mapper.writeValueAsString(request);
        mockMvc
                .perform(
                        post("/academy/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.ROLE_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.ROLE_NOT_FOUND.getDescription())))
        ;
    }

    @Test
    public void shouldReturn409WhenLoginWithEmailWithNoAccount() throws Exception {
        // Given
        final AuthRequest req = new AuthRequest();
        req.setEmail("john.doe@gmail.com");
        req.setPassword("123bar");
        req.setRole("student");

        // When
        final String jsonRequest = mapper.writeValueAsString(req);
        mockMvc
                .perform(
                        post("/academy/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.ACCOUNT_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.ACCOUNT_NOT_FOUND.getDescription())))
        ;
    }


    //-- VALIDATE
    @Test
    public void shouldReturn404WhenValidatingBadToken() throws Exception{
        mockMvc
                .perform(
                        get("/academy/v1/auth/access_token/invalid-token")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.SESSION_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.SESSION_NOT_FOUND.getDescription())))
        ;
    }

    @Test
    public void shouldReturn401WhenValidatingInactiveToken() throws Exception{
        mockMvc
                .perform(
                        get("/academy/v1/auth/access_token/12345678901234567890123456789000")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.SESSION_INACTIVE.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.SESSION_INACTIVE.getDescription())))
        ;
    }

    @Test
    public void shouldReturn401WhenValidatingExpiredToken() throws Exception{
        mockMvc
                .perform(
                        get("/academy/v1/auth/access_token/12345678901234567890123456789099")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.errors.length()", is(1)))
                .andExpect(jsonPath("$.errors[0].code", is(BusinessError.SESSION_EXPIRED.getCode())))
                .andExpect(jsonPath("$.errors[0].description", is(BusinessError.SESSION_EXPIRED.getDescription())))
        ;
    }
}
