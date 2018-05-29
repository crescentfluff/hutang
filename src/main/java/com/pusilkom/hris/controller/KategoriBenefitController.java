package com.pusilkom.hris.controller;

import com.pusilkom.hris.model.KategoriModel;
import com.pusilkom.hris.model.UserWeb;
import com.pusilkom.hris.service.KategoriBenefitService;
import com.pusilkom.hris.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@Controller
public class KategoriBenefitController {
    @Autowired
    KategoriBenefitService kbDAO;

    @Autowired
    PenggunaService penggunaDAO;

    @RequestMapping("/kategori-benefit/kelola")
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

        List<KategoriModel> kbs = kbDAO.selectAllKategoriBenefit();
        model.addAttribute ("kbs", kbs);
        return "kategori_benefit_kelola";
    }

    @RequestMapping("/kategori-benefit/tambah")
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

        List<KategoriModel> kbs = kbDAO.selectAllKategoriBenefit();
        model.addAttribute ("kbs",kbs);
        model.addAttribute("kategori_benefit", new KategoriModel());
        return "kategori_benefit_tambah";
    }

    @RequestMapping(value = "/kategori-benefit/tambah/submit", method = RequestMethod.POST)
    public String tambahSubmit (@NotNull Authentication auth, Model model, @ModelAttribute KategoriModel kategori_benefit)
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

        if (kategori_benefit != null) {
            if(kategori_benefit.getNama_kategori().length() <= 1){
                String kembalian = "redirect:/kategori-benefit/tambah?gagal=ubah";
                return kembalian;
            }
            kbDAO.addKategoriBenefit(kategori_benefit);
            model.addAttribute("message", "Status Berhasil Diubah");
            return "redirect:/kategori-benefit/kelola?success=menambah";
        } else {
            return "not-found";
        }
    }

    @RequestMapping("/kategori-benefit/ubah/{id_kategori}")
    public String viewPath (@NotNull Authentication auth, Model model, @PathVariable(value = "id_kategori") int id_kategori){

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

        KategoriModel kategori_benefit = kbDAO.selectKategoriBenefit(id_kategori);
        if(kategori_benefit != null){
            model.addAttribute("kategori_benefit", kategori_benefit);
            return "kategori_benefit_ubah";
        }else{
            model.addAttribute("message", "kategori_benefit " + id_kategori + " not found");
            return "not-found";
        }
    }

    @RequestMapping(value = "/kategori-benefit/ubah/submit", method = RequestMethod.POST)
    public String updateSubmit (@NotNull Authentication auth, Model model, @ModelAttribute KategoriModel kategori_benefit)
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

        if (kategori_benefit != null) {
            System.out.print(kategori_benefit);
            if(kategori_benefit.getNama_kategori().length() <= 1){
                String kembalian = "redirect:/kategori-benefit/ubah/" + kategori_benefit.getNama_kategori() + "?gagal=mengubah";
                return kembalian;
            }
            kbDAO.updateKategoriBenefit(kategori_benefit);
            model.addAttribute("message", "Status Berhasil Diubah");
            return "redirect:/kategori-benefit/kelola?success=mengubah";
        } else {
            return "not-found";
        }
    }

    @RequestMapping("/kategori-benefit/hapus/{id}")
    public String deleteKategoriBenefit (@NotNull Authentication auth, Model model, @PathVariable(value="id") int id) {

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

        if (kbDAO.cekAdaBenefit(id) != null){
            return "redirect:/kategori-benefit/kelola?gagal=menghapus";
        }

        kbDAO.deleteKategoriBenefit(id);
        model.addAttribute("message", "Berhasil dihapus");
        return "redirect:/kategori-benefit/kelola?success=menghapus";
    }

}