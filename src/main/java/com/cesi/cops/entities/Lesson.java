package com.cesi.cops.entities;

import com.cesi.cops.jsonViews.View;
import com.cesi.cops.utils.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "lessons")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lesson implements Serializable, CopEntity {

    @Id
    @GeneratedValue
    @JsonView(View.Principal.class)
    private Long id;

    @Column
    @JsonView(View.Principal.class)
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true)
    @JsonView(View.PrincipalWithManyToOne.class)
    private Offender teacher;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    @JsonView(View.PrincipalWithManyToOne.class)
    private Classroom classroom;

    @ManyToMany
    @JoinTable(
        name = "lessons_grades",
        joinColumns = {
            @JoinColumn(name = "lesson_id", nullable = false, updatable = false)
        },
        inverseJoinColumns = {
            @JoinColumn(name = "grade_id", nullable = false, updatable = false)
        },
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {
                "lesson_id",
                "grade_id"
            })
        }
    )
    @JsonView(View.PrincipalWithManyToOne.class)
    private List<Grade> grades;

    @Column(name = "date_lesson", nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    @JsonView(View.Principal.class)
    private DateTime dateLesson;

    @Column(name = "is_morning", nullable = false)
    @JsonView(View.Principal.class)
    private Boolean isMorning;

    @Column(name = "date_update")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @JsonView(View.Principal.class)
    private DateTime dateUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Offender getTeacher() {
        return teacher;
    }

    public void setTeacher(Offender teacher) {
        this.teacher = teacher;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public DateTime getDateLesson() {
        return dateLesson;
    }

    public void setDateLesson(DateTime dateLesson) {
        this.dateLesson = dateLesson;
    }

    public Boolean getIsMorning() {
        return isMorning;
    }

    public void setIsMorning(Boolean isMorning) {
        this.isMorning = isMorning;
    }

    public DateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(DateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
