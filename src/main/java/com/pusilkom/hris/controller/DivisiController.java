package com.pusilkom.hris.controller;

import com.pusilkom.hris.model.DivisiModel;
import com.pusilkom.hris.model.PenggunaModel;
import com.pusilkom.hris.model.UserWeb;
import com.pusilkom.hris.service.DivisiService;
import com.pusilkom.hris.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@Controller
public class DivisiController {
    @Autowired
    DivisiService divisiDAO;

    @Autowired
    PenggunaService penggunaDAO;

    @GetMapping("/divisi/kelola")
    public String view (@NotNull Authentication auth, Model model)
    {
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

        List<DivisiModel> divisis = divisiDAO.selectAllDivisi();
        model.addAttribute ("divisis", divisis);
        return "divisi_kelola";
    }

    @GetMapping("/divisi/kelola/inactive")
    public String viewInactive (@NotNull Authentication auth, Model model)
    {
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

        List<DivisiModel> divisis = divisiDAO.selectAllDivisiInactive();
        model.addAttribute ("divisis", divisis);
        return "divisi_kelola_inactive";
    }

    @GetMapping("/divisi/tambah")
    public String tambah (@NotNull Authentication auth, Model model)
    {
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

        List<DivisiModel> divisis = divisiDAO.selectAllDivisi();
        model.addAttribute ("divisis", divisis);
        model.addAttribute("divisi", new DivisiModel());
        return "divisi_tambah";
    }

    @PostMapping(value = "/divisi/tambah/submit")
    public String tambahSubmit (@NotNull Authentication auth, Model model, @ModelAttribute DivisiModel divisi)
    {
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

        if (divisi != null) {
            if(divisi.getNama_divisi().length() <= 1){
                String kembalian = "redirect:/divisi/tambah?gagal=ubah";
                return kembalian;
            }
            divisiDAO.addDivisi(divisi);
            model.addAttribute("message", "Status Berhasil Diubah");
            return "redirect:/divisi/kelola?success=menambah";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/divisi/ubah/{id_divisi}")
    public String viewPath (@NotNull Authentication auth, Model model, @PathVariable(value = "id_divisi") int id_divisi){

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

        DivisiModel divisi = divisiDAO.selectDivisi(id_divisi);
        List<DivisiModel> divisis = divisiDAO.selectAllParentDivisi(divisi);

        if(divisi != null){
            model.addAttribute("divisi", divisi);
            model.addAttribute("divisis", divisis);
            return "divisi_ubah";
        }else{
            model.addAttribute("message", "Divisi " + id_divisi + " not found");
            return "not-found";
        }
    }

    @PostMapping(value = "/divisi/ubah/submit")
    public String updateSubmit (@NotNull Authentication auth, Model model, @ModelAttribute DivisiModel divisi)
    {

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

        if (divisi != null) {
            System.out.print(divisi);
            if(divisi.getNama_divisi().length() <= 1){
                String kembalian = "redirect:/divisi/ubah/" + divisi.getId_divisi() + "?gagal=ubah";
                return kembalian;
            }
            divisiDAO.updateDivisi(divisi);
            model.addAttribute("message", "Status Berhasil Diubah");
            return "redirect:/divisi/kelola?success=mengubah";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/divisi/hapus/{id}")
    public String deleteDivisi (@NotNull Authentication auth, Model model, @PathVariable(value="id") int id) {

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

        if(divisiDAO.cekAdaDivisi(id) != null){
            return "redirect:/divisi/kelola?gagal=menghapus";
        }

        divisiDAO.deleteDivisi(id);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/divisi/kelola?success=menghapus";
    }

    @RequestMapping("/divisi/deactive/{id}")
    public String deactiveDivisi (@NotNull Authentication auth, Model model, @PathVariable(value="id") int id) {

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

        divisiDAO.deactiveDivisi(id);
        System.out.println(id);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/divisi/kelola/inactive?success=menonaktifakn";
    }

    @RequestMapping("/divisi/activate/{id}")
    public String activateDivisi (@NotNull Authentication auth, Model model, @PathVariable(value="id") int id) {

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

        divisiDAO.activateDivisi(id);
        System.out.println(id);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/divisi/kelola?success=mengaktifkan";
    }

}