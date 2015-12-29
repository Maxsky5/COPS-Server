package com.cesi.cops.repositories;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Lesson;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CopsCrudRepository<Lesson, Long> {

    List<Lesson> findTop10ByGradesAndDateLessonAfter(Grade grade, Date date);
}