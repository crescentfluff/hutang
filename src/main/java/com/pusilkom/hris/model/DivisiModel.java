package com.pusilkom.hris.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DivisiModel {
    private int id_divisi;
    private String nama_divisi;
    private int parent_divisi;
    private String nama_parent_divisi;
    private int is_aktif;
}
