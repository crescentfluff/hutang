package com.pusilkom.hris.service;

import com.pusilkom.hris.model.mapper.GajiMapper;
import com.pusilkom.hris.model.GajiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GajiServiceDatabase implements GajiService {

    @Autowired
    private GajiMapper gajiMapper;

    @Override
    public void addNewGaji(GajiModel gaji) {
        gajiMapper.addNewGaji(gaji);
    }

    @Override
    public List<GajiModel> selectGaji (int id_employee){
        return gajiMapper.selectGaji(id_employee);
    }

    @Override
    public GajiModel selectGajiById (int id_gaji){
        return gajiMapper.selectGajiById(id_gaji);
    }

    @Override
    public void updateGaji(GajiModel gaji) {
        gajiMapper.updateGaji(gaji);
    }

    @Override
    public void deleteGaji(int id_gaji) {
        gajiMapper.deleteGaji(id_gaji);
    }
}