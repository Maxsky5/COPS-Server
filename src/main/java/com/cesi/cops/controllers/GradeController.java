package com.cesi.cops.controllers;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.repositories.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grades")
public class GradeController extends AbstractRestController<Grade> {

    @Autowired
    protected GradeRepository repository;

}
