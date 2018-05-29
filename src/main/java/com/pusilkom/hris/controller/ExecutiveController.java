package com.pusilkom.hris.controller;

import java.util.List;


import com.pusilkom.hris.model.*;
import com.pusilkom.hris.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;


@Controller
public class ExecutiveController {

    @Autowired
    ExecutiveService executiveDAO;

    @Autowired
    PenggunaService penggunaDAO;

    @Autowired
    EmployeeService employeeDAO;

    @Autowired
    DataDiriService dataDiriDAO;

    @Autowired
    DivisiService divisiDAO;

    @RequestMapping(value = "/summary/{bulan}", method=RequestMethod.GET)
    public String chart(@NotNull Authentication auth, Model model, @PathVariable(value = "bulan") int bulan) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EXECUTIVE")) {
            return "redirect:/";
        }
        //end of check

        List<ExecutiveModel> listExe = executiveDAO.selectAllEmployee();
        int[] chartValue = new int[listExe.size()];
        String[] divisi = new String[listExe.size()];
        for (int i = 0; i < chartValue.length; i++) {
            chartValue[i] = listExe.get(i).getJum_pegawai();
            divisi[i] = listExe.get(i).getNama_divisi();
        }

        List<ExecutiveModel> listKehadiran = executiveDAO.selectAllKehadiran(bulan);
        int[] chartValueKehadiran = new int [listKehadiran.size()];
        String[] nama_kategori_kehadiran = new String[listKehadiran.size()];
        for (int i = 0; i < chartValueKehadiran.length; i++) {
            chartValueKehadiran[i] = listKehadiran.get(i).getJum_kehadiran();
            nama_kategori_kehadiran[i] = listKehadiran.get(i).getNama_kategori_kehadiran();
        }


        String nama_bulan = executiveDAO.getNamaBulan(bulan);
        model.addAttribute("nama_bulan", nama_bulan);
        model.addAttribute("chartValue", chartValue);
        model.addAttribute("divisi", divisi);
        model.addAttribute("chartValueKehadiran",chartValueKehadiran);
        model.addAttribute("nama_kategori_kehadiran",nama_kategori_kehadiran);
        return "executiveSummary";
    }

    @RequestMapping(value = "/summary/allemployee/{nama_divisi}", method = RequestMethod.GET)
    public String view (@NotNull Authentication auth, Model model, @PathVariable(value = "nama_divisi") String nama_divisi) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EXECUTIVE")) {
            return "redirect:/";
        }
        //end of check

        List<ExecutiveModel> listEmployeeByDivisi = executiveDAO.selectEmployeeByDivisi(nama_divisi);
        model.addAttribute("listEmployeeByDivisi",listEmployeeByDivisi);
        return "employeeByDivisi";
    }

    @RequestMapping("/summary/employee/profile/{id_employee}/{bulan}")
    public String view (@NotNull Authentication auth, Model model ,@PathVariable(value = "id_employee") int id_employee, @PathVariable(value = "bulan") int bulan){
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_EXECUTIVE")) {
            return "redirect:/";
        }
        //end of check

        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        DataDiriModel dataDiriEmployee = dataDiriDAO.selectDataDiri(id_employee);
        DivisiModel divisiEmployee = divisiDAO.selectDivisi(employee.getDivisi());
        List<DivisiModel> listDivisi = divisiDAO.selectAllDivisi();

        List<ExecutiveModel> listKehadiran = executiveDAO.selectKehadiranById(employee.getId_employee(), bulan);
        int[] chartValueKehadiran = new int [listKehadiran.size()];
        String[] nama_kategori_kehadiran = new String[listKehadiran.size()];
        for (int i = 0; i < chartValueKehadiran.length; i++) {
            chartValueKehadiran[i] = listKehadiran.get(i).getJum_kehadiran();
            nama_kategori_kehadiran[i] = listKehadiran.get(i).getNama_kategori_kehadiran();
        }

        String nama_bulan = executiveDAO.getNamaBulan(bulan);
        model.addAttribute("nama_bulan", nama_bulan);
        model.addAttribute("employee", employee);
        model.addAttribute("dataDiriEmployee", dataDiriEmployee);
        model.addAttribute("divisiEmployee", divisiEmployee);
        model.addAttribute("listDivisi", listDivisi);
        model.addAttribute("chartValueKehadiran",chartValueKehadiran);
        model.addAttribute("nama_kategori_kehadiran",nama_kategori_kehadiran);
        return "employeeSummary";
    }
}
