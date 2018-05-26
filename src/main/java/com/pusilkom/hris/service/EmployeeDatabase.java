package com.pusilkom.hris.service;

import com.pusilkom.hris.model.EmployeeModel;
import com.pusilkom.hris.model.mapper.DataDiriMapper;
import com.pusilkom.hris.model.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeDatabase implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private DataDiriMapper dataDiriMapper;

    @Override
    public void addEmployee(EmployeeModel employee){
        employeeMapper.updateEmployee(employee);
        dataDiriMapper.addDataDiri(employee);
    }

    @Override
    public void addNewEmployee(EmployeeModel employee){
        employeeMapper.addEmployee(employee);
        employee.setId_employee(employeeMapper.selectLastEmployee());
        dataDiriMapper.addDataDiri(employee);
    }

    @Override
    public void updateEmployee(EmployeeModel employee){
        deleteEmployee(employee.getId_employee());
        addEmployee(employee);
    }

    @Override
    public EmployeeModel selectEmployee (int id_employee){
        return employeeMapper.selectEmployee(id_employee);
    }

    @Override
    public List<EmployeeModel> selectAllEmployees(){
        return employeeMapper.selectAllEmployees();
    }

    @Override
    public List<EmployeeModel> selectAllEmployeesInactive(){
        return employeeMapper.selectAllEmployeesInactive();
    }

    @Override
    public void deleteEmployee(int id_employee){
        employeeMapper.deleteDataDiriEmployee(id_employee);
    }

    @Override
    public void deactivateEmployee(int id_employee){
        employeeMapper.deactivateEmployee(id_employee);
    }

    @Override
    public void activateEmployee(int id_employee){
        employeeMapper.activateEmployee(id_employee);
    }
}
