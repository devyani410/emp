package com.empManagement.empAssignement.Entities;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Manager {
    @Id
    @Column (name = "managerId")
    private int id;
    @Column(name="Name")
    private String name;

    @OneToOne
//    @Column(name="HeadOfDept")
    private Dept HOD;

    @OneToMany
//    @Column(name = "Employees_team")
    private Set<emp> employeeTeam=new HashSet<>();

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", HOD=" + HOD +
                ", employeeTeam=" + employeeTeam +
                '}';
    }

    public Manager(int id, String name, Dept HOD, Set<emp> employeeTeam) {
        this.id = id;
        this.name = name;
        this.HOD = HOD;
        this.employeeTeam = employeeTeam;
    }

    public Manager() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept getHOD() {
        return HOD;
    }

    public void setHOD(Dept HOD) {
        this.HOD = HOD;
    }

    public Set<emp> getEmployeeTeam() {
        return employeeTeam;
    }

    public void setEmployeeTeam(Set<emp> employeeTeam) {
        this.employeeTeam = employeeTeam;
    }
}
