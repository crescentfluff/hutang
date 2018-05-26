package com.pusilkom.hris.service;

import com.pusilkom.hris.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pusilkom.hris.model.DataDiriModel;
import com.pusilkom.hris.model.mapper.DataDiriMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DataDiriDatabase implements DataDiriService{

    @Autowired
    private DataDiriMapper dataDiriMapper;

    @Override
    public void addDataDiri (EmployeeModel data_diri){
        dataDiriMapper.addDataDiri(data_diri);
    }

    @Override
    public DataDiriModel selectDataDiri (int id_data_diri){
        return dataDiriMapper.selectDataDiri(id_data_diri);
    }

    @Override
    public DataDiriModel selectDataDiriEmployee (int id_employee){
        return dataDiriMapper.selectDataDiriEmployee(id_employee);
    }

    @Override
    public void deleteDataDiri(int id_employee){
        dataDiriMapper.deleteDataDiri(id_employee);
    }
}
