package com.pusilkom.hris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KontrakModel{
    private int id_kontrak;
    private int id_employee;
    private String tanggal_awal;
    private String tanggal_akhir;
    private String username_pengisi;
    private String no_kontrak;
}
