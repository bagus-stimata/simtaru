package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbKlasifikasiKegiatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbKlasifikasiKegiatanRepository extends JpaRepository<TbKlasifikasiKegiatan, Integer> {
    TbKlasifikasiKegiatan findById(int id);
    // List<TbKlasifikasiKegiatan> findByKode1(String kode1);

    // @Query("SELECT u TbKlasifikasiKegiatan TbOpd u WHERE u.description LIKE :description_")
    // List<TbKlasifikasiKegiatan> findByDescription(@Param("description_") String description_);


}
