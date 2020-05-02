package cichecki.kacper.jsonflattener.controller;

import cichecki.kacper.jsonflattener.dto.UserDto;
import cichecki.kacper.jsonflattener.errors.UserAlreadyExistException;
import cichecki.kacper.jsonflattener.persistence.model.User;
import cichecki.kacper.jsonflattener.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateInputException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class RegisterController {

    @Autowired
    private MessageSource messages;

    @Autowired
    private IUserService userService;

    @GetMapping("registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }


    @PostMapping("register")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView("registration", "user", userDto);

            Optional<String> passwordErrorMatch = bindingResult
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .filter(Objects::nonNull)
                    .filter(msg -> msg.contains("Passwords"))
                    .findFirst();

            passwordErrorMatch.ifPresent(s -> mav.addObject("password_match_err", s));

            return mav;
        }

        User registered;
        try {
            registered = userService.registerNewUserAccount(userDto);
        } catch (final UserAlreadyExistException uaeEx) {
            log.warn("Unable to register user", uaeEx);
            ModelAndView mav = new ModelAndView("registration", "user", userDto);
            String errMessage = messages.getMessage("message.regError.userExists", null, LocaleContextHolder.getLocale());
            mav.addObject("emaiExists", errMessage);
            return mav;
        } catch (final RuntimeException ex) {
            log.warn("Unable to register user", ex);
            return new ModelAndView("emailError", "user", userDto);
        }
        return new ModelAndView("successRegister", "newUser", registered);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data integrity violation")
    public String validationErro(MethodArgumentNotValidException exception) {
        log.info(exception.getMessage());

        return "registration";
    }

    @ExceptionHandler(Exception.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Data integrity violation")
    public ModelAndView validationErro(Exception exception) {
        log.info(exception.getMessage());

        UserDto userDto = new UserDto();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        modelAndView.addObject("message", exception.getMessage());
        modelAndView.addObject("user", userDto);
        return modelAndView;
    }

}
