package com.pusilkom.hris.controller;

import com.pusilkom.hris.model.UserWeb;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@Controller
@ControllerAdvice
public class SiteController {

    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET_')")
    public String index(Model model, @NotNull Authentication auth) {
        UserWeb user = (UserWeb) auth.getPrincipal();
//        model.addAttribute("currentUser", user);
        System.out.println("User : " + user.getUsername());
        for (GrantedAuthority author: user.getAuthorities()) {
            System.out.println("INI AUTHORITY: "+author.getAuthority());
        }
        return "site/index";
    }

    @GetMapping("/signin")
    public String login() {
        return "site/login";
    }

    @ModelAttribute("loggedInUser")
    public String getLoggedInUser(@NotNull Authentication auth) {
        UserWeb user = (UserWeb) auth.getPrincipal();
        String nama = user.getUsername();
        System.out.println("loggedin: "+nama);
        return nama;
    }
    @ModelAttribute("loggedInTitle")
    public String getLoggedInTitle(@NotNull Authentication auth) {
        UserWeb user = (UserWeb) auth.getPrincipal();
        String title = user.getRole().toString();
        return title;
    }
}
