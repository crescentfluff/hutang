package com.pusilkom.hris.service;

import com.example.demo.model.BenefitModel;

import java.util.List;

public interface BenefitService {
    void addNewBenefit(BenefitModel benefit);
    void updateBenefit(BenefitModel benefit);
    void deleteBenefit(int id_benefit);
    List<BenefitModel> selectBenefit(int id_employee);
    BenefitModel selectBenefitById(int id_benefit);

}
