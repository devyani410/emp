package com.empManagement.empAssignement.Services;

import com.empManagement.empAssignement.Repositry.DeptRepositry;
import com.empManagement.empAssignement.Repositry.ProjectRepositry;
import com.empManagement.empAssignement.Repositry.empRepositry;
import com.empManagement.empAssignement.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplicationExtensionsKt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjectServices {
    @Autowired
    private ProjectRepositry projectRepositry;
    @Autowired
    private DeptRepositry deptRepositry;
    @Autowired
    private empRepositry empRepo;


    public List<project> getAllProject(){
        return (List<project>) projectRepositry.findAll();
    }
    public project getProjectById(int id){
        Optional<project> proj=projectRepositry.findById(id);
        if(proj.isPresent()){return
                proj.get();
        }else{return null;}
    }

    public List<project> getProjectByDeptId(int deptId){
        Optional<Dept> dept=deptRepositry.findById(deptId);
        if(dept.isPresent()){
            return projectRepositry.findAllByDept(dept.get());
        }else{
            return null;
        }
    }
    public List<project> getProjectByEmpId(int emp_id){
        Optional<emp> e=empRepo.findById(emp_id);
        List<project>res=new ArrayList<>();
        if(e.isPresent()){
            List<project> projectList =projectRepositry.findAll();
            for(project p:projectList){
                Set<emp> temp=p.getEmployee();
                for(emp x:temp){
                    if(x.getEmpId()==emp_id){res.add(p);}
                }
            }
        }
        return res;
    }

    public project addProject(project p){
        projectRepositry.save(p);
        return p;
    }
    public project addProjectWithDeptId(project p, int deptid){
        Optional<Dept> dept=deptRepositry.findById(deptid);
        if(dept.isPresent()) {
            p.setDept(dept.get());
        }
            projectRepositry.save(p);
            return p;
    }
    public project addProjectWithEmp(project p,int emp_id){
        Optional<emp> e=empRepo.findById(emp_id);
        if(e.isPresent()){
            p.getEmployee().add(e.get());
        }
        projectRepositry.save(p);
        return p;
    }
    public boolean assignEmployeeToProject(int pid,int eid){
        Optional<project> projects=projectRepositry.findById(pid);
        Optional<emp> empOptional=empRepo.findById(eid);
        if(projects.isPresent() && empOptional.isPresent()){
            Set<emp> e=projects.get().getEmployee();
            e.add(empOptional.get());
            return true;
        }else{return false;}
    }

    public String deleteAllProjects(){
        projectRepositry.deleteAll();
        return "project Deleted SuccessFully";
    }
    public boolean deleteById(int id){
        if(projectRepositry.findById(id).isPresent()) {
            projectRepositry.deleteById(id);
            return true;
        }else{
            return  false;
        }
    }

   public Boolean updateDepartmentId(int project_id,int Dept_id){
        Optional<project> projectOptional=projectRepositry.findById(project_id);
        Optional<Dept> deptOptional=deptRepositry.findById(Dept_id);
        if(projectOptional.isPresent() && deptOptional.isPresent()){
            projectOptional.get().setDept(deptOptional.get());
            return true;
        }else{
            return false;
        }
    }

}
