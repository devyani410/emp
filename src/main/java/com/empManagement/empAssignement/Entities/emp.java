package com.empManagement.empAssignement.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Employee")
public class emp{

    @Id
    @Column(name = "Employee_Id")
    private int empId;

    @Column(name="Employee_name")
    private  String emp_name;

    @Column(name="Email")
    private String email;

//
    @OneToOne
//    @Column(name="manager")
    private  Manager manager;

    @ManyToOne
    @JoinColumn(name = "dept_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dept dept;

    @Column
    @ManyToMany
    @JoinTable(name="Employee_Project",joinColumns = @JoinColumn(name="Employee_Id"),inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<project> assignedproject=new HashSet<>();


    public emp(){
    }

    public emp(int empId, String emp_name, String email, Dept dept, Set<com.empManagement.empAssignement.Entities.project> project) {
        this.empId = empId;
        this.emp_name = emp_name;
        this.email = email;
        this.dept = dept;
        this.assignedproject = project;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Set<com.empManagement.empAssignement.Entities.project> getAssignedproject() {
        return assignedproject ;
    }

    public void setAssignedproject(Set<com.empManagement.empAssignement.Entities.project> project) {
        this.assignedproject = project;
    }

    @Override
    public String toString() {
        return "emp{" +
                "emp_id=" + empId +
                ", emp_name='" + emp_name + '\'' +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                ", project=" + assignedproject +
                '}';
    }
}
