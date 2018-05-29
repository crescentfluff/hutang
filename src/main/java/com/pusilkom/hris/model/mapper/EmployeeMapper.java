package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.EmployeeModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface EmployeeMapper {
//    @Update("UPDATE moka.\"employee\" SET nip = #{nip} , divisi = #{divisi} WHERE id_employee = #{id_employee}")
//    void updateEmployee (EmployeeModel employee);
//
//    @Insert("INSERT INTO moka.\"employee\" (divisi, nip) VALUES (#{divisi}, #{nip})")
//    void addEmployee (EmployeeModel employee);
//
//    @Select("SELECT * FROM moka.\"employee\" JOIN moka.\"datadiri\" ON EMPLOYEE.id_employee = DATADIRI.id_employee " +
//            "WHERE EMPLOYEE.id_employee = #{id_employee} AND DATADIRI.is_aktif = 1")
//    EmployeeModel selectEmployee(@Param("id_employee") int id_employee);
//
//    @Select("select id_employee from moka.\"employee\" order by id_employee desc limit 1")
//    int selectLastEmployee();
//
//    @Select("SELECT * FROM moka.\"employee\" JOIN moka.\"datadiri\" ON EMPLOYEE.id_employee = DATADIRI.id_employee " +
//            " JOIN DIVISI ON EMPLOYEE.divisi = DIVISI.id_divisi WHERE DATADIRI.is_aktif = 1 AND EMPLOYEE.is_aktif = 1 ORDER BY EMPLOYEE.id_employee")
//    List<EmployeeModel> selectAllEmployees();
//
//    @Select("SELECT * FROM moka.\"employee\" JOIN moka.\"datadiri\" ON EMPLOYEE.id_employee = DATADIRI.id_employee " +
//            " JOIN DIVISI ON EMPLOYEE.divisi = DIVISI.id_divisi WHERE DATADIRI.is_aktif = 1 AND EMPLOYEE.is_aktif = 0 ORDER BY EMPLOYEE.id_employee")
//    List<EmployeeModel> selectAllEmployeesInactive();
//
//    @Update("UPDATE moka.\"datadiri\" SET is_aktif = 0 WHERE id_employee = #{id_employee}")
//    void deleteDataDiriEmployee(@Param("id_employee") int id_employee);
//
//    @Update("UPDATE moka.\"employee\" SET is_aktif = 1 WHERE id_employee = #{id_employee}")
//    void activateEmployee(@Param("id_employee") int id_employee);
//
//    @Update("UPDATE moka.\"employee\" SET is_aktif = 0 WHERE id_employee = #{id_employee}")
//    void deactivateEmployee(@Param("id_employee") int id_employee);
//
//
//}

@Mapper
public interface EmployeeMapper {
    @Update("UPDATE employee SET nip = #{nip} , divisi = #{divisi} WHERE id_employee = #{id_employee}")
    void updateEmployee(EmployeeModel employee);

    @Insert("INSERT INTO employee (divisi, nip) VALUES (#{divisi}, #{nip})")
    void addEmployee(EmployeeModel employee);

    @Select("SELECT * FROM employee JOIN datadiri ON EMPLOYEE.id_employee = DATADIRI.id_employee " +
            "WHERE EMPLOYEE.id_employee = #{id_employee} AND DATADIRI.is_aktif = 1")
    EmployeeModel selectEmployee(@Param("id_employee") int id_employee);

    @Select("select id_employee from employee order by id_employee desc limit 1")
    int selectLastEmployee();

    @Select("SELECT * FROM employee JOIN datadiri ON EMPLOYEE.id_employee = DATADIRI.id_employee " +
            " JOIN DIVISI ON EMPLOYEE.divisi = DIVISI.id_divisi WHERE DATADIRI.is_aktif = 1 AND EMPLOYEE.is_aktif = 1 ORDER BY EMPLOYEE.id_employee")
    List<EmployeeModel> selectAllEmployees();

    @Select("SELECT * FROM employee JOIN datadiri ON EMPLOYEE.id_employee = DATADIRI.id_employee " +
            " JOIN DIVISI ON EMPLOYEE.divisi = DIVISI.id_divisi WHERE DATADIRI.is_aktif = 1 AND EMPLOYEE.is_aktif = 0 ORDER BY EMPLOYEE.id_employee")
    List<EmployeeModel> selectAllEmployeesInactive();

    @Update("UPDATE datadiri SET is_aktif = 0 WHERE id_employee = #{id_employee}")
    void deleteDataDiriEmployee(@Param("id_employee") int id_employee);

    @Update("UPDATE employee SET is_aktif = 1 WHERE id_employee = #{id_employee}")
    void activateEmployee(@Param("id_employee") int id_employee);

    @Update("UPDATE employee SET is_aktif = 0 WHERE id_employee = #{id_employee}")
    void deactivateEmployee(@Param("id_employee") int id_employee);


    @Select("SELECT * FROM pengguna, datadiri where pengguna.id_employee = DATADIRI.id_employee " +
            "and pengguna.username = #{username} AND DATADIRI.is_aktif = 1")
    EmployeeModel selectEmployeeByUsername(@Param("username") String username);
}
