package com.pusilkom.hris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExecutiveModel {

    private String nama_divisi;
    private int jum_pegawai;
    private String nama_kategori_kehadiran;
    private int jum_kehadiran;
    private String nama_lengkap;
    private int nip;
    private int id_employee;
    private int bulan;

    public int getBulan() {
        return bulan;
    }

    public void setBulan(int bulan) {
        this.bulan = bulan;
    }

    public String getNama_divisi() {
        return nama_divisi;
    }

    public void setNama_divisi(String nama_divisi) {
        this.nama_divisi = nama_divisi;
    }

    public int getJum_pegawai() {
        return jum_pegawai;
    }

    public void setJum_pegawai(int jum_pegawai) {
        this.jum_pegawai = jum_pegawai;
    }

    public String getNama_kategori_kehadiran() {
        return nama_kategori_kehadiran;
    }

    public void setNama_kategori_kehadiran(String nama_kategori_kehadiran) {
        this.nama_kategori_kehadiran = nama_kategori_kehadiran;
    }

    public int getJum_kehadiran() {
        return jum_kehadiran;
    }

    public void setJum_kehadiran(int jum_kehadiran) {
        this.jum_kehadiran = jum_kehadiran;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }



    @Override
    public String toString() {
        if (nama_divisi!=null)
        return "Nama Divisi: "+nama_divisi+", pegawai: ";
        else if (nama_lengkap!=null) return "Employee: "+"("+nama_kategori_kehadiran+": "+jum_kehadiran+")";
        else return "Kategori: "+nama_kategori_kehadiran+", Jumlah:"+jum_kehadiran;
    }
}
