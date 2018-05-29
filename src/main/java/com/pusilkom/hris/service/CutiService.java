package com.pusilkom.hris.service;

import com.pusilkom.hris.model.CutiModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CutiService {

    @PreAuthorize("hasRole('ROLE_HR')")
    List<CutiModel> selectAllCuti();

    CutiModel detailCuti(int id_employee);

    @PreAuthorize("hasRole('ROLE_HR')")
    void updateCuti(CutiModel cuti);

    List<CutiModel> selectAllCutiAEmployee(int id_employee);

    void addCuti(int id_employee, String tanggal_awal, String tanggal_akhir, String ket_cuti);

    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_EMPLOYEE')")
    CutiModel detailCutiEmployee(int id_employee, int id_cuti);
}
