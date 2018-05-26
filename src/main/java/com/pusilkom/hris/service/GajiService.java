package com.pusilkom.hris.service;

import com.pusilkom.hris.model.GajiModel;

import java.util.List;

public interface GajiService {
    void addNewGaji(GajiModel gaji);
    void updateGaji(GajiModel gaji);
    void deleteGaji(int id_gaji);
    List<GajiModel> selectGaji(int id_employee);
    GajiModel selectGajiById(int id_gaji);

}
