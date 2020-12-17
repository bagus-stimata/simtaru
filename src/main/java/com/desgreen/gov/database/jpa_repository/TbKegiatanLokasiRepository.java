package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbKegiatanLokasi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbKegiatanLokasiRepository extends JpaRepository<TbKegiatanLokasi, Integer> {
    TbKegiatanLokasi findById(int id);
    // List<TbKegiatanLokasi> findByKode1(String kode1);

    // @Query("SELECT u FROM TbDesa u WHERE u.description LIKE :description_")
    // List<TbKegiatanLokasi> findByDescription(@Param("description_") String description_);

}
