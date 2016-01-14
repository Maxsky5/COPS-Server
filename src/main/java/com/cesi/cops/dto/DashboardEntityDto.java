package com.cesi.cops.dto;

import com.cesi.cops.entities.*;
import com.cesi.cops.jsonViews.View;
import com.cesi.cops.utils.CustomDateTimeSerializer;
import com.cesi.cops.utils.OffenderTypeEnum;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.DateTime;

public class DashboardEntityDto {

    @JsonView(View.Principal.class)
    String name;

    @JsonView(View.Principal.class)
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    DateTime date;

    @JsonView(View.Principal.class)
    String iconClass;

    public DashboardEntityDto() {
    }

    public DashboardEntityDto(Classroom classroom) {
        this.name = classroom.getName();
        this.date = classroom.getDateUpdate();
        this.iconClass = "fa fa-university";
    }

    public DashboardEntityDto(Cop cop) {
        this.name = cop.getName();
        this.date = cop.getDateUpdate();
        this.iconClass = "fa-cop";
    }

    public DashboardEntityDto(Grade grade) {
        this.name = grade.getName();
        this.date = grade.getDateUpdate();
        this.iconClass = "fa fa-users";
    }

    public DashboardEntityDto(Lesson lesson) {
        this.name = lesson.getName();
        this.date = lesson.getDateUpdate();
        this.iconClass = "fa fa-laptop";
    }

    public DashboardEntityDto(Offender offender) {
        this.name = offender.getName();
        this.date = offender.getDateUpdate();
        this.iconClass = offender.getType().equals(OffenderTypeEnum.TEACHER) ? "fa fa-suitcase" : "fa fa-graduation-cap";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }
}
