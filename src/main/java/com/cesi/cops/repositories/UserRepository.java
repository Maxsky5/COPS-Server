package com.cesi.cops.repositories;

import com.cesi.cops.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

}