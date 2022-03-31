package com.beautify.demo.controllers;

import com.beautify.demo.objects.User;
import com.beautify.demo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Controller
@Transactional
public class SecurityController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("")
    public String index(Authentication authentication){

        if(authentication != null){
            return "redirect:/home";
        }
        return "index";

    }

    @GetMapping("/home")
    public String homePage(Authentication authentication, Model model){

            model.addAttribute("user", userService.getSpecificUser(authentication));

            return "main";

    }

    @GetMapping("/signin")
    public String showSignInForm(Authentication authentication){

        if(authentication != null){
            return "redirect:/home";
        }

        return "signin_form";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Authentication authentication, Model model){

        if(authentication != null){
            return "redirect:/home";
        }
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_signup")
    public String processSignUp(User user){

        userService.addUser(user);

        return "signin_form";
    }

}
