package com.pusilkom.hris.service;

import com.pusilkom.hris.model.ExecutiveModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface ExecutiveService {

    @PreAuthorize("hasRole('ROLE_EXECUTIVE')")
    List<ExecutiveModel> selectAllEmployee();

    @PreAuthorize("hasRole('ROLE_EXECUTIVE')")
    List<ExecutiveModel> selectAllKehadiran(int bulan);

    @PreAuthorize("hasRole('ROLE_EXECUTIVE')")
    List<ExecutiveModel> selectKehadiranById(int id_employee, int bulan);

    @PreAuthorize("hasRole('ROLE_EXECUTIVE')")
    List<ExecutiveModel> selectEmployeeByDivisi(String nama_divisi);

    String getNamaBulan(int bulan);

}
