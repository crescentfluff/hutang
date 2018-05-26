package com.pusilkom.hris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyMemberModel {
    private int id_family_member;
    private int id_employee;
    private String hubungan;
    private String nama_lengkap;
    private String tempat_lahir;
    private String tanggal_lahir;
    private String no_ktp;
    private String npwp;
    private int is_aktif;
}
