package cichecki.kacper.jsonflattener.controller;

import cichecki.kacper.jsonflattener.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {


    @GetMapping("registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }


    @PostMapping("register")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto, HttpServletRequest request, Errors errors) {

        return new ModelAndView("successRegister", "user", userDto);

    }


}
