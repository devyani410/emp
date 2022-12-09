package com.empManagement.empAssignement.Repositry;

import com.empManagement.empAssignement.Entities.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DeptRepositry extends JpaRepository<Dept,Integer> {

}
