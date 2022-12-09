package com.empManagement.empAssignement.Services;

import com.empManagement.empAssignement.Entities.Dept;
import com.empManagement.empAssignement.Entities.emp;
import com.empManagement.empAssignement.Entities.project;
import com.empManagement.empAssignement.Repositry.DeptRepositry;
import com.empManagement.empAssignement.Repositry.ProjectRepositry;
import com.empManagement.empAssignement.Repositry.empRepositry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {


        private MockMvc mockMvc;
        @MockBean
        private empRepositry empRepo;

        @Mock
        private DeptRepositry deptRepositry;
        @InjectMocks
        private EmployeeService employeeService;
        @Mock
        private ProjectRepositry projectRepositry;

        Dept d=new Dept(1,"HR","indore");

        emp e1=new emp(10,"emp1","emp1.gmail.com",d,new HashSet<>());
        emp e2=new emp(20,"emp2","emp2@gmail.com",null,null);
        emp e3=new emp(30,"emp3","emp3@gmail.com",d,null);
        emp e4=new emp(40,"emp4","emp4@gmail.com",null,new HashSet<>());
        project p = new project(1, "Test", "test", null, new HashSet<>());


        @Before
        public void setUp(){
            MockitoAnnotations.initMocks(this);
            this.mockMvc = MockMvcBuilders.standaloneSetup(employeeService).build();
        }
        @org.junit.Test
        public void getAllEmployees_success(){
            Mockito.when(empRepo.findAll()).thenReturn(Arrays.asList(e1,e2,e3,e4));
            assertThat(employeeService.getAllEmployees().size()).isEqualTo(4);
        }
        @org.junit.Test
        public void getEmployeeById_success(){
            Mockito.when(empRepo.findById(10)).thenReturn(Optional.ofNullable(e1));
            assertThat(employeeService.getEmployeeById(10).getEmpId()).isEqualTo(e1.getEmpId());
        }
        @org.junit.Test
        public void getEmployeeByDeptId_success() {
            Mockito.when(deptRepositry.findById(d.getId())).thenReturn(Optional.ofNullable(d));
            Mockito.when(empRepo.findByDept_Id(d.getId())).thenReturn(Arrays.asList(e1,e2,e3));
            assertThat(employeeService.getEmployeeByDeptId(1)).isNotNull();

        }

        @org.junit.Test
        public void getDeptDetailsOfEmp_success(){
            Mockito.when(empRepo.findById(10)).thenReturn(Optional.of(e1));
            assertThat(employeeService.getDeptDetailsOfEmp(e1.getEmpId())).isEqualTo(e1) ;
        }

        @org.junit.Test
        public void saveEmployee_success(){
            Mockito.when(empRepo.save(e1)).thenReturn(e1);
            Mockito.when(deptRepositry.findById(d.getId())).thenReturn(Optional.ofNullable(d));
            assertThat(employeeService.saveEmployee(e1)).isNotNull();
        }
        @org.junit.Test
        public void  deleteEmployee_success(){
            Mockito.when(empRepo.findById(1)).thenReturn(Optional.of(e1));
            employeeService.deleteEmployee(1);
            verify(empRepo).deleteById(1);
        }
        @org.junit.Test
        public void updateEmployee_success(){
            Mockito.when(empRepo.save(e4)).thenReturn(e4);
            employeeService.updateEmployee(e4,10,1);
            verify(empRepo).save(e4);

        }

        @Test
        public void assignProjectsToEmployee_success()
        {
            when(empRepo.findById(e1.getEmpId())).thenReturn(Optional.ofNullable(e1));
            when(projectRepositry.findById(p.getProject_id())).thenReturn(Optional.ofNullable(p));
            when(empRepo.save(e1)).thenReturn(e1);
            assertThat(employeeService.assignProjectsToEmployee(e1.getEmpId(), p.getProject_id())).isNotNull();
        }

    }
