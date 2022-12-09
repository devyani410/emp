package com.empManagement.empAssignement;

import com.empManagement.empAssignement.Entities.Dept;
import com.empManagement.empAssignement.Repositry.DeptRepositry;
import com.empManagement.empAssignement.Services.DeptServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
class EmpAssignementApplicationTests {
    @Autowired
	public DeptServices deptServices;

	@MockBean
	public DeptRepositry deptRepositry;
	@Test
	void contextLoads() {
	}

}
