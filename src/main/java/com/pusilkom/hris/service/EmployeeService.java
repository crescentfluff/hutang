package com.pusilkom.hris.service;

import com.pusilkom.hris.model.EmployeeModel;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface EmployeeService {
    EmployeeModel selectEmployee(int id_employee);

    @PreAuthorize("hasRole('ROLE_HR')")
    List<EmployeeModel> selectAllEmployees();

    @PreAuthorize("hasRole('ROLE_HR')")
    List<EmployeeModel> selectAllEmployeesInactive();

    @PreAuthorize("hasRole('ROLE_HR')")
    void addEmployee(EmployeeModel employee);

    @PreAuthorize("hasRole('ROLE_HR')")
    void addNewEmployee(EmployeeModel employee);

    void updateEmployee(EmployeeModel employee);

    @PreAuthorize("hasRole('ROLE_HR')")
    void deleteEmployee(int id_employee);

    @PreAuthorize("hasRole('ROLE_HR')")
    void deactivateEmployee(int id_employee);

    @PreAuthorize("hasRole('ROLE_HR')")
    void activateEmployee(int id_employee);

    @PreAuthorize("hasAnyRole('ROLE_HR','ROLE_ADMIN')")
    EmployeeModel selectEmployeeByUsername(String username);
}