package com.cesi.cops.controllers;

import com.cesi.cops.dto.DashboardCheckDto;
import com.cesi.cops.dto.DashboardDto;
import com.cesi.cops.entities.CopEntity;
import com.cesi.cops.repositories.CheckRepository;
import com.cesi.cops.repositories.CopRepository;
import com.cesi.cops.repositories.GradeRepository;
import com.cesi.cops.repositories.OffenderRepository;
import com.cesi.cops.utils.OffenderTypeEnum;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<DashboardDto> get() {
        DashboardDto dashboard = new DashboardDto();

        List<CopEntity> lastUpdates = new ArrayList<>();

        dashboard.setNbGrades(gradeRepository.findAll().size());
        dashboard.setNbStudents(offenderRepository.findByType(OffenderTypeEnum.STUDENT).size());
        dashboard.setNbTeachers(offenderRepository.findByType(OffenderTypeEnum.TEACHER).size());
        dashboard.setNbCops(copRepository.findAll().size());
        dashboard.setLastUpdates(lastUpdates);
        dashboard.setLastChecks(checkRepository.findTop10ByOrderByDateDesc().stream()
            .map(c -> new DashboardCheckDto(c))
            .collect(Collectors.toList()));

        return ResponseEntity.ok().body(dashboard);
    }
}
