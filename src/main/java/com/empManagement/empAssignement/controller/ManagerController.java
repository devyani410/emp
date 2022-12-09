package com.empManagement.empAssignement.controller;

import com.empManagement.empAssignement.Entities.*;
import com.empManagement.empAssignement.Services.ManagerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/Manager")
public class ManagerController {
    @Autowired
    private ManagerServices managerServices;
    @GetMapping
    public ResponseEntity<List<Manager>> getAll(){
        return new ResponseEntity<>(managerServices.getAllManager(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Manager> getById(@PathVariable("id") int id){
        return new ResponseEntity<>(managerServices.getManagerById(id),HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Set<emp>> getTeam(@PathVariable ("id")int id){
//        return new ResponseEntity<>(managerServices.getTeam(id),HttpStatus.OK);
//    }

//    @GetMapping("/{dept_id}")
//    public ResponseEntity<Manager> getHOD(@PathVariable("dept_id")int dept_id){
//        return new ResponseEntity<>(managerServices.getHOD(dept_id),HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<Manager> save(@RequestBody Manager m){
        managerServices.save(m);
        return new ResponseEntity<>(m,HttpStatus.OK);
    }

//    @PostMapping("/{mId}/{eId}")
//    public ResponseEntity<Set<emp>> addEmp(@PathVariable("mId")int mId,@PathVariable("eId")int eId){
//        managerServices.addOneMember(mId, eId);
//        return getTeam(mId);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@RequestBody Manager m,@PathVariable("id")int id){
        return new ResponseEntity<>(managerServices.updateManager(m,id),HttpStatus.OK);
    }
    @PutMapping("/{mId}/{dId}")
    public ResponseEntity<Manager> updateDept(@PathVariable("mId")int mId,@PathVariable("dId")int dId){
        managerServices.updateDept(mId, dId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Manager> upsert(@RequestBody Manager m,@PathVariable("id")int id){
        managerServices.upsert(m,id);
        return new ResponseEntity<>(m,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        managerServices.deleteAll();;
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id){
        managerServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
