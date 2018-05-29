package com.pusilkom.hris.controller;



import com.pusilkom.hris.model.AbsenModel;
import com.pusilkom.hris.model.KategoriModel;
import com.pusilkom.hris.model.UserWeb;
import com.pusilkom.hris.service.AbsenService;
import com.pusilkom.hris.service.KehadiranService;
import com.pusilkom.hris.service.PenggunaService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

@Controller
public class KehadiranController {
    @Autowired
    KehadiranService kehadiranDAO;

    @Autowired
    AbsenService absenDAO;

    @Autowired
    PenggunaService penggunaDAO;

    @GetMapping("kategorikehadiran")
    public String viewKategoriKehadiran(Model model, @NotNull Authentication auth) {

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

        List<KategoriModel> kehadiran = kehadiranDAO.selectAllKehadiran();
        List<String> laman = new ArrayList<String>();
        laman.add("Kategori"); laman.add("Kehadiran"); laman.add("Kelola Kategori Kehadiran");
        model.addAttribute("breadcrumb", laman);
        model.addAttribute("kehadiran", kehadiran);
        return "kehadiran_kelola";
    }

    @PostMapping("kategorikehadiran/simpan")
    public String updateKatKehadiran(KategoriModel kehadiran, Model model) {
        if (kehadiran.getId_kategori()>0) {
            kehadiranDAO.updateKategoriKehadiran(kehadiran);
            model.addAttribute("message", "Berhasil Mengubah Kategori Kehadiran");
            return "redirect:/kategorikehadiran?success=mengubah";
        }
        else {
            kehadiranDAO.addKategoriKehadiran(kehadiran.getNama_kategori());
            model.addAttribute("message", "Berhasil Menambah Kategori Kehadiran");
            return "redirect:/kategorikehadiran?success=menambah";
        }
    }

    @GetMapping("kategorikehadiran/hapus")
    public String hapusKatKehadiran(int id, Model model) {
        List<AbsenModel> absen = absenDAO.selectAbsenByKehadiran(id);
        if (absen!=null) return "redirect:/kategorikehadiran?gagal=menghapus";
        kehadiranDAO.deleteKategoriKehadiran(id);
        model.addAttribute("message", "Berhasil Menghapus Kategori Kehadiran");
        return "redirect:/kategorikehadiran?success=menghapus";
    }

    @GetMapping("findHadir/")
    @ResponseBody
    public KategoriModel findKehadiran(int id) {
        KategoriModel ada = kehadiranDAO.selectKehadiranById(id);;
        if (ada!=null) return ada;
        return new KategoriModel();
    }
}
