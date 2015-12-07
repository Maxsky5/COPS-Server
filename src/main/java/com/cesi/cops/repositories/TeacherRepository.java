package com.cesi.cops.repositories;

import com.cesi.cops.entities.Teacher;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findOneByEmail(String email);

    List<Teacher> findByDateUpdateAfter(DateTime date);

}