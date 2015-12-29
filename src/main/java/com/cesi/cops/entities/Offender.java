package com.cesi.cops.entities;

import com.cesi.cops.jsonViews.View;
import com.cesi.cops.utils.CustomDateTimeDeserializer;
import com.cesi.cops.utils.CustomDateTimeSerializer;
import com.cesi.cops.utils.OffenderTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "offenders")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Offender implements Serializable, CopEntity {

    @Id
    @GeneratedValue
    @JsonView(View.Principal.class)
    private Long id;

    @Column
    @JsonView(View.Principal.class)
    private String firstname;

    @Column
    @JsonView(View.Principal.class)
    private String lastname;

    @Column
    @JsonView(View.Principal.class)
    private String email;

    @Column(name = "date_update")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @JsonView(View.Principal.class)
    private DateTime dateUpdate;

    @ManyToOne
    @JoinColumn(name = "grade_id", nullable = true)
    @JsonView(View.PrincipalWithManyToOne.class)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    @JsonView(View.Principal.class)
    private OffenderTypeEnum type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return this.firstname + " " + this.getLastname();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public DateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(DateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public OffenderTypeEnum getType() {
        return type;
    }

    public void setType(OffenderTypeEnum type) {
        this.type = type;
    }

}
