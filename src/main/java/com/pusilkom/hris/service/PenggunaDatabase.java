package com.pusilkom.hris.service;

import com.pusilkom.hris.model.mapper.PenggunaMapper;
import com.pusilkom.hris.model.PenggunaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PenggunaDatabase implements PenggunaService {
    @Autowired
    private PenggunaMapper penggunaMapper;

    @Override
    public PenggunaModel selectPenggunaById(int id) {
        PenggunaModel pgn = penggunaMapper.selectPenggunabyId(id);
        log.info("dapet pengguna {}", pgn.getUsername());
        return pgn;
    }

    @Override
    public List<PenggunaModel> selectAllPengguna() {
        List<PenggunaModel> pgn = penggunaMapper.selectAllPengguna();
        for ( PenggunaModel pengguna : pgn) {
            String user = pengguna.getUsername();
            log.info("REURN ID : {}", user);
            String nama = penggunaMapper.selectNamaPengguna(user);
            pengguna.setNama(nama);
            log.info(nama);
        }
        return pgn;
    }

    @Override
    public void updatePengguna(PenggunaModel pengguna) {
        log.info("id penggunanya adalah {}", pengguna.getId() );
        String uname = pengguna.getUsername();
        log.info("uname barunya adalah {}", uname );
//        penggunaMapper.updatePengguna(
//                pengguna.getUsername(),
//                pengguna.getId_employee(),
//                pengguna.getId()
//        );

        log.info("ini idnya{}", pengguna.getId_employee());

        penggunaMapper.updatePengguna(uname, pengguna.getId_employee());
//
        penggunaMapper.delPenggunaRole(uname);
        log.info(uname);

        for (String role: pengguna.getRole()) {
            log.info("DAPET ROLE INI: {}", role);
            penggunaMapper.addRolePengguna(uname, role);
        }

        penggunaMapper.addRolePengguna(uname, "ROLE_EMPLOYEE");
//
//        log.info("ini semployee {}", pengguna.getIsEmployee());
//
//        if (pengguna.getIsAdmin()==1)
//            penggunaMapper.addRolePengguna(uname, "ROLE_ADMIN");
//
//        log.info("ini admin {}", pengguna.getIsAdmin());
//        if (pengguna.getIsHR()==1)
//            penggunaMapper.addRolePengguna(uname, "ROLE_HR");
//        log.info("ini hr {}", pengguna.getIsHR());
//        if (pengguna.getIsExecutive()==1)
//            penggunaMapper.addRolePengguna(uname, "ROLE_EXECUTIVE");
//        log.info("ini exec {}", pengguna.getIsExecutive());
//        log.info("DONE");
    }

    @Override
    public void statusPengguna(int idPengguna) {
        PenggunaModel pgn = penggunaMapper.selectPenggunabyId(idPengguna);
        int newStatus = (pgn.getIs_aktif() == 1) ? 0 : 1;
        //if oldState is 1 then newState would be false vice versa
        log.info("status lama = {}", pgn.getIs_aktif());
        log.info("status baru = {}", newStatus);
        penggunaMapper.activatePengguna(pgn.getUsername(), newStatus);
    }

    @Override

    public PenggunaModel selectPenggunaByUsername(String username) {
        return penggunaMapper.selectPenggunaByUsername(username);
    }

    public void addPengguna(PenggunaModel pengguna) {
        String uname = pengguna.getUsername();

        penggunaMapper.addPengguna(uname, pengguna.getId_employee());

        log.info("ini idnya{}", pengguna.getId_employee());

        penggunaMapper.addRolePengguna(uname, "ROLE_EMPLOYEE");
    }

    @Override
    public List<PenggunaModel> selectAllSSO() {
        return penggunaMapper.selectAllSSO();
    }

    @Override
    public void addRolePengguna(String uname, String role) {
        penggunaMapper.addRolePengguna(uname, role);
    }

}
