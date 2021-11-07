package com.tianyisoft.jvalidation.pojos;

import com.tianyisoft.jvalidate.annotations.*;

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
    // @AfterOrEqual(date = "1980-01-01")
    // @BeforeOrEqual(date = "2003-12-31")
    private Date birthday;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", homepage='" + homepage + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
