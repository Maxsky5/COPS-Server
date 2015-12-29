package com.cesi.cops.controllers;

import com.cesi.cops.entities.Classroom;
import com.cesi.cops.repositories.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController extends AbstractRestController<Classroom> {

}
