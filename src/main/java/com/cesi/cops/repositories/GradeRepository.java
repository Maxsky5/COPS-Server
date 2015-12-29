package com.cesi.cops.repositories;

import com.cesi.cops.entities.Grade;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GradeRepository extends CopsCrudRepository<Grade, Long> {
}