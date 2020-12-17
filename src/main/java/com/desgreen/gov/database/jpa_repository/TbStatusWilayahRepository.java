package com.desgreen.gov.database.jpa_repository;

import java.util.List;


import com.desgreen.gov.database.model.TbStatusWilayah;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbStatusWilayahRepository extends JpaRepository<TbStatusWilayah, Integer> {
    TbStatusWilayah findById(int id);
    // List<TbStatusWilayah> findByKode1(String kode1);

    // @Query("SELECT u FROM TbOpd u WHERE u.description LIKE :description_")
    // List<TbStatusWilayah> findByDescription(@Param("description_") String description_);


}
