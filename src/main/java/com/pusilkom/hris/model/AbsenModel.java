package com.pusilkom.hris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbsenModel {

    private int id_absen;
    private int id_employee;
    private String tanggal_absen;
    private String nama_kategori_kehadiran;
    private int id_kategori_kehadiran;
    private String keterangan;
    private int is_finalize;

    public AbsenModel(int employee, String tgl, String keterangan) {
        id_employee = employee;
        tanggal_absen=tgl;
        this.keterangan = keterangan;
        nama_kategori_kehadiran="Cuti";
        id_kategori_kehadiran=0;
        is_finalize=1;

    }

}
