package cichecki.kacper.jsonflattener;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ViewControllerTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template
                .withBasicAuth("user", "password")
                .getForEntity(base.toString(), String.class);

        assertThat(response.getBody().equals("Anything"));
    }

    @Test
    public void getHello2() throws Exception {
        String response = template
                .withBasicAuth("user", "password")
                .getForObject(base.toString(), String.class);

//        assertThat(response.contains("Anything"));
        assertThat(false);
    }

}
