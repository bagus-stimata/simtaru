package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbOpd;
import com.desgreen.gov.database.model.TbPermohonan;
import com.desgreen.gov.database.model.TbRekomendasi;
import com.desgreen.gov.database.model.TbTipeLayanan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbRekomendasiRepository extends JpaRepository<TbRekomendasi, Long> {
    TbRekomendasi findById(long id);
    // List<TbRekomendasi> findByKode1(String kode1);

    // @Query("SELECT u FROM TbRekomendasi u WHERE u.description LIKE :description_")
    // List<TbRekomendasi> findByDescription(@Param("description_") String description_);

    // @Query("SELECT u FROM TbPermohonan u WHERE u.fuserBean.id = :userBeanId")
    // List<TbRekomendasi> findByUserId(@Param("userBeanId") int userBeanId);


}
