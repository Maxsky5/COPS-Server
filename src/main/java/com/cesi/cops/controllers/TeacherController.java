package com.cesi.cops.controllers;

import com.cesi.cops.entities.Offender;
import com.cesi.cops.jsonViews.View;
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
@RequestMapping("/api/teachers")
public class TeacherController extends AbstractRestController<Offender> {

    @Autowired
    protected OffenderRepository repository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Offender> create(@Valid @RequestBody Offender teacher) throws URISyntaxException {
        teacher.setType(OffenderTypeEnum.TEACHER);

        return super.create(teacher);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Offender> update(@Valid @RequestBody Offender teacher) throws URISyntaxException {
        if (!teacher.getType().equals(OffenderTypeEnum.TEACHER)) {
            return ResponseEntity.badRequest().header("Failure", "This is not a teacher").body(null);
        }

        return super.update(teacher);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Offender>> getAll() {
        return ResponseEntity.ok().body(repository.findByType(OffenderTypeEnum.TEACHER));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Offender> get(@PathVariable Long id) {
        return Optional.ofNullable(repository.findOneByIdAndType(id, OffenderTypeEnum.TEACHER))
            .map(offender -> new ResponseEntity<>(
                offender,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
