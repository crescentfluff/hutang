package com.pusilkom.hris.service;

import com.pusilkom.hris.model.PenggunaModel;

import java.util.List;

public interface PenggunaService {
    PenggunaModel selectPenggunaById(int id);
    List<PenggunaModel> selectAllPengguna();
    void updatePengguna(PenggunaModel pengguna);
    void statusPengguna(int idPengguna);


    PenggunaModel selectPenggunaByUsername(String username);
    void addPengguna(PenggunaModel pengguna);
    List<PenggunaModel> selectAllSSO();

    void addRolePengguna(String uname, String role);
}