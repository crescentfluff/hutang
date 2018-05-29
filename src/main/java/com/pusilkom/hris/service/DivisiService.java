package com.pusilkom.hris.service;

import com.pusilkom.hris.model.DivisiModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface DivisiService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    DivisiModel selectDivisi(int id_divisi);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<DivisiModel> selectAllDivisi();
    void addDivisi(DivisiModel newDivisi);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateDivisi(DivisiModel updatedDivisi);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteDivisi(int id_divisi);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<DivisiModel> selectAllParentDivisi(DivisiModel divisi);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void activateDivisi(int id_divisi);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deactiveDivisi(int id_divisi);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<DivisiModel> selectAllDivisiInactive();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String cekAdaDivisi(int id);
}
