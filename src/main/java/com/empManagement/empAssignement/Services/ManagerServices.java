package com.empManagement.empAssignement.Services;

import com.empManagement.empAssignement.Entities.*;
import com.empManagement.empAssignement.Repositry.DeptRepositry;
import com.empManagement.empAssignement.Repositry.ManagerRepositry;
import com.empManagement.empAssignement.Repositry.empRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ManagerServices {
    @Autowired
    private ManagerRepositry managerRepositry;
    @Autowired
    private DeptRepositry deptRepositry;
    @Autowired
    private empRepositry empRepo;

    public List<Manager> getAllManager(){return managerRepositry.findAll();}
    public Manager getManagerById(int id){
       return   managerRepositry.findById(id);
    }

    public Set<emp> getTeam(int id){
        return managerRepositry.findById(id).getEmployeeTeam();
    }

//    public Manager getHOD(int dId){
//        Optional<Dept> d=deptRepositry.findById(dId);
//        if(d.isPresent()){
//            return managerRepositry.findByDept(d.get());
//        }
//        return null;
//    }

    public void save(Manager m){
        managerRepositry.save(m);
    }

    public void addOneMember(int mId,int eId){
        managerRepositry.findById(mId).getEmployeeTeam().add(empRepo.findById(eId).get());
    }

    public Manager updateManager(Manager m,int id){
        Manager temp=managerRepositry.findById(id);
        if(temp!=null){
            m.setId(id);
            managerRepositry.save(m);
            return m;
        }
        return null;
    }
    public void updateDept(int mId,int dId){
        managerRepositry.findById(mId).setHOD(deptRepositry.findById(dId).get());
    }

    public Manager upsert(Manager m,int id){
        m.setId(id);
        managerRepositry.save(m);
        return m;
    }
    public void deleteAll(){
        managerRepositry.deleteAll();
    }
    public void deleteById(int id){
        managerRepositry.deleteById(id);
    }

//    public void deleteByDept(int dId){
//      Optional<Dept> d=deptRepositry.findById(dId);
//      if(d.isPresent()){
//          managerRepositry.deleteByDept(d.get());
//      }
//    }
}
