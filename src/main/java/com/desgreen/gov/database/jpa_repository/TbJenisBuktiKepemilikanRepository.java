package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.TbJenisBuktiKepemilikan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TbJenisBuktiKepemilikanRepository extends JpaRepository<TbJenisBuktiKepemilikan, Integer> {
    TbJenisBuktiKepemilikan findById(int id);
    List<TbJenisBuktiKepemilikan> findByKode1(String kode1);

    @Query("SELECT u FROM TbJenisBuktiKepemilikan u WHERE u.description LIKE :description_")
    List<TbJenisBuktiKepemilikan> findByDescription(@Param("description_") String description_);


}
