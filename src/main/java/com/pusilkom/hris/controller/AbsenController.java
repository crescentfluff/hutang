package com.example.demo.controller;


import com.example.demo.model.AbsenModel;
import com.example.demo.model.KategoriModel;
import com.example.demo.model.PenggunaModel;
import com.example.demo.model.RolePengguna;
import com.example.demo.service.AbsenService;
import com.example.demo.service.KehadiranService;
import com.example.demo.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AbsenController {

    @Autowired
    AbsenService absenDAO;

    @Autowired
    PenggunaService penggunaDAO;

    @Autowired
    KehadiranService kehadiranDAO;

    @RequestMapping("/")
    public String index(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        return "redirect:/absen/kelola";
    }

    @RequestMapping("/absen/kelola/final")
    public String viewFinal (Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_EMPLOYEE) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        model.addAttribute("newAbsen", new AbsenModel());
        List<AbsenModel> absen = absenDAO.selectAllAbsen(penggunaLogin.getEmployee().getId_employee());

        model.addAttribute("kehadiran", kehadiranDAO.selectAllKehadiran());

        model.addAttribute("absen", absen);
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "absen_final";
    }

    @RequestMapping("/absen/kelola")
    public String view (Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_EMPLOYEE) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        model.addAttribute("newAbsen", new AbsenModel());
        List<AbsenModel> absen = absenDAO.selectAllAbsenInactive(penggunaLogin.getEmployee().getId_employee());

        model.addAttribute("kehadiran", kehadiranDAO.selectAllKehadiran());

        model.addAttribute("absen", absen);
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "absensi_inactive";
    }

    @RequestMapping(value = "/absen/ubah/{id_absen}", method = RequestMethod.GET)
    public String update(Principal principal, Model model, @PathVariable(value = "id_absen") int id_absen) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());
        model.addAttribute("penggunaLogin", penggunaLogin);

        AbsenModel absen = absenDAO.detailAbsen(id_absen);
        model.addAttribute("absen", absen);
        List<KategoriModel> hadir = kehadiranDAO.selectAllKehadiran();
        model.addAttribute("hadir",hadir);
        return "absen_ubah";
    }

    @RequestMapping(value = "/absen/ubah/submit", method = RequestMethod.POST)
    public String updateSubmit (Principal principal, @ModelAttribute AbsenModel absenUbah, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());
        model.addAttribute("penggunaLogin", penggunaLogin);

        AbsenModel absen = absenDAO.detailAbsen(absenUbah.getId_absen());
        absen.setId_kategori_kehadiran(absenUbah.getId_kategori_kehadiran());
        absen.setKeterangan(absenUbah.getKeterangan());
        absenDAO.updateAbsen(absen);
        return "redirect:/absen/kelola/?success=Simpan";
    }

    @RequestMapping(value = "/absensi/tambah/submit", method = RequestMethod.POST)
    public String tambahSubmit (Principal principal, Model model, @ModelAttribute("newAbsen") AbsenModel newAbsen)
    {
        //SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-mm-dd");
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_EMPLOYEE) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/absen/kelola";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);
        int id_employee = absenDAO.selectIdByUsername(penggunaLogin.getUsername());
        newAbsen.setId_employee(id_employee);
        if(absenDAO.cekAbsen(newAbsen) == null){
            absenDAO.addAbsen(newAbsen);
            return "redirect:/absen/kelola?success=menambah";
        } else {
            model.addAttribute("message", "Absensi pada tanggal " +newAbsen.getTanggal_absen() + " gagal dibuat");
            return "redirect:/absen/kelola?gagal=menambah";
        }

    }

    @RequestMapping(value = "/absen/finalisasi/{id_absen}", method = RequestMethod.GET)
    public String viewUpdate (Principal principal, Model model, @PathVariable(value = "id_absen") int id_absen, @ModelAttribute AbsenModel finalisasi) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());
        model.addAttribute("penggunaLogin", penggunaLogin);


        AbsenModel absen = absenDAO.detailAbsen(id_absen);
        model.addAttribute("absen", absen);
        absenDAO.finalizedAbsen(finalisasi);
        return "redirect:/absen/kelola/final?success=Finalisasi";
    }

    @RequestMapping(value = "/absen/unfinalized/{id_absen}", method = RequestMethod.GET)
    public String viewUnfinalized (Principal principal, Model model, @PathVariable(value = "id_absen") int id_absen, @ModelAttribute AbsenModel unfinalisasi) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());
        model.addAttribute("penggunaLogin", penggunaLogin);


        AbsenModel absen = absenDAO.detailAbsen(id_absen);
        model.addAttribute("absen", absen);
        absenDAO.unFinalizedAbsen(unfinalisasi);
        return "redirect:/absen/kelola/?success=BatalkanFinalisasi";
    }

    @RequestMapping(value = "/absen/hapus/{id_absen}", method = RequestMethod.GET)
    public String viewDelete (Principal principal, Model model, @PathVariable(value = "id_absen") int id_absen, @ModelAttribute AbsenModel delete) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_EMPLOYEE) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/absen/kelola";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);


        AbsenModel absen = absenDAO.detailAbsen(id_absen);
        model.addAttribute("absen", absen);
        absenDAO.deleteAbsen(delete);
        return "redirect:/absen/kelola/?success=Hapus";
    }

}
