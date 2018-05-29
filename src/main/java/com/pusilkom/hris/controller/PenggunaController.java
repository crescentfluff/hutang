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

    @GetMapping("pengguna/kelola")
    public String kelolaPengguna (@NotNull Authentication auth, Model model){
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        //end of check

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
    public String kelolaPenggunaInactive (@NotNull Authentication auth, Model model) {

        UserWeb penggunaLogin = (UserWeb) auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }

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
    public String formAddPengguna (@NotNull Authentication auth, Model model) {

        //checking
        UserWeb penggunaLogin = (UserWeb) auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }

        model.addAttribute("employees", employeeDAO.selectAllEmployees());
        PenggunaModel pgn =  new PenggunaModel();
        model.addAttribute("pengguna", pgn);
        return "pengguna_tambah";
    }


    @PostMapping(value="pengguna/tambah")
    public String successAdd (@NotNull Authentication auth, Model model, @ModelAttribute PenggunaModel pengguna) {

        //checking
        UserWeb penggunaLogin = (UserWeb) auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        //end of check

        penggunaDAO.addPengguna(pengguna);
        return "redirect:/pengguna/kelola?success=tambah";
    }

    @PostMapping(value="pengguna/ubah/{id}")
    public String successUpdate (@NotNull Authentication auth, Model model, @ModelAttribute PenggunaModel pengguna, @PathVariable(value = "id") int id) {

        //checking
        UserWeb penggunaLogin = (UserWeb) auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        //end of check

        PenggunaModel pgn = penggunaDAO.selectPenggunaById(id);

        penggunaDAO.updatePengguna(pengguna);

        return "redirect:/pengguna/kelola?success=ubah";
    }

    @GetMapping("pengguna/ubah/{id}")
    public String updatePengguna (@NotNull Authentication auth, Model model, @PathVariable(value = "id") int id){

        //checking
        UserWeb penggunaLogin = (UserWeb) auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        //end of check

        PenggunaModel pgn = penggunaDAO.selectPenggunaById(id);
        System.out.println(pgn.toString());
        model.addAttribute("employees", employeeDAO.selectAllEmployees());
        if (pgn != null) {
            model.addAttribute("pengguna", pgn);
            return "pengguna_ubah";
        } else
            return "not-found";
    }

    @GetMapping("pengguna/status/{id}")
    public String statusPengguna (@NotNull Authentication auth, Model model, @PathVariable(value="id") int id) {

        //checking
        UserWeb penggunaLogin = (UserWeb) auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_ADMIN")) {
            return "redirect:/";
        }
        //end of check

        penggunaDAO.statusPengguna(id);
        model.addAttribute("notif", "Status Berhasil Diubah!");
        List<PenggunaModel> user = penggunaDAO.selectAllPengguna();
        model.addAttribute("listpengguna", user);
        return "redirect:/pengguna/kelola?success=mengubah";
    }

    @RequestMapping("/login")
    public String login (@NotNull Authentication auth) {
        //checking
        UserWeb penggunaLogin = (UserWeb) auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        //end of check
        return "login";
    }

}
