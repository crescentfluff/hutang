package com.pusilkom.hris.service;

import com.pusilkom.hris.model.FamilyMemberModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface FamilyMemberService {

    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_EMPLOYEE')")
    List<FamilyMemberModel> selectAllFamilyMemberEmployee(int id_employee);

    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_EMPLOYEE')")
    FamilyMemberModel selectFamilyMemberEmployee(int id_employee, int id_family_member);

    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_EMPLOYEE')")
    FamilyMemberModel selectFamilyMemberById(int id_family_member);

    @PreAuthorize("hasRole('ROLE_HR')")
    void addFamilyMember(FamilyMemberModel familyMembe);

    @PreAuthorize("hasRole('ROLE_HR')")
    void updateFamilyMember(FamilyMemberModel familyMember);

    @PreAuthorize("hasRole('ROLE_HR')")
    void deleteFamilyMember(int id_family_member);
}