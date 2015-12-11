package com.cesi.cops.controllers;

import com.cesi.cops.entities.Cop;
import com.cesi.cops.repositories.CopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cops")
public class CopController extends AbstractRestController<Cop> {

    @Autowired
    protected CopRepository repository;

}
