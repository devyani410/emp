package com.empManagement.empAssignement.Services;

import com.empManagement.empAssignement.Entities.Dept;
import com.empManagement.empAssignement.Repositry.DeptRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DeptServices {
    @Autowired
    private DeptRepositry deptRepositry;

    public DeptServices(DeptRepositry deptRepositry) {
        this.deptRepositry = deptRepositry;
    }


    public List<Dept> getdepartmentDetails(){
        return (List<Dept>) deptRepositry.findAll();
    }

    public Dept getdepartmentDetailsbyId(int id){
        Optional<Dept>temp=deptRepositry.findById(id);
        if(temp.isPresent()){
            return temp.get();
        }else{
            return null;
        }
    }


    public Dept addDepartment(Dept dept){
        deptRepositry.save(dept);
        return dept;
    }


    public Dept updateDepartment(Dept dept,int id){
        Optional<Dept> dept1=deptRepositry.findById(id);

        if(dept1.isPresent()) {
            dept.setId(id);
            deptRepositry.save(dept);
            return dept;
        }
       return dept1.get();
    }

    public void deleteAllDepartment(){
        deptRepositry.deleteAll();
    }
    public void deleteDepartmentbyId(int id){
        deptRepositry.deleteById(id);
    }
    public Dept  upsert(Dept dept,int id){
        dept.setId(id);
        deptRepositry.save(dept);
        return dept;
    }
}
