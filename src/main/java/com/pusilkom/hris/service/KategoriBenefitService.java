package com.pusilkom.hris.service;

import com.pusilkom.hris.model.KategoriModel;

import java.util.List;

public interface KategoriBenefitService {
    KategoriModel selectKategoriBenefit(int id);
    List<KategoriModel> selectAllKategoriBenefit();
    void addKategoriBenefit(KategoriModel kategori) ;
    void updateKategoriBenefit(KategoriModel kategori);
    void deleteKategoriBenefit(int id);
    String cekAdaBenefit(int id_kategori);
}
