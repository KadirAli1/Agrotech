package com.agrotech.web.controller;

import com.agrotech.config.layout.Layout;
import com.agrotech.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LandingController {

    @Autowired
    private ProductTypeService productTypeService;

    @Layout("layout/default")
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("activeNav", "home");
        return "index";
    }

    @Layout("layout/default")
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("activeNav", "about");
        return "about";
    }

    @Layout("layout/default")
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("productTypes", productTypeService.findAll());
        model.addAttribute("activeNav", "contact");
        return "contact";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/";
    }
}
