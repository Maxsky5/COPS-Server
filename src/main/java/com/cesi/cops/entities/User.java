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

@Entity(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements CopEntity, Serializable {

    @Id
    @GeneratedValue
    @JsonView(View.Principal.class)
    private Long id;

    @Column(nullable = false)
    @JsonView(View.Principal.class)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @JsonView(View.Principal.class)
    private String lastname;

    @Column(nullable = false)
    @JsonView(View.Principal.class)
    private String firstname;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    @JsonView(View.PrincipalWithManyToOne.class)
    private Authority authority;

    @Column
    @JsonView(View.Principal.class)
    private Boolean isActive = true;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.firstname + " " + this.lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public DateTime getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(DateTime dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

}
