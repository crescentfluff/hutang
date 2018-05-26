package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.KategoriModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface KategoriBenefitMapper {
//
//    @Insert("INSERT INTO moka.\"kategoribenefit\" (nama_kategori_benefit, deskripsi) VALUES (#{nama_kategori}, #{deskripsi})")
//    void addKategoriBenefit(KategoriModel newKategori);
//
//    @Update("UPDATE moka.\"kategoribenefit\" SET nama_kategori_benefit = #{nama_kategori}, deskripsi = #{deskripsi} WHERE id_kategori_benefit = #{id_kategori}")
//    void updateKategoriBenefit(KategoriModel kategori);
//
//    @Delete("DELETE FROM moka.\"kategoribenefit\" WHERE id_kategori_benefit = #{id_kategori}")
//    void deleteKategoriBenefit(@Param("id_kategori") int id_kategori);
//
//    @Select("SELECT * FROM moka.\"kategoribenefit\" WHERE id_kategori_benefit = #{id_kategori}")
//    @Results(value= {
//            @Result(property="id_kategori", column="id_kategori_benefit"),
//            @Result(property="nama_kategori", column="nama_kategori_benefit"),
//            @Result(property="deskripsi", column="deskripsi")})
//    KategoriModel selectKategoriBenefit(@Param("id_kategori") int id_kategori);
//
//    @Select("SELECT * FROM moka.\"kategoribenefit\"")
//    @Results(value= {
//            @Result(property="id_kategori", column="id_kategori_benefit"),
//            @Result(property="nama_kategori", column="nama_kategori_benefit"),
//            @Result(property="deskripsi", column="deskripsi")})
//    List<KategoriModel> selectAllKategoriBenefit();
//
//    @Select("SELECT id_kategori_benefit FROM benefit WHERE id_kategori_benefit = #{id_kategori} LIMIT 1")
//    String cekAdaBenefit(@Param("id_kategori") int id_kategori);
//}

@Mapper
public interface KategoriBenefitMapper {

    @Insert("INSERT INTO kategoribenefit (nama_kategori_benefit, deskripsi) VALUES (#{nama_kategori}, #{deskripsi})")
    void addKategoriBenefit(KategoriModel newKategori);

    @Update("UPDATE kategoribenefit SET nama_kategori_benefit = #{nama_kategori}, deskripsi = #{deskripsi} WHERE id_kategori_benefit = #{id_kategori}")
    void updateKategoriBenefit(KategoriModel kategori);

    @Delete("DELETE FROM kategoribenefit WHERE id_kategori_benefit = #{id_kategori}")
    void deleteKategoriBenefit(@Param("id_kategori") int id_kategori);

    @Select("SELECT * FROM kategoribenefit WHERE id_kategori_benefit = #{id_kategori}")
    @Results(value= {
            @Result(property="id_kategori", column="id_kategori_benefit"),
            @Result(property="nama_kategori", column="nama_kategori_benefit"),
            @Result(property="deskripsi", column="deskripsi")})
    KategoriModel selectKategoriBenefit(@Param("id_kategori") int id_kategori);

    @Select("SELECT * FROM kategoribenefit")
    @Results(value= {
            @Result(property="id_kategori", column="id_kategori_benefit"),
            @Result(property="nama_kategori", column="nama_kategori_benefit"),
            @Result(property="deskripsi", column="deskripsi")})
    List<KategoriModel> selectAllKategoriBenefit();

    @Select("SELECT id_kategori_benefit FROM benefit WHERE id_kategori_benefit = #{id_kategori} LIMIT 1")
    String cekAdaBenefit(@Param("id_kategori") int id_kategori);
}