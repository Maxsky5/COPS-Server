package com.cesi.cops.dto;

import com.cesi.cops.entities.Cop;
import com.cesi.cops.entities.Grade;
import com.cesi.cops.entities.Lesson;
import com.cesi.cops.entities.Offender;
import com.cesi.cops.jsonViews.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

public class SynchronizationDto {

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Offender> offenders = new ArrayList<>();

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Grade> grades = new ArrayList<>();

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Cop> cops = new ArrayList<>();

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Lesson> lessons = new ArrayList<>();

    public SynchronizationDto() {
    }

    public SynchronizationDto(List<Offender> offenders, List<Grade> grades, List<Cop> cops, List<Lesson> lessons) {
        this.offenders = offenders;
        this.grades = grades;
        this.cops = cops;
        this.lessons = lessons;
    }

    public List<Offender> getOffenders() {
        return offenders;
    }

    public void setOffenders(List<Offender> offenders) {
        this.offenders = offenders;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public List<Cop> getCops() {
        return cops;
    }

    public void setCops(List<Cop> cops) {
        this.cops = cops;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
