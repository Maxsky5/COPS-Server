package com.cesi.cops.repositories;

import com.cesi.cops.entities.Classroom;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    List<Classroom> findByDateUpdateAfter(DateTime date);
}