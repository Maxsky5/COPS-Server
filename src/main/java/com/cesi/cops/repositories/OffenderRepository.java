package com.cesi.cops.repositories;

import com.cesi.cops.entities.Offender;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface OffenderRepository extends JpaRepository<Offender, Long> {

    Optional<Offender> findOneByEmail(String email);

    List<Offender> findByDateUpdateAfter(DateTime date);
}