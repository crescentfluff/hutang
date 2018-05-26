package com.pusilkom.hris.service;

import com.pusilkom.hris.model.mapper.KategoriBenefitMapper;
import com.pusilkom.hris.model.KategoriModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KategoriBenefitServiceDatabase implements KategoriBenefitService {

    @Autowired
    private KategoriBenefitMapper KategoriBenefitMapper;

    @Override
    public void addKategoriBenefit (KategoriModel newKategoriBenefit){
        System.out.println(newKategoriBenefit);
        KategoriBenefitMapper.addKategoriBenefit(newKategoriBenefit);
    }

    @Override
    public KategoriModel selectKategoriBenefit(int id){
        return KategoriBenefitMapper.selectKategoriBenefit(id);
    }

    @Override
    public List<KategoriModel> selectAllKategoriBenefit(){
        return KategoriBenefitMapper.selectAllKategoriBenefit();
    }

    @Override
    public void deleteKategoriBenefit(int id){
        KategoriBenefitMapper.deleteKategoriBenefit(id);
    }

    @Override
    public void updateKategoriBenefit(KategoriModel newKategoriBenefit){
        KategoriBenefitMapper.updateKategoriBenefit(newKategoriBenefit);
    }

    @Override
    public String cekAdaBenefit(int id_kategori){
        return KategoriBenefitMapper.cekAdaBenefit(id_kategori);
    }

}
