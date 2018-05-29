package com.pusilkom.hris.service;

import com.pusilkom.hris.model.GajiModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface GajiService {

    @PreAuthorize("hasRole('ROLE_HR')")
    void addNewGaji(GajiModel gaji);

    @PreAuthorize("hasRole('ROLE_HR')")
    void updateGaji(GajiModel gaji);

    @PreAuthorize("hasRole('ROLE_HR')")
    void deleteGaji(int id_gaji);

    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_EMPLOYEE')")
    List<GajiModel> selectGaji(int id_employee);

    @PreAuthorize("hasRole('ROLE_HR')")
    GajiModel selectGajiById(int id_gaji);

}
