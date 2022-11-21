package pl.dawidlisowski.OrganiserApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.dawidlisowski.OrganiserApp.models.forms.LoginForm;
import pl.dawidlisowski.OrganiserApp.models.forms.RegisterForm;
import pl.dawidlisowski.OrganiserApp.models.services.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "registerForm";
    }

    @PostMapping("/user/register")
    public String getDataFromRegisterForm(@ModelAttribute @Valid RegisterForm registerForm,
                                          BindingResult bindingResult,
                                          Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerInfo", "Wrong data");
            return "registerForm";
        }

        if (userService.checkIfLoginExists(registerForm.getLogin())) {
            model.addAttribute("registerInfo", "Login taken");
            return "registerForm";
        }

        if (!userService.isRepeatedPasswordCorrect(registerForm)) {
            model.addAttribute("registerInfo", "Repeated password is wrong");
            return "registerForm";
        }

        userService.addUser(registerForm);
        return "redirect:/user/login";
    }

    @GetMapping("/user/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "loginForm";
    }

    @PostMapping("/user/login")
    public String getLoginForm(@ModelAttribute LoginForm loginForm,
                               Model model) {
        if (userService.tryLogin(loginForm)) {
            return "redirect:/";
        }

        model.addAttribute("loginInfo", "Login or password is incorrect");
        return "loginForm";
    }
}
