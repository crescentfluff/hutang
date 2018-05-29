package com.pusilkom.hris.service;

import com.pusilkom.hris.model.BenefitModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface BenefitService {

    @PreAuthorize("hasRole('ROLE_HR')")
    void addNewBenefit(BenefitModel benefit);

    @PreAuthorize("hasRole('ROLE_HR')")
    void updateBenefit(BenefitModel benefit);

    @PreAuthorize("hasRole('ROLE_HR')")
    void deleteBenefit(int id_benefit);
    List<BenefitModel> selectBenefit(int id_employee);

    @PreAuthorize("hasRole('ROLE_HR')")
    BenefitModel selectBenefitById(int id_benefit);

}
