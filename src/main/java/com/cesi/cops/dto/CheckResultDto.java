package com.cesi.cops.dto;

import com.cesi.cops.utils.CustomDateTimeDeserializer;
import com.cesi.cops.utils.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

public class CheckResultDto {

    private Long studentId;

    private String copMacAddress;

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime date;

    private Boolean success;

    private String error;

    public CheckResultDto() {
    }

    public CheckResultDto(CheckDto check) {
        this.studentId = check.getStudentId();
        this.copMacAddress = check.getCopMacAddress();
        this.date = check.getDate();
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

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
