package com.cesi.cops.controllers;

import com.cesi.cops.dto.SynchronizationDto;
import com.cesi.cops.entities.*;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @RequestMapping(value = "/synchronize", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<SynchronizationDto> synchronize(
        @RequestParam(value = "copMacAddress", required = true) String copMacAddress
    ) {
        Cop cop = copRepository.findOneByMacAddress(copMacAddress);

        if (null == cop) {
            return ResponseEntity.badRequest().header("Failure", "Cop not found").body(null);
        }

        DateTime date = cop.getDateLastSync();

        List<Offender> offenders;
        List<Grade> grades;
        List<Cop> cops;
        List<Lesson> lessons;
        List<Classroom> classrooms;

        if (null == date) {
            offenders = offenderRepository.findAll();
            grades = gradeRepository.findAll();
            cops = copRepository.findAll();
            lessons = lessonRepository.findAll();
            classrooms = classroomRepository.findAll();
        } else {
            offenders = offenderRepository.findByDateUpdateAfter(date);
            grades = gradeRepository.findByDateUpdateAfter(date);
            cops = copRepository.findByDateUpdateAfter(date);
            lessons = lessonRepository.findByDateUpdateAfter(date);
            classrooms = classroomRepository.findByDateUpdateAfter(date);
        }

        SynchronizationDto result = new SynchronizationDto(offenders, grades, cops, lessons, classrooms);

        cop.setDateLastSync(new DateTime());
        copRepository.save(cop);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
