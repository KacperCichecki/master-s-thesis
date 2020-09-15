package cichecki.kacper.jsonflattener.controller;

import cichecki.kacper.jsonflattener.dto.JsonInput;
import cichecki.kacper.jsonflattener.dto.UserDto;
import cichecki.kacper.jsonflattener.errors.ErrorInfo;
import cichecki.kacper.jsonflattener.errors.WrongFormatException;
import cichecki.kacper.jsonflattener.service.JsonFlattenerService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("rest")
@Profile("rest")
public class MyRestController  {

    private final JsonFlattenerService jsonFlattenerService;

    @Autowired
    public MyRestController(JsonFlattenerService jsonFlattenerService) {
        this.jsonFlattenerService = jsonFlattenerService;
    }

    @PostMapping("json-input")
    public JsonInput formatJson(@RequestBody JsonInput jsonInput) {
        log.debug("JsonInput: " + jsonInput.toString());
        if (!validateInput(jsonInput))
            throw new WrongFormatException("json has empty fileds");

        String flattened = jsonInput.getFlatten().trim();
        String nested = jsonInput.getNested().trim();
        boolean flattenedEmpty = StringUtils.isEmpty(flattened);
        boolean nestedEmpty = StringUtils.isEmpty(nested);

        String errorInfo;

        if (flattenedEmpty && nestedEmpty) {
            throw new WrongFormatException("flattenedEmpty and nestedEmpty json is empty");
        }

        try {
            if (flattenedEmpty) {
                flattened = jsonFlattenerService.flatten(nested);
                jsonInput.setFlatten(flattened);
            } else {
                nested = jsonFlattenerService.nestFlattened(flattened);
                jsonInput.setNested(nested);
            }
        } catch (Exception e) {
            errorInfo = e.getMessage();
            log.error(errorInfo);
            e.printStackTrace();
            throw new WrongFormatException("error while processing json: " + errorInfo);
        }

        return jsonInput;
    }

    private boolean validateInput(JsonInput jsonInput) {
        if (jsonInput == null) {
            return false;
        }
        if (jsonInput.getFlatten() == null) {
            return false;
        }
        if (jsonInput.getNested() == null) {
            return false;
        }
        return true;
    }

    @PostMapping("user")
    ResponseEntity registerUser (@RequestBody UserDto user) {
        log.info("received user: " + user);
        // todo:
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody ErrorInfo
    handleGenericException(HttpServletRequest req, Exception ex) {
        log.error(ex.getMessage() + ex.getClass());
        return new ErrorInfo(req.getRequestURL().toString(), ex);
    }

}
