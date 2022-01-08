package com.tianyisoft.jvalidation.pojos;

import com.tianyisoft.jvalidate.annotations.*;
import com.tianyisoft.jvalidation.conditions.UserNameCondition;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class User {
    private Long id;
    @Required(message = "%s 嫑为空", allowEmpty = false)
    @RequiredIf(allowEmpty = false, condition = UserNameCondition.class, params = {"asd", "{{ this }}", "{{ request.path.id }}", "{{ homepage }}"})
    @Alpha
    @AlphaDash
    @AlphaNum
    @Between(min = 1, max = 3)
    private String name;
    @Bail
    @Required
    @Url
    @Different(field = "name")
    @StartsWith(starts = {"s", "u"})
    @EndsWith(ends = {"s", "/"})
    private String homepage;
    @Required
    @Email
    @Regexp(rule = "^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")
    @Unique(table = "users", field = "email")
    @Unique(table = "users", field = "email", where = " and id != {{ request.path.id }} ")
    @Exists(table = "users", field = "email", where = " and id != {{ request.path.id }}")
    @EndsWith(ends = {"com", "cc"})
    @In(values = {"aa@aa.com", "bb@bb.com"})
    private String email;
    @After(date = "1980-01-01")
    @AfterOrEqual(date = "1980-01-01")
    @Before(date = "2003-12-31")
    @BeforeOrEqual(date = "2003-12-31")
    @DateEquals(date = "1990-01-15")
    private Date birthday;
    @After(date = "1980-01-01")
    @AfterOrEqual(date = "1980-01-01")
    private LocalDate birthday1;
    @After(date = "1980-01-01T00:00:00.000Z")
    @AfterOrEqual(date = "1980-01-01T00:00:00.000Z")
    private Instant birthday2;

    @Between(min = 8, max = 70)
    private Integer age;
    @Required
    @Between(min = 30.0, max = 230.0)
    private Double weight;
    @Min(0)
    @Max(100)
    private Long score;
    @Between(min= 1, max= 2)
    @Distinct
    private List<String> hobbies;
    @Required
    @Ip
    @Ipv4(groups = Update.class)
    @Ipv6
    private String ip;
    @Required
    @Distinct
    private String[] friends;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public LocalDate getBirthday1() {
        return birthday1;
    }

    public void setBirthday1(LocalDate birthday1) {
        this.birthday1 = birthday1;
    }

    public Instant getBirthday2() {
        return birthday2;
    }

    public void setBirthday2(Instant birthday2) {
        this.birthday2 = birthday2;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String[] getFriends() {
        return friends;
    }

    public void setFriends(String[] friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", homepage='" + homepage + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", birthday1=" + birthday1 +
                ", birthday2=" + birthday2 +
                ", age=" + age +
                ", weight=" + weight +
                ", score=" + score +
                ", hobbies=" + hobbies +
                ", ip='" + ip + '\'' +
                ", friends=" + Arrays.toString(friends) +
                '}';
    }
}
