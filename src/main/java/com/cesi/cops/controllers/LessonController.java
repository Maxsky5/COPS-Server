package com.cesi.cops.controllers;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Lesson;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.GradeRepository;
import com.cesi.cops.repositories.LessonRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController extends AbstractRestController<Lesson> {

    @Autowired
    protected LessonRepository repository;

    @Autowired
    protected GradeRepository gradeRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Lesson> create(@Valid @RequestBody Lesson lesson) throws URISyntaxException {
        lesson.setIsMorning(true);

        return super.create(lesson);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Lesson> update(@Valid @RequestBody Lesson lesson) throws URISyntaxException {
        lesson.setIsMorning(true);

        return super.update(lesson);
    }


    @RequestMapping(value = "/grade/{gradeId}", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Lesson>> getNextLessons(@PathVariable Long gradeId) {
        List<Lesson> lessons = new ArrayList<>();

        Grade grade = gradeRepository.findOne(gradeId);

        if (null != grade) {
            lessons = repository.findTop10ByGradesAndDateLessonAfterOrderByDateLesson(grade, new DateTime());
        }

        return ResponseEntity.ok().body(lessons);
    }

}
