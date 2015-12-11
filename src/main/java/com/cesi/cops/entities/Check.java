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

@Entity(name = "checks")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Check implements Serializable {

    @Id
    @GeneratedValue
    @JsonView(View.Principal.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "offender_id", nullable = false)
    @JsonView(View.PrincipalWithManyToOne.class)
    private Offender offender;

    @ManyToOne
    @JoinColumn(name = "cop_id", nullable = false)
    @JsonView(View.PrincipalWithManyToOne.class)
    private Cop cop;

    @Column(name = "date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @JsonView(View.Principal.class)
    private DateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Offender getOffender() {
        return offender;
    }

    public void setOffender(Offender offender) {
        this.offender = offender;
    }

    public Cop getCop() {
        return cop;
    }

    public void setCop(Cop cop) {
        this.cop = cop;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }
}
