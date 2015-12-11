package com.cesi.cops.controllers;

import com.cesi.cops.entities.Lesson;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.LessonRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/lessons")
public class LessonController extends AbstractRestController<Lesson> {

    @Autowired
    protected LessonRepository repository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Lesson> create(@Valid @RequestBody Lesson lesson) throws URISyntaxException {
        lesson.setIsMorning(true);

        return super.create(lesson);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Lesson> update(@Valid @RequestBody Lesson lesson) throws URISyntaxException {
        lesson.setIsMorning(true);

        return super.update(lesson);
    }

}
