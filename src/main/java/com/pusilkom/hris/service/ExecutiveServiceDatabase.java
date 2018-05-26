package com.pusilkom.hris.service;

import com.pusilkom.hris.model.mapper.ExecutiveMapper;
import com.pusilkom.hris.model.ExecutiveModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExecutiveServiceDatabase implements ExecutiveService {

    @Autowired
    private ExecutiveMapper executiveMapper;

    @Override
    public List<ExecutiveModel> selectAllEmployee() {
        return executiveMapper.selectAllEmployee();
    }

    @Override
    public List<ExecutiveModel> selectAllKehadiran(int bulan) {
        return executiveMapper.selectAllKehadiran(bulan);
    }

    @Override
    public List<ExecutiveModel> selectKehadiranById(int id_employee, int bulan) {
        List<ExecutiveModel> hadirsEmpl = executiveMapper.selectKehadiranById(id_employee, bulan);
        for (ExecutiveModel y: hadirsEmpl) {
            log.info(y.toString());
        }
        return hadirsEmpl;
    }

    @Override
    public List<ExecutiveModel> selectEmployeeByDivisi(String nama_divisi) {
        return executiveMapper.selectEmployeeByDivisi(nama_divisi);
    }

    @Override
    public String getNamaBulan (int bulan){
        String nama_bulan = "";
        if(bulan == 1){
            nama_bulan = "Januari";
        } else if(bulan == 2){
            nama_bulan = "Februari";
        } else if(bulan == 3){
            nama_bulan = "Maret";
        } else if(bulan == 4){
            nama_bulan = "April";
        } else if(bulan == 5){
            nama_bulan = "Mei";
        } else if(bulan == 6){
            nama_bulan = "Juni";
        } else if(bulan == 7){
            nama_bulan = "Juli";
        } else if(bulan == 8){
            nama_bulan = "Agustus";
        } else if(bulan == 9){
            nama_bulan = "September";
        } else if(bulan == 10){
            nama_bulan = "Oktober";
        } else if(bulan == 12){
            nama_bulan = "November";
        } else {
            nama_bulan = "Desember";
        }
        return nama_bulan;
    }

}
