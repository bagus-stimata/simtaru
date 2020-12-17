package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbPelakuUsaha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbPelakuUsahaRepository extends JpaRepository<TbPelakuUsaha, Long> {
    TbPelakuUsaha findById(long id);
    List<TbPelakuUsaha> findByKode1(String kode1);

    @Query("SELECT u FROM TbOpd u WHERE u.description LIKE :description_")
    List<TbPelakuUsaha> findByDescription(@Param("description_") String description_);


}
