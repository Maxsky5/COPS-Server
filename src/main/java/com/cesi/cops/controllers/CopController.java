package com.cesi.cops.controllers;

import com.cesi.cops.entities.Cop;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.CopRepository;
import com.cesi.cops.utils.HeaderUtil;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/api/cops")
public class CopController {

    private final Logger LOGGER = LoggerFactory.getLogger(CopController.class);

    @Autowired
    private CopRepository copRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Cop> create(@Valid @RequestBody Cop cop) throws URISyntaxException {
        if (cop.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new cop cannot already have an ID").body(null);
        }

        cop.setDateUpdate(new DateTime());
        Cop result = copRepository.save(cop);
        return ResponseEntity.created(new URI("/api/cops/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("cop", result.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Cop> update(@Valid @RequestBody Cop cop) throws URISyntaxException {
        if (cop.getId() == null) {
            return create(cop);
        }

        cop.setDateUpdate(new DateTime());
        Cop result = copRepository.save(cop);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("cop", cop.getId().toString()))
            .body(result);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Cop>> getAll() {
        return ResponseEntity.ok().body(copRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<Cop> get(@PathVariable Long id) {
        return Optional.ofNullable(copRepository.findOne(id))
            .map(cop -> new ResponseEntity<>(
                cop,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        copRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("cop", id.toString())).build();
    }
}
