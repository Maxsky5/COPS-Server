package com.cesi.cops.repositories;

import com.cesi.cops.entities.Check;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CheckRepository extends JpaRepository<Check, Long> {
}