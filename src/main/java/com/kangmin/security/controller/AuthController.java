package com.kangmin.security.controller;

import com.kangmin.security.model.dto.AccountRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(final Model model) {
        model.addAttribute("form", new AccountRegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(final @Valid @ModelAttribute("form") AccountRegisterForm form,
                                  final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        System.out.println(form.getUsername());
        System.out.println(form.getPassword());
        System.out.println(form.getPassword2());

        // TODO: add registration logic

        // check if username already exists in the DB

        // check if password and password2 are the same

        // insert new entry into Account table

        // redirect and show flashAttributes

        return "redirect:/login";
    }
}
