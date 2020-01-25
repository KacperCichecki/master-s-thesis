package cichecki.kacper.jsonflattener.controller;


import cichecki.kacper.jsonflattener.model.JsonInput;
import cichecki.kacper.jsonflattener.service.JsonFlattenerService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Log
@Controller
public class ViewController {

    private JsonFlattenerService jsonFlattenerService;

    @Autowired
    public ViewController(JsonFlattenerService jsonFlattenerService) {
        this.jsonFlattenerService = jsonFlattenerService;
    }

    @GetMapping("/main")
    public String showForm(Model model) {
        model.addAttribute("jsonInput", new JsonInput());
        return "main";
    }

    @PostMapping("/json-input")
    public String formatJson(@ModelAttribute JsonInput jsonInput, Model model) {

        String flattened = jsonInput.getFlatten().trim();
        String nested = jsonInput.getNested().trim();
        boolean flattenedEmpty = StringUtils.isEmpty(flattened);
        boolean nestedEmpty = StringUtils.isEmpty(nested);

        String errorInfo = null;

        if (flattenedEmpty && nestedEmpty) {
            return "redirect:main";
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


    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(Model model) {
        // todo: implement logout logic
        return "main";
    }

    @GetMapping("/alljsons")
    public String showUsersJsons(Model model) {
        return "alljsons";
    }

}
