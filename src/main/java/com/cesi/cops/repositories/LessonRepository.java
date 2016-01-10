package com.cesi.cops.repositories;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Lesson;
import org.joda.time.DateTime;

import java.util.List;

public interface LessonRepository extends CopsCrudRepository<Lesson, Long> {

    List<Lesson> findTop10ByGradesAndDateLessonAfterOrderByDateLesson(Grade grade, DateTime date);

    Lesson findOneByGradesAndDateLesson(Grade grade, DateTime date);
}