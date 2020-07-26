package cichecki.kacper.jsonflattener;

import cichecki.kacper.jsonflattener.persistence.dao.UserRepository;
import cichecki.kacper.jsonflattener.persistence.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@DataJpaTest
public class JpaTest {

    //test database configuration
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(userRepository).isNotNull();
    }


    @Test
    @Sql(scripts = {"classpath:createUser.sql"})
    void whenInitializedByDbUnit_thenFindsByName() {
        User user = userRepository.findByEmail("kacper@o2.pl");
        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("kacper");
        assertThat(user.getLastName()).isEqualTo("cichecki");
    }

    @Test
    @Sql(scripts = {"classpath:createUser.sql"})
    void setuProperUpdateAndCreationTime() {
// todo
    }

}
