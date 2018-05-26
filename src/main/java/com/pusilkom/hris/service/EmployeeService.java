package com.pusilkom.hris.service;

import com.pusilkom.hris.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {
    EmployeeModel selectEmployee(int id_employee);

    List<EmployeeModel> selectAllEmployees();

    List<EmployeeModel> selectAllEmployeesInactive();

    void addEmployee(EmployeeModel employee);

    void addNewEmployee(EmployeeModel employee);

    void updateEmployee(EmployeeModel employee);

    void deleteEmployee(int id_employee);

    void deactivateEmployee(int id_employee);

    void activateEmployee(int id_employee);
}