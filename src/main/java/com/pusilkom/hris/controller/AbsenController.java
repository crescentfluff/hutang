package com.pusilkom.hris.controller;



import com.pusilkom.hris.model.AbsenModel;
import com.pusilkom.hris.model.KategoriModel;
import com.pusilkom.hris.model.UserWeb;
import com.pusilkom.hris.service.AbsenService;
import com.pusilkom.hris.service.KehadiranService;
import com.pusilkom.hris.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;
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
    public String index(@NotNull Authentication auth, Model model) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EMPLOYEE")) {
            return "redirect:/";
        }
        //end of check
    }

    @RequestMapping("/absen/kelola/final")
    public String viewFinal (@NotNull Authentication auth, Model model) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EMPLOYEE")) {
            return "redirect:/";
        }
        //end of check

        model.addAttribute("newAbsen", new AbsenModel());
        List<AbsenModel> absen = absenDAO.selectAllAbsen(penggunaLogin.getEmployee().getId_employee());

        model.addAttribute("kehadiran", kehadiranDAO.selectAllKehadiran());

        model.addAttribute("absen", absen);
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "absen_final";
    }

    @RequestMapping("/absen/kelola")
    public String view (@NotNull Authentication auth, Model model) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EMPLOYEE")) {
            return "redirect:/";
        }
        //end of check

        model.addAttribute("newAbsen", new AbsenModel());
        List<AbsenModel> absen = absenDAO.selectAllAbsenInactive(penggunaLogin.getEmployee().getId_employee());

        model.addAttribute("kehadiran", kehadiranDAO.selectAllKehadiran());

        model.addAttribute("absen", absen);
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "absensi_inactive";
    }

    @RequestMapping(value = "/absen/ubah/{id_absen}", method = RequestMethod.GET)
    public String update(@NotNull Authentication auth, Model model, @PathVariable(value = "id_absen") int id_absen) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EMPLOYEE")) {
            return "redirect:/";
        }
        //end of check

        AbsenModel absen = absenDAO.detailAbsen(id_absen);
        model.addAttribute("absen", absen);
        List<KategoriModel> hadir = kehadiranDAO.selectAllKehadiran();
        model.addAttribute("hadir",hadir);
        return "absen_ubah";
    }

    @RequestMapping(value = "/absen/ubah/submit", method = RequestMethod.POST)
    public String updateSubmit (@NotNull Authentication auth, @ModelAttribute AbsenModel absenUbah, Model model) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EMPLOYEE")) {
            return "redirect:/";
        }
        //end of check

        AbsenModel absen = absenDAO.detailAbsen(absenUbah.getId_absen());
        absen.setId_kategori_kehadiran(absenUbah.getId_kategori_kehadiran());
        absen.setKeterangan(absenUbah.getKeterangan());
        absenDAO.updateAbsen(absen);
        return "redirect:/absen/kelola/?success=Simpan";
    }

    @RequestMapping(value = "/absensi/tambah/submit", method = RequestMethod.POST)
    public String tambahSubmit (@NotNull Authentication auth, Model model, @ModelAttribute("newAbsen") AbsenModel newAbsen)
    {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EMPLOYEE")) {
            return "redirect:/";
        }
        //end of check

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
    public String viewUpdate (@NotNull Authentication auth, Model model, @PathVariable(value = "id_absen") int id_absen, @ModelAttribute AbsenModel finalisasi) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EMPLOYEE")) {
            return "redirect:/";
        }
        //end of check


        AbsenModel absen = absenDAO.detailAbsen(id_absen);
        model.addAttribute("absen", absen);
        absenDAO.finalizedAbsen(finalisasi);
        return "redirect:/absen/kelola/final?success=Finalisasi";
    }

    @RequestMapping(value = "/absen/unfinalized/{id_absen}", method = RequestMethod.GET)
    public String viewUnfinalized (@NotNull Authentication auth, Model model, @PathVariable(value = "id_absen") int id_absen, @ModelAttribute AbsenModel unfinalisasi) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EMPLOYEE")) {
            return "redirect:/";
        }
        //end of check


        AbsenModel absen = absenDAO.detailAbsen(id_absen);
        model.addAttribute("absen", absen);
        absenDAO.unFinalizedAbsen(unfinalisasi);
        return "redirect:/absen/kelola/?success=BatalkanFinalisasi";
    }

    @RequestMapping(value = "/absen/hapus/{id_absen}", method = RequestMethod.GET)
    public String viewDelete (@NotNull Authentication auth, Model model, @PathVariable(value = "id_absen") int id_absen, @ModelAttribute AbsenModel delete) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EMPLOYEE")) {
            return "redirect:/";
        }
        //end of check


        AbsenModel absen = absenDAO.detailAbsen(id_absen);
        model.addAttribute("absen", absen);
        absenDAO.deleteAbsen(delete);
        return "redirect:/absen/kelola/?success=Hapus";
    }

}
