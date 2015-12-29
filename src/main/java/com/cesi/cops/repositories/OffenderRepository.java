package com.cesi.cops.repositories;

import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Offender;
import com.cesi.cops.utils.OffenderTypeEnum;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OffenderRepository extends CopsCrudRepository<Offender, Long> {

    Offender findOneByIdAndType(Long id, OffenderTypeEnum type);

    List<Offender> findByType(OffenderTypeEnum type);

    List<Offender> findByTypeAndGrade(OffenderTypeEnum type, Grade grade);
}