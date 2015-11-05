package com.cesi.cops.repositories;

import com.cesi.cops.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProfileRepository extends JpaRepository<Profile, Long> {
}