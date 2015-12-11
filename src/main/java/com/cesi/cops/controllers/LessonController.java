package com.cesi.cops.controllers;

import com.cesi.cops.entities.Lesson;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.LessonRepository;
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
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Lesson> create(@Valid @RequestBody Lesson lesson) throws URISyntaxException {
        if (lesson.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new lesson cannot already have an ID").body(null);
        }

        lesson.setIsMorning(true);
        lesson.setDateUpdate(new DateTime());
        Lesson result = lessonRepository.save(lesson);
        return ResponseEntity.created(new URI("/api/lessons/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("lesson", result.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Lesson> update(@Valid @RequestBody Lesson lesson) throws URISyntaxException {
        if (lesson.getId() == null) {
            return create(lesson);
        }

        lesson.setIsMorning(true);
        lesson.setDateUpdate(new DateTime());
        Lesson result = lessonRepository.save(lesson);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("lesson", lesson.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Lesson>> getAll() {
        return ResponseEntity.ok().body(lessonRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Lesson> get(@PathVariable Long id) {
        return Optional.ofNullable(lessonRepository.findOne(id))
            .map(lesson -> new ResponseEntity<>(
                lesson,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("lesson", id.toString())).build();
    }
}
