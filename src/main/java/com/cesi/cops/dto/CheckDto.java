package com.cesi.cops.dto;


import com.cesi.cops.utils.CustomDateTimeDeserializer;
import com.cesi.cops.utils.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

public class CheckDto {

    Long studentId;

    Long copId;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    DateTime date;

    public CheckDto() {
    }

    public CheckDto(Long studentId, Long copId, DateTime date) {
        this.studentId = studentId;
        this.copId = copId;
        this.date = date;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCopId() {
        return copId;
    }

    public void setCopId(Long copId) {
        this.copId = copId;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
