package com.pusilkom.hris.controller;

import com.pusilkom.hris.model.*;

import com.pusilkom.hris.service.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@Slf4j
@Controller
public class EmployeeController {
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

    @RequestMapping("/employee/viewall")
    public String view(@NotNull Authentication auth, Model model) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        List<EmployeeModel> employees = employeeDAO.selectAllEmployees();
        model.addAttribute("employees", employees);

        return "kelola_employee";
    }

    @RequestMapping("/employee/kelola/inactive")
    public String viewEmployeeInactive(@NotNull Authentication auth, Model model) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        List<EmployeeModel> employees = employeeDAO.selectAllEmployeesInactive();
        model.addAttribute("employees", employees);

        return "kelola_employee_inactive";
    }

    @RequestMapping(value = "/employee/update/{id_employee}", method = RequestMethod.GET)
    public String update(@NotNull Authentication auth, Model model, @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        model.addAttribute("penggunaLogin", penggunaLogin);

        List<DivisiModel> listDivisi = divisiDAO.selectAllDivisi();
        model.addAttribute("listDivisi", listDivisi);

        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        model.addAttribute("employee", employee);

        List<GajiModel> gaji = gajiDAO.selectGaji(id_employee);

        model.addAttribute("gaji", gaji);

        List<BenefitModel> benefit = benefitDAO.selectBenefit(id_employee);
        model.addAttribute("benefit", benefit);

        List<KontrakModel> kontrak = kontrakDAO.selectAllKontrakEmployee(id_employee);
        model.addAttribute("kontrak", kontrak);

        List<FamilyMemberModel> familyMember = familyMemberDAO.selectAllFamilyMemberEmployee(id_employee);
        model.addAttribute("familyMember", familyMember);

        if (employee != null) {
            return "form_update_employee";
        } else {
            return "not-found";
        }
    }

    @RequestMapping(value = "/employee/update/{id}", method = RequestMethod.POST)
    public String updateSubmit(Model model, @NotNull Authentication auth, @ModelAttribute EmployeeModel employee, @PathVariable(value = "id") int id) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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
        model.addAttribute("penggunaLogin", penggunaLogin);

        if (employee != null) {
            employee.setId_employee(id);
            employeeDAO.updateEmployee(employee);
            return "redirect:/employee/viewall?success=ubah";
        } else {
            return "not-found";
        }
    }

    @RequestMapping("/employee/add")
    public String addNewEmployee(@NotNull Authentication auth, Model model) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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
        model.addAttribute("penggunaLogin", penggunaLogin);

        List<DivisiModel> listDivisi = divisiDAO.selectAllDivisi();
        model.addAttribute("listDivisi", listDivisi);
        model.addAttribute("employee", new EmployeeModel());
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "form_add_employee";
    }

    @RequestMapping(value = "/employee/add/submit", method = RequestMethod.POST)
    public String addNewEmployee(@NotNull Authentication auth, Model model, @ModelAttribute EmployeeModel employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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
        model.addAttribute("penggunaLogin", penggunaLogin);

        employeeDAO.addNewEmployee(employee);
        model.addAttribute("employee", employee);
        return "redirect:/employee/viewall?success=tambah";
    }

    @RequestMapping("/employee/delete/{id_employee}")
    public String delete(@NotNull Authentication auth, Model model, @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        model.addAttribute("penggunaLogin", penggunaLogin);

        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        model.addAttribute("employee", employee);
        model.addAttribute("penggunaLogin", penggunaLogin);

        if (employee != null) {
            employeeDAO.deactivateEmployee(id_employee);
            return "redirect:/employee/viewall?success=Nonaktifkan";
        } else {
            employeeDAO.deleteEmployee(id_employee);
            return "not-found";
        }
    }

    @RequestMapping("/gaji/add/{id_employee}")
    public String addNewGaji(@NotNull Authentication auth, Model model, @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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
        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        GajiModel gaji = new GajiModel();

        model.addAttribute("employee", employee);
        model.addAttribute("gaji", gaji);
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "form_add_gaji";
    }

    @RequestMapping(value = "/gaji/add/{id_employee}/submit", method = RequestMethod.POST)
    public String addNewEmployee(@NotNull Authentication auth, Model model, @ModelAttribute GajiModel gaji,
                                 @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        gaji.setUsername_pengisi(penggunaLogin.getUsername());
        gajiDAO.addNewGaji(gaji);
        model.addAttribute("gaji", gaji);
        return "redirect:/employee/update/{id_employee}" + "?successGaji=tambah";
    }

    @RequestMapping(value = "/gaji/updategaji/{id_gaji}", method = RequestMethod.GET)
    public String updateGaji(@NotNull Authentication auth, Model model, @PathVariable(value = "id_gaji") int id_gaji) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        GajiModel gaji = gajiDAO.selectGajiById(id_gaji);
        model.addAttribute("penggunaLogin", penggunaLogin);
        model.addAttribute("gaji", gaji);

        if (gaji == null) {
            return "not-found";
        }

        EmployeeModel employee = employeeDAO.selectEmployee(gaji.getId_employee());
        return "form_update_gaji";
    }

    @RequestMapping(value = "/gaji/updategaji/submit", method = RequestMethod.POST)
    public String updateGaji(@NotNull Authentication auth, @ModelAttribute GajiModel gaji) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        gaji.setUsername_pengisi(penggunaLogin.getUsername());
        gajiDAO.updateGaji(gaji);
        return "redirect:/employee/update/" + gaji.getId_employee() +"?successGaji=Ubah";
    }

    @RequestMapping("/gaji/delete/{id_gaji}")
    public String delete(@NotNull Authentication auth, Model model, @ModelAttribute GajiModel gaji,
                         @PathVariable(value="id_gaji") int id_gaji) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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


        GajiModel deletedGaji = gajiDAO.selectGajiById(id_gaji);
        gajiDAO.deleteGaji(id_gaji);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/update/" + deletedGaji.getId_employee()+"?successGaji=hapus" ;
    }

    @RequestMapping(value = "/benefit/update/{id_benefit}", method = RequestMethod.GET)
    public String updateBenefit(@NotNull Authentication auth, Model model, @PathVariable(value = "id_benefit") int id_benefit) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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
        model.addAttribute("penggunaLogin", penggunaLogin);

        BenefitModel benefit = benefitDAO.selectBenefitById(id_benefit);
        model.addAttribute("benefit", benefit);

        List<KategoriModel> listKategoriBenefit = kategoriBenefitDAO.selectAllKategoriBenefit();
        model.addAttribute("listKategoriBenefit", listKategoriBenefit);

        if (benefit == null) {
            return "not-found";
        }
        return "form_update_benefit";
    }

    @RequestMapping(value = "/benefit/update/submit", method = RequestMethod.POST)
    public String updateBenefit(@NotNull Authentication auth, @ModelAttribute BenefitModel benefit) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        benefit.setUsername_pengisi(penggunaLogin.getUsername());
        benefitDAO.updateBenefit(benefit);
        return "redirect:/employee/update/" + benefit.getId_employee()+"?successBenefit=Ubah";
    }

    @RequestMapping("/benefit/add/{id_employee}")
    public String addNewBenefit(@NotNull Authentication auth, Model model, @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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
        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        BenefitModel benefit = new BenefitModel();

        List<KategoriModel> listKategoriBenefit = kategoriBenefitDAO.selectAllKategoriBenefit();
        model.addAttribute("listKategoriBenefit", listKategoriBenefit);

        model.addAttribute("employee", employee);
        model.addAttribute("benefit", benefit);
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "form_add_benefit";
    }

    @RequestMapping(value = "/benefit/add/{id_employee}/submit", method = RequestMethod.POST)
    public String addNewEmployee(@NotNull Authentication auth, Model model, @ModelAttribute BenefitModel benefit,
                                 @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        benefit.setUsername_pengisi(penggunaLogin.getUsername());
        benefitDAO.addNewBenefit(benefit);
        model.addAttribute("benefit", benefit);
        return "redirect:/employee/update/" + id_employee +"?successBenefit=tambah";
    }

    @RequestMapping("/benefit/delete/{id_benefit}")
    public String deleteBenefit(@NotNull Authentication auth, Model model, @ModelAttribute BenefitModel benefit,
                                @PathVariable(value="id_benefit") int id_benefit) {


        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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


        BenefitModel deletedBenefit = benefitDAO.selectBenefitById(id_benefit);
        benefitDAO.deleteBenefit(id_benefit);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/update/" + deletedBenefit.getId_employee() +"?successBenefit=hapus" ;
    }

    @RequestMapping("/employee/activate/{id_employee}")
    public String activeEmployee (@NotNull Authentication auth, Model model, @PathVariable(value="id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        employeeDAO.activateEmployee(id_employee);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/kelola/inactive?success=Aktifkan";
    }

    @RequestMapping("/kontrak/add/{id_employee}")
    public String addKontrak(@NotNull Authentication auth, Model model, @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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
        EmployeeModel employee = employeeDAO.selectEmployee(id_employee);
        KontrakModel kontrak = new KontrakModel();
        kontrak.setUsername_pengisi(penggunaLogin.getUsername());
        model.addAttribute("employee", employee);
        model.addAttribute("kontrak", kontrak);
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "form_add_kontrak";
    }

    @RequestMapping(value = "/kontrak/add/{id_employee}/submit", method = RequestMethod.POST)
    public String addNewKontrak(@NotNull Authentication auth, Model model, @ModelAttribute KontrakModel kontrak,
                                @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        kontrakDAO.addKontrak(kontrak);
        model.addAttribute("kontrak", kontrak);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/update/{id_employee}" + "/?successTambahKontrak=menambah" ;
    }

    @RequestMapping(value = "/kontrak/update/{id_kontrak}")
    public String updateKontrak(@NotNull Authentication auth, Model model, @PathVariable(value = "id_kontrak") int id_kontrak) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        KontrakModel kontrak = kontrakDAO.selectKontrakById(id_kontrak);
        model.addAttribute("penggunaLogin", penggunaLogin);
        model.addAttribute("kontrak", kontrak);

        if (kontrak == null) {
            return "404";
        }

        EmployeeModel employee = employeeDAO.selectEmployee(kontrak.getId_employee());
        kontrak.setUsername_pengisi(penggunaLogin.getUsername());
        return "form_update_kontrak";
    }

    @RequestMapping(value = "/kontrak/update/submit", method = RequestMethod.POST)
    public String updateGaji(@NotNull Authentication auth, Model model, @ModelAttribute KontrakModel kontrak) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        kontrakDAO.updateKontrak(kontrak);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/update/" + kontrak.getId_employee() + "?successUbahKontrak=mengubah";
    }

    @RequestMapping("/kontrak/delete/{id_kontrak}")
    public String deleteKontrak(@NotNull Authentication auth, Model model, @ModelAttribute KontrakModel kontrak,
                                @PathVariable(value="id_kontrak") int id_kontrak) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        KontrakModel oldKontrak = kontrakDAO.selectKontrakById(id_kontrak);
        int id_employee = oldKontrak.getId_employee();
        kontrakDAO.deleteKontrak(id_kontrak);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/update/" + id_employee + "?successKontrak=hapus";
    }

    @RequestMapping("/anggotaKeluarga/add/{id_employee}")
    public String addAnggotaKeluarga(@NotNull Authentication auth, Model model, @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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
        FamilyMemberModel familyMember = new FamilyMemberModel();
        familyMember.setId_employee(id_employee);
        model.addAttribute("familyMember", familyMember);
        model.addAttribute("penggunaLogin", penggunaLogin);
        return "form_add_family_member";
    }

    @RequestMapping(value = "/anggotaKeluarga/add/{id_employee}/submit", method = RequestMethod.POST)
    public String addNewAnggotaKeluarga(@NotNull Authentication auth, Model model, @ModelAttribute FamilyMemberModel familyMember,
                                        @PathVariable(value = "id_employee") int id_employee) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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
        familyMemberDAO.addFamilyMember(familyMember);
        model.addAttribute("familyMember", familyMember);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/update/{id_employee}" + "/?successTambahAnggotaKeluarga=menambah";
    }

    @RequestMapping(value = "/anggotaKeluarga/update/{id_family_member}")
    public String updateAnggotaKeluarga(@NotNull Authentication auth, Model model, @PathVariable(value = "id_family_member") int id_family_member) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        FamilyMemberModel familyMember = familyMemberDAO.selectFamilyMemberById(id_family_member);
        model.addAttribute("penggunaLogin", penggunaLogin);
        model.addAttribute("familyMember", familyMember);

        if (familyMember == null) {
            return "404";
        }

        EmployeeModel employee = employeeDAO.selectEmployee(familyMember.getId_employee());
        return "form_update_family_member";
    }

    @RequestMapping(value = "/anggotaKeluarga/update/submit", method = RequestMethod.POST)
    public String submitAnggotaKeluarga(@NotNull Authentication auth, Model model, @ModelAttribute FamilyMemberModel familyMember) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        familyMemberDAO.updateFamilyMember(familyMember);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/update/" + familyMember.getId_employee() + "/?successUbahAnggotaKeluarga=mengubah";
    }

    @RequestMapping("/anggotaKeluarga/delete/{id_family_member}")
    public String deleteAnggotaKeluarga(@NotNull Authentication auth, Model model, @ModelAttribute FamilyMemberModel familyMember,
                                        @PathVariable(value="id_family_member") int id_family_member) {

        //checking ---- ganti 'ROLE_HR' sesuai halaman aja
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

        FamilyMemberModel oldFamilyMember = familyMemberDAO.selectFamilyMemberById(id_family_member);
        int id_employee = oldFamilyMember.getId_employee();
        familyMemberDAO.deleteFamilyMember(id_family_member);
        model.addAttribute("message", "Status Berhasil Diubah");
        return "redirect:/employee/update/" + id_employee + "?successAnggotaKeluarga=hapus";
    }
}
