package com.pusilkom.hris.service;

import com.pusilkom.hris.model.ExecutiveModel;

import java.util.List;

public interface ExecutiveService {

    List<ExecutiveModel> selectAllEmployee();

    List<ExecutiveModel> selectAllKehadiran(int bulan);

    List<ExecutiveModel> selectKehadiranById(int id_employee, int bulan);

    List<ExecutiveModel> selectEmployeeByDivisi(String nama_divisi);

    String getNamaBulan(int bulan);

}
