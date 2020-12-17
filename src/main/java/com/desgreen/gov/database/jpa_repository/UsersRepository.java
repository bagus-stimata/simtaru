package com.desgreen.gov.database.jpa_repository;

import com.desgreen.gov.database.model.FUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<FUser, Integer> {
    // Optional<FUser> findByEmail(String email);
    FUser findById(int id);

    FUser findByEmail(String email);

    // Optional<FUser> findByUsername(String username);
    FUser findByUsername(String username);


}
