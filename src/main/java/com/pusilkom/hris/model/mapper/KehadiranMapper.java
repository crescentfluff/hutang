package com.pusilkom.hris.model.mapper;


import com.pusilkom.hris.model.KategoriModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface KehadiranMapper {
//
//    @Insert("INSERT INTO moka.\"kategori_kehadiran\" (nama_kategori_kehadiran) values (#{kategorikehadiran}) ")
//    void addKategoriKehadiran (String kategorikehadiran);
//
//    @Delete("DELETE FROM moka.\"kategori_kehadiran\" WHERE id_kehadiran=#{id_kehadiran}")
//    void deleteKategoriKehadiran(int id_kehadiran);
//
//    @Update("UPDATE moka.\"kategori_kehadiran\" SET nama_kategori_kehadiran=#{nama_kategori} WHERE id_kehadiran=#{id_kehadiran}")
//    void updateKategoriKehadiran(@Param("nama_kategori") String nama_kategori,
//                                 @Param("id_kehadiran") int id_kategori);
//
//    @Select("SELECT * FROM moka.\"kategori_kehadiran\" ORDER BY id_kehadiran asc")
//    @Results(value= {
//            @Result(property="id_kategori", column="id_kehadiran"),
//            @Result(property="nama_kategori", column="nama_kategori_kehadiran")})
//    List<KategoriModel> selectAllKategoriKehadiran();
//
//    @Select("SELECT * FROM moka.\"kategori_kehadiran\" WHERE id_kehadiran=#{id}")
//    @Results(value= {
//            @Result(property="id_kategori", column="id_kehadiran"),
//            @Result(property="nama_kategori", column="nama_kategori_kehadiran")})
//    KategoriModel  selectKehadiran(int id);
//}

@Mapper
public interface KehadiranMapper {

    @Insert("INSERT INTO kategori_kehadiran (nama_kategori_kehadiran) values (#{kategorikehadiran}) ")
    void addKategoriKehadiran(String kategorikehadiran);

    @Delete("DELETE FROM kategori_kehadiran WHERE id_kehadiran=#{id_kehadiran}")
    void deleteKategoriKehadiran(int id_kehadiran);

    @Update("UPDATE kategori_kehadiran SET nama_kategori_kehadiran=#{nama_kategori} WHERE id_kehadiran=#{id_kehadiran}")
    void updateKategoriKehadiran(@Param("nama_kategori") String nama_kategori,
                                 @Param("id_kehadiran") int id_kategori);

    @Select("SELECT * FROM kategori_kehadiran ORDER BY id_kehadiran asc")
    @Results(value= {
            @Result(property="id_kategori", column="id_kehadiran"),
            @Result(property="nama_kategori", column="nama_kategori_kehadiran")})
    List<KategoriModel> selectAllKategoriKehadiran();

    @Select("SELECT * FROM kategori_kehadiran WHERE id_kehadiran=#{id}")
    @Results(value= {
            @Result(property="id_kategori", column="id_kehadiran"),
            @Result(property="nama_kategori", column="nama_kategori_kehadiran")})
    KategoriModel  selectKehadiran(int id);
}
