package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbRencanaPemanfaatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbRencanaPemanfaatanRepository extends JpaRepository<TbRencanaPemanfaatan, Integer> {
    TbRencanaPemanfaatan findById(int id);
    List<TbRencanaPemanfaatan> findByKode1(String kode1);

    // @Query("SELECT u FROM TbRencanaPemanfaatan u WHERE u.description LIKE :description_")
    // List<TbRencanaPemanfaatan> findByDescription(@Param("description_") String description_);


}
