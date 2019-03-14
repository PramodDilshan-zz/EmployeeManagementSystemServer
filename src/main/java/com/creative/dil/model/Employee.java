package com.creative.dil.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employee_tbl")
public class Employee implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employee_id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "birthday")
    @NotNull
    private Date birthday;

    @ManyToMany(targetEntity=Skill.class,
            fetch = FetchType.EAGER,
            cascade={CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinTable(name = "employees_skills_tbl" ,
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills = new ArrayList<Skill>();




    public long getId() {
        return employee_id;
    }

    public void setId(long employee_id) {
        this.employee_id = employee_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
