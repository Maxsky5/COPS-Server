package com.cesi.cops.controllers;

import com.cesi.cops.dto.SynchronizationDto;
import com.cesi.cops.entities.*;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SynchronizationController {

    private final Logger LOGGER = LoggerFactory.getLogger(SynchronizationController.class);

    @Inject
    private OffenderRepository offenderRepository;

    @Inject
    private GradeRepository gradeRepository;

    @Inject
    private CopRepository copRepository;

    @Inject
    private LessonRepository lessonRepository;

    @Inject
    private ClassroomRepository classroomRepository;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<SynchronizationDto> init() {
        List<Offender> offenders = offenderRepository.findAll();
        List<Grade> grades = gradeRepository.findAll();
        List<Cop> cops = copRepository.findAll();
        List<Lesson> lessons = lessonRepository.findAll();
        List<Classroom> classrooms = classroomRepository.findAll();

        SynchronizationDto result = new SynchronizationDto(offenders, grades, cops, lessons, classrooms);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/synchronize", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<SynchronizationDto> synchronize(
            @RequestParam(value = "date", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") DateTime date
    ) {
        List<Offender> offenders = offenderRepository.findByDateUpdateAfter(date);
        List<Grade> grades = gradeRepository.findByDateUpdateAfter(date);
        List<Cop> cops = copRepository.findByDateUpdateAfter(date);
        List<Lesson> lessons = lessonRepository.findByDateUpdateAfter(date);
        List<Classroom> classrooms = classroomRepository.findByDateUpdateAfter(date);

        SynchronizationDto result = new SynchronizationDto(offenders, grades, cops, lessons, classrooms);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
