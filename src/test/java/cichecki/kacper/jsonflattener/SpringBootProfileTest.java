package cichecki.kacper.jsonflattener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "bar2=test")
@AutoConfigureMockMvc
@ActiveProfiles("main")
class SpringBootProfileTest {

    @Value("${foo}")
    String foo;

    @Value("${bar}")
    String bar;

    @Value("${bar2}")
    String bar2;

    @Test
    void test1(){
        assertThat(foo).isEqualTo("main");
    }

    @Test
    void test2(){
        assertThat(bar).isEqualTo("bar");
    }

    @Test
    void test3(){
        assertThat(bar2).isEqualTo("test");
    }
}