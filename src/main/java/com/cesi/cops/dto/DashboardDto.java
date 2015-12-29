package com.cesi.cops.dto;

import com.cesi.cops.entities.CopEntity;

import java.util.List;

public class DashboardDto {

    private Integer nbStudents;

    private Integer nbTeachers;

    private Integer nbGrades;

    private Integer nbCops;

    private List<DashboardEntityDto> lastUpdates;

    private List<DashboardCheckDto> lastChecks;

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

    public Integer getNbCops() {
        return nbCops;
    }

    public void setNbCops(Integer nbCops) {
        this.nbCops = nbCops;
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
}
