package com.pusilkom.hris.service;


import com.example.demo.mapper.AbsenMapper;
import com.example.demo.model.AbsenModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AbsenServiceDatabase implements AbsenService {


    @Autowired
    private AbsenMapper absenMapper;

    @Override
    public List<AbsenModel> selectAllAbsen(int id_employee) {
        return absenMapper.selectAllAbsen(id_employee);
    }

    @Override
    public void finalizedAbsen(AbsenModel absen) {
        absenMapper.finalizedAbsen(absen);
    }

    @Override
    public AbsenModel detailAbsen (int id_absen) {
        AbsenModel absen = absenMapper.detailAbsen(id_absen);
        return absen;
    }

    @Override
    public void addAbsen(AbsenModel absen){
        absenMapper.addAbsen(absen);
    }

    @Override
    public List<AbsenModel> selectAllAbsenInactive(int id_employee) {
        return absenMapper.selectAllAbsenInactive(id_employee);
    }
    @Override
    public int selectIdByUsername(String username) {
        return absenMapper.selectIdByUsername(username);
    }

    @Override
    public void unFinalizedAbsen(AbsenModel absen) {
        absenMapper.unFinalizedAbsen(absen);
    }

    @Override
    public void deleteAbsen(AbsenModel absen) {
        absenMapper.deleteAbsen(absen);
    }

    @Override
    public void updateAbsen(AbsenModel absen) {
        absenMapper.updateAbsen(absen);

    }

    @Override
    public List<AbsenModel> selectAbsenByKehadiran(int id) {
        List<AbsenModel> absens = absenMapper.selectAbsenByKehadiran(id);
        if (absens.size()<=0) return null;
        for (AbsenModel abs: absens
             ) {
            log.info("WEH ADA NIH: {}",abs.getId_employee());
        }
        return absens;
    }

    @Override
    public String cekAbsen(AbsenModel absen){
        return absenMapper.cekAbsen(absen);
    }
}
