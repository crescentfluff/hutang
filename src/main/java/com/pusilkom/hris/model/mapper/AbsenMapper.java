package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.AbsenModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface AbsenMapper {
//
//    @Select("select * from moka.\"absen\" join moka.\"kategori_kehadiran\" on id_kehadiran=id_kategori_kehadiran where id_employee = #{id_employee} and is_finalize = 1")
//    List<AbsenModel> selectAllAbsen(@Param("id_employee") int id_employee);
//
//    @Select("select * from moka.\"absen\" join moka.\"kategori_kehadiran\" on id_kehadiran=id_kategori_kehadiran where id_employee = #{id_employee} and is_finalize = 0")
//    List<AbsenModel> selectAllAbsenInactive(@Param("id_employee") int id_employee);
//
//    @Select("select id_absen, id_employee, tanggal_absen, nama_kategori_kehadiran, id_kategori_kehadiran, keterangan from moka.\"absen\" join moka.\"kategori_kehadiran\" on id_kehadiran=id_kategori_kehadiran where id_absen = #{id_absen}")
//    AbsenModel detailAbsen (@Param("id_absen") int id_absen);
//
//    @Update("update moka.\"absen\" set is_finalize = 1 where id_absen = #{id_absen}")
//    void finalizedAbsen(AbsenModel absen);
//
//    @Update("update moka.\"absen\" set is_finalize = 0 where id_absen = #{id_absen}")
//    void unFinalizedAbsen(AbsenModel absen);
//
//    @Insert("INSERT INTO moka.\"absen\" (id_employee, tanggal_absen, id_kategori_kehadiran, keterangan) VALUES (#{id_employee}, #{tanggal_absen}::DATE, #{id_kategori_kehadiran}, #{keterangan})")
//    void addAbsen(AbsenModel absen);
//
//    @Select("SELECT id_employee FROM moka.\"penggunaemployee\" where username=#{username}")
//    int selectIdByUsername (String username);
//
//    @Delete("delete from moka.\"absen\" where id_absen = #{id_absen}")
//    void deleteAbsen(AbsenModel absen);
//
//    @Update("update moka.\"absen\" set id_kategori_kehadiran = #{id_kategori_kehadiran}, keterangan = #{keterangan} where id_absen = #{id_absen}")
//    void updateAbsen (AbsenModel absen);
//
//    @Select("select id_absen, id_employee, tanggal_absen, nama_kategori_kehadiran, id_kategori_kehadiran, keterangan from moka.\"absen\" join moka.\"kategori_kehadiran\" on id_kehadiran=id_kategori_kehadiran where id_kehadiran = #{id} ")
//    List<AbsenModel> selectAbsenByKehadiran(int id);
//
//    @Select("SELECT tanggal_absen FROM absen where tanggal_absen = #{tanggal_absen}::DATE and id_employee = #{id_employee}")
//    String cekAbsen (AbsenModel absen);
//}

@Mapper
public interface AbsenMapper {

    @Select("select * from absen join kategori_kehadiran on id_kehadiran=id_kategori_kehadiran where id_employee = #{id_employee} and is_finalize = 1")
    List<AbsenModel> selectAllAbsen(@Param("id_employee") int id_employee);

    @Select("select * from absen join kategori_kehadiran on id_kehadiran=id_kategori_kehadiran where id_employee = #{id_employee} and is_finalize = 0")
    List<AbsenModel> selectAllAbsenInactive(@Param("id_employee") int id_employee);

    @Select("select id_absen, id_employee, tanggal_absen, nama_kategori_kehadiran, id_kategori_kehadiran, keterangan from absen join kategori_kehadiran on id_kehadiran=id_kategori_kehadiran where id_absen = #{id_absen}")
    AbsenModel detailAbsen(@Param("id_absen") int id_absen);

    @Update("update absen set is_finalize = 1 where id_absen = #{id_absen}")
    void finalizedAbsen(AbsenModel absen);

    @Update("update absen set is_finalize = 0 where id_absen = #{id_absen}")
    void unFinalizedAbsen(AbsenModel absen);

    @Insert("INSERT INTO absen (id_employee, tanggal_absen, id_kategori_kehadiran, keterangan) VALUES (#{id_employee}, #{tanggal_absen}::DATE, #{id_kategori_kehadiran}, #{keterangan})")
    void addAbsen(AbsenModel absen);

    @Select("SELECT id_employee FROM penggunaemployee where username=#{username}")
    int selectIdByUsername(String username);

    @Delete("delete from absen where id_absen = #{id_absen}")
    void deleteAbsen(AbsenModel absen);

    @Update("update absen set id_kategori_kehadiran = #{id_kategori_kehadiran}, keterangan = #{keterangan} where id_absen = #{id_absen}")
    void updateAbsen(AbsenModel absen);

    @Select("select id_absen, id_employee, tanggal_absen, nama_kategori_kehadiran, id_kategori_kehadiran, keterangan from absen join kategori_kehadiran on id_kehadiran=id_kategori_kehadiran where id_kehadiran = #{id} ")
    List<AbsenModel> selectAbsenByKehadiran(int id);

    @Select("SELECT tanggal_absen FROM absen where tanggal_absen = #{tanggal_absen}::DATE and id_employee = #{id_employee}")
    String cekAbsen(AbsenModel absen);
}
