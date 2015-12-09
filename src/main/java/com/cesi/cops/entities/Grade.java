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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "grades")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Grade implements Serializable {

    @Id
    @GeneratedValue
    @JsonView(View.Principal.class)
    private Long id;

    @Column
    @JsonView(View.Principal.class)
    private String name;

    @Column(name = "date_start", columnDefinition = "DATE")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonView(View.Principal.class)
    private Date dateStart;

    @Column(name = "date_end", columnDefinition = "DATE")
    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonView(View.Principal.class)
    private Date dateEnd;

    @OneToMany(mappedBy = "grade")
    @JsonView(View.PrincipalWithOneToMany.class)
    private List<Offender> offenders = new ArrayList<>();

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<Offender> getOffenders() {
        return offenders;
    }

    public void setOffenders(List<Offender> offenders) {
        this.offenders = offenders;
    }

    public DateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(DateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}
