package com.pusilkom.hris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CutiModel {

    private int id_cuti;
    private int id_employee;
    private String tanggal_awal;
    private String tanggal_akhir;
    private String status;
    private String ket_cuti;
    private String ket_penolakan;
    private String username_pereview;
    private String nama_lengkap;
    private String nama_divisi;

}
