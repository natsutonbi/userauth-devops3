package com.example.demo.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//随机端口启动
// @EnableWebMvc
// @RunWith(SpringRunner.class)
public class authenTest{
    // @Autowired
    // MockMvc mvc;

    // @WithMockUser(authorities="USER")
    // @Test
    // public void endpointWhenUserAuthorityThenAuthorized() throws Exception{
    //     this.mvc.perform(MockMvcRequestBuilders.get("/hello"))
    //         .andExpect(MockMvcResultMatchers.status().isOk());
    // }

    // @WithMockUser
    // @Test
    // public void endpointWhenNotUserAuthorityThenForbidden() throws Exception {
    //     this.mvc.perform(MockMvcRequestBuilders.get("/endpoint"))
    //         .andExpect(MockMvcResultMatchers.status().isForbidden());
    // }

    // @Test
    // public void anyWhenUnauthenticatedThenUnauthorized() throws Exception {
    //     this.mvc.perform(MockMvcRequestBuilders.get("/any"))
    //         .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    // }
}
