package com.pusilkom.hris.service;

import com.pusilkom.hris.model.KontrakModel;

import java.util.List;

public interface KontrakService {
    List<KontrakModel> selectAllKontrakEmployee(int id_employee);

    KontrakModel selectKontrakEmployee(int id_employee, int id_kontrak);

    KontrakModel selectKontrakById(int id_kontrak);

    void addKontrak(KontrakModel kontrak);

    void updateKontrak(KontrakModel kontrak);

    void deleteKontrak(int id_kontrak);
}
