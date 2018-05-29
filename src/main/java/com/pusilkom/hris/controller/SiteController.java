package com.pusilkom.hris.controller;

import com.pusilkom.hris.model.AbsenModel;
import com.pusilkom.hris.model.PenggunaModel;
import com.pusilkom.hris.model.UserWeb;
import com.pusilkom.hris.service.AbsenService;
import com.pusilkom.hris.service.EmployeeService;
import com.pusilkom.hris.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class SiteController {

    @Autowired
    EmployeeService employeeDAO;
    @Autowired
    PenggunaService penggunaDAO;


    @GetMapping("/signin")
    public String login() {
        return "site/login";
    }

    @ModelAttribute("penggunaLogin")
    public UserWeb getLoggedInUser(@NotNull Authentication auth) {
        UserWeb user = (UserWeb) auth.getPrincipal();

        List<GrantedAuthority> authenticatedUser = new ArrayList<>();
        for (String r: user.getRole()
             ) {
            authenticatedUser.add(new SimpleGrantedAuthority(r));
            System.out.println(r);
        }
        Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), authenticatedUser);
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        System.out.println("             "+user.getAttributes().toString()+"   ");
        String nama = user.getUsername();
        System.out.println("loggedin: "+nama);
        return user;
    }
    @ModelAttribute("loggedInTitle")
    public String getLoggedInTitle(@NotNull Authentication auth) {
        UserWeb user = (UserWeb) auth.getPrincipal();
        String title = user.getAuthorities().toString();
        System.out.println("loggedin: "+title);
        if (employeeDAO.selectEmployeeByUsername(user.getUsername())!=null)
            title = employeeDAO.selectEmployeeByUsername(user.getUsername()).getNama_lengkap();
        return title;
    }
}
