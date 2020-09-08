package cichecki.kacper.jsonflattener;

import cichecki.kacper.jsonflattener.service.MyEmailService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MyEmailServiceTest {

    @Autowired
    private MyEmailService myEmailService;


    @Test
    public void sendEmail() {

        myEmailService.sendSimpleMessage("wojo@getnada.com", "welcome email", "Thanks for registration in our service");
        System.out.println("message sent");
    }

}
