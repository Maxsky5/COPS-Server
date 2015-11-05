package com.cesi.cops.controllers;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Student;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.GradeRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GradeController {

    private final Logger LOGGER = LoggerFactory.getLogger(GradeController.class);

    @Autowired
    private GradeRepository gradeRepository;


    @RequestMapping(value = "/grades", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.PrincipalWithOneToMany.class)
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
}
