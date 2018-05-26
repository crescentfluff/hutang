package com.pusilkom.hris.model.mapper;


import com.pusilkom.hris.model.ExecutiveModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExecutiveMapper {
//
//    @Select("select nama_divisi, count(*) as jum_pegawai from moka.\"employee\", moka.\"divisi\" , moka.\"datadiri\" where datadiri.id_employee = employee.id_employee and employee.divisi = divisi.id_divisi and datadiri.is_aktif=1 group by nama_divisi")
//    List<ExecutiveModel> selectAllEmployee();
//
//    @Select("select kategori_kehadiran.nama_kategori_kehadiran, count(absen.id_kategori_kehadiran) as jum_kehadiran from moka.\"absen\", moka.\"kategori_kehadiran\" where absen.id_kategori_kehadiran = kategori_kehadiran.id_kehadiran and extract(month from tanggal_absen) = #{bulan} group by kategori_kehadiran.nama_kategori_kehadiran")
//    List<ExecutiveModel> selectAllKehadiran(@Param("bulan") int bulan);
//
//
//    @Select("select nama_kategori_kehadiran, count(id_kategori_kehadiran) as jum_kehadiran from moka.\"kategori_kehadiran\" k, moka.\"absen\" where id_kategori_kehadiran=id_kehadiran and absen.id_employee=#{id_employee} and extract(month from tanggal_absen) = #{bulan} group by nama_kategori_kehadiran")
//    List<ExecutiveModel> selectKehadiranById(@Param("id_employee")int id_employee, @Param("bulan") int bulan);
//
//    @Select("select datadiri.nama_lengkap, nip, employee.id_employee from moka.\"employee\", moka.\"divisi\", moka.\"datadiri\" where datadiri.id_employee = employee.id_employee and employee.divisi = divisi.id_divisi and divisi.nama_divisi = #{nama_divisi} and datadiri.is_aktif = 1")
//    List<ExecutiveModel> selectEmployeeByDivisi(@Param("nama_divisi") String nama_divisi);

    @Select("select nama_divisi, count(*) as jum_pegawai from employee, divisi , datadiri where datadiri.id_employee = employee.id_employee and employee.divisi = divisi.id_divisi and datadiri.is_aktif=1 group by nama_divisi")
    List<ExecutiveModel> selectAllEmployee();

    @Select("select kategori_kehadiran.nama_kategori_kehadiran, count(absen.id_kategori_kehadiran) as jum_kehadiran from absen, kategori_kehadiran where absen.id_kategori_kehadiran = kategori_kehadiran.id_kehadiran and extract(month from tanggal_absen) = #{bulan} group by kategori_kehadiran.nama_kategori_kehadiran")
    List<ExecutiveModel> selectAllKehadiran(@Param("bulan") int bulan);


    @Select("select nama_kategori_kehadiran, count(id_kategori_kehadiran) as jum_kehadiran from kategori_kehadiran k, absen where id_kategori_kehadiran=id_kehadiran and absen.id_employee=#{id_employee} and extract(month from tanggal_absen) = #{bulan} group by nama_kategori_kehadiran")
    List<ExecutiveModel> selectKehadiranById(@Param("id_employee") int id_employee, @Param("bulan") int bulan);

    @Select("select datadiri.nama_lengkap, nip, employee.id_employee from employee, divisi, datadiri where datadiri.id_employee = employee.id_employee and employee.divisi = divisi.id_divisi and divisi.nama_divisi = #{nama_divisi} and datadiri.is_aktif = 1")
    List<ExecutiveModel> selectEmployeeByDivisi(@Param("nama_divisi") String nama_divisi);

}
