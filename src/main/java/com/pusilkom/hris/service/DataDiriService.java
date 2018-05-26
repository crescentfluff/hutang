package com.pusilkom.hris.service;
import com.pusilkom.hris.model.EmployeeModel;
import org.apache.ibatis.annotations.Param;

import com.pusilkom.hris.model.DataDiriModel;

public interface DataDiriService {
    DataDiriModel selectDataDiri(int id_data_diri);
    void addDataDiri(EmployeeModel data_diri);
    DataDiriModel selectDataDiriEmployee(int id_employee);
    void deleteDataDiri(int id_employee);
}
