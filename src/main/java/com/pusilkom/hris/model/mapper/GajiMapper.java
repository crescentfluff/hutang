package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.GajiModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface GajiMapper {
//
//    @Insert("INSERT INTO moka.\"gaji\" (tanggal_aktif, nominal, id_employee, username_pengisi) VALUES (#{tanggal_aktif}::DATE, #{nominal}, #{id_employee}, #{username_pengisi})")
//    void addNewGaji(GajiModel gaji);
//
//    @Select("SELECT * FROM moka.\"gaji\" WHERE id_employee = #{id_employee} ORDER BY id_gaji DESC")
//    List<GajiModel> selectGaji(@Param("id_employee") int id_employee);
//
//    @Select("SELECT * FROM moka.\"gaji\" WHERE id_gaji = #{id_gaji} ORDER BY id_gaji DESC")
//    GajiModel selectGajiById(@Param("id_gaji") int id_gaji);
//
//    @Update("UPDATE moka.\"gaji\" SET tanggal_aktif = #{tanggal_aktif}::DATE, nominal = #{nominal} WHERE id_gaji = #{id_gaji}")
//    void updateGaji(GajiModel gaji);
//
//    @Delete("DELETE FROM moka.\"gaji\" WHERE id_gaji = #{id_gaji}")
//    void deleteGaji (@Param("id_gaji") int id_gaji);
//}

@Mapper
public interface GajiMapper {

    @Insert("INSERT INTO gaji (tanggal_aktif, nominal, id_employee, username_pengisi) VALUES (#{tanggal_aktif}::DATE, #{nominal}, #{id_employee}, #{username_pengisi})")
    void addNewGaji(GajiModel gaji);

    @Select("SELECT * FROM gaji WHERE id_employee = #{id_employee} ORDER BY id_gaji DESC")
    List<GajiModel> selectGaji(@Param("id_employee") int id_employee);

    @Select("SELECT * FROM gaji WHERE id_gaji = #{id_gaji} ORDER BY id_gaji DESC")
    GajiModel selectGajiById(@Param("id_gaji") int id_gaji);

    @Update("UPDATE gaji SET tanggal_aktif = #{tanggal_aktif}::DATE, nominal = #{nominal} WHERE id_gaji = #{id_gaji}")
    void updateGaji(GajiModel gaji);

    @Delete("DELETE FROM gaji WHERE id_gaji = #{id_gaji}")
    void deleteGaji(@Param("id_gaji") int id_gaji);
}

