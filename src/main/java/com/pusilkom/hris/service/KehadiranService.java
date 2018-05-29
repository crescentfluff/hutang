package com.pusilkom.hris.service;

import com.pusilkom.hris.model.KategoriModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface KehadiranService {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addKategoriKehadiran(String nama_kategori);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteKategoriKehadiran(int id_kehadiran);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateKategoriKehadiran(KategoriModel kehadiran);

    public List<KategoriModel> selectAllKehadiran();

    public KategoriModel selectKehadiranById(int id);
}
