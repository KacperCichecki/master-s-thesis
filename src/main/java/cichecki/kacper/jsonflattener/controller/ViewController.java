package cichecki.kacper.jsonflattener.controller;

import cichecki.kacper.jsonflattener.dto.JsonInput;
import cichecki.kacper.jsonflattener.service.JsonFlattenerService;
import cichecki.kacper.jsonflattener.service.JsonPersistenceService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@SessionAttributes("jsonInput")
public class ViewController {

    private final JsonFlattenerService jsonFlattenerService;
    private final JsonPersistenceService jsonPersistenceService;

    @Autowired
    public ViewController(JsonFlattenerService jsonFlattenerService, JsonPersistenceService jsonPersistenceService) {
        this.jsonFlattenerService = jsonFlattenerService;
        this.jsonPersistenceService = jsonPersistenceService;
    }

    @ModelAttribute("jsonInput")
    public JsonInput jsonInput() {
        return new JsonInput();
    }

    @GetMapping("main")
    public String showForm(PushBuilder pushBuilder) {
        // todo: PushBuilder jest nullem!
        // using push function from servlet 4 specification
        if (pushBuilder != null) {
            pushBuilder.path("css/flattener.css").push();
            pushBuilder.path("js/flattener.js").push();
        }
        return "main";
    }

    @PostMapping("json-input")
    public String formatJson(@ModelAttribute("jsonInput") JsonInput jsonInput, Model model) {

        String flattened = jsonInput.getFlatten().trim();
        String nested = jsonInput.getNested().trim();
        boolean flattenedEmpty = StringUtils.isEmpty(flattened);
        boolean nestedEmpty = StringUtils.isEmpty(nested);

        String errorInfo = null;

        if (flattenedEmpty && nestedEmpty) {
            return "main";
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
        }

        model.addAttribute("error", errorInfo);
//        model.addAttribute("jsonInput", jsonInput);

        return "result";
    }

    @GetMapping("save-json")
    public ResponseEntity saveJson(@ModelAttribute("jsonInput") JsonInput jsonInput) {
        log.info("saving json: " + jsonInput);

        try {
            jsonPersistenceService.saveResult(jsonInput);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Json has been saved sucessfully", HttpStatus.CREATED);
    }

    @GetMapping("json-input")
    public String showResultPage() {
        return "result";
    }


    @GetMapping("profile")
    public String showUsersJsons(HttpServletRequest request, Model model) {

        return "profile";
    }

}
