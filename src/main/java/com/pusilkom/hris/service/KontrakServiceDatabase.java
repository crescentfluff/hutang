package com.pusilkom.hris.service;

import com.pusilkom.hris.model.mapper.KontrakMapper;
import com.pusilkom.hris.model.KontrakModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KontrakServiceDatabase implements KontrakService{

    @Autowired
    private KontrakMapper valianMapper;

    @Override
    public List<KontrakModel> selectAllKontrakEmployee(int id_employee){
        return valianMapper.selectAllKontrakEmployee(id_employee);
    }

    @Override
    public KontrakModel selectKontrakEmployee(int id_employee, int id_kontrak){
        return  valianMapper.selectKontrakEmployee(id_employee, id_kontrak);
    }

    @Override
    public KontrakModel selectKontrakById(int id_kontrak){
        return  valianMapper.selectKontrakById(id_kontrak);
    }

    @Override
    public void addKontrak(KontrakModel kontrak){
        valianMapper.addKontrak(kontrak);
    }

    @Override
    public void updateKontrak(KontrakModel kontrak){
        valianMapper.updateKontrak(kontrak);
    }

    @Override
    public void deleteKontrak(int id_kontrak){
        valianMapper.deleteKontrak(id_kontrak);
    }
}
