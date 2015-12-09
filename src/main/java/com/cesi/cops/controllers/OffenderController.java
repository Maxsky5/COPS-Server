package com.cesi.cops.controllers;

import com.cesi.cops.entities.Offender;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.OffenderRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OffenderController {

    private final Logger LOGGER = LoggerFactory.getLogger(OffenderController.class);

    @Autowired
    private OffenderRepository offenderRepository;

    @RequestMapping(value = "/offenders", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Offender>> getAll() {
        return ResponseEntity.ok().body(offenderRepository.findAll());
    }

    @RequestMapping(value = "/offenders/updated", method = RequestMethod.GET)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<List<Offender>> getUpdated(
            @RequestParam(value = "date", required = true) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") DateTime date
    ) {
        return ResponseEntity.ok().body(offenderRepository.findByDateUpdateAfter(date));
    }


}
