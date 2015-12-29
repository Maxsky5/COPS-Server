package com.cesi.cops.repositories;

import com.cesi.cops.entities.CopEntity;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface CopsCrudRepository<T extends CopEntity, ID extends Serializable> extends JpaRepository<T, ID> {

    List<T> findByDateUpdateAfter(DateTime date);

    List<T> findTop10ByOrderByDateUpdateDesc();
}
