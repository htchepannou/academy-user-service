package io.tchepannou.academy.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.tchepannou.academy.user.dao.SessionDao;
import io.tchepannou.academy.user.domain.Session;
import io.tchepannou.academy.user.dto.auth.AuthRequest;
import io.tchepannou.academy.user.dto.auth.AuthResponse;
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

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
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
public class AuthControllerIT extends ControllerITSupport{
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }



    @Test
    public void login() throws Exception {
        final Date now = new Date();
        Thread.sleep(100);

        final AuthRequest req = new AuthRequest();
        req.setEmail("ray.sponsible@gmail.com");
        req.setPassword("bar123");
        req.setRole("student");

        final String jsonRequest = mapper.writeValueAsString(req);
        final String jsonResponse = mockMvc
                .perform(
                        post("/academy/v1/auth")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequest)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.session.accessToken", notNullValue()))
                .andExpect(jsonPath("$.session.roleId", is(1)))
                .andExpect(jsonPath("$.session.accountId", is(100)))
                .andExpect(jsonPath("$.session.expiryDateTime", notNullValue()))
                .andExpect(jsonPath("$.session.active", is(true)))
                .andReturn()
                .getResponse()
                .getContentAsString()
                ;
        final AuthResponse resp = mapper.readValue(jsonResponse, AuthResponse.class);


        final Session session = sessionDao.findOne(resp.getSession().getId());
        assertThat(session.getAccessToken()).isNotNull();
        assertThat(session.getExpiryDateTime()).isAfter(now);
        assertThat(session.getAccountId()).isEqualTo(100);
        assertThat(session.getRoleId()).isEqualTo(1);
        assertThat(session.isActive()).isTrue();
    }

    @Test
    public void validate() throws Exception{
        mockMvc
                .perform(
                        get("/academy/v1/auth/access_token/12345678901234567890123456789012")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.session.accessToken", is("12345678901234567890123456789012")))
                .andExpect(jsonPath("$.session.roleId", is(1)))
                .andExpect(jsonPath("$.session.accountId", is(201)))
                .andExpect(jsonPath("$.session.expiryDateTime", notNullValue()))
                .andExpect(jsonPath("$.session.active", is(true)))
                ;
    }
}
