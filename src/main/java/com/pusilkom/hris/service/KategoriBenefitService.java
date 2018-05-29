package com.pusilkom.hris.service;

import com.pusilkom.hris.model.KategoriModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface KategoriBenefitService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    KategoriModel selectKategoriBenefit(int id);

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_HR')")
    List<KategoriModel> selectAllKategoriBenefit();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void addKategoriBenefit(KategoriModel kategori) ;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateKategoriBenefit(KategoriModel kategori);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteKategoriBenefit(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    String cekAdaBenefit(int id_kategori);
}
