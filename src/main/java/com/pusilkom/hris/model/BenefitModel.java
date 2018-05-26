package com.pusilkom.hris.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefitModel {
    private int id_kategori_benefit;
    private String nama_kategori_benefit;
    private int id_benefit;
    private int id_employee;
    private int nominal_benefit;
    private int is_aktif;
    private String username_pengisi;
}
