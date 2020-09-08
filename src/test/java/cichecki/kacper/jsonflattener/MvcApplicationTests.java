package cichecki.kacper.jsonflattener;

import cichecki.kacper.jsonflattener.controller.ViewController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MvcApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ViewController controller;

    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void checkUnauthorized() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/profile"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }


}
