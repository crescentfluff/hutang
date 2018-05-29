package com.pusilkom.hris.service;

import com.pusilkom.hris.model.KontrakModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface KontrakService {

    @PreAuthorize("hasRole('ROLE_HR')")
    List<KontrakModel> selectAllKontrakEmployee(int id_employee);

    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_EMPLOYEE')")
    KontrakModel selectKontrakEmployee(int id_employee, int id_kontrak);

    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_EMPLOYEE')")
    KontrakModel selectKontrakById(int id_kontrak);

    @PreAuthorize("hasRole('ROLE_HR')")
    void addKontrak(KontrakModel kontrak);

    @PreAuthorize("hasRole('ROLE_HR')")
    void updateKontrak(KontrakModel kontrak);

    @PreAuthorize("hasRole('ROLE_HR')")
    void deleteKontrak(int id_kontrak);
}
