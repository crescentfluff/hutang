package com.pusilkom.hris.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DataDiriModel {
    private int id_data_diri;
    private int id_employee;
    private String nama_lengkap;
    private String jenis_kelamin;
    private String tempat_lahir;
    private Date tanggal_lahir;
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
    private String no_KTP;
    private String kewarganegaraan;
    private String agama;
    private int status_kawin;
    private String nomor_rekening;
    private String bank_rekening;
    private String atas_nama_rekening;
    private int is_aktif;
}
