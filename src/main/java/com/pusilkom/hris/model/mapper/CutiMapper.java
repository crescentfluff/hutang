package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.CutiModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface CutiMapper {
//    @Select(" select * from moka.\"cuti\", moka.\"datadiri\", moka.\"divisi\" d, moka.\"employee\" where cuti.id_employee=employee.id_employee and employee.divisi=d.id_divisi and employee.id_employee = datadiri.id_employee and datadiri.is_aktif=1")
//    List<CutiModel> selectAllCuti();
//
//    @Select("select * from moka.\"cuti\", moka.\"datadiri\", moka.\"divisi\" d, moka.\"employee\" where cuti.id_employee=employee.id_employee and employee.divisi=d.id_divisi and employee.id_employee = datadiri.id_employee and datadiri.is_aktif=1 and cuti.id_cuti=#{id_cuti}")
//    CutiModel detailCuti(@Param("id_cuti") int id_cuti);
//
//    @Update("update moka.\"cuti\" set status = #{status}, ket_penolakan = #{ket_penolakan}, username_pereview = #{username_pereview} where id_cuti = #{id_cuti}")
//    void updateCuti(CutiModel cuti);
//
//    @Select("select cuti.id_employee, cuti.id_cuti, cuti.tanggal_awal, cuti.tanggal_akhir, cuti.ket_cuti, cuti.ket_penolakan, cuti.status from moka.\"cuti\" where cuti.id_employee = #{id_employee}")
//    List<CutiModel> selectAllCutiAEmployee(int id_employee);
//
//    @Insert("insert into moka.\"cuti\" (id_employee,tanggal_awal,tanggal_akhir,status,ket_cuti,ket_penolakan,username_pereview) values (#{id_employee}, #{dateAwal}::DATE, #{dateAkhir}::DATE, 0, #{ket_cuti}, null, null)")
//    void addCuti(@Param("id_employee") int id_employee, @Param("dateAwal") String tanggal_awal, @Param("dateAkhir") String tanggal_akhir, @Param("ket_cuti") String ket_cuti);
//
//    @Select("select * from moka.\"cuti\" where cuti.id_employee = #{id_employee} and cuti.id_cuti = #{id_cuti}")
//    CutiModel detailCutiEmployee(@Param("id_employee") int id_employee, @Param("id_cuti") int id_cuti);
//}

@Mapper
public interface CutiMapper {
    @Select(" select * from cuti, datadiri, divisi d, employee where cuti.id_employee=employee.id_employee and employee.divisi=d.id_divisi and employee.id_employee = datadiri.id_employee and datadiri.is_aktif=1")
    List<CutiModel> selectAllCuti();

    @Select("select * from cuti, datadiri, divisi d, employee where cuti.id_employee=employee.id_employee and employee.divisi=d.id_divisi and employee.id_employee = datadiri.id_employee and datadiri.is_aktif=1 and cuti.id_cuti=#{id_cuti}")
    CutiModel detailCuti(@Param("id_cuti") int id_cuti);

    @Update("update cuti set status = #{status}, ket_penolakan = #{ket_penolakan}, username_pereview = #{username_pereview} where id_cuti = #{id_cuti}")
    void updateCuti(CutiModel cuti);

    @Select("select cuti.id_employee, cuti.id_cuti, cuti.tanggal_awal, cuti.tanggal_akhir, cuti.ket_cuti, cuti.ket_penolakan, cuti.status from cuti where cuti.id_employee = #{id_employee}")
    List<CutiModel> selectAllCutiAEmployee(int id_employee);

    @Insert("insert into cuti (id_employee,tanggal_awal,tanggal_akhir,status,ket_cuti,ket_penolakan,username_pereview) values (#{id_employee}, #{dateAwal}::DATE, #{dateAkhir}::DATE, 0, #{ket_cuti}, null, null)")
    void addCuti(@Param("id_employee") int id_employee, @Param("dateAwal") String tanggal_awal, @Param("dateAkhir") String tanggal_akhir, @Param("ket_cuti") String ket_cuti);

    @Select("select * from cuti where cuti.id_employee = #{id_employee} and cuti.id_cuti = #{id_cuti}")
    CutiModel detailCutiEmployee(@Param("id_employee") int id_employee, @Param("id_cuti") int id_cuti);
}