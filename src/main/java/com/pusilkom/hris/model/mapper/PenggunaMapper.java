package com.pusilkom.hris.model.mapper;

import com.pusilkom.hris.model.EmployeeModel;
import com.pusilkom.hris.model.PenggunaModel;
import com.pusilkom.hris.model.RoleModel;
import com.pusilkom.hris.model.RolePengguna;
import org.apache.ibatis.annotations.*;

import java.util.List;

//@Mapper
//public interface PenggunaMapper {
//
//    @Insert("INSERT INTO moka.\"penggunaemployee\" (username, id_employee) VALUES (#{username}, #{id_employee})")
//    void addPengguna (@Param("username") String username, @Param("id_employee") int id_employee);
//
//    @Update("UPDATE moka.\"penggunaemployee\" SET username=#{username}, id_employee=#{idEmployee} where username=#{username}")
//    void updatePengguna (@Param("username") String username, @Param("idEmployee") int idEmployee);
//
//
//    @Update("UPDATE moka.\"pengguna\" SET is_aktif=#{is_aktif} where username=#{username}")
//    void activatePengguna (@Param("username") String username,
//                           @Param("is_aktif") int is_aktif);
//
//    @Select("SELECT * FROM moka.\"pengguna\", moka.\"penggunaemployee\" p WHERE pengguna.username=p.username")
//    List<PenggunaModel> selectAllPengguna ();
//
//    @Select ("select r.username, roles from moka.\"rolepengguna\" r join moka.\"penggunaemployee\" e on e.username = r.username where username=#{uname}")
//    List<RoleModel> selectRolePengguna(String uname);
//
//    @Select("SELECT * FROM moka.\"pengguna\" WHERE is_aktif=1")
//    List<PenggunaModel> selectAllSSO ();
//
//    @Select("SELECT nama_lengkap from moka.\"datadiri\" d, moka.\"penggunaemployee\" p where username=#{username} and d.id_employee=p.id_employee and is_aktif=1")
//    String selectNamaPengguna(String username);
//
//
//    @Select("SELECT d.* , e.nip, e.divisi, dv.nama_divisi, pe.id as id_pengguna " +
//            "FROM moka.\"penggunaemployee\" PE JOIN moka.\"employee\" E ON PE.id_employee=e.id_employee " +
//            "JOIN moka.\"datadiri\" D ON D.id_employee=E.id_employee " +
//            "JOIN moka.\"divisi\" DV ON DV.id_divisi=E.divisi " +
//            "WHERE PE.username=#{username} AND " +
//            "D.is_aktif = 1 " +
//            "ORDER BY id_data_diri desc LIMIT 1")
//    EmployeeModel selectEmployeePenggunaByUsername(String username);
//
//
//    @Select("SELECT * FROM moka.\"pengguna\" WHERE username = #{username}")
//    @Results(value = {
//            @Result(property = "username", column = "username"),
//            @Result(property = "roles", column = "username",
//                    javaType =  List.class,
//                    many = @Many(select = "selectRolesByUsername")),
//            @Result(property = "employee", column = "username",
//                    javaType = EmployeeModel.class,
//                    one = @One(select = "selectEmployeePenggunaByUsername")),
//    })
//    PenggunaModel selectPenggunaByUsername(String username);
//
//    @Select("SELECT roles FROM moka.\"rolepengguna\" WHERE username = #{username}")
//    List<RolePengguna> selectRolesByUsername(String username);
//
//    @Select("SELECT * FROM moka.\"pengguna\" NATURAL JOIN moka.\"penggunaemployee\" WHERE id=#{idPengguna}")
//    PenggunaModel selectPenggunabyId(int idPengguna);
//
//    @Insert("INSERT INTO moka.\"rolepengguna\" (username, roles) VALUES (#{username}, #{role})")
//    void addRolePengguna(@Param("username") String username, @Param("role") String role);
//
//    @Delete("DELETE FROM moka.\"rolepengguna\" WHERE username=#{uname}")
//    void delPenggunaRole(String uname);
//
//}

@Mapper
public interface PenggunaMapper {

    @Insert("INSERT INTO penggunaemployee (username, id_employee) VALUES (#{username}, #{id_employee})")
    void addPengguna(@Param("username") String username, @Param("id_employee") int id_employee);

    @Update("UPDATE penggunaemployee username=#{username}, id_employee=#{idEmployee} where username=#{username}")
    void updatePengguna(@Param("username") String username, @Param("idEmployee") int idEmployee);


    @Update("UPDATE pengguna SET is_aktif=#{is_aktif} where username=#{username}")
    void activatePengguna(@Param("username") String username,
                          @Param("is_aktif") int is_aktif);

    @Select("SELECT * FROM pengguna, penggunaemployee p WHERE pengguna.username=p.username")
    List<PenggunaModel> selectAllPengguna();

    @Select("select r.username, roles from rolepengguna r join penggunaemployee e on e.username = r.username where username=#{uname}")
    List<RoleModel> selectRolePengguna(String uname);

    @Select("SELECT * FROM pengguna WHERE is_aktif=1")
    List<PenggunaModel> selectAllSSO();

    @Select("SELECT nama_lengkap from datadiri d, penggunaemployee p where username=#{username} and d.id_employee=p.id_employee and is_aktif=1")
    String selectNamaPengguna(String username);


    @Select("SELECT d.* , e.nip, e.divisi, dv.nama_divisi, pe.id as id_pengguna " +
            "FROM penggunaemployee PE JOIN employee E ON PE.id_employee=e.id_employee " +
            "JOIN datadiri D ON D.id_employee=E.id_employee " +
            "JOIN divisi DV ON DV.id_divisi=E.divisi " +
            "WHERE PE.username=#{username} AND " +
            "D.is_aktif = 1 " +
            "ORDER BY id_data_diri desc LIMIT 1")
    EmployeeModel selectEmployeePenggunaByUsername(String username);


    @Select("SELECT * FROM pengguna WHERE username = #{username}")
    @Results(value = {
            @Result(property = "username", column = "username"),
            @Result(property = "roles", column = "username",
                    javaType =  List.class,
                    many = @Many(select = "selectRolesByUsername")),
            @Result(property = "employee", column = "username",
                    javaType = EmployeeModel.class,
                    one = @One(select = "selectEmployeePenggunaByUsername")),
    })
    PenggunaModel selectPenggunaByUsername(String username);

    @Select("SELECT roles FROM rolepengguna WHERE username = #{username}")
    List<RolePengguna> selectRolesByUsername(String username);

    @Select("SELECT * FROM pengguna NATURAL JOIN penggunaemployee WHERE id=#{idPengguna}")
    PenggunaModel selectPenggunabyId(int idPengguna);

    @Insert("INSERT INTO rolepengguna (username, roles) VALUES (#{username}, #{role})")
    void addRolePengguna(@Param("username") String username, @Param("role") String role);

    @Delete("DELETE FROM rolepengguna WHERE username=#{uname}")
    void delPenggunaRole(String uname);

}
