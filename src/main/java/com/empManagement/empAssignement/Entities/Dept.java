package com.empManagement.empAssignement.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Department")
public class Dept {

    @Id
    @Column(name = "Dept_Id")
    private  int id;

    @Column(name="Dept_name")
    private String dept_name;

    @Column(name="Dept_location")
    private String dept_loc;

    public Dept(int dept_id, String dept_name, String dept_loc) {
        this.id = dept_id;
        this.dept_name = dept_name;
        this.dept_loc = dept_loc;
    }

    public Dept() {
    }

    public int getId() {
        return id;
    }

    public void setId(int dept_id) {
        this.id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_loc() {
        return dept_loc;
    }

    public void setDept_loc(String dept_loc) {
        this.dept_loc = dept_loc;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dept_id=" + id +
                ", dept_name='" + dept_name + '\'' +
                ", dept_loc='" + dept_loc + '\'' +
                '}';
    }
}
