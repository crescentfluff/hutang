package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.DataDiriModel;
import com.pusilkom.hris.model.EmployeeModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//@Mapper
//public interface DataDiriMapper {
//    @Insert("INSERT INTO moka.\"datadiri\" (id_employee, nama_lengkap, jenis_kelamin, tempat_lahir, tanggal_lahir, " +
//            "alamat_tinggal, alamat_ktp, email_kantor, email_pribadi, no_telepon_rumah, no_telepon_darurat, " +
//            "no_telepon_seluler, no_bpjs_kesehatan, no_bpjs_tenaga_kerja, npwp, no_KTP, kewarganegaraan, agama, " +
//            "status_kawin, no_rekening, bank_rekening, atas_nama_rekening, is_aktif) " +
//            "VALUES (#{id_employee}, #{nama_lengkap}, #{jenis_kelamin}, #{tempat_lahir}, #{tanggal_lahir}::DATE, " +
//            " #{alamat_tinggal}, #{alamat_ktp}, #{email_kantor}, #{email_pribadi}, #{no_telepon_rumah}, #{no_telepon_darurat}, " +
//            " #{no_telepon_seluler}, #{no_bpjs_kesehatan}, #{no_bpjs_tenaga_kerja}, #{npwp}, #{no_ktp}, #{kewarganegaraan}, " +
//            " #{agama}, #{status_kawin}, #{no_rekening}, #{bank_rekening}, #{atas_nama_rekening}, 1)")
//    void addDataDiri(EmployeeModel employee);
//
//    @Insert("UPDATE moka.\"datadiri\" SET is_aktif = 0 WHERE id_employee = #{id_employee}")
//    void deleteDataDiri(@Param("id_employee") int id_employee);
//
//    @Select("SELECT id_data_diri, id_employee, nama_lengkap, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat_tinggal, " +
//            "alamat_ktp, email_kantor, email_pribadi, no_telepon_rumah, no_telepon_darurat, no_telepon_seluler," +
//            " no_bpjs_kesehatan, no_bpjs_tenaga_kerja, npwp, no_KTP, kewarganegaraan, agama, status_kawin, no_rekening, " +
//            "bank_rekening, atas_nama_rekening, is_aktif FROM moka.\"datadiri\" WHERE id_data_diri = #{id_data_diri}")
//    DataDiriModel selectDataDiri(int id_data_diri);
//
//    @Select("SELECT id_data_diri, id_employee, nama_lengkap, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat_tinggal, " +
//            "alamat_ktp, email_kantor, email_pribadi, no_telepon_rumah, no_telepon_darurat, no_telepon_seluler," +
//            " no_bpjs_kesehatan, no_bpjs_tenaga_kerja, npwp, no_KTP, kewarganegaraan, agama, status_kawin, no_rekening, " +
//            "bank_rekening, atas_nama_rekening, is_aktif FROM moka.\"datadiri\" WHERE id_employee = #{id_employee} and is_aktif = 1")
//    DataDiriModel selectDataDiriEmployee(@Param("id_employee") int id_employee);
//
//}

@Mapper
public interface DataDiriMapper {
    @Insert("INSERT INTO datadiri (id_employee, nama_lengkap, jenis_kelamin, tempat_lahir, tanggal_lahir, " +
            "alamat_tinggal, alamat_ktp, email_kantor, email_pribadi, no_telepon_rumah, no_telepon_darurat, " +
            "no_telepon_seluler, no_bpjs_kesehatan, no_bpjs_tenaga_kerja, npwp, no_KTP, kewarganegaraan, agama, " +
            "status_kawin, no_rekening, bank_rekening, atas_nama_rekening, is_aktif) " +
            "VALUES (#{id_employee}, #{nama_lengkap}, #{jenis_kelamin}, #{tempat_lahir}, #{tanggal_lahir}::DATE, " +
            " #{alamat_tinggal}, #{alamat_ktp}, #{email_kantor}, #{email_pribadi}, #{no_telepon_rumah}, #{no_telepon_darurat}, " +
            " #{no_telepon_seluler}, #{no_bpjs_kesehatan}, #{no_bpjs_tenaga_kerja}, #{npwp}, #{no_ktp}, #{kewarganegaraan}, " +
            " #{agama}, #{status_kawin}, #{no_rekening}, #{bank_rekening}, #{atas_nama_rekening}, 1)")
    void addDataDiri(EmployeeModel employee);

    @Insert("UPDATE datadiri SET is_aktif = 0 WHERE id_employee = #{id_employee}")
    void deleteDataDiri(@Param("id_employee") int id_employee);

    @Select("SELECT id_data_diri, id_employee, nama_lengkap, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat_tinggal, " +
            "alamat_ktp, email_kantor, email_pribadi, no_telepon_rumah, no_telepon_darurat, no_telepon_seluler," +
            " no_bpjs_kesehatan, no_bpjs_tenaga_kerja, npwp, no_KTP, kewarganegaraan, agama, status_kawin, no_rekening, " +
            "bank_rekening, atas_nama_rekening, is_aktif FROM datadiri WHERE id_data_diri = #{id_data_diri}")
    DataDiriModel selectDataDiri(int id_data_diri);

    @Select("SELECT id_data_diri, id_employee, nama_lengkap, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat_tinggal, " +
            "alamat_ktp, email_kantor, email_pribadi, no_telepon_rumah, no_telepon_darurat, no_telepon_seluler," +
            " no_bpjs_kesehatan, no_bpjs_tenaga_kerja, npwp, no_KTP, kewarganegaraan, agama, status_kawin, no_rekening, " +
            "bank_rekening, atas_nama_rekening, is_aktif FROM datadiri WHERE id_employee = #{id_employee} and is_aktif = 1")
    DataDiriModel selectDataDiriEmployee(@Param("id_employee") int id_employee);

}

