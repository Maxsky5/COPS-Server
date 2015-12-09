package com.cesi.cops.repositories;

import com.cesi.cops.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Authority, Long> {

    Authority findOneByName(String name);
}