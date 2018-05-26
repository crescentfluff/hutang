package com.pusilkom.hris.model.mapper;
import com.pusilkom.hris.model.EmployeeModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Update("UPDATE EMPLOYEE SET nip = #{nip} , divisi = #{divisi} WHERE id_employee = #{id_employee}")
    void updateEmployee(EmployeeModel employee);

    @Insert("INSERT INTO EMPLOYEE (divisi, nip) VALUES (#{divisi}, #{nip})")
    void addEmployee(EmployeeModel employee);

    @Select("SELECT * FROM EMPLOYEE JOIN DATADIRI ON EMPLOYEE.id_employee = DATADIRI.id_employee " +
            "WHERE EMPLOYEE.id_employee = #{id_employee} AND DATADIRI.is_aktif = 1")
    EmployeeModel selectEmployee(@Param("id_employee") int id_employee);

    @Select("select id_employee from Employee order by id_employee desc limit 1")
    int selectLastEmployee();

//    @Select("SELECT id_employee, divisi, id_pengguna FROM EMPLOYEE WHERE id_employee = #{id_employee}")
//    @Results(value = {@Result(property = "id_employee", column = "id_employee"),
//            @Result(property = "divisi", column = "divisi"),
//            @Result(property = "id_pengguna", column = "id_pengguna"),
//            @Result(property = "datadiri", column = "id_employee",
//                    javaType = List.class,
//                    many = @Many(select = "selectDataDiri")) })
//    EmployeeModel selectEmployee(@Param("id_employee") int id_employee);


    @Select("SELECT * FROM EMPLOYEE JOIN DATADIRI ON EMPLOYEE.id_employee = DATADIRI.id_employee " +
            " JOIN DIVISI ON EMPLOYEE.divisi = DIVISI.id_divisi WHERE DATADIRI.is_aktif = 1 AND EMPLOYEE.is_aktif = 1 ORDER BY EMPLOYEE.id_employee")
    List<EmployeeModel> selectAllEmployees();

    @Select("SELECT * FROM EMPLOYEE JOIN DATADIRI ON EMPLOYEE.id_employee = DATADIRI.id_employee " +
            " JOIN DIVISI ON EMPLOYEE.divisi = DIVISI.id_divisi WHERE DATADIRI.is_aktif = 1 AND EMPLOYEE.is_aktif = 0 ORDER BY EMPLOYEE.id_employee")
    List<EmployeeModel> selectAllEmployeesInactive();

    @Update("UPDATE DATADIRI SET is_aktif = 0 WHERE id_employee = #{id_employee}")
    void deleteDataDiriEmployee(@Param("id_employee") int id_employee);

    @Update("UPDATE EMPLOYEE SET is_aktif = 1 WHERE id_employee = #{id_employee}")
    void activateEmployee(@Param("id_employee") int id_employee);

    @Update("UPDATE EMPLOYEE SET is_aktif = 0 WHERE id_employee = #{id_employee}")
    void deactivateEmployee(@Param("id_employee") int id_employee);


}
