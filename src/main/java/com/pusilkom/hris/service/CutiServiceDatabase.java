package com.pusilkom.hris.service;

import com.pusilkom.hris.model.mapper.CutiMapper;
import com.pusilkom.hris.model.CutiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CutiServiceDatabase implements CutiService{

    @Autowired
    private CutiMapper cutiMapper;

    @Override
    public List<CutiModel> selectAllCuti() {

        return cutiMapper.selectAllCuti();
    }

    @Override
    public CutiModel detailCuti(int id_employee) {
        return cutiMapper.detailCuti(id_employee);
    }

    @Override
    public void updateCuti (CutiModel cuti) {

        cutiMapper.updateCuti(cuti);
    }

    @Override
    public List<CutiModel> selectAllCutiAEmployee(int id_employee){
        return cutiMapper.selectAllCutiAEmployee(id_employee);
    }

    @Override
    public void addCuti(int id_employee, String tanggal_awal, String tanggal_akhir, String ket_cuti){
        cutiMapper.addCuti(id_employee, tanggal_awal, tanggal_akhir, ket_cuti);
    }

    @Override
    public CutiModel detailCutiEmployee(int id_employee, int id_cuti){
        return cutiMapper.detailCutiEmployee(id_employee, id_cuti);
    }
}
