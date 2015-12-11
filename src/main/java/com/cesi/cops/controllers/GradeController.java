package com.cesi.cops.controllers;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.GradeRepository;
import com.cesi.cops.utils.HeaderUtil;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    @Autowired
    private GradeRepository gradeRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.Principal.class)
    public ResponseEntity<Grade> create(@Valid @RequestBody Grade grade) throws URISyntaxException {
        if (grade.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new grade cannot already have an ID").body(null);
        }

        grade.setDateUpdate(new DateTime());
        Grade result = gradeRepository.save(grade);
        return ResponseEntity.created(new URI("/api/grades/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("grade", result.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.Principal.class)
    public ResponseEntity<Grade> update(@Valid @RequestBody Grade grade) throws URISyntaxException {
        if (grade.getId() == null) {
            return create(grade);
        }

        grade.setDateUpdate(new DateTime());
        Grade result = gradeRepository.save(grade);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("grade", grade.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.Principal.class)
    public ResponseEntity<List<Grade>> getAll() {
        return ResponseEntity.ok().body(gradeRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(View.Principal.class)
    public ResponseEntity<Grade> get(@PathVariable Long id) {
        return Optional.ofNullable(gradeRepository.findOne(id))
            .map(grade -> new ResponseEntity<>(
                grade,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gradeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("grade", id.toString())).build();
    }
}
