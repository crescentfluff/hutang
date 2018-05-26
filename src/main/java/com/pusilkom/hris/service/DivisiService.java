package com.pusilkom.hris.service;

import com.pusilkom.hris.model.DivisiModel;

import java.util.List;

public interface DivisiService {
    DivisiModel selectDivisi(int id_divisi);
    List<DivisiModel> selectAllDivisi();
    void addDivisi(DivisiModel newDivisi);
    void updateDivisi(DivisiModel updatedDivisi);
    void deleteDivisi(int id_divisi);
    List<DivisiModel> selectAllParentDivisi(DivisiModel divisi);
    void activateDivisi(int id_divisi);
    void deactiveDivisi(int id_divisi);
    List<DivisiModel> selectAllDivisiInactive();
    String cekAdaDivisi(int id);
}
