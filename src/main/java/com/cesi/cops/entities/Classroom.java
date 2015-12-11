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
import java.util.ArrayList;
import java.util.List;

@Entity(name = "classrooms")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Classroom implements Serializable, CopEntity {

    @Id
    @GeneratedValue
    @JsonView(View.Principal.class)
    private Long id;

    @Column
    @JsonView(View.Principal.class)
    private String name;

    @Column
    @JsonView(View.Principal.class)
    private Integer nbPlace;

    @OneToMany(mappedBy = "classroom")
    @JsonView(View.PrincipalWithOneToMany.class)
    private List<Cop> cops = new ArrayList<>();

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

    public Integer getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public List<Cop> getCops() {
        return cops;
    }

    public void setCops(List<Cop> cops) {
        this.cops = cops;
    }

    public DateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(DateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
