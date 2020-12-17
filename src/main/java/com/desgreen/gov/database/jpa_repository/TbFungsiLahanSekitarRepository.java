package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbFungsiLahanSekitar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbFungsiLahanSekitarRepository extends JpaRepository<TbFungsiLahanSekitar, Integer> {
    TbFungsiLahanSekitar findById(int id);
    List<TbFungsiLahanSekitar> findByKode1(String kode1);

    @Query("SELECT u FROM TbOpd u WHERE u.description LIKE :description_")
    List<TbFungsiLahanSekitar> findByDescription(@Param("description_") String description_);


}
