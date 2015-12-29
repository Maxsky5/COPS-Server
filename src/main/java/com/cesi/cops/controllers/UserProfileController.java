package com.cesi.cops.controllers;

import com.cesi.cops.entities.Authority;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.ProfileRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @JsonView(View.Principal.class)
    public ResponseEntity<List<Authority>> getAllProfiles() {
        return ResponseEntity.ok().body(profileRepository.findAll());
    }
}
