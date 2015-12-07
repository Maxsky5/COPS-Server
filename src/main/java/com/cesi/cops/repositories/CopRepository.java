package com.cesi.cops.repositories;

import com.cesi.cops.entities.Cop;
import com.cesi.cops.entities.Teacher;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopRepository extends JpaRepository<Cop, Long> {

    List<Cop> findByDateUpdateAfter(DateTime date);
}
