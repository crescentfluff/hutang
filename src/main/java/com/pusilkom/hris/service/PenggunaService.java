package com.pusilkom.hris.service;

import com.pusilkom.hris.model.PenggunaModel;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface PenggunaService {

    PenggunaModel selectPenggunaById(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<PenggunaModel> selectAllPengguna();

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updatePengguna(PenggunaModel pengguna);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void statusPengguna(int idPengguna);

    PenggunaModel selectPenggunaByUsername(String username);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void addPengguna(PenggunaModel pengguna);

    List<PenggunaModel> selectAllSSO();

    void addRolePengguna(String uname, String role);
}
