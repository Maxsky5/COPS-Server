package com.cesi.cops.controllers;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Offender;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.GradeRepository;
import com.cesi.cops.repositories.OffenderRepository;
import com.cesi.cops.utils.OffenderTypeEnum;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController extends AbstractRestController<Offender> {

    @Autowired
    protected OffenderRepository repository;

    @Autowired
    private GradeRepository gradeRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Offender> create(@Valid @RequestBody Offender student) throws URISyntaxException {
        student.setType(OffenderTypeEnum.STUDENT);

        return super.create(student);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Offender> update(@Valid @RequestBody Offender student) throws URISyntaxException {
        if (!student.getType().equals(OffenderTypeEnum.STUDENT)) {
            return ResponseEntity.badRequest().header("Failure", "This is not a student").body(null);
        }

        return super.update(student);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Offender>> getAll() {
        return ResponseEntity.ok().body(repository.findByType(OffenderTypeEnum.STUDENT));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Offender> get(@PathVariable Long id) {
        return Optional.ofNullable(repository.findOneByIdAndType(id, OffenderTypeEnum.STUDENT))
            .map(offender -> new ResponseEntity<>(
                offender,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/grade/{gradeId}", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Offender>> getFromGrade(@PathVariable Long gradeId) {
        Grade grade = gradeRepository.findOne(gradeId);
        return ResponseEntity.ok().body(repository.findByTypeAndGrade(OffenderTypeEnum.STUDENT, grade));
    }

}
