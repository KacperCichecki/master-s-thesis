package cichecki.kacper.jsonflattener.persistence.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getFirstName() {
        User user = new User();
        User user1 = new User();

        System.out.println(user.getFirstName().equals(user1.getFirstName()));
    }
}