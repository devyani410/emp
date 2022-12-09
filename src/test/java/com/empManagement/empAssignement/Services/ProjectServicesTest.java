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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

class ProjectServicesTest {


        @Mock
        private ProjectRepositry projectRepositry;
        @InjectMocks
        private ProjectServices projectServices;
        @Mock
        private DeptRepositry deptRepositry;
        @Mock
        private empRepositry empRepo;
        private MockMvc mockMvc;

        Dept d=new Dept(1,"Dept1","Loction1");
        project p1=new project(1,"p1.com","webDevelopment1",null,new HashSet<>());
        project p2=new project(2,"p2.com","webDevelopment2",d,new HashSet<>());
        project p3=new project(3,"p3.com","webDevelopment3",null,null);
        project p4=new project(4,"p4.com","webDevelopment4",d,null);

        emp e=new emp(10,"emp1","emp@gmail.com",d,new HashSet<>());

        @Before
        public void setUp(){
            MockitoAnnotations.initMocks(this);
            this.mockMvc = MockMvcBuilders.standaloneSetup(projectServices).build();
        }
        @Test
        public void getAllProject_success(){
            Mockito.when(projectRepositry.findAll()).thenReturn(Arrays.asList(p1,p2,p3,p4));
            assertThat(projectServices.getAllProject().size()).isEqualTo(4);
        }
        @Test
        public  void getProjectById_success(){

            Mockito.when(projectRepositry.findById(1)).thenReturn(Optional.ofNullable(p1));
            assertThat(projectServices.getProjectById(p1.getProject_id()));
        }
        @Test
        public void getProjectByDeptId_success(){
            Mockito.when(deptRepositry.findById(1)).thenReturn(Optional.ofNullable(d));
            Mockito.when(projectRepositry.findAllByDept(d)).thenReturn(Arrays.asList(p2,p4));
            assertThat(projectServices.getProjectByDeptId(d.getId()));
        }
        @Test
        public void addProject_success(){
            Mockito.when(projectRepositry.save(p1)).thenReturn(p1);
            assertThat(projectServices.addProject(p1)).isNotNull();
        }
        @Test
        public void addProjectWithDeptId_success(){
            Mockito.when(projectRepositry.save(p1)).thenReturn(p1);
            Mockito.when(deptRepositry.findById(d.getId())).thenReturn(Optional.ofNullable(d));
            assertThat(projectServices.addProjectWithDeptId(p1,d.getId()));
        }
        @Test
        public void addProjectWithEmp_success(){
            Mockito.when(projectRepositry.save(p1)).thenReturn(p1);
            Mockito.when(empRepo.findById(10)).thenReturn(Optional.ofNullable(e));
            assertThat(projectServices.addProjectWithEmp(p1,e.getEmpId()));
        }
        @Test
        public void deleteProjectByID_success(){

            Mockito.when(projectRepositry.findById(p1.getProject_id())).thenReturn(Optional.of(p1));
            projectServices.deleteById(p1.getProject_id());
            verify(projectRepositry).deleteById(p1.getProject_id());
        }

        @Test
        public void deleteAllProject_success(){
            Mockito.when(projectRepositry.findAll()).thenReturn(Arrays.asList(p1,p2,p3,p4));
            projectServices.deleteAllProjects();
            verify(projectRepositry).deleteAll();
        }

        @Test
        public void updateDepartmentId(){
            Mockito.when(projectRepositry.findById(p1.getProject_id())).thenReturn(Optional.ofNullable(p1));
            Mockito.when(deptRepositry.findById(d.getId())).thenReturn(Optional.ofNullable(d));
            assertThat(projectServices.updateDepartmentId(p1.getProject_id(),d.getId()));
        }
    }
