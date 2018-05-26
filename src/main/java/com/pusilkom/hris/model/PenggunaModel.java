package com.pusilkom.hris.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PenggunaModel {
    private int id;
    private String username;
    private int is_aktif;
    private EmployeeModel employee;
    private List<String> role;
    private String nama;
    private int id_employee;
}
