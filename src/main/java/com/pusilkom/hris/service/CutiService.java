package com.pusilkom.hris.service;

import com.example.demo.model.CutiModel;

import java.util.List;

public interface CutiService {

    List<CutiModel> selectAllCuti();

    CutiModel detailCuti(int id_employee);

    void updateCuti(CutiModel cuti);

    List<CutiModel> selectAllCutiAEmployee(int id_employee);

    void addCuti(int id_employee, String tanggal_awal, String tanggal_akhir, String ket_cuti);

    CutiModel detailCutiEmployee(int id_employee, int id_cuti);
}
