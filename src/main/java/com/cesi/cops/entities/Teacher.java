package com.cesi.cops.entities;

import com.cesi.cops.jsonViews.View;
import com.cesi.cops.utils.CustomDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "teachers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Teacher implements Serializable {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(DateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
