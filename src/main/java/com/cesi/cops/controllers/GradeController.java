package com.cesi.cops.controllers;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.GradeRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GradeController {

    private final Logger LOGGER = LoggerFactory.getLogger(GradeController.class);

    @Autowired
    private GradeRepository gradeRepository;

    @RequestMapping(value = "/grades", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithOneToMany.class)
    public ResponseEntity<Grade> add(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "date_start", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateStart,
            @RequestParam(value = "date_end", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateEnd
    ) {
        Grade grade = new Grade();
        grade.setDateUpdate(new DateTime());
        grade.setName(name);
        grade.setDateStart(dateStart);
        grade.setDateEnd(dateEnd);

        return new ResponseEntity<>(gradeRepository.save(grade), HttpStatus.OK);
    }

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithOneToMany.class)
    public ResponseEntity<List<Grade>> getAllGrades() {
        return new ResponseEntity<>(gradeRepository.findAll(), HttpStatus.OK);
    }
}
