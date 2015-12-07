package com.cesi.cops.controllers;

import com.cesi.cops.entities.Profile;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.ProfileRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfileController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileRepository profileRepository;

    @RequestMapping(value = "/profiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.Principal.class)
    public ResponseEntity<List<Profile>> getAllProfiles() {
        return new ResponseEntity<>(profileRepository.findAll(), HttpStatus.OK);
    }
}
