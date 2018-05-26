package com.pusilkom.hris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KategoriModel {
    private int id_kategori;
    @NotNull
    private String nama_kategori;
    private String deskripsi;
}
