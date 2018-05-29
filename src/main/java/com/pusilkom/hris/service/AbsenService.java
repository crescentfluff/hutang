package com.pusilkom.hris.service;

import com.pusilkom.hris.model.AbsenModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface AbsenService {

    List<AbsenModel> selectAllAbsen(int id_employee);

    List<AbsenModel> selectAllAbsenInactive(int id_employee);

    void finalizedAbsen(AbsenModel absen);

    void unFinalizedAbsen(AbsenModel absen);

    AbsenModel detailAbsen(int id_absen);

    void addAbsen(AbsenModel absen);

    int selectIdByUsername(String username);

    void deleteAbsen(AbsenModel absen);

    void updateAbsen(AbsenModel absen);

    List<AbsenModel> selectAbsenByKehadiran(int id);

    String cekAbsen(AbsenModel absen);
}
