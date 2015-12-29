package com.cesi.cops.controllers;

import com.cesi.cops.entities.Authority;
import com.cesi.cops.entities.User;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.repositories.ProfileRepository;
import com.cesi.cops.repositories.UserRepository;
import com.cesi.cops.utils.HeaderUtil;
import com.fasterxml.jackson.annotation.JsonView;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/users")
public class UserController extends AbstractRestController<User> {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<User> create(@Valid @RequestBody User entity) throws URISyntaxException {
        Authority authority = profileRepository.findOneByName("admin");
        entity.setAuthority(authority);

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return super.create(entity);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @JsonView(View.PrincipalWithManyToOne.class)
    public ResponseEntity<User> update(@Valid @RequestBody User entity) throws URISyntaxException {

        if (!StringUtils.isEmpty(entity.getPassword())) {
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        } else {
            User dbUser = repository.findOne(entity.getId());
            entity.setPassword(dbUser.getPassword());
        }

        return super.update(entity);
    }

}
