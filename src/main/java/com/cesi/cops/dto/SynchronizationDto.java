package com.cesi.cops.dto;

import com.cesi.cops.entities.*;
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

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Classroom> classrooms = new ArrayList<>();

    public SynchronizationDto(List<Offender> offenders, List<Grade> grades, List<Cop> cops, List<Lesson> lessons,
                              List<Classroom> classrooms) {
        this.offenders = offenders;
        this.grades = grades;
        this.cops = cops;
        this.lessons = lessons;
        this.classrooms = classrooms;
    }

}
