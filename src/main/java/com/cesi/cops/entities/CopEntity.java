package com.cesi.cops.entities;

import org.joda.time.DateTime;

public interface CopEntity {

    Long getId();

    void setId(Long id);

    DateTime getDateUpdate();

    void setDateUpdate(DateTime date);
}
