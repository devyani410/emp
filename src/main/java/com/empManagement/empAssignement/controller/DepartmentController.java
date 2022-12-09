package com.empManagement.empAssignement.controller;

import com.empManagement.empAssignement.Entities.Dept;
import com.empManagement.empAssignement.Services.DeptServices;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.EscapedErrors;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostRemove;
import java.util.List;
import java.util.Optional;

@RestController
public class DepartmentController {

    @Autowired
    private DeptServices deptServices;
    @GetMapping("/Dept")
    public List<Dept> getDepartment(){
        return deptServices.getdepartmentDetails();
    }


    @GetMapping("Dept/{id}")
    public ResponseEntity<Dept> getDepartment(@PathVariable("id") int id){
        return ResponseEntity.of(Optional.of(deptServices.getdepartmentDetailsbyId(id)));
    }

   @PostMapping("/Dept")
    public ResponseEntity<Dept> addDepartment(@RequestBody Dept d){
        return ResponseEntity.of(Optional.of(deptServices.addDepartment(d)));}

    // build update department REST API
    @PutMapping("/Dept/{id}")
    public ResponseEntity<Dept> updateDepartment(@RequestBody Dept d, @PathVariable("id") int id){
          Dept temp=deptServices.updateDepartment(d,id);
          if(temp==null){
           return   ResponseEntity.status(HttpStatus.NOT_FOUND).build();
          }else{
             return ResponseEntity.of(Optional.of(temp));
          }
    }

    @DeleteMapping("/Dept")
    public ResponseEntity<String> deleteAllDepartment(){
        deptServices.deleteAllDepartment();
        return new ResponseEntity<>("All Department deleted successfully!", HttpStatus.OK);
    }
    // build delete department REST API
    @DeleteMapping("/Dept/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") int id){
        deptServices.deleteDepartmentbyId(id);
        return new ResponseEntity<>("Department deleted successfully!", HttpStatus.OK);
    }

    @PatchMapping("/Dept/{id}")
    public ResponseEntity<Dept> upsert(@RequestBody Dept dept,@PathVariable int id){
        return ResponseEntity.of(Optional.of(deptServices.upsert(dept,id)));
    }

}
