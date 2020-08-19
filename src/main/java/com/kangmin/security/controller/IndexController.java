package com.kangmin.security.controller;

import com.kangmin.security.config.security.PasswordConfig;
import com.kangmin.security.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Import({
        PasswordConfig.class,
})
@Controller
public class IndexController {

    private final TestService testService;

    @Autowired
    public IndexController(final TestService testService) {
        this.testService = testService;
    }

    @GetMapping({"/", "/index"})
    public String getIndexPage(final Model model) {
        model.addAttribute("message", testService.getMessage());
        model.addAttribute("skills", testService.getSkills());
        return "index";
    }
}
