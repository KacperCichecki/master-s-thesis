package cichecki.kacper.jsonflattener;

import cichecki.kacper.jsonflattener.persistence.dao.UserRepository;
import cichecki.kacper.jsonflattener.persistence.model.JsonRecord;
import cichecki.kacper.jsonflattener.persistence.model.User;
import cichecki.kacper.jsonflattener.service.JsonPersistenceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class MockBeanTest {

    @Autowired
    private JsonPersistenceService jsonPersistenceService;

    @MockBean
    private UserRepository userRepository;

    @Mock
    JsonRecord jsonRecord;

    @Test
    void testRegister(){
        // given
        User activeUser = new User();
        activeUser.setEmail("test@test.com");
        ArrayList<JsonRecord> jsonRecords = new ArrayList<>();
        jsonRecords.add(jsonRecord);
        activeUser.setJsonRecords(jsonRecords);

        Mockito.when(userRepository.findByEmail(any(String.class))).thenReturn(activeUser);


        // when
        List<JsonRecord> jsonRecordsToCheck = jsonPersistenceService.getJsonRecords(activeUser);

        // then
        assertThat(jsonRecordsToCheck.size()).isEqualTo(1L);
    }

}