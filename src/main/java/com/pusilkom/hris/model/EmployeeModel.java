package com.pusilkom.hris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
    private int id_employee;
    private int divisi;
    private int is_aktif;
    private String nip;
    private int id_pengguna;
    private String nama_lengkap;
    private String nama_divisi;
    private String jenis_kelamin;
    private String tempat_lahir;
    private String tanggal_lahir;
    private String alamat_tinggal;
    private String alamat_ktp;
    private String email_kantor;
    private String email_pribadi;
    private String no_telepon_rumah;
    private String no_telepon_darurat;
    private String no_telepon_seluler;
    private String no_bpjs_kesehatan;
    private String no_bpjs_tenaga_kerja;
    private String npwp;
    private String no_pegawai;
    private String no_ktp;
    private String kewarganegaraan;
    private String agama;
    private int status_kawin;
    private String no_rekening;
    private String bank_rekening;
    private String atas_nama_rekening;
}
