package com.cesi.cops.controllers;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Lesson;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.GradeRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController extends AbstractRestController<Grade> {

    @Autowired
    protected GradeRepository repository;

}
