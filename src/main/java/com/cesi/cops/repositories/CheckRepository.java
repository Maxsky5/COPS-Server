package com.cesi.cops.repositories;

import com.cesi.cops.entities.Check;
import com.cesi.cops.entities.Classroom;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckRepository extends JpaRepository<Check, Long> {

    List<Check> findTop10ByOrderByDateDesc();
}