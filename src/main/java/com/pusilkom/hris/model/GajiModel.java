package com.pusilkom.hris.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GajiModel {
    private int id_gaji;
    private int id_employee;
    private String tanggal_aktif;
    private int nominal;
    private String username_pengisi;

}
