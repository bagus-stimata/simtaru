package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbOpd;
import com.desgreen.gov.database.model.TbTipeLayanan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbTipeLayananRepository extends JpaRepository<TbTipeLayanan, Integer> {
    TbTipeLayanan findById(int id);
    List<TbTipeLayanan> findByKode1(String kode1);

    // @Query("SELECT u FROM TbTipeLayanan u WHERE u.description LIKE :description_")
    // List<TbTipeLayanan> findByDescription(@Param("description_") String description_);


}
