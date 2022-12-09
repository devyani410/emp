package com.empManagement.empAssignement.Services;

import com.empManagement.empAssignement.Entities.Dept;
import com.empManagement.empAssignement.Repositry.DeptRepositry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class DeptServicesTest {



        private MockMvc mockMvc;

        Dept d1 = new Dept(1, "Test1", "Test1");
        Dept d2 = new Dept(2, "Test2", "Test2");
        Dept d3 = new Dept(3, "Test3", "Test3");
        Dept d = new Dept(4, "Test4", "Test4");


        @Mock
        private DeptRepositry departmentRepository;

        @InjectMocks
        private DeptServices departmentService;

        @Before
        public void setUp(){
            MockitoAnnotations.initMocks(this);
            this.mockMvc = MockMvcBuilders.standaloneSetup(departmentService).build();
        }

        @Test
        public void getAllDepartments_success() throws Exception {
            Mockito.when(departmentRepository.findAll()).thenReturn(Arrays.asList(d1,d2,d3));
            assertThat(departmentService.getdepartmentDetails().size()).isEqualTo(3);
        }

        @Test
        public void getDepartmentById_success() throws Exception {
            int id = 1;
            Mockito.when(departmentRepository.findById(id)).thenReturn(Optional.ofNullable(d1));
            assertThat(departmentService.getdepartmentDetailsbyId(id)).isNotNull();
        }



        @Test
        public void createDepartment_success() throws Exception {
            Mockito.when(departmentRepository.save(d)).thenReturn(d);
            assertThat(departmentService.addDepartment(d)).isNotNull();
        }

        @Test
        public void updateDepartment_success() throws Exception {
            Mockito.when(departmentRepository.save(d)).thenReturn(d);
            Mockito.when(departmentRepository.findById(d.getId())).thenReturn(Optional.ofNullable(d));
            assertThat(departmentService.updateDepartment(d, d.getId())).isNotNull();
        }



        @Test
        public void deleteDepartment_success() throws Exception {
            when(departmentRepository.findById(1)).thenReturn(Optional.of(d1));
            departmentService.deleteDepartmentbyId(1);
            verify(departmentRepository).deleteById(1);
        }

        @Test
        public void deleteAllDepartment_success() throws Exception{
            when(departmentRepository.findAll()).thenReturn(Arrays.asList(d1,d2,d3));
            departmentService.deleteAllDepartment();
            verify(departmentRepository).deleteAll();

        }



    }
