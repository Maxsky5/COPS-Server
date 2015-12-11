package com.cesi.cops.controllers;

import com.cesi.cops.entities.Classroom;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.ClassroomRepository;
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
@RequestMapping("/api/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomRepository classroomRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.Principal.class)
    public ResponseEntity<Classroom> create(@Valid @RequestBody Classroom classroom) throws URISyntaxException {
        if (classroom.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new classroom cannot already have an ID").body(null);
        }

        classroom.setDateUpdate(new DateTime());
        Classroom result = classroomRepository.save(classroom);
        return ResponseEntity.created(new URI("/api/classrooms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("classroom", result.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.Principal.class)
    public ResponseEntity<Classroom> update(@Valid @RequestBody Classroom classroom) throws URISyntaxException {
        if (classroom.getId() == null) {
            return create(classroom);
        }

        classroom.setDateUpdate(new DateTime());
        Classroom result = classroomRepository.save(classroom);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("classroom", classroom.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.Principal.class)
    public ResponseEntity<List<Classroom>> getAll() {
        return ResponseEntity.ok().body(classroomRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(View.Principal.class)
    public ResponseEntity<Classroom> get(@PathVariable Long id) {
        return Optional.ofNullable(classroomRepository.findOne(id))
            .map(classroom -> new ResponseEntity<>(
                classroom,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classroomRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("classroom", id.toString())).build();
    }
}
