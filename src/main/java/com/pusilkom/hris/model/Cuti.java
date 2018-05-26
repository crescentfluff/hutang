package com.pusilkom.hris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuti {

    private int id_cuti;
    private int id_employee;
    private String tanggal_awal;
    private String tanggal_akhir;
    private String status;
    private String ket_cuti;
    private String ket_penolakan;
    private int id_hr_pereview;
    private String nama_lengkap;
    private String nama_divisi;

    public int getId_cuti() {
        return id_cuti;
    }

    public void setId_cuti(int id_cuti) {
        this.id_cuti = id_cuti;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getTanggal_akhir() {
        return tanggal_akhir;
    }

    public void setTanggal_akhir(String tanggal_akhir) {
        this.tanggal_akhir = tanggal_akhir;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKet_penolakan() {
        return ket_penolakan;
    }

    public void setKet_penolakan(String ket_penolakan) {
        this.ket_penolakan = ket_penolakan;
    }

    public int getId_hr_pereview() {
        return id_hr_pereview;
    }

    public void setId_hr_pereview(int id_hr_pereview) {
        this.id_hr_pereview = id_hr_pereview;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNama_divisi() {
        return nama_divisi;
    }

    public void setNama_divisi(String nama_divisi) {
        this.nama_divisi = nama_divisi;
    }

    public String getTanggal_awal() {
        return tanggal_awal;
    }

    public void setTanggal_awal(String tanggal_awal) {
        this.tanggal_awal = tanggal_awal;
    }

    public String getKet_cuti() {
        return ket_cuti;
    }

    public void setKet_cuti(String ket_cuti) {
        this.ket_cuti = ket_cuti;
    }
}
