package io.tchepannou.academy.user.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"classpath:/sql/clean.sql", "classpath:/sql/PersonController.sql"})
public class PersonControllerIT {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void findByRole() throws Exception {
        // When
        mockMvc
                .perform(
                        get("/academy/v1/persons/roles/100")
                                .contentType(MediaType.APPLICATION_JSON)
                )

                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId", notNullValue()))
                .andExpect(jsonPath("$.person.id", is(100)))
                .andExpect(jsonPath("$.person.email", is("ray.sponsible@gmail.com")))
                .andExpect(jsonPath("$.person.firstName", is("Ray")))
                .andExpect(jsonPath("$.person.lastName", is("Sponsible")))
                .andExpect(jsonPath("$.person.language", is("en")))
                .andExpect(jsonPath("$.person.pictureUrl", is("http://img.com/ray.sponsible")))
                .andExpect(jsonPath("$.person.biography", is("Bio...")))
        ;    }

}
