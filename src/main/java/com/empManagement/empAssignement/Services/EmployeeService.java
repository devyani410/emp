package com.empManagement.empAssignement.Services;

import com.empManagement.empAssignement.Entities.Dept;
import com.empManagement.empAssignement.Repositry.DeptRepositry;
import com.empManagement.empAssignement.Repositry.ProjectRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import com.empManagement.empAssignement.Repositry.empRepositry;
import com.empManagement.empAssignement.Entities.emp;
import com.empManagement.empAssignement.Entities.project;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    private  empRepositry empRepo;
    @Autowired
    private DeptRepositry deptRepositry;
    @Autowired
    private ProjectRepositry projectRepositry;


    public emp  saveEmployee(emp e){
        empRepo.save(e);
        return e;
    }

    public List<emp> getAllEmployees() {
//        System.out.println(empRepo.findAll());
        return (List<emp>) empRepo.findAll();
    }

    public emp getEmployeeById(int id){
        Optional<emp> e=empRepo.findById(id);
        if(e.isPresent()){return e.get();}else{return null;}
    }

    public List<emp> getEmployeeByDeptId(int dept_id){
        Optional<Dept>temp =deptRepositry.findById(dept_id);
        if(temp.isPresent()){
            return empRepo.findByDept_Id(dept_id);
        }else{
            return null;
        }
    }
//
    public emp getEmployeeByIdAndDeptId(int emp_id,int dept_id){
        return empRepo.findByEmpIdAndDeptId(emp_id, dept_id);
    }
//
    public Dept  getDeptDetailsOfEmp(int id){
        Optional<emp>e=empRepo.findById(id);
        if(e.isPresent())
        return e.get().getDept();
        else return null;
    }
    public List<emp> getEmployeeByProjectId(int project_id){
        Optional<project> projectOptional=projectRepositry.findById(project_id);
        List<emp> res=new ArrayList<>();
        if(projectOptional.isPresent()){
            List<emp> empList =empRepo.findAll();
            for(emp e:empList){
                Set<project> temp=e.getAssignedproject();
                for(project x:temp){
                    if(x.getProject_id()==project_id){res.add(e);}
                }
            }
        }
        return res;
    }

    public Optional<emp> updateEmployee(emp e,int emp_id, int dept_id){
        Optional<emp> temprary= empRepo.findById(emp_id);
        Optional<Dept> dept=deptRepositry.findById(dept_id);
        if(temprary.isPresent() ){
            e.setEmpId(emp_id);
            if(dept.isPresent()){
            e.setDept(dept.get());
            }
            empRepo.save(e);
            return empRepo.findById(emp_id);
        }

        return null;
    }
//
    public emp assignProjectsToEmployee(int emp_id,int project_id){
        Set<project> projects = null;
        Optional<emp>e = empRepo.findById(emp_id);
        Optional<project> p = projectRepositry.findById(project_id);
        projects = e.get().getAssignedproject();
        projects.add(p.get());
        e.get().setAssignedproject(projects);
        return empRepo.save(e.get());
    }
    public void deleteEmployee(int id){
        empRepo.deleteById(id);
    }
}
