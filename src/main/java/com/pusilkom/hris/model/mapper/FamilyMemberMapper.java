package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.FamilyMemberModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface FamilyMemberMapper {
//
//    @Insert("INSERT INTO moka.\"familymember\"(id_employee, hubungan," +
//            " nama_lengkap, tempat_lahir, tanggal_lahir, " +
//            " no_ktp, npwp, is_aktif) " +
//            "VALUES (#{id_employee}, #{hubungan}, #{nama_lengkap}, #{tempat_lahir}, #{tanggal_lahir}::DATE, #{no_ktp}, #{npwp}, 1)")
//    void addFamilyMember(FamilyMemberModel familyMember);
//
//    @Update("UPDATE moka.\"familymember\" SET is_aktif = 0 WHERE id_family_member = #{id_family_member}")
//    void deleteFamilyMember(@Param("id_family_member") int id_family_member);
//
//    @Update("UPDATE moka.\"familymember\" SET hubungan = #{hubungan}, nama_lengkap = #{nama_lengkap}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}::DATE, no_ktp = #{no_ktp}, npwp = #{npwp}" +
//            " WHERE id_family_member = #{id_family_member}")
//    void updateFamilyMember(FamilyMemberModel familyMember);
//
//    @Select("SELECT * FROM moka.\"familymember\" WHERE id_employee = #{id_employee} AND is_aktif = 1")
//    List<FamilyMemberModel> selectAllFamilyMemberEmployee(@Param("id_employee") int id_employee);
//
//    @Select("SELECT * FROM moka.\"familymember\" WHERE id_employee = #{id_employee} AND id_family_member = #{id_family_member}")
//    FamilyMemberModel selectFamilyMemberEmployee(@Param("id_employee") int id_employee, @Param("id_family_member") int id_family_member);
//
//    @Select("SELECT * FROM moka.\"familymember\" WHERE id_family_member = #{id_family_member}")
//    FamilyMemberModel selectFamilyMemberById(@Param("id_family_member") int id_family_member);
//}

@Mapper
public interface FamilyMemberMapper {

    @Insert("INSERT INTO familymember(id_employee, hubungan," +
            " nama_lengkap, tempat_lahir, tanggal_lahir, " +
            " no_ktp, npwp, is_aktif) " +
            "VALUES (#{id_employee}, #{hubungan}, #{nama_lengkap}, #{tempat_lahir}, #{tanggal_lahir}::DATE, #{no_ktp}, #{npwp}, 1)")
    void addFamilyMember(FamilyMemberModel familyMember);

    @Update("UPDATE familymember SET is_aktif = 0 WHERE id_family_member = #{id_family_member}")
    void deleteFamilyMember(@Param("id_family_member") int id_family_member);

    @Update("UPDATE familymember SET hubungan = #{hubungan}, nama_lengkap = #{nama_lengkap}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}::DATE, no_ktp = #{no_ktp}, npwp = #{npwp}" +
            " WHERE id_family_member = #{id_family_member}")
    void updateFamilyMember(FamilyMemberModel familyMember);

    @Select("SELECT * FROM familymember WHERE id_employee = #{id_employee} AND is_aktif = 1")
    List<FamilyMemberModel> selectAllFamilyMemberEmployee(@Param("id_employee") int id_employee);

    @Select("SELECT * FROM familymember WHERE id_employee = #{id_employee} AND id_family_member = #{id_family_member}")
    FamilyMemberModel selectFamilyMemberEmployee(@Param("id_employee") int id_employee, @Param("id_family_member") int id_family_member);

    @Select("SELECT * FROM familymember WHERE id_family_member = #{id_family_member}")
    FamilyMemberModel selectFamilyMemberById(@Param("id_family_member") int id_family_member);
}
