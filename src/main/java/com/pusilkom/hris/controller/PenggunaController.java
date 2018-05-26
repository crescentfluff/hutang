package com.pusilkom.hris.controller;

import com.pusilkom.hris.model.PenggunaModel;
import com.pusilkom.hris.model.UserWeb;
import com.pusilkom.hris.service.EmployeeService;
import com.pusilkom.hris.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

@Controller
public class PenggunaController {
    @Autowired
    PenggunaService penggunaDAO;

    @Autowired
    EmployeeService employeeDAO;


    @PreAuthorize("hasAuthority('GET_PENGGUNA_KELOLA')")
    @GetMapping("pengguna/kelola")
    public String kelolaPengguna (@NotNull Authentication auth, Model model){
        UserWeb login = (UserWeb)auth.getPrincipal();

        if (login.getUsername().equalsIgnoreCase(null))
            return "redirect:/login";

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(login.getUsername());

        if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }

        model.addAttribute("penggunaLogin", penggunaLogin);

        for (String role: penggunaLogin.getRole()
             ) {
            System.out.println("ROLE: "+role);
        }

        List<PenggunaModel> user = penggunaDAO.selectAllPengguna();
        List<PenggunaModel> userIn = new ArrayList<>();
        for (PenggunaModel userAktif: user) {
            if(userAktif.getIs_aktif()==1) userIn.add(userAktif);
        }
        model.addAttribute("listpengguna", userIn);
        model.addAttribute("notif", NULL);
        return "site/pengguna_kelola";
    }

    @GetMapping("pengguna/kelola/inactive")
    public String kelolaPenggunaInactive (Principal principal, Model model){
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());


        if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        List<PenggunaModel> user = penggunaDAO.selectAllPengguna();
        List<PenggunaModel> userIn = new ArrayList<>();
        for (PenggunaModel userAktif: user) {
            if(userAktif.getIs_aktif()==0) userIn.add(userAktif);
        }
        model.addAttribute("listpengguna", userIn);
        model.addAttribute("notif", NULL);
        return "pengguna_kelola_inactive";
    }


    @GetMapping("pengguna/tambah")
    public String formAddPengguna (Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }


        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        model.addAttribute("listPengguna", penggunaDAO.selectAllSSO());
        model.addAttribute("employees", employeeDAO.selectAllEmployees());
        PenggunaModel pgn =  new PenggunaModel();
        model.addAttribute("pengguna", pgn);
        return "pengguna_tambah";
    }


    @RequestMapping(value="pengguna/tambah", method=RequestMethod.POST)
    public String successAdd (Principal principal, Model model, @ModelAttribute PenggunaModel pengguna) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);


        penggunaDAO.addPengguna(pengguna);
        return "redirect:/pengguna/kelola?success=tambah";
    }

    @PostMapping(value="pengguna/ubah/{id}")
    public String successUpdate (Principal principal, Model model, @ModelAttribute PenggunaModel pengguna, @PathVariable(value = "id") int id) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        PenggunaModel pgn = penggunaDAO.selectPenggunaById(id);

        penggunaDAO.updatePengguna(pengguna);

        return "redirect:/pengguna/kelola?success=ubah";
    }

    @GetMapping("pengguna/ubah/{id}")
    public String updatePengguna (Principal principal, Model model, @PathVariable(value = "id") int id){
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        PenggunaModel pgn = penggunaDAO.selectPenggunaById(id);
        model.addAttribute("pengguna", pgn);
        model.addAttribute("employees", employeeDAO.selectAllEmployees());
        if (pgn != null) {
            model.addAttribute("pengguna", pgn);
            return "pengguna_ubah";
        } else
            return "not-found";
    }

    @GetMapping("pengguna/status/{id}")
    public String statusPengguna (Principal principal, Model model, @PathVariable(value="id") int id) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        penggunaDAO.statusPengguna(id);
        model.addAttribute("notif", "Status Berhasil Diubah!");
        List<PenggunaModel> user = penggunaDAO.selectAllPengguna();
        model.addAttribute("listpengguna", user);
        return "redirect:/pengguna/kelola?success=mengubah";
    }

    @RequestMapping("/login")
    public String login (Principal principal) {
        if (principal != null) {
            return "redirect:/";
        }
        return "login";
    }


}
