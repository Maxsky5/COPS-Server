package com.cesi.cops.entities;

import com.cesi.cops.jsonViews.View;
import com.cesi.cops.utils.CustomDateTimeDeserializer;
import com.cesi.cops.utils.CustomDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "cops")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cop implements Serializable {

    @Id
    @GeneratedValue
    @JsonView(View.Principal.class)
    private Long id;

    @Column
    @JsonView(View.Principal.class)
    private String name;

    @Column
    @JsonView(View.Principal.class)
    private String macAddress;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    @JsonView(View.PrincipalWithManyToOne.class)
    private Classroom classroom;

    @Column(name = "date_update")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @JsonView(View.Principal.class)
    private DateTime dateUpdate;

    @Column(name = "date_last_sync", nullable = true)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @JsonView(View.Principal.class)
    private DateTime dateLastSync;

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

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public DateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(DateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public DateTime getDateLastSync() {
        return dateLastSync;
    }

    public void setDateLastSync(DateTime dateLastSync) {
        this.dateLastSync = dateLastSync;
    }
}
