package com.cesi.cops.dto;


import com.cesi.cops.utils.CustomDateTimeDeserializer;
import com.cesi.cops.utils.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

public class CheckDto {

    Long studentId;

    String copMacAddress;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    DateTime date;

    public CheckDto() {
    }

    public CheckDto(Long studentId, String copMacAddress, DateTime date) {
        this.studentId = studentId;
        this.copMacAddress = copMacAddress;
        this.date = date;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getCopMacAddress() {
        return copMacAddress;
    }

    public void setCopMacAddress(String copMacAddress) {
        this.copMacAddress = copMacAddress;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
