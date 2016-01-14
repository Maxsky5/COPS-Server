package com.cesi.cops.controllers;

import com.cesi.cops.entities.Check;
import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Lesson;
import com.cesi.cops.entities.Offender;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.CheckRepository;
import com.cesi.cops.repositories.GradeRepository;
import com.cesi.cops.repositories.LessonRepository;
import com.cesi.cops.repositories.OffenderRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lessons")
public class LessonController extends AbstractRestController<Lesson> {

    @Autowired
    protected LessonRepository repository;

    @Autowired
    protected GradeRepository gradeRepository;

    @Autowired
    protected CheckRepository checkRepository;

    @Autowired
    protected OffenderRepository offenderRepository;

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Lesson>> getAll() {
        return ResponseEntity.ok().body(repository.findByOrderByDateLesson());
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

    @RequestMapping(value = "/{lessonId}/checks", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Check>> getUsersCheckForLesson(@PathVariable Long lessonId) {
        Lesson lesson = repository.findOne(lessonId);

        if (null == lesson) {
            return ResponseEntity.badRequest().header("Failure", "Lesson not found").body(null);
        }

        return ResponseEntity.ok().body(checkRepository.findByLessonOrderByDateAsc(lesson));
    }

    @RequestMapping(value = "/{lessonId}/nocheck", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Offender>> getUsersNoCheckForLesson(@PathVariable Long lessonId) {
        Lesson lesson = repository.findOne(lessonId);

        if (null == lesson) {
            return ResponseEntity.badRequest().header("Failure", "Lesson not found").body(null);
        }

        List<Offender> offendersCheck = checkRepository.findByLessonOrderByDateAsc(lesson).stream()
            .map(c -> c.getOffender())
            .collect(Collectors.toList());


        List<Offender> offendersNoCheck = lesson.getGrades().stream()
            .flatMap(g -> g.getOffenders().stream())
            .filter(o -> !offendersCheck.contains(o))
            .collect(Collectors.toList());


        return ResponseEntity.ok().body(offendersNoCheck);
    }

}
