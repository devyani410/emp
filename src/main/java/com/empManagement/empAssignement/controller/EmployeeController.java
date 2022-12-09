package com.empManagement.empAssignement.controller;



import com.empManagement.empAssignement.Entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import  com.empManagement.empAssignement.Services.*;
import com.empManagement.empAssignement.Entities.emp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService empservice;


//    // build create employee REST API
    @PostMapping()
    public ResponseEntity<Object> saveEmployee(@RequestBody emp e){
        emp temprarory=empservice.saveEmployee(e);
        return new ResponseEntity<>(temprarory, HttpStatus.CREATED);
    }

//    // build get all employee REST API
    @GetMapping()
    public ResponseEntity<List<emp>> getAllEmployees(){
        return new ResponseEntity<>(empservice.getAllEmployees(), HttpStatus.OK);
    }

//    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<emp> getEmployeeById(@PathVariable("id")  int id){
        emp temp=empservice.getEmployeeById(id);
        if(temp==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        ResponseEntity<emp> empResponseEntity = new ResponseEntity<emp>(empservice.getEmployeeById(id), HttpStatus.OK);
        return empResponseEntity;
    }

//    // build get employees by department id REST API
    @GetMapping("/department/{did}")
    public ResponseEntity<List<emp>> getEmployeeByDeptId(@PathVariable("did") int id){
        List<emp> temp= empservice.getEmployeeByDeptId(id);
        if(temp==null){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.of(Optional.of(temp));
        }
    }

//    // build get employees by department id and employee id REST API
    @GetMapping("{eid}/department/{did}")
    public ResponseEntity<emp> getEmployeeByIdAndDeptId(@PathVariable("eid") int id, @PathVariable("did") int dept_id){
        return new ResponseEntity<>(empservice.getEmployeeByIdAndDeptId(id, dept_id), HttpStatus.OK);
    }

    @GetMapping("project/{project_id}")
    public ResponseEntity<List<emp>> getEmployeebyProjectId(@PathVariable("project_id")int project_id) {
      return ResponseEntity.of(Optional.of(empservice.getEmployeeByProjectId(project_id)));
    }
    @GetMapping("/deptDetails/{empid}")
    public ResponseEntity<Dept> getDeptDetailsOfEmp(@PathVariable("empid") int id){
        Dept d=empservice.getDeptDetailsOfEmp(id);
        if(d==null){return ResponseEntity.status(HttpStatus.NOT_FOUND).build();}
        else{return ResponseEntity.of(Optional.of(d));}
    }
//
//    // build update employee REST API
    @PutMapping("{eid}/department/{did}")
    public ResponseEntity<emp> updateEmployee(@RequestBody emp e, @PathVariable("eid") int id, @PathVariable("did") int dept_id){
        Optional<emp> temp=empservice.updateEmployee(e,id,dept_id);
        if(temp.isPresent()){
            return ResponseEntity.of(Optional.of(temp.get()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
//
//    // build assign employee project REST API
    @PutMapping("{eid}/project/{pid}")
    public ResponseEntity<emp> assignProjectsToEmployee(@PathVariable int eid, @PathVariable int pid){
        return new ResponseEntity<>(empservice.assignProjectsToEmployee(eid, pid), HttpStatus.OK);
    }

   // build delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        empservice.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
    }

}
