package cichecki.kacper.jsonflattener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "/foo.properties")
class SpringBootPropertySourceTest {

    @Value("${testProperty}")
    String testProperty;

    @Test
    void test(){
        assertThat(testProperty).isEqualTo("testValue");
    }
}