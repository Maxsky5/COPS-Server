package com.cesi.cops.dto;

import com.cesi.cops.entities.*;
import com.cesi.cops.jsonViews.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

public class SynchronizationDto {

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Student> students = new ArrayList<>();

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Teacher> teachers = new ArrayList<>();

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Grade> grades = new ArrayList<>();

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Cop> cops = new ArrayList<>();

    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Lesson> lessons = new ArrayList<>();

    public SynchronizationDto() {
    }

    public SynchronizationDto(List<Student> students, List<Teacher> teachers, List<Grade> grades, List<Cop> cops, List<Lesson> lessons) {
        this.students = students;
        this.teachers = teachers;
        this.grades = grades;
        this.cops = cops;
        this.lessons = lessons;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
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
