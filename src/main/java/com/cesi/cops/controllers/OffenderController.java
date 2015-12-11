package com.cesi.cops.controllers;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Offender;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.GradeRepository;
import com.cesi.cops.repositories.OffenderRepository;
import com.cesi.cops.utils.HeaderUtil;
import com.cesi.cops.utils.OffenderTypeEnum;
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
@RequestMapping("/api/offenders")
public class OffenderController {

    @Autowired
    private OffenderRepository offenderRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Offender> create(@Valid @RequestBody Offender offender) throws URISyntaxException {
        if (offender.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new offender cannot already have an ID").body(null);
        }

        offender.setDateUpdate(new DateTime());
        Offender result = offenderRepository.save(offender);
        return ResponseEntity.created(new URI("/api/offenders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("offender", result.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Offender> update(@Valid @RequestBody Offender offender) throws URISyntaxException {
        if (offender.getId() == null) {
            return create(offender);
        }

        offender.setDateUpdate(new DateTime());
        Offender result = offenderRepository.save(offender);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("offender", offender.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Offender>> getAll() {
        return ResponseEntity.ok().body(offenderRepository.findAll());
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Offender>> getTeachers() {
        return ResponseEntity.ok().body(offenderRepository.findByType(OffenderTypeEnum.TEACHER));
    }

    @RequestMapping(value = "/students/{gradeId}", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Offender>> getStudentsFromGrade(@PathVariable Long gradeId) {
        Grade grade = gradeRepository.findOne(gradeId);

        return ResponseEntity.ok().body(offenderRepository.findByTypeAndGrade(OffenderTypeEnum.STUDENT, grade));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Offender> get(@PathVariable Long id) {
        return Optional.ofNullable(offenderRepository.findOne(id))
            .map(offender -> new ResponseEntity<>(
                offender,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        offenderRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("offender", id.toString())).build();
    }

}
