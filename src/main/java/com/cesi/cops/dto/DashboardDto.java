package com.cesi.cops.dto;

import com.cesi.cops.entities.Lesson;
import com.cesi.cops.jsonViews.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class DashboardDto {

    @JsonView(View.Principal.class)
    private Integer nbStudents;

    @JsonView(View.Principal.class)
    private Integer nbTeachers;

    @JsonView(View.Principal.class)
    private Integer nbGrades;

    @JsonView(View.Principal.class)
    private Integer nbLessons;

    @JsonView(View.Principal.class)
    private List<DashboardEntityDto> lastUpdates;

    @JsonView(View.Principal.class)
    private List<DashboardCheckDto> lastChecks;

    @JsonView(View.Principal.class)
    private List<Lesson> lessonsToday;

    public DashboardDto() {
    }

    public Integer getNbStudents() {
        return nbStudents;
    }

    public void setNbStudents(Integer nbStudents) {
        this.nbStudents = nbStudents;
    }

    public Integer getNbTeachers() {
        return nbTeachers;
    }

    public void setNbTeachers(Integer nbTeachers) {
        this.nbTeachers = nbTeachers;
    }

    public Integer getNbGrades() {
        return nbGrades;
    }

    public void setNbGrades(Integer nbGrades) {
        this.nbGrades = nbGrades;
    }

    public Integer getNbLessons() {
        return nbLessons;
    }

    public void setNbLessons(Integer nbLessons) {
        this.nbLessons = nbLessons;
    }

    public List<DashboardEntityDto> getLastUpdates() {
        return lastUpdates;
    }

    public void setLastUpdates(List<DashboardEntityDto> lastUpdates) {
        this.lastUpdates = lastUpdates;
    }

    public List<DashboardCheckDto> getLastChecks() {
        return lastChecks;
    }

    public void setLastChecks(List<DashboardCheckDto> lastChecks) {
        this.lastChecks = lastChecks;
    }

    public List<Lesson> getLessonsToday() {
        return lessonsToday;
    }

    public void setLessonsToday(List<Lesson> lessonsToday) {
        this.lessonsToday = lessonsToday;
    }
}
