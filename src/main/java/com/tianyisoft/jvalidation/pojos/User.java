package com.tianyisoft.jvalidation.pojos;

import com.tianyisoft.jvalidate.annotations.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class User {
    private Long id;
    @Required(message = "嫑为空")
    @Alpha
    @AlphaDash
    @AlphaNum
    @Accepted
    private String name;
    @Required
    @Url
    private String homepage;
    @After(date = "1980-01-01")
    private Date birthday;
    @After(date = "1980-01-01")
    private Date birthday1;
    @After(date = "1980-01-01T00:00:00.000Z")
    // @AfterOrEqual(date = "1980-01-01")
    // @BeforeOrEqual(date = "2003-12-31")
    private Instant birthday2;

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

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday1() {
        return birthday1;
    }

    public void setBirthday1(Date birthday1) {
        this.birthday1 = birthday1;
    }

    public Instant getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(Instant birthday2) {
        this.birthday2 = birthday2;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", homepage='" + homepage + '\'' +
                ", birthday=" + birthday +
                ", birthday1=" + birthday1 +
                ", birthday2=" + birthday2 +
                '}';
    }
}
