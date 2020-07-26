package cichecki.kacper.jsonflattener;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import cichecki.kacper.jsonflattener.controller.ViewController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.containsString;


@SpringBootTest
@AutoConfigureMockMvc
class JsonFlattenerApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ViewController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void checkUnuthirized() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
//                .andExpect(content().string(containsString("Hello, World")));
    }

/*    @Test
    @WithMockUser(username = "test@test.com", password = "test", roles = "USER")
    public void checkAuthirized() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/profile").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*/

}
