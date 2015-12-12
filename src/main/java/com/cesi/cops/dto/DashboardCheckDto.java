package com.cesi.cops.dto;

import com.cesi.cops.entities.Check;
import com.cesi.cops.utils.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

public class DashboardCheckDto {

    String student;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    DateTime date;

    public DashboardCheckDto() {
    }

    public DashboardCheckDto(Check check) {
        this.student = check.getOffender().getFirstname() + " " + check.getOffender().getLastname();
        this.date = check.getDate();
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
