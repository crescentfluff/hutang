package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.DivisiModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface DivisiMapper {
//
//    @Insert("INSERT INTO moka.\"divisi\" (nama_divisi, parent_divisi) VALUES (#{nama_divisi}, #{parent_divisi})")
//    void addDivisi(DivisiModel newDivisi);
//
//    @Update("UPDATE moka.\"divisi\" SET nama_divisi = #{nama_divisi}, parent_divisi = #{parent_divisi} WHERE id_divisi = #{id_divisi}")
//    void updateDivisi(DivisiModel Divisi);
//
//    @Select("SELECT a.id_divisi, a.nama_divisi, a.parent_divisi, COALESCE(b.nama_divisi,'-') AS nama_parent_divisi " +
//            "FROM moka.\"divisi\" a LEFT JOIN moka.\"divisi\" b on a.parent_divisi = b.id_divisi WHERE a.id_divisi = #{id_divisi}")
//    DivisiModel selectDivisi(@Param("id_divisi")int id_divisi);
//
//
//    @Select("SELECT a.id_divisi, a.nama_divisi, a.parent_divisi, COALESCE(b.nama_divisi,'-') AS nama_parent_divisi " +
//            "FROM moka.\"divisi\" a LEFT JOIN moka.\"divisi\" b on a.parent_divisi = b.id_divisi WHERE a.is_aktif = 1")
//    List<DivisiModel> selectAllDivisi();
//
//    @Select("SELECT a.id_divisi, a.nama_divisi, a.parent_divisi, COALESCE(b.nama_divisi,'-') AS nama_parent_divisi " +
//            "FROM moka.\"divisi\" a LEFT JOIN moka.\"divisi\" b on a.parent_divisi = b.id_divisi WHERE a.is_aktif = 0")
//    List<DivisiModel> selectAllDivisiInactive();
//
//    @Select("SELECT a.id_divisi, a.nama_divisi, a.parent_divisi, COALESCE(b.nama_divisi,'-') AS nama_parent_divisi " +
//            "FROM moka.\"divisi\" a LEFT JOIN moka.\"divisi\" b on a.parent_divisi = b.id_divisi WHERE a.id_divisi != #{id_divisi} AND a.is_aktif = 1")
//    List<DivisiModel> selectAllParentDivisi(@Param("id_divisi")int id_divisi);
//
//    @Delete("DELETE FROM moka.\"divisi\" WHERE id_divisi = #{id_divisi}")
//    void deleteDivisi(@Param("id_divisi") int id_divisi);
//
//    @Update("UPDATE moka.\"divisi\" SET is_aktif = 1 WHERE id_divisi = #{id_divisi}")
//    void activateDivisi(@Param("id_divisi") int id_divisi);
//
//    @Update("UPDATE moka.\"divisi\" SET is_aktif = 0 WHERE id_divisi = #{id_divisi}")
//    void deactivateDivisi(@Param("id_divisi") int id_divisi);
//
//    @Select("SELECT nip FROM employee WHERE divisi = #{id_divisi} LIMIT 1")
//    String cekAdaDivisiEmp(@Param("id_divisi") int id_divisi);
//}

@Mapper
public interface DivisiMapper {

    @Insert("INSERT INTO divisi (nama_divisi, parent_divisi) VALUES (#{nama_divisi}, #{parent_divisi})")
    void addDivisi(DivisiModel newDivisi);

    @Update("UPDATE divisi SET nama_divisi = #{nama_divisi}, parent_divisi = #{parent_divisi} WHERE id_divisi = #{id_divisi}")
    void updateDivisi(DivisiModel Divisi);

    @Select("SELECT a.id_divisi, a.nama_divisi, a.parent_divisi, COALESCE(b.nama_divisi,'-') AS nama_parent_divisi " +
            "FROM divisi a LEFT JOIN divisi b on a.parent_divisi = b.id_divisi WHERE a.id_divisi = #{id_divisi}")
    DivisiModel selectDivisi(@Param("id_divisi") int id_divisi);

    @Select("SELECT a.id_divisi, a.nama_divisi, a.parent_divisi, COALESCE(b.nama_divisi,'-') AS nama_parent_divisi " +
            "FROM divisi a LEFT JOIN divisi b on a.parent_divisi = b.id_divisi WHERE a.is_aktif = 1")
    List<DivisiModel> selectAllDivisi();

    @Select("SELECT a.id_divisi, a.nama_divisi, a.parent_divisi, COALESCE(b.nama_divisi,'-') AS nama_parent_divisi " +
            "FROM divisi a LEFT JOIN divisi b on a.parent_divisi = b.id_divisi WHERE a.is_aktif = 0")
    List<DivisiModel> selectAllDivisiInactive();

    @Select("SELECT a.id_divisi, a.nama_divisi, a.parent_divisi, COALESCE(b.nama_divisi,'-') AS nama_parent_divisi " +
            "FROM divisi a LEFT JOIN divisi b on a.parent_divisi = b.id_divisi WHERE a.id_divisi != #{id_divisi} AND a.is_aktif = 1")
    List<DivisiModel> selectAllParentDivisi(@Param("id_divisi") int id_divisi);

    @Delete("DELETE FROM divisi WHERE id_divisi = #{id_divisi}")
    void deleteDivisi(@Param("id_divisi") int id_divisi);

    @Update("UPDATE divisi SET is_aktif = 1 WHERE id_divisi = #{id_divisi}")
    void activateDivisi(@Param("id_divisi") int id_divisi);

    @Update("UPDATE divisi SET is_aktif = 0 WHERE id_divisi = #{id_divisi}")
    void deactivateDivisi(@Param("id_divisi") int id_divisi);

    @Select("SELECT nip FROM employee WHERE divisi = #{id_divisi} LIMIT 1")
    String cekAdaDivisiEmp(@Param("id_divisi") int id_divisi);
}
