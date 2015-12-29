package com.cesi.cops.controllers;

import com.cesi.cops.entities.CopEntity;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.utils.HeaderUtil;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public abstract class AbstractRestController<T extends CopEntity> {

    @Autowired
    protected JpaRepository<T, Long> repository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<T> create(@Valid @RequestBody T entity) throws URISyntaxException {
        if (entity.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new " + entity.getClass().getSimpleName() + " cannot already have an ID").body(null);
        }

        entity.setDateUpdate(new DateTime());
        T result = repository.save(entity);
        return ResponseEntity.created(new URI("/api/" + entity.getClass().getSimpleName().toLowerCase() + "s/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(entity.getClass().getSimpleName().toLowerCase(), result.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<T> update(@Valid @RequestBody T entity) throws URISyntaxException {
        if (entity.getId() == null) {
            return create(entity);
        }

        entity.setDateUpdate(new DateTime());
        T result = repository.save(entity);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(entity.getClass().getSimpleName().toLowerCase(), entity.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<T>> getAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<T> get(@PathVariable Long id) {
        return Optional.ofNullable(repository.findOne(id))
            .map(e -> new ResponseEntity<>(e, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id, T entity) {
        repository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(entity.getClass().getSimpleName().toLowerCase(), id.toString())).build();
    }
}
