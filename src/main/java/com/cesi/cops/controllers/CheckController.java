package com.cesi.cops.controllers;

import com.cesi.cops.entities.Check;
import com.cesi.cops.entities.Cop;
import com.cesi.cops.entities.Student;
import com.cesi.cops.repositories.CheckRepository;
import com.cesi.cops.repositories.CopRepository;
import com.cesi.cops.repositories.StudentRepository;
import com.cesi.cops.utils.HeaderUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
@RequestMapping("/api")
public class CheckController {

    private final Logger LOGGER = LoggerFactory.getLogger(CheckController.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CopRepository copRepository;

    @Autowired
    private CheckRepository checkRepository;

    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addCheck(
            @RequestParam(value = "student_id", required = true) Long studentId,
            @RequestParam(value = "cop_id", required = true) Long copId,
            @RequestParam(value = "date", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") DateTime date
    ) {
        Student student = studentRepository.findOne(studentId);
        Cop cop = copRepository.findOne(copId);

        if (null == cop) {
            return ResponseEntity.badRequest().header("Failure", "Cop not found").build();
        }

        if (null == student) {
            return ResponseEntity.badRequest().header("Failure", "Student not found").build();
        }

        Check check = new Check();
        check.setDate(date);
        check.setStudent(student);
        check.setCop(cop);

        Check result = checkRepository.save(check);

        return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert(result.getClass().toString(), result.getId().toString())).build();
    }
}
