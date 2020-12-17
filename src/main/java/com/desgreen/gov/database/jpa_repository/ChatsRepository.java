package com.desgreen.gov.database.jpa_repository;

import java.util.List;

import com.desgreen.gov.database.model.Chats;
import com.desgreen.gov.database.model.TbDesa;
import com.desgreen.gov.database.model.TbOpd;
import com.desgreen.gov.database.model.TbTipeLayanan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ChatsRepository extends JpaRepository<Chats, Long> {
    Chats findById(long id);
    // List<Chats> findByKode1(String kode1);

    // @Query("SELECT u FROM Chats u WHERE u.description LIKE :description_")
    // List<Chats> findByDescription(@Param("description_") String description_);


}
