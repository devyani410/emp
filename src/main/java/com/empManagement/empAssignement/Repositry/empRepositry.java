package com.empManagement.empAssignement.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.empManagement.empAssignement.Entities.emp;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface empRepositry extends JpaRepository<emp,Integer> {
    public List<emp> findByDept_Id(int dept_id);
   public emp findByEmpIdAndDeptId(int emp_id,int dept_id);
}
