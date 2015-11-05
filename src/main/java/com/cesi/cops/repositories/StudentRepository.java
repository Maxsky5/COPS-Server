package com.cesi.cops.repositories;

import com.cesi.cops.entities.Student;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findOneByEmail(String email);

    List<Student> findByDateUpdateAfter(DateTime date);
}