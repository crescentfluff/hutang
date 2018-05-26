package com.pusilkom.hris.service;

import com.example.demo.mapper.KehadiranMapper;
import com.example.demo.model.KategoriModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KehadiranDatabase implements KehadiranService{
    @Autowired
    KehadiranMapper kehadiranMapper;

    @Override
    public void addKategoriKehadiran(String nama_kategori) {
        log.info("KATEGORI KEHADIRAN {} SUDAH DITAMBAHKAN", nama_kategori);
        kehadiranMapper.addKategoriKehadiran(nama_kategori);
    }

    @Override
    public void deleteKategoriKehadiran(int id_kehadiran){
        log.info("KATEGORI DENGAN ID {}", id_kehadiran);
        kehadiranMapper.deleteKategoriKehadiran(id_kehadiran);
        log.info("DELETE SUCCESS");
    }
    @Override
    public void updateKategoriKehadiran(KategoriModel kehadiran) {
        log.info(kehadiran.toString());
        kehadiranMapper.updateKategoriKehadiran(kehadiran.getNama_kategori(), kehadiran.getId_kategori());
    }

    @Override
    public List<KategoriModel> selectAllKehadiran(){
        List<KategoriModel> hadir =  kehadiranMapper.selectAllKategoriKehadiran();
        for (KategoriModel had: hadir
             ) {
            log.info(had.toString());
        }
        return hadir;
    }

    @Override
    public KategoriModel selectKehadiranById(int id) {
        log.info("IDNYA DAPETNYA {}", id);
        if (id=='0') return null;
        KategoriModel hadir = kehadiranMapper.selectKehadiran(id);
//        log.info("Single "+hadir.toString());
        return hadir;
    }
}
