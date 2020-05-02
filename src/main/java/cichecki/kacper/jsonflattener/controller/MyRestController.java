package cichecki.kacper.jsonflattener.controller;

import cichecki.kacper.jsonflattener.dto.UserDto;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;

@Log
@RestController
@RequestMapping("rest")
@Profile("rest")
public class MyRestController  {


    @PostMapping("user")
    ResponseEntity registerUser (@RequestBody UserDto user) {
        log.info("received user: " + user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "user", produces = { "application/json", "application/xml" })
    UserDto registerUser () {
        UserDto newUser = new UserDto();
        newUser.setEmail("test@gmail.com");
        newUser.setLastName("blacha");

        log.info("Returning user: " + newUser);
        return newUser;
    }

}
