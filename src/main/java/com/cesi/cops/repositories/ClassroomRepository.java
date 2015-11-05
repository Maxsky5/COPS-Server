package com.cesi.cops.repositories;

import com.cesi.cops.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}