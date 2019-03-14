package com.creative.dil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "skill_tbl")
public class Skill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long skill_id;

    @Column(name = "name")
    @NotNull
    private String name;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE},
            mappedBy = "skills",
            targetEntity = Employee.class)
    private List<Employee> employees;

    public long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(long skill_id) {
        this.skill_id = skill_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


public List<Employee> getEmployees() {
        return employees;
        }

public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        }
        }
