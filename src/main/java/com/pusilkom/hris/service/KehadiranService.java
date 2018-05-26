package com.pusilkom.hris.service;

import com.example.demo.model.KategoriModel;

import java.util.List;

public interface KehadiranService {
    public void addKategoriKehadiran(String nama_kategori);
    public void deleteKategoriKehadiran(int id_kehadiran);
    public void updateKategoriKehadiran(KategoriModel kehadiran);
    public List<KategoriModel> selectAllKehadiran();
    public KategoriModel selectKehadiranById(int id);
}
