package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller

public class MainController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String showIndex() {

        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("newUser") User newUser, BindingResult result) {
        String thePassword = newUser.getPassword();

        if (result.hasErrors()) {

            return "register";
        }


        newUser.addRole(roleRepository.findByRole("USER"));
        newUser.setPassword(passwordEncoder.encode(thePassword));
        return "redirect:/login";

    }

    @RequestMapping("/granteduser")
    public String showUser() {
        return "userpage";
    }

    @RequestMapping("/grantedadmin")
    public String showAdmin() {
        return showUser();
    }

}

