package com.pusilkom.hris.service;

import com.pusilkom.hris.model.FamilyMemberModel;

import java.util.List;

public interface FamilyMemberService {
    List<FamilyMemberModel> selectAllFamilyMemberEmployee(int id_employee);

    FamilyMemberModel selectFamilyMemberEmployee(int id_employee, int id_family_member);

    FamilyMemberModel selectFamilyMemberById(int id_family_member);

    void addFamilyMember(FamilyMemberModel familyMembe);

    void updateFamilyMember(FamilyMemberModel familyMember);

    void deleteFamilyMember(int id_family_member);
}