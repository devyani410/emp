package com.empManagement.empAssignement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Project")
public class project {

    @Id
    @Column
    private int project_id;

    @Column
    private String project_name;

    @Column(name = "project_description")
    private String project_des;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Dept_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Dept dept;


    @JsonIgnore
    @Column(name="employeeAssigned")
    @ManyToMany(mappedBy = "assignedproject")
    private Set<emp> employee=new HashSet<>();



    public project() {
    }

    public project(int project_id, String project_name, String project_des, Dept dept, Set<emp> employee) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_des = project_des;
        this.dept = dept;
        this.employee = employee;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_des() {
        return project_des;
    }

    public void setProject_des(String project_des) {
        this.project_des = project_des;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Set<emp> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<emp> employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "project{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", project_des='" + project_des + '\'' +
                ", dept=" + dept +
                ", employee=" + employee +
                '}';
    }
}
