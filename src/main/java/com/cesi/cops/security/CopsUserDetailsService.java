package com.cesi.cops.security;

import com.cesi.cops.entities.User;
import com.cesi.cops.exceptions.UserNotActiveException;
import com.cesi.cops.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Authenticate a user from the database.
 */
@Component("copsUserDetailsService")
public class CopsUserDetailsService implements UserDetailsService {

    private final Logger LOGGER = LoggerFactory.getLogger(CopsUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {

        userRepository.findOneByEmail("sefse");
        LOGGER.info("Authenticating {}", email);
        Optional<User> userFromDatabase =  userRepository.findOneByEmail(email);
        return userFromDatabase.map(user -> {
            if (!user.getIsActive()) {
                throw new UserNotActiveException("User " + email + " is not active");
            }
            return new CopsUserDetails(user);
        }).orElseThrow(() -> new UsernameNotFoundException("User " + email + " was not found in the database"));
    }
}
