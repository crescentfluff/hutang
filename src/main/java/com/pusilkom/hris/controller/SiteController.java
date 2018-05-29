package com.pusilkom.hris.controller;

import com.pusilkom.hris.model.UserWeb;
import com.pusilkom.hris.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    EmployeeService employeeDAO;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET_')")
    public String index(Model model, @NotNull Authentication auth) {
        UserWeb user = (UserWeb) auth.getPrincipal();
//        model.addAttribute("currentUser", user);
        System.out.println("User : " + user.getUsername());
        for (String r: user.getRole()) {
            System.out.println("INI AUTHORITY: "+r);
        }
        return "absensi";
    }

    @GetMapping("/signin")
    public String login() {
        return "site/login";
    }

    @ModelAttribute("penggunaLogin")
    public UserWeb getLoggedInUser(@NotNull Authentication auth) {
        UserWeb user = (UserWeb) auth.getPrincipal();
        String nama = user.getUsername();
        System.out.println("loggedin: "+nama);
        return user;
    }
    @ModelAttribute("loggedInTitle")
    public String getLoggedInTitle(@NotNull Authentication auth) {
        UserWeb user = (UserWeb) auth.getPrincipal();
        String title = user.getRole().toString();
        System.out.println("loggedin: "+title);
        if (employeeDAO.selectEmployeeByUsername(user.getUsername())!=null)
            title = employeeDAO.selectEmployeeByUsername(user.getUsername()).getNama_lengkap();
        return title;
    }
}
