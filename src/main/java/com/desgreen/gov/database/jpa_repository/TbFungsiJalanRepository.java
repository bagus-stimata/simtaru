package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbFungsiJalan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbFungsiJalanRepository extends JpaRepository<TbFungsiJalan, Integer> {
    TbFungsiJalan findById(int id);
    List<TbFungsiJalan> findByKode1(String kode1);

    @Query("SELECT u FROM TbOpd u WHERE u.description LIKE :description_")
    List<TbFungsiJalan> findByDescription(@Param("description_") String description_);


}
