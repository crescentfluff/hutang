package com.pusilkom.hris.service;

import com.example.demo.mapper.BenefitMapper;
import com.example.demo.model.BenefitModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BenefitServiceDatabase implements BenefitService{

    @Autowired
    private BenefitMapper benefitMapper;

    @Override
    public void addNewBenefit(BenefitModel benefit) {
        benefitMapper.addNewBenefit(benefit);
    }

    @Override
    public List<BenefitModel> selectBenefit (int id_employee){
        return benefitMapper.selectBenefit(id_employee);
    }

    @Override
    public BenefitModel selectBenefitById (int id_benefit){
        return benefitMapper.selectBenefitById(id_benefit);
    }

    @Override
    public void updateBenefit(BenefitModel benefit) {
        benefitMapper.updateBenefit(benefit);
    }

    @Override
    public void deleteBenefit(int id_benefit) {
        benefitMapper.deleteBenefit(id_benefit);
    }

}
