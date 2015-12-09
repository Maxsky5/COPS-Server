package com.cesi.cops.repositories;

import com.cesi.cops.entities.Lesson;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByDateUpdateAfter(DateTime date);

}