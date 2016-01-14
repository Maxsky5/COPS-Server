package com.cesi.cops.controllers;

import com.cesi.cops.dto.DashboardCheckDto;
import com.cesi.cops.dto.DashboardDto;
import com.cesi.cops.dto.DashboardEntityDto;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.*;
import com.cesi.cops.utils.OffenderTypeEnum;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private OffenderRepository offenderRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private CopRepository copRepository;

    @Autowired
    private CheckRepository checkRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<DashboardDto> get() {
        DashboardDto dashboard = new DashboardDto();

        List<DashboardEntityDto> lastUpdates = new ArrayList<>();

        lastUpdates.addAll(offenderRepository.findTop10ByOrderByDateUpdateDesc().stream()
            .map(o -> new DashboardEntityDto(o))
            .collect(Collectors.toList()));
        lastUpdates.addAll(gradeRepository.findTop10ByOrderByDateUpdateDesc().stream()
            .map(g -> new DashboardEntityDto(g))
            .collect(Collectors.toList()));
        lastUpdates.addAll(copRepository.findTop10ByOrderByDateUpdateDesc().stream()
            .map(c -> new DashboardEntityDto(c))
            .collect(Collectors.toList()));
        lastUpdates.addAll(classroomRepository.findTop10ByOrderByDateUpdateDesc().stream()
            .map(c -> new DashboardEntityDto(c))
            .collect(Collectors.toList()));
        lastUpdates.addAll(lessonRepository.findTop10ByOrderByDateUpdateDesc().stream()
            .map(l -> new DashboardEntityDto(l))
            .collect(Collectors.toList()));

        dashboard.setLastUpdates(lastUpdates.stream()
            .sorted((u1, u2) -> u2.getDate().compareTo(u1.getDate()))
            .limit(10)
            .collect(Collectors.toList()));

        dashboard.setNbGrades(gradeRepository.findAll().size());
        dashboard.setNbStudents(offenderRepository.findByType(OffenderTypeEnum.STUDENT).size());
        dashboard.setNbTeachers(offenderRepository.findByType(OffenderTypeEnum.TEACHER).size());
        dashboard.setNbLessons(lessonRepository.findAll().size());
        dashboard.setLastChecks(checkRepository.findTop10ByOrderByDateDesc().stream()
            .map(c -> new DashboardCheckDto(c))
            .collect(Collectors.toList()));

        DateTime today = new DateTime();
        today = today.withHourOfDay(0);
        today = today.withMinuteOfHour(0);
        today = today.withSecondOfMinute(0);
        dashboard.setLessonsToday(lessonRepository.findByDateLesson(today));

        return ResponseEntity.ok().body(dashboard);
    }
}
