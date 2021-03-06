package com.cesi.cops.repositories;

import com.cesi.cops.entities.Cop;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopRepository extends CopsCrudRepository<Cop, Long> {

    Cop findOneByMacAddress(String macAddress);
}
