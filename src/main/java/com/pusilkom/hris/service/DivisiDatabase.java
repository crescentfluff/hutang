package com.pusilkom.hris.service;

import com.example.demo.mapper.DivisiMapper;
import com.example.demo.model.DivisiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DivisiDatabase implements DivisiService {

    @Autowired
    private DivisiMapper divisiMapper;

    @Override
    public void addDivisi (DivisiModel newDivisi){
        System.out.println(newDivisi);
        divisiMapper.addDivisi(newDivisi);
    }

    @Override
    public void updateDivisi(DivisiModel updatedDivisi){
        divisiMapper.updateDivisi(updatedDivisi);
    }

    @Override
    public DivisiModel selectDivisi (int id_divisi){
        return divisiMapper.selectDivisi(id_divisi);
    }

    @Override
    public List<DivisiModel> selectAllDivisi(){
        return divisiMapper.selectAllDivisi();
    }

    @Override
    public List<DivisiModel> selectAllDivisiInactive(){
        return divisiMapper.selectAllDivisiInactive();
    }

    @Override
    public void deleteDivisi (int id_divisi){
        divisiMapper.deleteDivisi(id_divisi);
    }

    @Override
    public List<DivisiModel> selectAllParentDivisi(DivisiModel divisi){
        return divisiMapper.selectAllParentDivisi(divisi.getId_divisi());
    }

    @Override
    public void deactiveDivisi(int id_divisi){
        divisiMapper.deactivateDivisi(id_divisi);

    }

    @Override
    public void activateDivisi(int id_divisi) {
        divisiMapper.activateDivisi(id_divisi);
    }

    @Override
    public String cekAdaDivisi(int id){ return divisiMapper.cekAdaDivisiEmp(id);}
}
