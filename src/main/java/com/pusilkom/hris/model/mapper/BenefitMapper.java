package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.BenefitModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface BenefitMapper {
//    @Insert("INSERT INTO moka.\"benefit\" (id_kategori_benefit, id_employee, nominal_benefit, username_pengisi) " +
//            "VALUES (#{id_kategori_benefit}, #{id_employee}, #{nominal_benefit}, #{username_pengisi})")
//    void addNewBenefit(BenefitModel benefit);
//
//    @Select("SELECT * FROM moka.\"benefit\" JOIN moka.\"kategoribenefit\" ON benefit.id_kategori_benefit = kategoribenefit.id_kategori_benefit " +
//            "WHERE BENEFIT.id_employee = #{id_employee} ORDER BY id_benefit DESC")
//    List<BenefitModel> selectBenefit(@Param("id_employee") int id_employee);
//
//    @Select("SELECT * FROM moka.\"benefit\" JOIN moka.\"kategoribenefit\" ON benefit.id_kategori_benefit = kategoribenefit.id_kategori_benefit " +
//            "WHERE BENEFIT.id_benefit = #{id_benefit} ORDER BY id_benefit DESC")
//    BenefitModel selectBenefitById(@Param("id_benefit") int id_benefit);
//
//    @Update("UPDATE moka.\"benefit\" SET id_kategori_benefit = #{id_kategori_benefit}, nominal_benefit = #{nominal_benefit}, username_pengisi = #{username_pengisi} " +
//            "WHERE id_benefit = #{id_benefit}")
//    void updateBenefit(BenefitModel benefit);
//
//    @Delete("DELETE FROM moka.\"benefit\" WHERE id_benefit = #{id_benefit}")
//    void deleteBenefit (@Param("id_benefit") int id_benefit);
//
//}

@Mapper
public interface BenefitMapper {
    @Insert("INSERT INTO benefit (id_kategori_benefit, id_employee, nominal_benefit, username_pengisi) " +
            "VALUES (#{id_kategori_benefit}, #{id_employee}, #{nominal_benefit}, #{username_pengisi})")
    void addNewBenefit(BenefitModel benefit);

    @Select("SELECT * FROM benefit JOIN kategoribenefit ON benefit.id_kategori_benefit = kategoribenefit.id_kategori_benefit " +
            "WHERE BENEFIT.id_employee = #{id_employee} ORDER BY id_benefit DESC")
    List<BenefitModel> selectBenefit(@Param("id_employee") int id_employee);

    @Select("SELECT * FROM benefit JOIN kategoribenefit ON benefit.id_kategori_benefit = kategoribenefit.id_kategori_benefit " +
            "WHERE BENEFIT.id_benefit = #{id_benefit} ORDER BY id_benefit DESC")
    BenefitModel selectBenefitById(@Param("id_benefit") int id_benefit);

    @Update("UPDATE benefit SET id_kategori_benefit = #{id_kategori_benefit}, nominal_benefit = #{nominal_benefit}, username_pengisi = #{username_pengisi} " +
            "WHERE id_benefit = #{id_benefit}")
    void updateBenefit(BenefitModel benefit);

    @Delete("DELETE FROM benefit WHERE id_benefit = #{id_benefit}")
    void deleteBenefit(@Param("id_benefit") int id_benefit);

}
