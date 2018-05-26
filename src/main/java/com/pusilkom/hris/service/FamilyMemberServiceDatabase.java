package com.pusilkom.hris.service;

import com.pusilkom.hris.model.mapper.FamilyMemberMapper;
import com.pusilkom.hris.model.FamilyMemberModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FamilyMemberServiceDatabase implements FamilyMemberService{

    @Autowired
    private FamilyMemberMapper valianMapper;

    @Override
    public List<FamilyMemberModel> selectAllFamilyMemberEmployee(int id_employee){
        return valianMapper.selectAllFamilyMemberEmployee(id_employee);
    }

    @Override
    public FamilyMemberModel selectFamilyMemberEmployee(int id_employee, int id_family_member){
        return  valianMapper.selectFamilyMemberEmployee(id_employee, id_family_member);
    }

    @Override
    public FamilyMemberModel selectFamilyMemberById(int id_family_member){
        return  valianMapper.selectFamilyMemberById(id_family_member);
    }

    @Override
    public void addFamilyMember(FamilyMemberModel familyMember){
        valianMapper.addFamilyMember(familyMember);
    }

    @Override
    public void updateFamilyMember(FamilyMemberModel familyMember){
        valianMapper.updateFamilyMember(familyMember);
    }

    @Override
    public void deleteFamilyMember(int id_family_member){
        valianMapper.deleteFamilyMember(id_family_member);
    }
}
