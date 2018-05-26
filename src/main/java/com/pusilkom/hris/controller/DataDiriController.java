package com.pusilkom.hris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.pusilkom.hris.model.DataDiriModel;
import com.pusilkom.hris.service.DataDiriService;

@Controller
public class DataDiriController {
    @Autowired
    DataDiriService dataDiriDAO;

    @GetMapping("/datadiri/view/{id_data_diri}")
    @PreAuthorize("hasAuthority('GET_DATADIRI_VIEW')")
    public String viewPath (Model model, @PathVariable(value = "id_data_diri") int id_data_diri){
        DataDiriModel dataDiri = dataDiriDAO.selectDataDiri(id_data_diri);
        if(dataDiri != null){
            model.addAttribute("dataDiri", dataDiri);
            return "formUpdateEmployee";
        }else{
            model.addAttribute("id_data_diri", id_data_diri);
            return "not-found";
        }
    }
}
