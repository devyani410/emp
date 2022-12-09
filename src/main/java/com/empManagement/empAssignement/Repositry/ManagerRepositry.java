package com.empManagement.empAssignement.Repositry;

import com.empManagement.empAssignement.Entities.Dept;
import com.empManagement.empAssignement.Entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
//@EnableJpaRepositories
public interface ManagerRepositry extends JpaRepository<Manager, Integer> {
    public Manager findById(int id);
//    public Manager findByDept(Dept d);

//    public void deleteByDept(Dept d);
}
