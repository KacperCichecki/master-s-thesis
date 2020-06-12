package cichecki.kacper.jsonflattener.controller;

import cichecki.kacper.jsonflattener.dto.JsonInput;
import cichecki.kacper.jsonflattener.service.JsonFlattenerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.PushBuilder;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Log
@Controller
@SessionAttributes("jsonInput")
public class ViewController {

    private final JsonFlattenerService jsonFlattenerService;

    @Autowired
    public ViewController(JsonFlattenerService jsonFlattenerService) {
        this.jsonFlattenerService = jsonFlattenerService;
    }

    @GetMapping("main")
    public String showForm(PushBuilder pushBuilder, Model model) {
        // todo: PushBuilder jest nullem!
        // using push function from servlet 4 specification
        if (null != pushBuilder) {
            pushBuilder.path("css/flattener.css").push();
            pushBuilder.path("js/flattener.js").push();
        }
        model.addAttribute("jsonInput", new JsonInput());
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
            e.printStackTrace();
        }

        model.addAttribute("error", errorInfo);
        model.addAttribute("jsonInput", jsonInput);

        return "result";
    }


    @GetMapping("profile")
    public String showUsersJsons(HttpServletRequest request, Model model) {

        return "profile";
    }

}
