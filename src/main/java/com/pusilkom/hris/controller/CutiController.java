package com.example.demo.controller;


import com.example.demo.model.*;
import com.example.demo.service.AbsenService;
import com.example.demo.service.CutiService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class CutiController {

    @Autowired
    CutiService cutiDAO;

    @Autowired
    AbsenService absenDAO;

    @Autowired
    EmployeeService employeeDAO;

    @Autowired
    PenggunaService penggunaDAO;

    @RequestMapping("/cuti/viewall")
    public String view (Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_HR) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        List<CutiModel> cuti = cutiDAO.selectAllCuti();
        model.addAttribute("cuti", cuti);
        return "reviewCuti";
    }

    @RequestMapping("/cuti/detail/{id_cuti}")
    public String view (Principal principal, Model model, @PathVariable(value = "id_cuti") int id_cuti) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_HR) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        CutiModel cuti = cutiDAO.detailCuti(id_cuti);
        model.addAttribute("cuti", cuti);
        return "detailCuti";
    }

    @RequestMapping(value = "/cuti/update/submit/terima",method = RequestMethod.POST)
    public String terimaSubmit (Principal principal, @ModelAttribute CutiModel cuti, Model model) throws ParseException {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_HR) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);
        cuti.setUsername_pereview(penggunaLogin.getUsername());
        cuti.setStatus("1");
        cutiDAO.updateCuti(cuti);

        return "redirect:/cuti/viewall?success=menerima";
    }

    @RequestMapping(value = "/cuti/update/submit/tolak",method = RequestMethod.POST)
    public String tolakSubmit (Principal principal, @ModelAttribute CutiModel cuti, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_HR) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);
        cuti.setUsername_pereview(penggunaLogin.getUsername());
        cuti.setStatus("2");
        cutiDAO.updateCuti(cuti);
        return "redirect:/cuti/viewall?success=menolak";
    }

    @RequestMapping("/listCuti")
    public String listCuti (Principal principal, Model model){
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_EMPLOYEE) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        int id_employee = penggunaLogin.getEmployee().getId_employee();

        List<CutiModel> cuti = cutiDAO.selectAllCutiAEmployee(id_employee);
        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        model.addAttribute("cuti", cuti);
        model.addAttribute("employee", employee);
        return "list_cuti_employee";
    }

    @RequestMapping("/listCuti/detail/{id_cuti}")
    public String keteranganCuti (Principal principal, Model model, @PathVariable(value = "id_cuti") int id_cuti){
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_EMPLOYEE) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        int id_employee = penggunaLogin.getEmployee().getId_employee();
        CutiModel cuti = cutiDAO.detailCutiEmployee(id_employee, id_cuti);
        System.out.println(cuti.getKet_penolakan());
        model.addAttribute("cuti", cuti);
        return "detail_cuti_employee";
    }

    @RequestMapping(value = "listCuti/formCuti" , method = RequestMethod.POST)
    public String formCuti (Principal principal, @ModelAttribute("employee")EmployeeModel employee, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_EMPLOYEE) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);

        int id_employee = employee.getId_employee();
        EmployeeModel employeeApply = employeeDAO.selectEmployee(id_employee);
        model.addAttribute("employeeApply", employeeApply);
        model.addAttribute("cuti", new CutiModel());
        return "form_pengajuan_cuti";
    }

    @RequestMapping(value = "listCuti/formCuti/submit" , method = RequestMethod.POST)
    public String submitCuti (Principal principal, @ModelAttribute("cuti")CutiModel cuti, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        PenggunaModel penggunaLogin = penggunaDAO.selectPenggunaByUsername(principal.getName());

        if (!penggunaLogin.getRoles().contains(RolePengguna.ROLE_EMPLOYEE) && !penggunaLogin.getRoles().contains(RolePengguna.ROLE_EXECUTIVE)) {
            return "redirect:/";
        }
        model.addAttribute("penggunaLogin", penggunaLogin);
        cutiDAO.addCuti(cuti.getId_employee(), cuti.getTanggal_awal(), cuti.getTanggal_akhir() ,cuti.getKet_cuti());
        EmployeeModel employee = employeeDAO.selectEmployee(cuti.getId_employee());
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/listCuti?successCuti=mengajukan";
    }
}
