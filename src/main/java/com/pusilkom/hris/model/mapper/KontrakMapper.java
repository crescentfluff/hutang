package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.KontrakModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface KontrakMapper {
//    @Insert("INSERT INTO moka.\"kontrak\"(id_employee, tanggal_awal, tanggal_akhir, username_pengisi, no_kontrak) " +
//            "VALUES (#{id_employee}, #{tanggal_awal}::DATE, #{tanggal_akhir}::DATE, #{username_pengisi}, #{no_kontrak})")
//    void addKontrak(KontrakModel kontrak);
//
//    @Update("DELETE FROM moka.\"kontrak\" WHERE id_kontrak = #{id_kontrak}")
//    void deleteKontrak(@Param("id_kontrak") int id_kontrak);
//
//    @Update("UPDATE moka.\"kontrak\" SET tanggal_awal = #{tanggal_awal}::DATE, tanggal_akhir = #{tanggal_akhir}::DATE, " +
//            "username_pengisi = #{username_pengisi}, no_kontrak = #{no_kontrak} WHERE id_kontrak = #{id_kontrak}")
//    void updateKontrak(KontrakModel kontrak);
//
//    @Select("SELECT * FROM moka.\"kontrak\" WHERE id_employee = #{id_employee} ORDER BY id_kontrak DESC")
//    List<KontrakModel> selectAllKontrakEmployee(@Param("id_employee") int id_employee);
//
//    @Select("SELECT * FROM moka.\"kontrak\" WHERE id_employee = #{id_employee} AND id_kontrak = #{id_kontrak}")
//    KontrakModel selectKontrakEmployee(@Param("id_employee") int id_employee, @Param("id_kontrak") int id_kontrak);
//
//    @Select("SELECT * FROM moka.\"kontrak\" WHERE id_kontrak = #{id_kontrak}")
//    KontrakModel selectKontrakById(@Param("id_kontrak") int id_kontrak);
//
//}

@Mapper
public interface KontrakMapper {
    @Insert("INSERT INTO kontrak(id_employee, tanggal_awal, tanggal_akhir, username_pengisi, no_kontrak) " +
            "VALUES (#{id_employee}, #{tanggal_awal}::DATE, #{tanggal_akhir}::DATE, #{username_pengisi}, #{no_kontrak})")
    void addKontrak(KontrakModel kontrak);

    @Update("DELETE FROM kontrak WHERE id_kontrak = #{id_kontrak}")
    void deleteKontrak(@Param("id_kontrak") int id_kontrak);

    @Update("UPDATE kontrak SET tanggal_awal = #{tanggal_awal}::DATE, tanggal_akhir = #{tanggal_akhir}::DATE, " +
            "username_pengisi = #{username_pengisi}, no_kontrak = #{no_kontrak} WHERE id_kontrak = #{id_kontrak}")
    void updateKontrak(KontrakModel kontrak);

    @Select("SELECT * FROM kontrak WHERE id_employee = #{id_employee} ORDER BY id_kontrak DESC")
    List<KontrakModel> selectAllKontrakEmployee(@Param("id_employee") int id_employee);

    @Select("SELECT * FROM kontrak WHERE id_employee = #{id_employee} AND id_kontrak = #{id_kontrak}")
    KontrakModel selectKontrakEmployee(@Param("id_employee") int id_employee, @Param("id_kontrak") int id_kontrak);

    @Select("SELECT * FROM kontrak WHERE id_kontrak = #{id_kontrak}")
    KontrakModel selectKontrakById(@Param("id_kontrak") int id_kontrak);

}
