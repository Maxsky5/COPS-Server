package com.cesi.cops.repositories;

import com.cesi.cops.entities.Cop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CopRepository extends JpaRepository<Cop, Long> {
}
