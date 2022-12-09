package com.empManagement.empAssignement.controller;

import com.empManagement.empAssignement.Repositry.DeptRepositry;
import com.empManagement.empAssignement.Repositry.ProjectRepositry;
import com.empManagement.empAssignement.Repositry.empRepositry;
import com.empManagement.empAssignement.Services.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplicationExtensionsKt;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  com.empManagement.empAssignement.Entities.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Project")
public class ProjectController {


    @Autowired
    private ProjectServices projectServices;

    @GetMapping
    public ResponseEntity<List<project>> getAllProject(){
       return ResponseEntity.of(Optional.of (projectServices.getAllProject()));
    }
    @GetMapping("{ProjectId}")
    public ResponseEntity<project> getProjectById(@PathVariable("ProjectId") int id){
        project temp=projectServices.getProjectById(id);
        if(temp==null){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        else{return ResponseEntity.of(Optional.of(temp));}
    }
    @GetMapping("department/{did}")
    public ResponseEntity<List<project>> getProjectByDeptId(@PathVariable("did") int id){
        List<project> temp=projectServices.getProjectByDeptId(id);
        if(temp==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.of(Optional.of(temp));
        }
    }
    @GetMapping("employee/{empId}")
    public ResponseEntity<List<project>> getProjectbyEmpId(@PathVariable("empId")int emp_id){
       return ResponseEntity.of(Optional.of(projectServices.getProjectByEmpId(emp_id)));

    }
    @PostMapping
    public ResponseEntity<project> addProject(@RequestBody project p){
        return new ResponseEntity<>(projectServices.addProject(p),HttpStatus.OK);
    }

    @PostMapping("Dept/{deptId}")
    public ResponseEntity<project> addProjectWithDeptId(@RequestBody project p, @PathVariable("deptId") int id) {
        return  new ResponseEntity<>(projectServices.addProjectWithEmp(p,id),HttpStatus.OK);
    }
   @PostMapping("emp/{empId}")
   public ResponseEntity<project> addProjectWithEmp(@RequestBody project p,@PathVariable("empId")int id){
       return  new ResponseEntity<>(projectServices.addProjectWithEmp(p,id),HttpStatus.OK);
   }


    @PutMapping("{pid}/employee/{eid}")
    public ResponseEntity<String> assignEmployeeToProject(@PathVariable("pid") int pid, @PathVariable("eid") int eid){
        Boolean flag=projectServices.assignEmployeeToProject(pid, eid);
       if(flag){return new ResponseEntity<>("Employee Assigned SuccessFully", HttpStatus.OK);}
       else{
           return new ResponseEntity<>("Project or Department is Not Present",HttpStatus.NOT_FOUND);
       }

    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllProjects(){
        return new ResponseEntity<>(projectServices.deleteAllProjects(),HttpStatus.OK);
    }
    @DeleteMapping("{project_id}")
    public ResponseEntity<String> deleteById(@PathVariable("project_id") int id){
       boolean flag= projectServices.deleteById(id);
        if(flag){return new ResponseEntity("Project Deleted Successfully",HttpStatus.OK);}
        else{
           return  new ResponseEntity("Project is Not Available",HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("{project_id}/updateDepartment/{dept_id}")
    public ResponseEntity<String> updateDepartmentId(@PathVariable("project_id") int project_id,@PathVariable("dept_id") int dept_id){
        Boolean flag=projectServices.updateDepartmentId(project_id,dept_id);
        if(flag) {
           return new ResponseEntity<>("Department Updated Successfully", HttpStatus.OK);
        }
       return new ResponseEntity<>("Department or Project is not Found",HttpStatus.NOT_FOUND);
    }


}