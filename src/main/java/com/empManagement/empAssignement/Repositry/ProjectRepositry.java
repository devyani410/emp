package com.empManagement.empAssignement.Repositry;

import com.empManagement.empAssignement.Entities.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.empManagement.empAssignement.Entities.project;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepositry extends JpaRepository<project,Integer> {
    List<project> findAllByDept(Dept dept);
}
