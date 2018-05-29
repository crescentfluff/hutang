package com.pusilkom.hris.service;
import com.pusilkom.hris.model.EmployeeModel;
import org.apache.ibatis.annotations.Param;

import com.pusilkom.hris.model.DataDiriModel;
import org.springframework.security.access.prepost.PreAuthorize;

public interface DataDiriService {

    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_HR')")
    DataDiriModel selectDataDiri(int id_data_diri);


    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_HR')")
    void addDataDiri(EmployeeModel data_diri);


    @PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_HR')")
    DataDiriModel selectDataDiriEmployee(int id_employee);


    @PreAuthorize("hasRole('ROLE_HR')")
    void deleteDataDiri(int id_employee);
}
