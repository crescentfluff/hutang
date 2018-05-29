package com.pusilkom.hris.controller;



import com.pusilkom.hris.model.CutiModel;
import com.pusilkom.hris.model.EmployeeModel;
import com.pusilkom.hris.model.UserWeb;
import com.pusilkom.hris.service.AbsenService;
import com.pusilkom.hris.service.CutiService;
import com.pusilkom.hris.service.EmployeeService;
import com.pusilkom.hris.service.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
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

        List<CutiModel> cuti = cutiDAO.selectAllCuti();
        model.addAttribute("cuti", cuti);
        return "reviewCuti";
    }

    @RequestMapping("/cuti/detail/{id_cuti}")
    public String view (@NotNull Authentication auth, Model model, @PathVariable(value = "id_cuti") int id_cuti) {
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

        CutiModel cuti = cutiDAO.detailCuti(id_cuti);
        model.addAttribute("cuti", cuti);
        return "detailCuti";
    }

    @RequestMapping(value = "/cuti/update/submit/terima",method = RequestMethod.POST)
    public String terimaSubmit (@NotNull Authentication auth, @ModelAttribute CutiModel cuti, Model model) throws ParseException {
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
        cuti.setUsername_pereview(penggunaLogin.getUsername());
        cuti.setStatus("1");
        cutiDAO.updateCuti(cuti);

        return "redirect:/cuti/viewall?success=menerima";
    }

    @RequestMapping(value = "/cuti/update/submit/tolak",method = RequestMethod.POST)
    public String tolakSubmit (@NotNull Authentication auth, @ModelAttribute CutiModel cuti, Model model) {
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
        cuti.setUsername_pereview(penggunaLogin.getUsername());
        cuti.setStatus("2");
        cutiDAO.updateCuti(cuti);
        return "redirect:/cuti/viewall?success=menolak";
    }

    @RequestMapping("/listCuti")
    public String listCuti (@NotNull Authentication auth, Model model){
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

        int id_employee = penggunaLogin.getEmployee().getId_employee();

        List<CutiModel> cuti = cutiDAO.selectAllCutiAEmployee(id_employee);
        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        model.addAttribute("cuti", cuti);
        model.addAttribute("employee", employee);
        return "list_cuti_employee";
    }

    @RequestMapping("/listCuti/detail/{id_cuti}")
    public String keteranganCuti (@NotNull Authentication auth, Model model, @PathVariable(value = "id_cuti") int id_cuti){
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

        int id_employee = penggunaLogin.getEmployee().getId_employee();
        CutiModel cuti = cutiDAO.detailCutiEmployee(id_employee, id_cuti);
        System.out.println(cuti.getKet_penolakan());
        model.addAttribute("cuti", cuti);
        return "detail_cuti_employee";
    }

    @RequestMapping(value = "listCuti/formCuti" , method = RequestMethod.POST)
    public String formCuti (@NotNull Authentication auth, @ModelAttribute("employee")EmployeeModel employee, Model model) {
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

        int id_employee = employee.getId_employee();
        EmployeeModel employeeApply = employeeDAO.selectEmployee(id_employee);
        model.addAttribute("employeeApply", employeeApply);
        model.addAttribute("cuti", new CutiModel());
        return "form_pengajuan_cuti";
    }

    @RequestMapping(value = "listCuti/formCuti/submit" , method = RequestMethod.POST)
    public String submitCuti (@NotNull Authentication auth, @ModelAttribute("cuti")CutiModel cuti, Model model) {
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
        cutiDAO.addCuti(cuti.getId_employee(), cuti.getTanggal_awal(), cuti.getTanggal_akhir() ,cuti.getKet_cuti());
        EmployeeModel employee = employeeDAO.selectEmployee(cuti.getId_employee());
        model.addAttribute("employee", employee);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/listCuti?successCuti=mengajukan";
    }
}
