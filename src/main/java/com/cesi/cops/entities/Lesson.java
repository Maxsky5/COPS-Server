package com.cesi.cops.entities;

import com.cesi.cops.jsonViews.View;
import com.cesi.cops.utils.CustomDateSerializer;
import com.cesi.cops.utils.CustomDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "lessons", uniqueConstraints = {@UniqueConstraint(columnNames = {"grade_id", "is_morning", "date_lesson"})})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lesson implements Serializable {

    @Id
    @GeneratedValue
    @JsonView(View.Principal.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = true)
    @JsonView(View.PrincipalWithManyToOne.class)
    private Offender teacher;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    @JsonView(View.PrincipalWithManyToOne.class)
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = false)
    @JsonView(View.PrincipalWithManyToOne.class)
    private Grade grade;

    @Column(name = "date_lesson", nullable = false, columnDefinition = "DATE")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonView(View.Principal.class)
    private Date dateLesson;

    @Column(name = "is_morning", nullable = false)
    @JsonView(View.Principal.class)
    private Boolean isMorning;

    @Column(name = "date_update", columnDefinition = "TIMESTAMP")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonView(View.Principal.class)
    private DateTime dateUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Date getDateLesson() {
        return dateLesson;
    }

    public void setDateLesson(Date dateLesson) {
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
