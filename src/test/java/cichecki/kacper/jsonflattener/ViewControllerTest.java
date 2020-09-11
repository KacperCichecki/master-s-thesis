package cichecki.kacper.jsonflattener;

import java.net.URL;
import cichecki.kacper.jsonflattener.controller.ViewController;
import cichecki.kacper.jsonflattener.service.MyUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;


// w ten sposób podnosimy całą aplikację wraz z serverem co jest kosztowną operacja
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("deploy")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ViewControllerTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private ViewController controller;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
    }

    @Test
    @Order(1)
    public void contexLoads() {
        assertThat(controller).isNotNull();
    }


    @Test
    @Order(2)
    public void getMainPageWhenNotLoggedIn() {
        ResponseEntity<String> response = template
                .getForEntity(base.toString(), String.class);

        String responseString = response.getBody();
        assertThat(responseString).isNotNull();
        Assert.isTrue(responseString.contains("put here flatten json"), "response should contains proper string");
    }

    @Test
    @Order(3)
    public void getProfiePageWhenLoggedIn() {
        ResponseEntity<String> response = template
                .withBasicAuth("test@test.com", "test")
                .getForEntity(base.toString() + "/profile", String.class);

        String responseString = response.getBody();
        assertThat(responseString).isNotNull();
        Assert.isTrue(responseString.contains("table table-dark"), "response should contains proper string");
    }

    @Test
    @Order(4)
    public void dontAllowNotLoggedInUserProfilePage() {
        ResponseEntity<String> response = template
                .withBasicAuth("test@test.com", "wrognPassword")
                .getForEntity(base.toString() + "/profile", String.class);

        String responseString = response.getBody();
        assertThat(responseString).isNotNull();
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    @Order(5)
    public void loadUserByUsernameAndCheckAuthority() {

        UserDetails userDetails = userDetailsService.loadUserByUsername("test@test.com");
        assertThat(userDetails).isNotNull();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                Assert.isTrue(true);
                return;
            }
        }
        Assert.isTrue(false, "user doesn't have proper role");
    }

}
