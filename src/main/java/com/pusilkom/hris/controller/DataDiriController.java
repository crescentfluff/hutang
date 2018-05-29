package com.pusilkom.hris.controller;

import com.pusilkom.hris.model.*;
import com.pusilkom.hris.service.*;
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
import java.util.List;

@Controller
public class DataDiriController {
    @Autowired
    EmployeeService employeeDAO;
    @Autowired
    AbsenService absenDAO;
    @Autowired
    PenggunaService penggunaDAO;
    @Autowired
    GajiService gajiDAO;
    @Autowired
    BenefitService benefitDAO;
    @Autowired
    DataDiriService dataDiriDAO;
    @Autowired
    DivisiService divisiDAO;
    @Autowired
    KategoriBenefitService kategoriBenefitDAO;
    @Autowired
    KontrakService kontrakDAO;
    @Autowired
    FamilyMemberService familyMemberDAO;

    @RequestMapping("/employee/profile/")
    public String lihatProfil (@NotNull Authentication auth, Model model){
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

        List<DivisiModel> listDivisi = divisiDAO.selectAllDivisi();
        model.addAttribute("listDivisi", listDivisi);

        List<GajiModel> gaji = gajiDAO.selectGaji(id_employee);
        model.addAttribute("gaji", gaji);

        List<BenefitModel> benefit = benefitDAO.selectBenefit(id_employee);
        model.addAttribute("benefit", benefit);

        List<KontrakModel> kontrak = kontrakDAO.selectAllKontrakEmployee(id_employee);
        model.addAttribute("kontrak", kontrak);

        List<FamilyMemberModel> familyMember = familyMemberDAO.selectAllFamilyMemberEmployee(id_employee);
        model.addAttribute("familyMember", familyMember);

        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        DataDiriModel dataDiriEmployee = dataDiriDAO.selectDataDiri(id_employee);
        DivisiModel divisiEmployee = divisiDAO.selectDivisi(employee.getDivisi());

        model.addAttribute("employee", employee);
        model.addAttribute("dataDiriEmployee", dataDiriEmployee);
        model.addAttribute("divisiEmployee", divisiEmployee);
        return "detail_employee";
    }

    @RequestMapping("/employee/profile/ubah")
    public String ubahProfil (@NotNull Authentication auth, Model model){
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

        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        DataDiriModel dataDiriEmployee = dataDiriDAO.selectDataDiri(id_employee);
        DivisiModel divisiEmployee = divisiDAO.selectDivisi(employee.getDivisi());
        List<DivisiModel> listDivisi = divisiDAO.selectAllDivisi();

        model.addAttribute("employee", employee);
        model.addAttribute("dataDiriEmployee", dataDiriEmployee);
        model.addAttribute("divisiEmployee", divisiEmployee);
        model.addAttribute("listDivisi", listDivisi);
        return "form_ubah_data_diri";
    }

    @RequestMapping(value = "/employee/profile/ubah/submit", method = RequestMethod.POST)
    public String ubahProfil (@NotNull Authentication auth, Model model , @ModelAttribute("employee")EmployeeModel employee){
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
        employee.setId_employee(id_employee);

        dataDiriDAO.deleteDataDiri(employee.getId_employee());
        dataDiriDAO.addDataDiri(employee);
        return "redirect:/employee/profile/?success=Ubah";
    }

    @RequestMapping("employee/profile/anggotaKeluarga/add")
    public String addAnggotaKeluargaEmployee(@NotNull Authentication auth, Model model) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_HR") && !penggunaLogin.getRole().contains("ROLE_EXECUTIVE")) {
            return "redirect:/";
        }
        //end of check
        FamilyMemberModel familyMember = new FamilyMemberModel();

        int id_employee = absenDAO.selectIdByUsername(penggunaLogin.getUsername());

        familyMember.setId_employee(id_employee);
        model.addAttribute("familyMember", familyMember);
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "form_add_family_member_employee";
    }

    @RequestMapping(value = "employee/profile/anggotaKeluarga/add/submit", method = RequestMethod.POST)
    public String addNewAnggotaKeluargaEmployee(@NotNull Authentication auth, Model model, @ModelAttribute FamilyMemberModel familyMember) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_HR") && !penggunaLogin.getRole().contains("ROLE_EXECUTIVE")) {
            return "redirect:/";
        }
        //end of check
        familyMemberDAO.addFamilyMember(familyMember);
        model.addAttribute("familyMember", familyMember);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/profile" + "/?successTambahAnggotaKeluarga=menambah";
    }

    @RequestMapping(value = "employee/profile/anggotaKeluarga/update/{id_family_member}")
    public String updateAnggotaKeluargaEmployee(@NotNull Authentication auth, Model model, @PathVariable(value="id_family_member") int id_family_member) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_HR") && !penggunaLogin.getRole().contains("ROLE_EXECUTIVE")) {
            return "redirect:/";
        }
        //end of check
        int id_employee = absenDAO.selectIdByUsername(penggunaLogin.getUsername());
        FamilyMemberModel familyMember = familyMemberDAO.selectFamilyMemberById(id_family_member);
        if(familyMember.getId_employee() == id_employee) {
            model.addAttribute("penggunaLogin", penggunaLogin);
            model.addAttribute("familyMember", familyMember);
        }
        else if(familyMember == null) {
            return "404";
        }
        else{
            return "403";
        }

        EmployeeModel employee = employeeDAO.selectEmployee(familyMember.getId_employee());
        return "form_update_family_member_employee";
    }

    @RequestMapping(value = "employee/profile/anggotaKeluarga/update/submit", method = RequestMethod.POST)
    public String submitAnggotaKeluarga(@NotNull Authentication auth, Model model, @ModelAttribute FamilyMemberModel familyMember) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_HR") && !penggunaLogin.getRole().contains("ROLE_EXECUTIVE")) {
            return "redirect:/";
        }
        //end of check

        familyMemberDAO.updateFamilyMember(familyMember);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/profile/" + "?successUbahAnggotaKeluarga=mengubah";
    }

    @RequestMapping(value = "employee/profile/anggotaKeluarga/delete/{id_family_member}")
    public String deleteAnggotaKeluargaEmployee(@NotNull Authentication auth, Model model, @ModelAttribute FamilyMemberModel familyMember,
                                                @PathVariable(value="id_family_member") int id_family_member) {
        //checking ---- ganti 'ROLE_ADMIN' sesuai halaman aja
        UserWeb penggunaLogin = (UserWeb)auth.getPrincipal();
        if (penggunaLogin.getUsername().equalsIgnoreCase(null)) {
            return "redirect:/login";
        }
        else if (penggunaDAO.selectPenggunaByUsername(penggunaLogin.getUsername())==null) {
            return "redirect:/logout";
        }
        else if (!penggunaLogin.getRole().contains("ROLE_HR")) {
            return "redirect:/";
        }
        //end of check

        int id_employee = absenDAO.selectIdByUsername(penggunaLogin.getUsername());

        if(familyMember.getId_employee() == id_employee) {
            familyMemberDAO.deleteFamilyMember(familyMember.getId_family_member());
            model.addAttribute("message", "Status Berhasil Diubah");
            return "redirect:/employee/profile/" + "?successAnggotaKeluarga=menghapus";
        }
        return "403";
    }
}