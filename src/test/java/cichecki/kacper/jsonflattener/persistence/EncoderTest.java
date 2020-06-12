package cichecki.kacper.jsonflattener.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class EncoderTest {

private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11);

    @Test
    public void checkPasswordEncoding() throws Exception {
        String ecnodedTest = bCryptPasswordEncoder.encode("test");
        boolean result = bCryptPasswordEncoder.matches("test", ecnodedTest);
        System.out.println(result);
    }
}
